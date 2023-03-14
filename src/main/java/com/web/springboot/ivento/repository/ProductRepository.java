/**
 * 
 */
package com.web.springboot.ivento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.springboot.ivento.model.ProductEntity;

/**
 * @author mokht
 *
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//	@Query("select t from TaskDB t JOIN t.assignedUser u where u.email = :email")
	@Query("select p from ProductEntity p where upper(p.name) like upper(concat('%', :search,'%')) OR upper(p.brand) LIKE upper(concat('%', :search,'%')) OR upper(p.productCode) LIKE upper(concat('%', :search,'%'))")
	public Optional<List<ProductEntity>> searchProducts(/* @Param("search") */ String search);

}
