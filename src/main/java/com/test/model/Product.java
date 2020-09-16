package com.test.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;

@Data
@Setter
@Getter
@EqualsAndHashCode
public class Product {
	@Id
	@Field("_id")
	private String sku;

	@Min(0)
	private Integer positionNumber;

	@NotBlank(message = "Name is mandatory")
	@NotNull
	private String name;

	private boolean disabled;

	@Min(0)
	private Integer quantity;

	@Min(0)
	private Double price;

	@NotBlank(message = "Type is mandatory")
	@NotNull
	private String type;
}
