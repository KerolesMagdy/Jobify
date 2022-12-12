package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    @NotNull(message = "email not found")
    @NotEmpty(message = "email is empty")
    private char[] email;
    @NotNull(message = "password not found")
    @NotEmpty(message = "password is empty")
    private char[] password;
    @NotNull(message = "first name not found")
    @NotEmpty(message = "first name is empty")
    private char[] firstName;
    @NotNull(message = "last name not found")
    @NotEmpty(message = "last name is empty")
    private char[] lastName;
    @NotBlank(message = "job title must not be null")
    private String jobTitle;
    @NotBlank(message = "your number must not be null")
    private String mobileNumber;
    private boolean locked=false;
    private boolean expired;
    private boolean enabled=false;
    @OneToOne(cascade = CascadeType.REMOVE)
    @Nullable
    @JoinColumn(name = "profile_image_id")
    private Media profileImage;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    @NotNull(message = "company must not be null")
    private Company company;
    @ManyToMany(fetch = EAGER,cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name =  "employers_roles"
            ,joinColumns = @JoinColumn(name = "employer_id")
            ,inverseJoinColumns = @JoinColumn(name = "role_Id"))
    private List<UserRole> userRoles;


}
