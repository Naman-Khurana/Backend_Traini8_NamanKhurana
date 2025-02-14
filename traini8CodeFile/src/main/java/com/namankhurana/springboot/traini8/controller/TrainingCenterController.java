package com.namankhurana.springboot.traini8.controller;

import com.namankhurana.springboot.traini8.dto.TrainingCenterInputDTO;
import com.namankhurana.springboot.traini8.entity.TrainingCenter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.namankhurana.springboot.traini8.service.TrainingCenterService;

import java.util.List;

@RestController
@RequestMapping("/api/trainingCenter")
@Validated
public class TrainingCenterController {

    private TrainingCenterService trainingCenterService;


    @Autowired
    public TrainingCenterController(TrainingCenterService trainingCenterService) {
        this.trainingCenterService = trainingCenterService;
    }

    //    add new training center
    @PostMapping("/add")
    public ResponseEntity<TrainingCenter> addNewTrainingCenter(@Valid @RequestBody TrainingCenterInputDTO trainingCenterInputDTO){
        //call the service function to add new training center
        TrainingCenter tempTrainingCenter=trainingCenterService.addTrainingCenter(trainingCenterInputDTO);

        //return the added training center json representation
        return ResponseEntity.ok(tempTrainingCenter);
    }


    /// get all training center records saved in database
    @GetMapping("/getAll")
    public ResponseEntity<List<TrainingCenter>> getAllTrainingCenterRecords(){
        //call the service fucntion to retrieve all the training centers
        List<TrainingCenter> trainingCenterList=trainingCenterService.getAllTrainingCenters();

        // return all teh records
        return ResponseEntity.ok(trainingCenterList);
    }

    //get filtered records
    @GetMapping("/getFiltered")
    public ResponseEntity<List<TrainingCenter>> getFilteredTrainingCentres(
            @RequestParam(required = false) String centerName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String centerCode,
            @RequestParam(required = false) @Min(1) Long minCapacity,
            @RequestParam(required = false) @Min(1) Long maxCapacity) {

        // Validate that minCapacity is less than or equal to maxCapacity
        if (minCapacity != null && maxCapacity != null && minCapacity > maxCapacity) {
            throw new IllegalArgumentException("minCapacity should be less than or equal to maxCapacity");
        }

        List<TrainingCenter> filteredCenters = trainingCenterService.getFilteredTrainingCenters(centerName, city, state,centerCode, minCapacity, maxCapacity);
        return ResponseEntity.ok(filteredCenters);
    }
}
