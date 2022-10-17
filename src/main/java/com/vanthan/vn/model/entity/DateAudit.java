package com.vanthan.vn.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean deleted = false;

}
