package com.wkl.provider;

import java.util.ArrayList;
import java.util.List;

import com.wkl.api.CallService;

public class DemoServiceImpl implements CallService {

	public List<String> getServiceInfo(Integer id) {
		// TODO Auto-generated method stub
		List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
        
	}

}
