/**
 * 
 */
package com.kd.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kd.rest.model.ProductModel;
import com.kd.rest.repository.ProductRepository;

/**
 * @author klawand
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<ProductModel> findAll() {

		return productRepository.findAll();
	}

	@Override
	public String add(ProductModel productModel) {

		ProductModel save = productRepository.save(productModel);
		return save.getId();
	}

	@Override
	public List<ProductModel> findByProductName(String productName) {
		List<ProductModel> productModels = new ArrayList<ProductModel>();

		Iterable<ProductModel> findByProductName = productRepository.findByProductName(productName);

		findByProductName.forEach(productModels::add);

		return productModels;
	}

}
