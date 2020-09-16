package com.test.service.impl;

import com.google.common.collect.Lists;
import com.test.dao.ProductRepository;
import com.test.model.Product;
import com.test.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class VendingMachineProductService implements ProductService {

	@Resource
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return Lists.newArrayList(productRepository.findAll());
	}

	@Override
	public List<Product> getNotAvailableProducts() {
		return Lists.newArrayList(productRepository.findNotAvailableProducts());
	}

	@Override
	public List<Product> getAvailableProducts() {
		return Lists.newArrayList(productRepository.findAvailableProducts());
	}

	@Override
	public Product disableProduct(String skuCode) {
		Product product = productRepository.findBySku(skuCode);

		if (product != null) {
			product.setDisabled(true);
			productRepository.save(product);
			return product;
		}

		return null;
	}

	@Override
	public Product enableProduct(String skuCode) {
		Product product = productRepository.findBySku(skuCode);

		if (product != null) {
			product.setDisabled(false);
			productRepository.save(product);
			return product;
		}

		return null;
	}

	@Override
	public Product findBySku(String sku) {
		return productRepository.findBySku(sku);
	}

	@Override
	public List<Product> findByType(String type) {
		return productRepository.findByType(type);
	}

	@Override
	public Product findByPosition(Integer position) {
		return productRepository.findByPositionNumber(position);
	}
}
