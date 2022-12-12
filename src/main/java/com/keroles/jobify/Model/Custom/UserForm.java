package com.keroles.jobify.Model.Custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @NotNull(message = "name not found")
    @NotEmpty(message = "name is empty")
    private char[] fullName;
    @NotNull(message = "email not found")
    @NotEmpty(message = "email is empty")
    private char[] email;
    @NotNull(message = "password not found")
    @NotEmpty(message = "password is empty")
    private char[] password;
}
