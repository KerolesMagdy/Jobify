package com.keroles.jobify.Model.Entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "name not found")
    @NotEmpty(message = "name is empty")
    private String fullName;
    @Column(unique = true)
    @NotNull(message = "email not found")
    @NotEmpty(message = "email is empty")
    private String email;
    @NotNull(message = "password not found")
    @NotEmpty(message = "password is empty")
    private String password;
    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date created;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastModified;
    private boolean enabled=false;
    private boolean expired=false;
    private boolean locked=false;
    @ManyToMany(fetch = EAGER)
    @JoinTable(name =  "users_roles"
            ,joinColumns = @JoinColumn(name = "user_id")
            ,inverseJoinColumns = @JoinColumn(name = "role_Id"))
    private Set<UserRole> userRoles;


    
}
