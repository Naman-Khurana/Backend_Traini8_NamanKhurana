package com.namankhurana.springboot.traini8.service;

import com.namankhurana.springboot.traini8.dto.TrainingCenterInputDTO;
import com.namankhurana.springboot.traini8.entity.TrainingCenter;

import java.util.List;

public interface TrainingCenterService {

    public TrainingCenter addTrainingCenter(TrainingCenterInputDTO trainingCenterInputDTO);

    public List<TrainingCenter> getAllTrainingCenters();

    public List<TrainingCenter> getFilteredTrainingCenters(String centerName, String city, String state,String centerCode, Long minCapacity, Long maxCapacity);
}
