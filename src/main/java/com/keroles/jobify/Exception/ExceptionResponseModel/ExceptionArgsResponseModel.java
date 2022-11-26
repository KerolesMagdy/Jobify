package com.keroles.jobify.Exception.ExceptionResponseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionArgsResponseModel {
    @JsonFormat(pattern = "yy-mm-dd HH:MM:SS")
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private List<String> message;
    private String path;
}
