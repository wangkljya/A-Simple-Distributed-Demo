package com.wkl.provider;

import com.wkl.api.OrderService;
import com.wkl.object.GoodOrder;

public class OrderServiceImpl implements OrderService {

	public boolean OrderAdd(GoodOrder goodOrder) {
		// TODO Auto-generated method stub
		System.out.println(Long.toBinaryString(goodOrder.getOrderId()));
		return true;
	}
}
