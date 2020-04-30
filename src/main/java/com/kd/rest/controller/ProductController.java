/**
 * 
 */
package com.kd.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.rest.model.ProductModel;
import com.kd.rest.service.ProductService;

/**
 * @author klawand
 *
 */
@RestController
@RequestMapping(value = "/grahakbazar")
public class ProductController {

	private static final Logger LOGGER = Logger.getLogger(ProductController.class.getSimpleName());

	@Autowired
	ProductService productService;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@GetMapping(value = "/product/list")
	public ResponseEntity<List<ProductModel>> findAllProduct() {
		List<ProductModel> productModels = new ArrayList<ProductModel>();
		Iterable<ProductModel> findAll = productService.findAll();
		findAll.forEach(productModels::add);

		return new ResponseEntity<List<ProductModel>>(productModels, HttpStatus.OK);
	}

	@PostMapping(value = "/product/add")
	public ResponseEntity<String> addProduct(@RequestBody ProductModel productModel) {
		String add = productService.add(productModel);
		return new ResponseEntity<String>(add, HttpStatus.CREATED);
	}

	/**
	 * 
	 * <p>
	 * sampel term aggegation
	 * </p>
	 * 
	 * @param tagname
	 * 
	 *                <p>
	 *                elastic search quuery
	 * 
	 *                { "size": 0, "aggregations": { "my-tags": { "terms": {
	 *                "field": "tag" } } } }
	 * 
	 * 
	 * 
	 * @return
	 */
	@GetMapping(value = "/product/{tagname}")
	public ResponseEntity<Map<String, Long>> getTags(@PathVariable String tagname) {

		Map<String, Long> aggegates = new HashMap<String, Long>();

		SearchQuery query = new NativeSearchQueryBuilder()
				.addAggregation(AggregationBuilders.terms("my-tags").field(tagname)).build();

		Aggregations aggregations = elasticsearchTemplate.query(query, new ResultsExtractor<Aggregations>() {

			@Override
			public Aggregations extract(SearchResponse response) {
				return response.getAggregations();
			}
		});

		LOGGER.info(aggregations.asMap().get("my-tags").toString());

		Map<String, Aggregation> asMap = aggregations.getAsMap();

		System.out.println(asMap.size());
		StringTerms str;
		try {
			str = (StringTerms) asMap.get("my-tags");
		} catch (ClassCastException e) {
			LOGGER.info("no aggegate result found....returnin empty");
			return new ResponseEntity<Map<String, Long>>(aggegates, HttpStatus.OK);
		}

		List<Bucket> buckets = str.getBuckets();

		buckets.forEach(r -> {
			aggegates.put((String) r.getKey(), r.getDocCount());

		});

		return new ResponseEntity<Map<String, Long>>(aggegates, HttpStatus.OK);
	}

	@GetMapping(value = "/product/search/{productName}")
	public ResponseEntity<List<ProductModel>> findByProductName(@PathVariable String productName) {
		return new ResponseEntity<List<ProductModel>>(productService.findByProductName(productName), HttpStatus.OK);
	}

}
