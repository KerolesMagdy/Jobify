package com.keroles.jobify.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAuditEntity {
    @CreatedBy
    private Date createdBy;
    @CreatedDate
    private Date createdTimeStamp;
    @LastModifiedBy
    private Date lastModifiedBy;
    @LastModifiedDate
    private Date lastModifiedTimeStamp;

}
