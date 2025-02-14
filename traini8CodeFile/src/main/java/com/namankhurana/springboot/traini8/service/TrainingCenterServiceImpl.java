package com.namankhurana.springboot.traini8.service;

import com.namankhurana.springboot.traini8.dao.TrainingCenterRepository;
import com.namankhurana.springboot.traini8.dto.TrainingCenterInputDTO;
import com.namankhurana.springboot.traini8.entity.Address;
import com.namankhurana.springboot.traini8.entity.Courses;
import com.namankhurana.springboot.traini8.entity.TrainingCenter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingCenterServiceImpl implements TrainingCenterService {

    private TrainingCenterRepository trainingCenterRepository;

    @Autowired
    public TrainingCenterServiceImpl(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    @Override
    @Transactional
    public TrainingCenter addTrainingCenter(TrainingCenterInputDTO trainingCenterInputDTO) {

        //create new training center object
        TrainingCenter tempTrainingCenter=new TrainingCenter();

        //set attributes for the new object
        tempTrainingCenter.setCenterName(trainingCenterInputDTO.getCenterName());
        tempTrainingCenter.setCenterCode(trainingCenterInputDTO.getCenterCode());
        tempTrainingCenter.setStudentCapacity(trainingCenterInputDTO.getStudentCapacity());
        tempTrainingCenter.setContactEmail(trainingCenterInputDTO.getContactEmail());
        tempTrainingCenter.setContactPhone(trainingCenterInputDTO.getContactPhone());


        // set address

        Address address=trainingCenterInputDTO.getAddress();
        tempTrainingCenter.setAddress(address);

        //set courses
        List<Courses> courses=trainingCenterInputDTO.getCourses();
        if(courses!=null){
            for(Courses ele : courses){
                tempTrainingCenter.addCourse(ele);
            }
        }

        //save the new training center object in database
        trainingCenterRepository.save(tempTrainingCenter);

        //return the training center object saved in database
        return tempTrainingCenter;
    }

    @Override
    public List<TrainingCenter> getAllTrainingCenters() {
        List<TrainingCenter> trainingCenterList=trainingCenterRepository.findAll();

        return trainingCenterList;
    }

    @Override
    public List<TrainingCenter> getFilteredTrainingCenters(String centerName, String city, String state,String centerCode, Long minCapacity, Long maxCapacity) {
        return trainingCenterRepository.filterTrainingCenters(centerName, city, state,centerCode, minCapacity, maxCapacity);
    }
}
