package com.test.service;

import com.test.model.Product;

public interface InventoryService {
	Integer getQuantity(String sku);
	Product releaseProduct(Integer positionNumber);
}
