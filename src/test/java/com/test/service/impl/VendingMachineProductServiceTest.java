package com.test.service.impl;

import com.test.dao.ProductRepository;
import com.test.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineProductServiceTest {

	private static final String SKU = "SKU";
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private VendingMachineProductService service;

	private Product product1;
	private Product product2;
	private Product product3;

	@Before
	public void setUp() {
		product1 = new Product();
		product2 = new Product();
		product3 = new Product();

		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2, product3));
		when(productRepository.findAvailableProducts()).thenReturn(Arrays.asList(product1, product2));
		when(productRepository.findNotAvailableProducts()).thenReturn(Arrays.asList(product1, product3));
		when(productRepository.findBySku(SKU)).thenReturn(product1);
	}

	@Test
	public void shouldGetAllProducts() {
		List<Product> actual = service.getAllProducts();

		assertEquals(Arrays.asList(product1, product2, product3), actual);
	}

	@Test
	public void shouldGetAvailableProducts() {

		List<Product> actual = service.getAvailableProducts();

		assertEquals(Arrays.asList(product1, product2), actual);
	}

	@Test
	public void shouldGetNotAvailableProducts() {
		List<Product> actual = service.getNotAvailableProducts();

		assertEquals(Arrays.asList(product1, product3), actual);
	}
}
