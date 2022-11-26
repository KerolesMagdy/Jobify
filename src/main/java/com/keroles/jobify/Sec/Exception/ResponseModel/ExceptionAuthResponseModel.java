/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keroles.jobify.Sec.Exception.ResponseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;


/**
 *
 * @author Keroles Magdy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionAuthResponseModel {

    @JsonFormat(pattern = "yy-mm-dd HH:MM:SS")
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;
    private String path;
}
