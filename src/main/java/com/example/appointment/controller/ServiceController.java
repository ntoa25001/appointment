package com.example.appointment.controller;

import com.example.appointment.model.Doctor;
import com.example.appointment.model.Service;
import com.example.appointment.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public ResponseEntity<?> getAllService(@RequestParam(value = "name",required = false) String name
            , @RequestParam(value = "page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("name"));
        Page<Service> resultPage = null;
        if (StringUtils.hasText(name)) {
            resultPage = serviceService.findByServiceListNameContaining(name, pageable);

        } else {
            resultPage = serviceService.findAll(pageable);
        }
        return ResponseEntity.ok(resultPage);
    }

}
