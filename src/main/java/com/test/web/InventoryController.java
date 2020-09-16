package com.test.web;

import com.test.model.Product;
import com.test.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Resource
	private InventoryService inventoryService;

	@GetMapping(value = "/availability/{sku}")
	public ResponseEntity<?> checkAvailabilityBySku(@PathVariable String sku) {
		return ResponseEntity.ok(inventoryService.getQuantity(sku));
	}

	@GetMapping(value = "/release/{position}")
	public ResponseEntity<?> checkAvailabilityBySku(@PathVariable Integer position) {
		final Product product = inventoryService.releaseProduct(position);
		if (product != null)
			return ResponseEntity.ok(product);

		return ResponseEntity.status(406).body("Cannot release product");
	}
}
