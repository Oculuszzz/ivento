/**
 * 
 */
package com.web.springboot.ivento.payload.response;

import java.time.LocalDateTime;
import java.util.Objects;

import com.web.springboot.ivento.model.ProductEntity;

/**
 * @author mokht
 *
 */
public class ProductResponse {

	private Long id;

	private String productCode;

	private String name;

	private String brand;

	private Long quantity;

	private Long price;

	private LocalDateTime lastUpdate;

	/**
	 * 
	 */
	public ProductResponse() {
		super();
	}

	/**
	 * 
	 */
	public ProductResponse(ProductEntity entity) {

		this.setId(entity.getId());
		this.setProductCode(entity.getProductCode());
		this.setName(entity.getName());
		this.setBrand(entity.getBrand());
		this.setPrice(entity.getPrice());
		this.setQuantity(entity.getQuantity());
		this.setLastUpdate(entity.getLastUpdate());
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

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, lastUpdate, name, price, productCode, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductResponse other = (ProductResponse) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(id, other.id)
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(productCode, other.productCode)
				&& Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", productCode=" + productCode + ", name=" + name + ", brand=" + brand
				+ ", quantity=" + quantity + ", price=" + price + ", lastUpdate=" + lastUpdate + "]";
	}

}
