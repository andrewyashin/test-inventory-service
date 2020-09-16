package com.test.service;

import com.test.model.Product;

import java.util.List;

public interface ProductService {

	Product save(Product product);

	List<Product> getAllProducts();

	List<Product> getNotAvailableProducts();

	List<Product> getAvailableProducts();

	Product disableProduct(String sku);

	Product enableProduct(String sku);

	Product findBySku(String sku);

	List<Product> findByType(String type);

	Product findByPosition(Integer position);
}
