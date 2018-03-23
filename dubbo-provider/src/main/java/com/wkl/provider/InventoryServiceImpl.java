package com.wkl.provider;
import com.wkl.api.InventoryService;
import com.wkl.object.Product;
import com.wkl.object.Test;

public class InventoryServiceImpl implements InventoryService{

	public Product getProductInfo(Integer productId) {
		// TODO Auto-generated method stub
		//整合mybatis之后在写吧
		Product p = new Product();
		p.setProductId(productId);
		p.setProductName("衣服");
		p.setStockQuantity(123);
		return p;
	}

}
