/**
 * 
 */
package com.web.springboot.ivento.payload.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.web.springboot.ivento.model.CustomerOrderEntity;
import com.web.springboot.ivento.model.EOrderStatus;

/**
 * @author mokht
 *
 */
public class CustomerOrderResponse {

	private Long id;

	private String name;

	private String phoneNumber;

	private String address;

	private String companyName;

	private String companyAddress;

	private List<OrderedProductResponse> products;

	private EOrderStatus status;

	private Long totalPrice;

	private LocalDateTime lastUpdate;

	/**
	 * 
	 */
	public CustomerOrderResponse() {
		super();
	}

	/**
	 * 
	 */
	public CustomerOrderResponse(CustomerOrderEntity entity) {

		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setPhoneNumber(entity.getPhoneNumber());
		this.setAddress(entity.getAddress());
		this.setCompanyName(entity.getCompanyName());
		this.setCompanyAddress(entity.getCompanyAddress());
		this.setStatus(entity.getStatus());
		this.setTotalPrice(entity.getTotalPrice());
		this.setLastUpdate(entity.getLastUpdate());
		this.setProducts(entity.getOrderedProducts().stream().map(OrderedProductResponse::new).toList());

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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the products
	 */
	public List<OrderedProductResponse> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<OrderedProductResponse> products) {
		this.products = products;
	}

	/**
	 * @return the status
	 */
	public EOrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EOrderStatus status) {
		this.status = status;
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
		return Objects.hash(address, companyAddress, companyName, id, lastUpdate, name, phoneNumber, products, status,
				totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerOrderResponse other = (CustomerOrderResponse) obj;
		return Objects.equals(address, other.address) && Objects.equals(companyAddress, other.companyAddress)
				&& Objects.equals(companyName, other.companyName) && Objects.equals(id, other.id)
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(products, other.products)
				&& status == other.status && Objects.equals(totalPrice, other.totalPrice);
	}

	@Override
	public String toString() {
		return "CustomerOrderResponse [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", companyName=" + companyName + ", companyAddress=" + companyAddress + ", products="
				+ products + ", status=" + status + ", totalPrice=" + totalPrice + ", lastUpdate=" + lastUpdate + "]";
	}

}
