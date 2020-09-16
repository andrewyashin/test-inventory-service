package com.test.dao;

import com.test.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	@Query("{'$or' : [ {'quantity' : { $lt: 1 }}, {'disabled': true}] }")
	List<Product> findNotAvailableProducts();

	@Query("{ 'quantity' : { $gt: 0 }, 'disabled': false }")
	List<Product> findAvailableProducts();

	Product findBySku(String sku);

	List<Product> findByType(String type);

	Product findByPositionNumber(Integer positionNumber);
}
