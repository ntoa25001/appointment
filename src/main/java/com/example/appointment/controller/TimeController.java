package com.example.appointment.controller;


import com.example.appointment.model.Time;
import com.example.appointment.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/auth/")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @GetMapping("/times")
    public ResponseEntity<?> getAllDoctor(@RequestParam(value = "period",required = false) String period
            , @RequestParam(value = "page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("period"));
        Page<Time> resultPage = null;
        if (StringUtils.hasText(period)) {
            resultPage = timeService.findByPeriodContaining(period, pageable);

        } else {
            resultPage = timeService.findAll(pageable);
        }
        return ResponseEntity.ok(resultPage);
    }
}
