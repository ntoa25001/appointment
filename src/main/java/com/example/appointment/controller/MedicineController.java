package com.example.appointment.controller;

import com.example.appointment.model.Medicine;
import com.example.appointment.model.Service;
import com.example.appointment.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;
    @GetMapping("/medicine")
    public ResponseEntity<?> getAllMedicine(@RequestParam(value = "name",required = false) String name
            , @RequestParam(value = "page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("name"));
        Page<Medicine> resultPage = null;
        if (StringUtils.hasText(name)) {
            resultPage = medicineService.findByMedicineNameContaining(name, pageable);

        } else {
            resultPage = medicineService.findAll(pageable);
        }
        return ResponseEntity.ok(resultPage);
    }
}
