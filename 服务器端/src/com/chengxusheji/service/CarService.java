package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.CarModel;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.Car;

import com.chengxusheji.mapper.CarMapper;
@Service
public class CarService {

	@Resource CarMapper carMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加车辆记录*/
    public void addCar(Car car) throws Exception {
    	carMapper.addCar(car);
    }

    /*按照查询条件分页查询车辆记录*/
    public ArrayList<Car> queryCar(String carNo,CarModel carModelObj,String pinpai,String chexianriqi,UserInfo userObj,String addTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!carNo.equals("")) where = where + " and t_car.carNo like '%" + carNo + "%'";
    	if(null != carModelObj && carModelObj.getModelId()!= null && carModelObj.getModelId()!= 0)  where += " and t_car.carModelObj=" + carModelObj.getModelId();
    	if(!pinpai.equals("")) where = where + " and t_car.pinpai like '%" + pinpai + "%'";
    	if(!chexianriqi.equals("")) where = where + " and t_car.chexianriqi like '%" + chexianriqi + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_car.userObj='" + userObj.getUser_name() + "'";
    	if(!addTime.equals("")) where = where + " and t_car.addTime like '%" + addTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return carMapper.queryCar(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<Car> queryCar(String carNo,CarModel carModelObj,String pinpai,String chexianriqi,UserInfo userObj,String addTime) throws Exception  { 
     	String where = "where 1=1";
    	if(!carNo.equals("")) where = where + " and t_car.carNo like '%" + carNo + "%'";
    	if(null != carModelObj && carModelObj.getModelId()!= null && carModelObj.getModelId()!= 0)  where += " and t_car.carModelObj=" + carModelObj.getModelId();
    	if(!pinpai.equals("")) where = where + " and t_car.pinpai like '%" + pinpai + "%'";
    	if(!chexianriqi.equals("")) where = where + " and t_car.chexianriqi like '%" + chexianriqi + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_car.userObj='" + userObj.getUser_name() + "'";
    	if(!addTime.equals("")) where = where + " and t_car.addTime like '%" + addTime + "%'";
    	return carMapper.queryCarList(where);
    }

    /*查询所有车辆记录*/
    public ArrayList<Car> queryAllCar()  throws Exception {
        return carMapper.queryCarList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String carNo,CarModel carModelObj,String pinpai,String chexianriqi,UserInfo userObj,String addTime) throws Exception {
     	String where = "where 1=1";
    	if(!carNo.equals("")) where = where + " and t_car.carNo like '%" + carNo + "%'";
    	if(null != carModelObj && carModelObj.getModelId()!= null && carModelObj.getModelId()!= 0)  where += " and t_car.carModelObj=" + carModelObj.getModelId();
    	if(!pinpai.equals("")) where = where + " and t_car.pinpai like '%" + pinpai + "%'";
    	if(!chexianriqi.equals("")) where = where + " and t_car.chexianriqi like '%" + chexianriqi + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_car.userObj='" + userObj.getUser_name() + "'";
    	if(!addTime.equals("")) where = where + " and t_car.addTime like '%" + addTime + "%'";
        recordNumber = carMapper.queryCarCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取车辆记录*/
    public Car getCar(int carId) throws Exception  {
        Car car = carMapper.getCar(carId);
        return car;
    }

    /*更新车辆记录*/
    public void updateCar(Car car) throws Exception {
        carMapper.updateCar(car);
    }

    /*删除一条车辆记录*/
    public void deleteCar (int carId) throws Exception {
        carMapper.deleteCar(carId);
    }

    /*删除多条车辆信息*/
    public int deleteCars (String carIds) throws Exception {
    	String _carIds[] = carIds.split(",");
    	for(String _carId: _carIds) {
    		carMapper.deleteCar(Integer.parseInt(_carId));
    	}
    	return _carIds.length;
    }
}
