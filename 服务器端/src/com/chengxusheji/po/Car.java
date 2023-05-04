package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Car {
    /*车辆id*/
    private Integer carId;
    public Integer getCarId(){
        return carId;
    }
    public void setCarId(Integer carId){
        this.carId = carId;
    }

    /*车牌*/
    @NotEmpty(message="车牌不能为空")
    private String carNo;
    public String getCarNo() {
        return carNo;
    }
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    /*车型*/
    private CarModel carModelObj;
    public CarModel getCarModelObj() {
        return carModelObj;
    }
    public void setCarModelObj(CarModel carModelObj) {
        this.carModelObj = carModelObj;
    }

    /*品牌*/
    @NotEmpty(message="品牌不能为空")
    private String pinpai;
    public String getPinpai() {
        return pinpai;
    }
    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    /*车辆照片*/
    private String carPhoto;
    public String getCarPhoto() {
        return carPhoto;
    }
    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
    }

    private String carPhotoUrl;
    public void setCarPhotoUrl(String carPhotoUrl) {
		this.carPhotoUrl = carPhotoUrl;
	}
	public String getCarPhotoUrl() {
		return  SessionConsts.BASE_URL + carPhoto;
	}
    /*油型*/
    @NotEmpty(message="油型不能为空")
    private String youxing;
    public String getYouxing() {
        return youxing;
    }
    public void setYouxing(String youxing) {
        this.youxing = youxing;
    }

    /*耗油量*/
    @NotEmpty(message="耗油量不能为空")
    private String haoyouliang;
    public String getHaoyouliang() {
        return haoyouliang;
    }
    public void setHaoyouliang(String haoyouliang) {
        this.haoyouliang = haoyouliang;
    }

    /*车险日期*/
    @NotEmpty(message="车险日期不能为空")
    private String chexianriqi;
    public String getChexianriqi() {
        return chexianriqi;
    }
    public void setChexianriqi(String chexianriqi) {
        this.chexianriqi = chexianriqi;
    }

    /*总里程*/
    @NotEmpty(message="总里程不能为空")
    private String zonglicheng;
    public String getZonglicheng() {
        return zonglicheng;
    }
    public void setZonglicheng(String zonglicheng) {
        this.zonglicheng = zonglicheng;
    }

    /*车辆详情*/
    private String carDesc;
    public String getCarDesc() {
        return carDesc;
    }
    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    /*所属用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*登记时间*/
    @NotEmpty(message="登记时间不能为空")
    private String addTime;
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonCar=new JSONObject(); 
		jsonCar.accumulate("carId", this.getCarId());
		jsonCar.accumulate("carNo", this.getCarNo());
		jsonCar.accumulate("carModelObj", this.getCarModelObj().getModelName());
		jsonCar.accumulate("carModelObjPri", this.getCarModelObj().getModelId());
		jsonCar.accumulate("pinpai", this.getPinpai());
		jsonCar.accumulate("carPhoto", this.getCarPhoto());
		jsonCar.accumulate("youxing", this.getYouxing());
		jsonCar.accumulate("haoyouliang", this.getHaoyouliang());
		jsonCar.accumulate("chexianriqi", this.getChexianriqi().length()>19?this.getChexianriqi().substring(0,19):this.getChexianriqi());
		jsonCar.accumulate("zonglicheng", this.getZonglicheng());
		jsonCar.accumulate("carDesc", this.getCarDesc());
		jsonCar.accumulate("userObj", this.getUserObj().getName());
		jsonCar.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonCar.accumulate("addTime", this.getAddTime().length()>19?this.getAddTime().substring(0,19):this.getAddTime());
		return jsonCar;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}