package com.example.appointment.controller;


import com.example.appointment.model.Specialist;
import com.example.appointment.service.SpecialistService;
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
@RequestMapping("api/auth")
public class SpecialistController {

    @Autowired
    private SpecialistService specialistService;

    @GetMapping("/specialists")
    public ResponseEntity<?> getAllDoctor(@RequestParam(value = "specialistName",required = false) String specialistName
            , @RequestParam(value = "page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("specialistName"));
        Page<Specialist> resultPage = null;
        if (StringUtils.hasText(specialistName)) {
            resultPage = specialistService.findBySpecialistNameContaining(specialistName, pageable);

        } else {
            resultPage = specialistService.findAll(pageable);
        }
        return ResponseEntity.ok(resultPage);
    }
}
