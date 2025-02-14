package com.namankhurana.springboot.traini8.dto;

import com.namankhurana.springboot.traini8.entity.Address;
import com.namankhurana.springboot.traini8.entity.Courses;
import com.namankhurana.springboot.traini8.validator.UniqueCenterCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TrainingCenterInputDTO {

    @NotBlank(message = "Center Name cannot be blank")
    @Size(max = 39,message = "Center Name length should be less than 40 characters. ")
    private String centerName;

    @NotBlank(message = "Center Code cannot be blank")
    @Size(min = 12,max = 12,message = "Center Code should be of exactly 12 alphanumeric characters. ")
    //custom validation annotation
    @UniqueCenterCode
    private String centerCode;

    @NotNull
    @Valid
    private Address address;

    @Min(value = 1,message = "Student capacity must be at least 1.")
    private long studentCapacity;



    @Valid
    private List<Courses> courses;
    //skipped created on as it has to be set default

    @Email(message = "Invalid email format.")
    private String contactEmail;

    @Pattern(regexp = "^\\d{10}$", message = "Phone No Must be of exactly 10 digits only.")
    private String contactPhone;


    private long createdOn;
}
