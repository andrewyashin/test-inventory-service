package com.test.web;

import com.test.model.Product;
import com.test.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {
	@Resource
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> products() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping(value = "/available")
	public ResponseEntity<List<Product>> availableProducts() {
		return ResponseEntity.ok(productService.getAvailableProducts());
	}

	@GetMapping(value = "/not-available")
	public ResponseEntity<List<Product>> notAvailableProducts() {
		return ResponseEntity.ok(productService.getNotAvailableProducts());
	}

	@PutMapping(value = "/save")
	public ResponseEntity<?> save(@Valid @RequestBody Product product) {
		return ResponseEntity.ok(productService.save(product));
	}

	@PostMapping(value = "/disable/{sku}")
	public ResponseEntity<?> disableProduct(@PathVariable String sku) {
		final Product product = productService.disableProduct(sku);
		if (product != null) {
			return ResponseEntity.ok(product);

		}
		return ResponseEntity.status(406).body("Cannot disable product");
	}

	@PostMapping(value = "/enable/{sku}")
	public ResponseEntity<?> enableProduct(@PathVariable String sku) {
		final Product product = productService.disableProduct(sku);
		if (product != null) {
			return ResponseEntity.ok(product);

		}
		return ResponseEntity.status(406).body("Cannot disable product");
	}

	@GetMapping(value = "/code/{sku}")
	public ResponseEntity<?> findProduct(@PathVariable String sku) {
		return ResponseEntity.ok(productService.findBySku(sku));
	}

	@GetMapping(value = "/type/{type}")
	public ResponseEntity<?> findProductByType(@PathVariable String type) {
		return ResponseEntity.ok(productService.findByType(type));
	}
}
