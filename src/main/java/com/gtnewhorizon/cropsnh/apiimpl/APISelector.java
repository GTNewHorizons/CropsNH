package com.gtnewhorizon.cropsnh.apiimpl;

import com.gtnewhorizon.cropsnh.api.API;
import com.gtnewhorizon.cropsnh.api.APIBase;
import com.gtnewhorizon.cropsnh.api.APIStatus;
import com.gtnewhorizon.cropsnh.apiimpl.v1.APIimplv1;


public class APISelector implements APIBase {

	private APISelector() {}

	public static void init() {
		API.setAPI(new APISelector());
	}

	@Override
	public APIBase getAPI(int maxVersion) {
		if (maxVersion <= 0) {
			return this;
		} else {
			switch(maxVersion) {
				case 1:
				default:
					return new APIimplv1(1, APIStatus.OK);
			}
		}
	}

	@Override
	public APIStatus getStatus() {
		return APIStatus.ERROR;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
