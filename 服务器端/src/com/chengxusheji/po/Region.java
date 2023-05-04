package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Region {
    /*区域id*/
    private Integer regionId;
    public Integer getRegionId(){
        return regionId;
    }
    public void setRegionId(Integer regionId){
        this.regionId = regionId;
    }

    /*所在楼层*/
    @NotEmpty(message="所在楼层不能为空")
    private String regionFloor;
    public String getRegionFloor() {
        return regionFloor;
    }
    public void setRegionFloor(String regionFloor) {
        this.regionFloor = regionFloor;
    }

    /*区域名称*/
    @NotEmpty(message="区域名称不能为空")
    private String regionName;
    public String getRegionName() {
        return regionName;
    }
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /*区域说明*/
    private String regionDesc;
    public String getRegionDesc() {
        return regionDesc;
    }
    public void setRegionDesc(String regionDesc) {
        this.regionDesc = regionDesc;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonRegion=new JSONObject(); 
		jsonRegion.accumulate("regionId", this.getRegionId());
		jsonRegion.accumulate("regionFloor", this.getRegionFloor());
		jsonRegion.accumulate("regionName", this.getRegionName());
		jsonRegion.accumulate("regionDesc", this.getRegionDesc());
		return jsonRegion;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}