package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarModel {
    /*车型id*/
    private Integer modelId;
    public Integer getModelId(){
        return modelId;
    }
    public void setModelId(Integer modelId){
        this.modelId = modelId;
    }

    /*车型名称*/
    @NotEmpty(message="车型名称不能为空")
    private String modelName;
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonCarModel=new JSONObject(); 
		jsonCarModel.accumulate("modelId", this.getModelId());
		jsonCarModel.accumulate("modelName", this.getModelName());
		return jsonCarModel;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}