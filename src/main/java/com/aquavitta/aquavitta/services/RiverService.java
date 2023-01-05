package com.aquavitta.aquavitta.services;

import com.aquavitta.aquavitta.models.RiverModel;
import com.aquavitta.aquavitta.repositories.RiverRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RiverService {
    @Autowired
    RiverRepository riverRepository;

    public RiverModel addRiver(RiverModel riverModel){
        return riverRepository.save(riverModel);
    }

    public Page<RiverModel> getRivers(Pageable pageable){
        return riverRepository.findAll(pageable);
    }

    public Optional<RiverModel> getOneRiver(UUID id){
        return riverRepository.findById(id);
    }

    public Optional<RiverModel> findById(UUID id){
        return riverRepository.findById(id);
    }



}
