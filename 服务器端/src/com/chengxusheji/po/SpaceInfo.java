package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SpaceInfo {
    /*记录id*/
    private Integer spaceId;
    public Integer getSpaceId(){
        return spaceId;
    }
    public void setSpaceId(Integer spaceId){
        this.spaceId = spaceId;
    }

    /*所在区域*/
    private Region regionObj;
    public Region getRegionObj() {
        return regionObj;
    }
    public void setRegionObj(Region regionObj) {
        this.regionObj = regionObj;
    }

    /*车位名称*/
    @NotEmpty(message="车位名称不能为空")
    private String spaceNo;
    public String getSpaceNo() {
        return spaceNo;
    }
    public void setSpaceNo(String spaceNo) {
        this.spaceNo = spaceNo;
    }

    /*车位照片*/
    private String spacePhoto;
    public String getSpacePhoto() {
        return spacePhoto;
    }
    public void setSpacePhoto(String spacePhoto) {
        this.spacePhoto = spacePhoto;
    }

    private String spacePhotoUrl;
    public void setSpacePhotoUrl(String spacePhotoUrl) {
		this.spacePhotoUrl = spacePhotoUrl;
	}
	public String getSpacePhotoUrl() {
		return  SessionConsts.BASE_URL + spacePhoto;
	}
    /*车位价格*/
    @NotNull(message="必须输入车位价格")
    private Float spacePrice;
    public Float getSpacePrice() {
        return spacePrice;
    }
    public void setSpacePrice(Float spacePrice) {
        this.spacePrice = spacePrice;
    }

    /*车位状态*/
    @NotEmpty(message="车位状态不能为空")
    private String spaceState;
    public String getSpaceState() {
        return spaceState;
    }
    public void setSpaceState(String spaceState) {
        this.spaceState = spaceState;
    }

    /*车位描述*/
    private String spaceDesc;
    public String getSpaceDesc() {
        return spaceDesc;
    }
    public void setSpaceDesc(String spaceDesc) {
        this.spaceDesc = spaceDesc;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonSpaceInfo=new JSONObject(); 
		jsonSpaceInfo.accumulate("spaceId", this.getSpaceId());
		jsonSpaceInfo.accumulate("regionObj", this.getRegionObj().getRegionName());
		jsonSpaceInfo.accumulate("regionObjPri", this.getRegionObj().getRegionId());
		jsonSpaceInfo.accumulate("spaceNo", this.getSpaceNo());
		jsonSpaceInfo.accumulate("spacePhoto", this.getSpacePhoto());
		jsonSpaceInfo.accumulate("spacePrice", this.getSpacePrice());
		jsonSpaceInfo.accumulate("spaceState", this.getSpaceState());
		jsonSpaceInfo.accumulate("spaceDesc", this.getSpaceDesc());
		return jsonSpaceInfo;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}