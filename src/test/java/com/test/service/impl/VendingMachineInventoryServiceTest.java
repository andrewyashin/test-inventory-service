package com.test.service.impl;

import com.test.dao.ProductRepository;
import com.test.model.Product;
import com.test.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineInventoryServiceTest {
	private static final String SKU = "sku";
	private static final Integer EXPECTED_QUANTITY = 100;

	@Mock
	private ProductService productService;

	@InjectMocks
	private VendingMachineInventoryService service;

	private Product product;

	@Test
	public void shouldReturnQuantityWhenProductIsPresent() {
		product = new Product();
		product.setQuantity(EXPECTED_QUANTITY);
		when(productService.findBySku(SKU)).thenReturn(product);

		Integer actual = service.getQuantity(SKU);
		assertEquals(EXPECTED_QUANTITY, actual);
	}

	@Test
	public void shouldReturnZeroQuantityWhenProductIsNotPresent() {
		when(productService.findBySku(SKU)).thenReturn(null);

		Integer actual = service.getQuantity(SKU);
		assertEquals(Integer.valueOf(0), actual);
	}

	@Test
	public void shouldReleaseProductWhenQuantityGtZeroAndDisabledFalse() {
		product = new Product();
		product.setQuantity(EXPECTED_QUANTITY);
		product.setDisabled(false);
		when(productService.findByPosition(1)).thenReturn(product);
		when(productService.save(product)).thenReturn(product);

		Product actual = service.releaseProduct(1);
		assertNotNull(actual);
		assertEquals(Integer.valueOf(EXPECTED_QUANTITY - 1), actual.getQuantity());
	}

	@Test
	public void shouldNotReleaseProductWhenQuantityLtOneAndDisabledFalse() {
		product = new Product();
		product.setQuantity(0);
		product.setDisabled(false);
		when(productService.findByPosition(1)).thenReturn(product);

		Product actual = service.releaseProduct(1);
		assertNull(actual);
	}

	@Test
	public void shouldNotReleaseProductWhenQuantityGtZeroAndDisabledTrue() {
		product = new Product();
		product.setQuantity(1);
		product.setDisabled(true);
		when(productService.findByPosition(1)).thenReturn(product);

		Product actual = service.releaseProduct(1);
		assertNull(actual);
	}


}
