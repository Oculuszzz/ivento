/**
 * 
 */
package com.web.springboot.ivento.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * @author mokht
 *
 */
@Entity
@Table(name = "ORDERED_PRODUCT")
public class OrderedProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "productId", nullable = false)
	private Long productId;

	@NotBlank
	@Column(name = "productCode", nullable = false)
	private String productCode;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "quantity", nullable = false)
	private Long quantity;

	@Column(name = "price", nullable = false)
	private Long price;

	@Column(name = "totalPrice", nullable = false)
	private Long totalPrice;

	/**
	 * 
	 */
	public OrderedProductEntity() {
		super();
	}

	/**
	 * @param id
	 * @param productId
	 * @param productCode
	 * @param name
	 * @param brand
	 * @param quantity
	 * @param price
	 * @param totalPrice
	 */
	public OrderedProductEntity(Long productId, @NotBlank String productCode, @NotBlank String name,
			@NotBlank String brand, Long quantity, Long price, Long totalPrice) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.name = name;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return the totalPrice
	 */
	public Long getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, name, price, productCode, productId, quantity, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedProductEntity other = (OrderedProductEntity) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(productCode, other.productCode)
				&& Objects.equals(productId, other.productId) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(totalPrice, other.totalPrice);
	}

	@Override
	public String toString() {
		return "OrderedProductEntity [id=" + id + ", productId=" + productId + ", productCode=" + productCode
				+ ", name=" + name + ", brand=" + brand + ", quantity=" + quantity + ", price=" + price
				+ ", totalPrice=" + totalPrice + "]";
	}

}
