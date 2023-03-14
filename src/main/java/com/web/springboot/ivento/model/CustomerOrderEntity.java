/**
 * 
 */
package com.web.springboot.ivento.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * @author mokht
 *
 */
@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;

	@NotBlank
	@Column(name = "address")
	private String address;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "companyAddress")
	private String companyAddress;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "customer_ordered_product", joinColumns = {
			@JoinColumn(name = "customer_order_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "product_id", nullable = false) })
	private List<OrderedProductEntity> orderedProducts;

	@Column(name = "totalPrice", nullable = false)
	private Long totalPrice;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private EOrderStatus status;

	@Column(name = "lastUpdate", nullable = false)
	private LocalDateTime lastUpdate;

	/**
	 * 
	 */
	public CustomerOrderEntity() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param companyName
	 * @param companyAddress
	 * @param orderedProducts
	 * @param totalPrice
	 * @param status
	 * @param lastUpdate
	 */
	public CustomerOrderEntity(Long id, @NotBlank String name, @NotBlank String phoneNumber, @NotBlank String address,
			String companyName, String companyAddress, List<OrderedProductEntity> orderedProducts,
			@NotBlank Long totalPrice, @NotBlank EOrderStatus status, @NotBlank LocalDateTime lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.orderedProducts = orderedProducts;
		this.totalPrice = totalPrice;
		this.status = status;
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
	 * @return the lProducts
	 */
	public List<OrderedProductEntity> getOrderedProducts() {
		return orderedProducts;
	}

	/**
	 * @param lProducts the lProducts to set
	 */
	public void setOrderedProducts(List<OrderedProductEntity> orderedProducts) {
		this.orderedProducts = orderedProducts;
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
		return Objects.hash(address, companyAddress, companyName, id, lastUpdate, name, orderedProducts, phoneNumber,
				status, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerOrderEntity other = (CustomerOrderEntity) obj;
		return Objects.equals(address, other.address) && Objects.equals(companyAddress, other.companyAddress)
				&& Objects.equals(companyName, other.companyName) && Objects.equals(id, other.id)
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(name, other.name)
				&& Objects.equals(orderedProducts, other.orderedProducts)
				&& Objects.equals(phoneNumber, other.phoneNumber) && status == other.status
				&& Objects.equals(totalPrice, other.totalPrice);
	}

	@Override
	public String toString() {
		return "CustomerOrderEntity [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", companyName=" + companyName + ", companyAddress=" + companyAddress + ", orderedProducts="
				+ orderedProducts + ", totalPrice=" + totalPrice + ", status=" + status + ", lastUpdate=" + lastUpdate
				+ "]";
	}

}
