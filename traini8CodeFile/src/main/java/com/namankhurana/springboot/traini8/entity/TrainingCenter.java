package com.namankhurana.springboot.traini8.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_center")
//to prevent infinite json response
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "centerCode")
public class TrainingCenter {




//attributes

    @Id
    @Column(name = "training_center_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingCenterId;


    @Column(name = "center_name")
    private String centerName;

    @Column(name = "center_code")
    private String centerCode;

    @Column(name = "student_capacity")
    private long studentCapacity;


    //optional
    @Column(name = "created_on")
    private long createdOn;

    // automatically set the time to inserting of training center data
    @PrePersist
    protected void onCreate(){
        createdOn= Instant.now().getEpochSecond();
    }



    @Column(name = "contact_email")
    private String contactEmail;

    //validation of the phone no is required.
    @Column(name = "contact_phone")
    private String contactPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    //optional
    @OneToMany(mappedBy = "trainingCenter",cascade = CascadeType.ALL)
    private List<Courses> courses;

//constructor
    // with only essential attributes

    public TrainingCenter(String centerName, String centerCode, Address address, String contactPhone) {
        this.centerName = centerName;
        this.centerCode = centerCode;
        this.address = address;
        this.contactPhone=contactPhone;
    }



    // bidirection Convenience method

    //for courses
    public void addCourse(Courses tempCourses){
        if(courses==null)
            courses=new ArrayList<>();
        //now add relation bettween the two entities from both the sides

        //from AccountHolder side
        courses.add(tempCourses);

        //from Expenses side
        tempCourses.setTrainingCenter(this);
    }

}
