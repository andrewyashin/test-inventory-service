package com.test.service.impl;

import com.test.model.Product;
import com.test.service.InventoryService;
import com.test.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("inventoryService")
public class VendingMachineInventoryService implements InventoryService {

	@Resource
	private ProductService productService;

	@Override
	public Integer getQuantity(String sku) {
		Product product = productService.findBySku(sku);
		if (product != null)
			return product.getQuantity();
		return 0;
	}

	@Override
	public Product releaseProduct(Integer positionNumber) {
		final Product product = productService.findByPosition(positionNumber);

		if (product != null && product.getQuantity() > 0 && !product.isDisabled()) {
			product.setQuantity(product.getQuantity() - 1);
			if (product.getQuantity() < 1)
				product.setDisabled(true);
			return productService.save(product);
		}

		return null;
	}
}
