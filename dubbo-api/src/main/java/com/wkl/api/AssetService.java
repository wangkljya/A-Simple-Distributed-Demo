package com.wkl.api;

import com.wkl.object.Asset;

public interface AssetService {
	
	public Asset getAssetInfo(Integer userId);
	
	public boolean updateAssetInfo(Double balance);
}
