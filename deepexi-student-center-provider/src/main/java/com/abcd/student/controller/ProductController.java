package com.abcd.student.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcd.student.domain.ProductDTO;
import com.abcd.student.domain.ProductQuery;
import com.abcd.student.domain.ProductVO;
import com.abcd.student.service.ProductService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;

/**
 * Created by donh on 2018/11/5.
 */
@Validated
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 分页查询产品信息
	 * @param productQuery
	 * @return
	 */
	@GetMapping
	public Payload<PageBean<ProductVO>> getProductList(@Valid ProductQuery productQuery) {
		List<ProductDTO> products = productService.listProducts(productQuery);
		List<ProductVO> targetProducts = ObjectCloneUtils.convertList(products, ProductVO.class);
		// 如果前端page赋值为-1表示不分页，但是后台返回的JSON格式约定统一使用PageBean
		PageBean<ProductVO> pageBean = new PageBean<ProductVO>(targetProducts);
		return new Payload<>(pageBean);
	}

	/**
	 * 根据ID获取产品信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id:[a-zA-Z0-9]+}")
	public Payload<ProductVO> getProductById(@PathVariable("id") Long id) {
		ProductDTO productDTO = productService.getProductById(id);
		ProductVO productVO = productDTO.clone(ProductVO.class);
		return new Payload<>(productVO);
	}

	/**
	 * 新增商品
	 * 
	 * @param productVO
	 * @return
	 */
	@PostMapping
	public Payload<ProductVO> saveProduct(@Valid @RequestBody ProductVO productVO) {
		ProductDTO productDTO = productVO.clone(ProductDTO.class);
		productDTO = productService.saveProduct(productDTO);
		return new Payload<>(productDTO.clone(ProductVO.class));
	}

	/**
	 * 更新产品
	 * 
	 * @param id
	 * @param productVO
	 * @return
	 */
	@PutMapping("/{id:[a-zA-Z0-9]+}")
	public Payload<ProductVO> updateProductById(@PathVariable("id") String id,
			@Valid @RequestBody ProductVO productVO) {
		ProductDTO productDTO = productVO.clone(ProductDTO.class);
		productDTO = productService.updateProduct(productDTO);
		return new Payload<>(productDTO.clone(ProductVO.class));
	}

	/**
	 * 删除产品
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id:[a-zA-Z0-9]+}")
	public Payload<Boolean> deleteProductById(@PathVariable("id") Long id) {
		Boolean flag = productService.deleteProductById(id);
		return new Payload<>(flag);
	}

	/**
	 * 测试统一异常处理
	 * 
	 * @return
	 */
	@GetMapping("/testError")
	public Payload<Boolean> testError() {
		productService.testError();
		return new Payload<>(true);
	}

	/**
	 * 绑定参数校验demo，测试post方式
	 */
	@PostMapping("/testPostValidate")
	public Payload<Boolean> testPostValidate(@Min(value = 5, message = "id必须大于等于5") @RequestParam Integer id,
			@Valid @RequestBody ProductVO productVO) {
		return new Payload<>(true);
	}

	/**
	 * 绑定参数校验demo2，测试get url传参方式 参数少时可以直接列出来单独写 参数多时可以选择封装成dto对象
	 */
	@GetMapping("/testGetValidate")
	public Payload<Boolean> testGetValidate(@Min(value = 5, message = "id必须大于等于5") @RequestParam Integer id,
			@Valid ProductVO productVO) {
		return new Payload<>(true);
	}
}
