package com.abcd.student.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abcd.student.domain.ProductDO;
import com.abcd.student.domain.ProductDTO;
import com.abcd.student.domain.ProductQuery;
import com.abcd.student.enums.ResultEnum;
import com.abcd.student.mapper.ProductMapper;
import com.abcd.student.service.ProductService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;

/**
 * 产品Service组件实现
 * @author yangxi
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;
    
    @Override
    public List<ProductDTO> listProducts(ProductQuery productQuery) {
    	if(productQuery == null) {
    		log.error("productQuery is null");
    		throw new ApplicationException(ResultEnum.QUERY_PARAM_NOT_FOUND);
    	}
    	
    	// page=-1是跟前端约定好的参数，如果传-1表示不需要分页
    	if(productQuery.getPage() != null && productQuery.getPage() != -1) {
    		PageHelper.startPage(productQuery.getPage(), productQuery.getSize());
    	}
        
        List<ProductDO> products = productMapper.listProducts(productQuery);
        List<ProductDTO> targetProducts = ObjectCloneUtils.convertList(products, ProductDTO.class);
        return targetProducts;
    }

    @Override
	public ProductDTO getProductById(Long id) {
    	if(id == null) {
    		return null;
    	}
		ProductDO productDO = productMapper.selectById(id);
		if(productDO != null) {
			return productDO.clone(ProductDTO.class);
		}
		return null;
	}

	@Override
	@Transactional
	public ProductDTO saveProduct(ProductDTO productDTO) {
		if(productDTO == null) {
			log.error("productDTO is null");
			throw new ApplicationException(ResultEnum.REQUEST_PARAM_NOT_FOUND);
		}
		ProductDO productDO = productDTO.clone(ProductDO.class);
		int num = productMapper.insert(productDO);
		if(num > 0) {
			productDO = productMapper.selectById(productDO.getId());
			return productDO.clone(ProductDTO.class);
		}
		return null;
	}

	@Override
	@Transactional
	public ProductDTO updateProduct(ProductDTO productDTO) {
		if(productDTO == null) {
			log.error("productDTO is null");
			throw new ApplicationException(ResultEnum.REQUEST_PARAM_NOT_FOUND);
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean deleteProductById(Long id) {
		if(id == null) {
			log.error("id is null");
			return false;
		}
		int num = productMapper.deleteById(id);
		if(num > 0) {
			return true;
		}
		return false;
	}

	@Override
    public void testError() {
        throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
    }
}