package com.example.GorkyQuest.quests.controller;

import com.example.GorkyQuest.quests.model.District;
import com.example.GorkyQuest.quests.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("api/district")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("getAllDistrict")
    public ResponseEntity<TreeMap<String, String>> getAllDistrict() {
        TreeMap<String, String> districts = districtService.getAllDistrict();
        if (districts.isEmpty()) {
            return ResponseEntity.noContent().build();  // Если данных нет
        }
        return ResponseEntity.ok(districts);  // Если данные есть
    }
}
