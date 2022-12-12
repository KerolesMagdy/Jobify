package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGeneralInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "email not valid")
    @Column(unique = true)
    private char[] email;
    @NotBlank(message = "first name is empty")
    private char[] firstName;
    @NotBlank(message = "middle name is empty")
    private char[] middleName;
    @NotBlank(message = "last name is empty")
    private char[] lastName;
    @NotNull(message = "date is empty")
    @JsonFormat(pattern = "yyyy-mm-dd HH:MM:SS")
    private Date Birthdate;
    private String gender;
    private String nationality;
    private String maritalStatus;
    private int numberOfDependents;
    private String militaryStatus;
    private boolean drivingLicense;
    @NotBlank(message = "job title must not be null")
    private String jobTitle;
    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "address_id")
    @NotNull(message = "address must not be null")
    private Address address;
    private String mobileNumber;
    private String alternativeNumber;
    private String image;


}
