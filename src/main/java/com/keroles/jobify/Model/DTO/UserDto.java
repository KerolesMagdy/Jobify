package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String fullName;
    private String email;
    private boolean enabled;
    private boolean expired;
    private boolean locked;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:SS")
    private Date created;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:SS")
    private Date lastModified;
    private CompositeToken token;
}
