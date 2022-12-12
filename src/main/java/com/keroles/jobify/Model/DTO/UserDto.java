package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private char[] fullName;
    private char[] email;
    private boolean enabled;
    private boolean expired;
    private boolean locked;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:SS")
    private Date created;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:SS")
    private Date lastModified;
    private CompositeToken token;
    private Media media;
}
