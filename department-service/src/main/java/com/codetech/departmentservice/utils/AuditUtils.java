package com.codetech.departmentservice.utils;

import java.time.Instant;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditUtils {

	@Column(name = "created_date")
	private Instant createdDate = Instant.now();

	@Column(name = "updated_date")
	private Instant updatedDate =  Instant.now();

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;
}
