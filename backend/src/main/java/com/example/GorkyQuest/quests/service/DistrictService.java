package com.example.GorkyQuest.quests.service;

import com.example.GorkyQuest.quests.model.District;
import com.example.GorkyQuest.quests.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public TreeMap<String, String> getAllDistrict() {
        List<District> districts = districtRepository.findAll();
        TreeMap<String, String> districsDescription = new TreeMap<>();
        for (District district : districts) {
            districsDescription.put(district.getName(), district.getDescription());
        }
        return districsDescription;
    }
}
