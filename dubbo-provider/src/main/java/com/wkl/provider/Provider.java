package com.wkl.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	
	public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
		try {
	        System.out.println(context.getDisplayName() + ": here");
	        context.start();
	        System.out.println("服务1已经启动...");
	        System.in.read();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			context.close();
		}
    }
}
