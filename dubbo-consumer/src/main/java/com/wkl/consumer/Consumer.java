package com.wkl.consumer;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.jute.Utils;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

import com.wkl.api.CallService;
import com.wkl.api.InventoryService;
import com.wkl.api.OrderService;
import com.wkl.object.GoodOrder;
import com.wkl.object.Product;
import com.wkl.utils.ConvertUtil;
import com.wkl.utils.RedisLockUtil;
import com.wkl.utils.SerializeUtil;

public class Consumer {
	
	private long workerId = 00001L;
	private long datacenterId = 00001L; 
	
	//库存可以用redis去实现
    InventoryService products;
    OrderService orderService;
    SerialNumGenerato generato;
    Jedis jedis;
    
    //当做调用接口入参
	private Integer productId = 1;
	private String productName;
	private Integer userId = 1;
	private String userName = "wkl";
	private Double balance = 100.0;
	private Date time;
	
	@Test
	public void OrderTest() throws Exception {
		//HashMap
		init();
		
		while(!RedisLockUtil.lock("addOrder"+userId.toString(), userId.toString(), 60000, jedis)) {
			Thread.sleep(1000);
		};
		
		Product product = (Product) SerializeUtil.unserialize(jedis.get(ConvertUtil.intToBytes(productId)));
        if(product.getStockQuantity() > 0){  //能下订单
            long id = generato.getOrderId();  //生成唯一订单号
            productName = product.getProductName();
            time = new Date();
            GoodOrder goodOrder = new GoodOrder(id, productId, productName, userId, userName, balance, time);
            if(orderService.OrderAdd(goodOrder)) {
            	System.out.println("成功");
            }
            else {
            	System.out.println("失败");
			}
        }
        //暂时不理解如果在释放锁失败的时候该怎么处理
    	RedisLockUtil.releaseLock("addOrder"+userId.toString(), userId.toString(), jedis);
	}
	
	public void init() {
		//测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        context.start();
        System.out.println("consumer start");
        //库存可以用redis去实现
        products = context.getBean(InventoryService.class);
        orderService = context.getBean(OrderService.class);
        generato = context.getBean(SerialNumGenerato.class);
        jedis = new Jedis("localhost");
        getProductToCache();
	}
	
	public void getProductToCache(){
		//可以在xml中配置需要进缓存的数据,在读取xml并调用接口获取
		jedis.set(ConvertUtil.intToBytes(1), SerializeUtil.serialize(products.getProductInfo(1)));
		jedis.set(ConvertUtil.intToBytes(2), SerializeUtil.serialize(products.getProductInfo(2)));
	}
}
