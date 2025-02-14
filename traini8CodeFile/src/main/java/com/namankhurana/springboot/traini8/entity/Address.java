package com.namankhurana.springboot.traini8.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
//prevent infinite json response
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "detailed_address")
    @NotBlank(message = "Detailed Address cannot be empty.")
    private String detailedAddress;

    @Column(name = "city")
    @NotBlank(message = "City cannot be empty.")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "state cannot be empty.")
    private String state;


    @NotBlank(message = "Pin-code cannot be empty.")
    @Pattern(regexp = "^\\d{6}$", message = "Pincode must be exactly 6 digits.")
    @Column(name = "pincode")
    private String pincode;


    //for bidirectional search
    @OneToOne(mappedBy = "address",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    private TrainingCenter trainingCenter;

    //constructor


    public Address(String detailedAddress, String city, String state, String pincode) {
        this.detailedAddress = detailedAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
}
