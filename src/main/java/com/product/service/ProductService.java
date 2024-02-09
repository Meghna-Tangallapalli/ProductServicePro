package com.product.service;

import org.springframework.stereotype.Component;

import com.product.vo.ProductVo;

@Component
public interface ProductService {

	ProductVo addProduct(ProductVo productVo);

	ProductVo getById(long id);

	void reduceQuantity(long id, long quantity);

}
