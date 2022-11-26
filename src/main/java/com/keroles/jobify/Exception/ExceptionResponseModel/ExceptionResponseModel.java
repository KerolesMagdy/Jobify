/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keroles.jobify.Exception.ExceptionResponseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityListeners;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Keroles Magdy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponseModel {

    @JsonFormat(pattern = "yy-mm-dd HH:MM:SS")
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private List<String> message;
    private String path;
}
