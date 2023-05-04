package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Park {
    /*记录id*/
    private Integer parkId;
    public Integer getParkId(){
        return parkId;
    }
    public void setParkId(Integer parkId){
        this.parkId = parkId;
    }

    /*车辆信息*/
    private Car carObj;
    public Car getCarObj() {
        return carObj;
    }
    public void setCarObj(Car carObj) {
        this.carObj = carObj;
    }

    /*停车用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*停入车位*/
    private SpaceInfo spaceObj;
    public SpaceInfo getSpaceObj() {
        return spaceObj;
    }
    public void setSpaceObj(SpaceInfo spaceObj) {
        this.spaceObj = spaceObj;
    }

    /*停车开始时间*/
    @NotEmpty(message="停车开始时间不能为空")
    private String startTime;
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /*停车离开时间*/
    @NotEmpty(message="停车离开时间不能为空")
    private String endTime;
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /*车位价格*/
    @NotNull(message="必须输入车位价格")
    private Float price;
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    /*停车时长*/
    @NotEmpty(message="停车时长不能为空")
    private String timeSpan;
    public String getTimeSpan() {
        return timeSpan;
    }
    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    /*停车费用*/
    @NotNull(message="必须输入停车费用")
    private Float parkMoney;
    public Float getParkMoney() {
        return parkMoney;
    }
    public void setParkMoney(Float parkMoney) {
        this.parkMoney = parkMoney;
    }

    /*停车备注*/
    private String parkMemo;
    public String getParkMemo() {
        return parkMemo;
    }
    public void setParkMemo(String parkMemo) {
        this.parkMemo = parkMemo;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonPark=new JSONObject(); 
		jsonPark.accumulate("parkId", this.getParkId());
		jsonPark.accumulate("carObj", this.getCarObj().getCarNo());
		jsonPark.accumulate("carObjPri", this.getCarObj().getCarId());
		jsonPark.accumulate("userObj", this.getUserObj().getName());
		jsonPark.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonPark.accumulate("spaceObj", this.getSpaceObj().getSpaceNo());
		jsonPark.accumulate("spaceObjPri", this.getSpaceObj().getSpaceId());
		jsonPark.accumulate("startTime", this.getStartTime().length()>19?this.getStartTime().substring(0,19):this.getStartTime());
		jsonPark.accumulate("endTime", this.getEndTime().length()>19?this.getEndTime().substring(0,19):this.getEndTime());
		jsonPark.accumulate("price", this.getPrice());
		jsonPark.accumulate("timeSpan", this.getTimeSpan());
		jsonPark.accumulate("parkMoney", this.getParkMoney());
		jsonPark.accumulate("parkMemo", this.getParkMemo());
		return jsonPark;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}