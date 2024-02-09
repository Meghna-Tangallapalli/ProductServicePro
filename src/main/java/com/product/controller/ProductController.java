package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.ProductService;
import com.product.vo.ProductVo;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<ProductVo> addProduct(@RequestBody ProductVo productVo) {
		ProductVo result = productService.addProduct(productVo);
		return new ResponseEntity<ProductVo>(result, HttpStatus.CREATED);
	}

	@GetMapping("/getById")
	public ResponseEntity<ProductVo> getById(@RequestParam long id){
		ProductVo byId = productService.getById(id);
		return new ResponseEntity<ProductVo>(byId, HttpStatus.OK);
	}

	@PutMapping("/reduceQty")
	public ResponseEntity<Void> reduceQuantity(@RequestParam long productId, @RequestParam long quantity){
		productService.reduceQuantity(productId, quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
