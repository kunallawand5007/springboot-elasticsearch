/**
 * 
 */
package com.kd.rest.service;

import java.util.List;

import com.kd.rest.model.ProductModel;

/**
 * @author klawand
 *
 */
public interface ProductService {

	
	Iterable<ProductModel> findAll();
	
	String add(ProductModel productModel);
	
	List<ProductModel> findByProductName(String productName);
}
