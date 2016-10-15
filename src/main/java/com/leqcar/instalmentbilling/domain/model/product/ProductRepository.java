package com.leqcar.instalmentbilling.domain.model.product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

	Product findByCode(Product product);

}
