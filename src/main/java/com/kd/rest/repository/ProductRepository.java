/**
 * 
 */
package com.kd.rest.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.kd.rest.model.ProductModel;

/**
 * @author klawand
 *
 */

@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductModel, String>{

	public Iterable<ProductModel> findByProductName(String searchTerm);
	

}
