package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.keroles.jobify.Model.Entity.Company;
import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Model.Entity.UserRole;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import lombok.AllArgsConstructor;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private Long id;
    private char[] email;
    private char[] password;
    private char[] firstName;
    private char[] lastName;
    private String jobTitle;
    private String mobileNumber;
    private boolean locked=false;
    private boolean expired;
    private boolean enabled=false;
    private Media profileImage;
    private CompanyDto company;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CompositeToken token;
}
