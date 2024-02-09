package com.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.ProductEntity;
import com.product.exception.InvalidDataException;
import com.product.repository.ProductRepository;
import com.product.vo.ProductVo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductVo addProduct(ProductVo productVo) {
		log.info("Saving product details: {} " + productVo);
		ProductEntity productEntity = ProductEntity.builder().productName(productVo.getProductName())
				.price(productVo.getPrice()).quantity(productVo.getQuantity()).build();
		ProductEntity savedProduct = productRepository.save(productEntity);
		log.info("Product details saved successfully: {} " + savedProduct);

		ProductVo productVoResponse = ProductVo.builder().productId(savedProduct.getProductId())
				.productName(savedProduct.getProductName()).price(savedProduct.getPrice())
				.quantity(savedProduct.getQuantity()).build();

		return productVoResponse;
	}

	@Override
	public ProductVo getById(long id) {
		// Entity Data
		Optional<ProductEntity> product = productRepository.findById(id);
		log.info("Fetching product details from database with id : {} " + id);

		ProductVo productVo = new ProductVo();
		if (product.isPresent()) {

			productVo.setProductId(product.get().getProductId());
			productVo.setProductName(product.get().getProductName());
			productVo.setPrice(product.get().getPrice());
			productVo.setQuantity(product.get().getQuantity());

			log.info("Fetched product details from database with id : {} " + id);
		} else {
			throw new InvalidDataException("Product not found with id:" + id);
		}

		return productVo;
	}

	@Override
	public void reduceQuantity(long id, long quantity)  {
		Optional<ProductEntity> productOpt = productRepository.findById(id);
		if (productOpt.isPresent()) {
			
			ProductEntity productEntity = productOpt.get();
			
			if (productEntity.getQuantity()<quantity) {
				throw new InvalidDataException("Product quantity insufficient");
			}
			
			productEntity.setQuantity(productEntity.getQuantity()- quantity);
			
			productRepository.save(productEntity);
			
		}
		
	}

}
