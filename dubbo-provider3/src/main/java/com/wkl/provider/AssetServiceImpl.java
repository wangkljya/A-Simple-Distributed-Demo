package com.wkl.provider;

import com.wkl.api.AssetService;
import com.wkl.object.Asset;

public class AssetServiceImpl implements AssetService {

	public Asset getAssetInfo(Integer userId) {
		
		Asset asset = new Asset();
		asset.setUserId(userId);
		asset.setAccount("wkl_account");
		asset.setCurrentCash(1000.0);
		return asset;
	}

	public boolean updateAssetInfo(Double balance) {
		// TODO Auto-generated method stub
		System.out.println("更新成功:"+balance);
		return true;
	}

}
