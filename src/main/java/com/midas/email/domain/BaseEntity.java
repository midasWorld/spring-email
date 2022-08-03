package com.midas.email.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

	@CreatedDate
	protected LocalDateTime createdAt;

	@CreatedBy
	protected String createdBy;

	@LastModifiedDate
	protected LocalDateTime modifiedAt;

	@LastModifiedBy
	protected String modifiedBy;
}
