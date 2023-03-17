/**
 * 
 */
package com.web.springboot.ivento.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.springboot.ivento.model.CustomerOrderEntity;

/**
 * @author mokht
 *
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {

	@Query("select o from CustomerOrderEntity o JOIN o.orderedProducts p where upper(o.name) like upper(concat('%', :search,'%'))")
	public Optional<List<CustomerOrderEntity>> searchByCustomerName(/* @Param("search") */ String search);

	@Query("select o from CustomerOrderEntity o JOIN o.orderedProducts p WHERE o.lastUpdate >= :startDate AND o.lastUpdate < :endDate")
	public Optional<List<CustomerOrderEntity>> findByPlaceOrderStartDateTimeAndDateTime(LocalDateTime startDate,
			LocalDateTime endDate);

}
