/**
 * 
 */
package com.kd.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author klawand
 *
 */
@Document(indexName = "products", type = "_doc")
public class ProductModel {

	@Id
	@JsonIgnore
	private String id;

	// we can directly pass type from here also
	// @Field(type = FieldType.Keyword)
	private String productName;
	private String descripation;
	private String category;
	private String tag;
	private double price;
	private double coupon;
	private long vendorUserId;
	private String rate;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescripation() {
		return descripation;
	}

	public void setDescripation(String descripation) {
		this.descripation = descripation;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCoupon() {
		return coupon;
	}

	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	public long getVendorUserId() {
		return vendorUserId;
	}

	public void setVendorUserId(long vendorUserId) {
		this.vendorUserId = vendorUserId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
