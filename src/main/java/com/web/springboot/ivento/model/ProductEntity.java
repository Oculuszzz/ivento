package com.web.springboot.ivento.model;

import java.time.LocalDateTime;

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
@Table(name = "PRODUCTS")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "productCode", nullable = false, unique = true)
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

	@Column(name = "lastUpdate", nullable = false)
	private LocalDateTime lastUpdate;

	/**
	 * 
	 */
	public ProductEntity() {
		super();
	}

	/**
	 * @param productCode
	 * @param name
	 * @param brand
	 * @param quantity
	 * @param price
	 * @param lastUpdate
	 */
	public ProductEntity(@NotBlank String productCode, @NotBlank String name, @NotBlank String brand,
			@NotBlank Long quantity, @NotBlank Long price, @NotBlank LocalDateTime lastUpdate) {
		super();
		this.productCode = productCode;
		this.name = name;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
		this.lastUpdate = lastUpdate;
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
	 * @return the lastUpdate
	 */
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
