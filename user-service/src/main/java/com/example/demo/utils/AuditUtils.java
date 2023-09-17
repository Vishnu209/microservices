package com.example.demo.utils;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditUtils {

	@Field(name = "created_date")
	private Instant createdDate;

	@Field(name = "updated_date")
	private Instant updatedDate;

	@Field(name = "created_by")
	private String createdBy;

	@Field(name = "modified_by")
	private String modifiedBy;
}
