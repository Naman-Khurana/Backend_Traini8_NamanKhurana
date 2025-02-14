package com.namankhurana.springboot.traini8.validator;

import com.namankhurana.springboot.traini8.dao.TrainingCenterRepository;
import com.namankhurana.springboot.traini8.exception.DuplicateCenterCodeException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCenterCodeValidator implements ConstraintValidator<UniqueCenterCode,String> {

    private TrainingCenterRepository trainingCenterRepository;

    @Autowired
    public UniqueCenterCodeValidator(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    @Override
    public boolean isValid(String centerCode, ConstraintValidatorContext constraintValidatorContext) {
        if(centerCode==null){
            return true;
            //notblank will handle
        }
        boolean exists=trainingCenterRepository.existsByCenterCode(centerCode);
        if(exists){
            throw new DuplicateCenterCodeException("Center Code : " + centerCode + " already exists.");
        }
        return true;
    }


}
