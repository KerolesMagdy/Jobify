package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoWithoutToken {

    private Long id;
    private char[] fullName;
    private char[] email;
    private boolean enabled;
    private boolean expired;
    private boolean locked;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private Date created;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private Date lastModified;

}
