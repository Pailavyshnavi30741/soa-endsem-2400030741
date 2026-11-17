package com.klef.soa.docker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.docker.dto.OrganicFarmRequest;
import com.klef.soa.docker.dto.OrganicFarmResponse;
import com.klef.soa.docker.entity.OrganicFarm;
import com.klef.soa.docker.repository.OrganicFarmRepository;
import com.klef.soa.docker.service.OrganicFarmService;

@Service
public class OrganicFarmServiceImpl implements OrganicFarmService
{
    @Autowired
    private OrganicFarmRepository repo;

    @Override
    public OrganicFarmResponse addFarm(OrganicFarmRequest request)
    {
        OrganicFarm farm = OrganicFarm.builder()
                .farmName(request.getFarmName())
                .cropType(request.getCropType())
                .areaAcres(request.getAreaAcres())
                .certified(request.getCertified())
                .build();

        OrganicFarm savedFarm = repo.save(farm);

        return mapToResponse(savedFarm);
    }

    @Override
    public List<OrganicFarmResponse> displayAll()
    {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    private OrganicFarmResponse mapToResponse(OrganicFarm farm)
    {
        return OrganicFarmResponse.builder()
                .id(farm.getId())
                .farmName(farm.getFarmName())
                .cropType(farm.getCropType())
                .areaAcres(farm.getAreaAcres())
                .certified(farm.getCertified())
                .build();
    }
}