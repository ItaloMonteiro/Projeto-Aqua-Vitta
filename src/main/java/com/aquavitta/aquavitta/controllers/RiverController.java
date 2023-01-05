package com.aquavitta.aquavitta.controllers;

import com.aquavitta.aquavitta.dtos.RiverDto;
import com.aquavitta.aquavitta.models.RiverModel;
import com.aquavitta.aquavitta.services.RiverService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class RiverController {
    @Autowired
    RiverService riverService;

    @PostMapping("/adding-river")
    public ResponseEntity<RiverModel> addingRiver(@RequestBody @Valid RiverDto riverDto){
        RiverModel riverModel = new RiverModel();
        BeanUtils.copyProperties(riverDto, riverModel);
        riverService.addRiver(riverModel);
        return new ResponseEntity<>(riverModel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<RiverModel>> getAllRivers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(riverService.getRivers(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOneRiver(@PathVariable(value = "id") UUID id){
        Optional<RiverModel> riverModelOptional = riverService.getOneRiver(id);
        if (!riverModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("River not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(riverModelOptional.get());
    }

    @DeleteMapping("/delete-river/{id}")
    public ResponseEntity<Object> deleteRiver(@PathVariable(value = "id") UUID id){
        Optional<RiverModel> riverModelOptional = riverService.findById(id);
        if (!riverModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("River not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("River deleted with sucessfully");
    }

    @PutMapping("/update-river/{id}")
    public ResponseEntity<Object> updateRiver(@PathVariable(value = "id") UUID id, @RequestBody @Valid RiverDto riverDto){
        Optional<RiverModel> riverModelOptional = riverService.findById(id);
        if (!riverModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("River not found");
        }
        var riverModel = new RiverModel();
        BeanUtils.copyProperties(riverDto, riverModel);
        riverModel.setId(riverModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("River deleted with sucessfully");
    }
}
