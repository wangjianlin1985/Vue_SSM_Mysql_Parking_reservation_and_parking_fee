<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.Car" %>
<%@ page import="com.chengxusheji.po.CarModel" %>
<%@ page import="com.chengxusheji.po.UserInfo" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的carModelObj信息
    List<CarModel> carModelList = (List<CarModel>)request.getAttribute("carModelList");
    //获取所有的userObj信息
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    Car car = (Car)request.getAttribute("car");

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
  <TITLE>修改车辆信息</TITLE>
  <link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/animate.css" rel="stylesheet"> 
</head>
<body style="margin-top:70px;"> 
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="col-md-9 wow fadeInLeft">
	<ul class="breadcrumb">
  		<li><a href="<%=basePath %>index.jsp">首页</a></li>
  		<li class="active">车辆信息修改</li>
	</ul>
		<div class="row"> 
      	<form class="form-horizontal" name="carEditForm" id="carEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="car_carId_edit" class="col-md-3 text-right">车辆id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="car_carId_edit" name="car.carId" class="form-control" placeholder="请输入车辆id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="car_carNo_edit" class="col-md-3 text-right">车牌:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="car_carNo_edit" name="car.carNo" class="form-control" placeholder="请输入车牌">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_carModelObj_modelId_edit" class="col-md-3 text-right">车型:</label>
		  	 <div class="col-md-9">
			    <select id="car_carModelObj_modelId_edit" name="car.carModelObj.modelId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_pinpai_edit" class="col-md-3 text-right">品牌:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="car_pinpai_edit" name="car.pinpai" class="form-control" placeholder="请输入品牌">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_carPhoto_edit" class="col-md-3 text-right">车辆照片:</label>
		  	 <div class="col-md-9">
			    <img  class="img-responsive" id="car_carPhotoImg" border="0px"/><br/>
			    <input type="hidden" id="car_carPhoto" name="car.carPhoto"/>
			    <input id="carPhotoFile" name="carPhotoFile" type="file" size="50" />
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_youxing_edit" class="col-md-3 text-right">油型:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="car_youxing_edit" name="car.youxing" class="form-control" placeholder="请输入油型">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_haoyouliang_edit" class="col-md-3 text-right">耗油量:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="car_haoyouliang_edit" name="car.haoyouliang" class="form-control" placeholder="请输入耗油量">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_chexianriqi_edit" class="col-md-3 text-right">车险日期:</label>
		  	 <div class="col-md-9">
                <div class="input-group date car_chexianriqi_edit col-md-12" data-link-field="car_chexianriqi_edit" data-link-format="yyyy-mm-dd">
                    <input class="form-control" id="car_chexianriqi_edit" name="car.chexianriqi" size="16" type="text" value="" placeholder="请选择车险日期" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_zonglicheng_edit" class="col-md-3 text-right">总里程:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="car_zonglicheng_edit" name="car.zonglicheng" class="form-control" placeholder="请输入总里程">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_carDesc_edit" class="col-md-3 text-right">车辆详情:</label>
		  	 <div class="col-md-9">
			    <script name="car.carDesc" id="car_carDesc_edit" type="text/plain"   style="width:100%;height:500px;"></script>
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_userObj_user_name_edit" class="col-md-3 text-right">所属用户:</label>
		  	 <div class="col-md-9">
			    <select id="car_userObj_user_name_edit" name="car.userObj.user_name" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="car_addTime_edit" class="col-md-3 text-right">登记时间:</label>
		  	 <div class="col-md-9">
                <div class="input-group date car_addTime_edit col-md-12" data-link-field="car_addTime_edit">
                    <input class="form-control" id="car_addTime_edit" name="car.addTime" size="16" type="text" value="" placeholder="请选择登记时间" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
		  	 </div>
		  </div>
			  <div class="form-group">
			  	<span class="col-md-3""></span>
			  	<span onclick="ajaxCarModify();" class="btn btn-primary bottom5 top5">修改</span>
			  </div>
		</form> 
	    <style>#carEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
   </div>
</div>


<jsp:include page="../footer.jsp"></jsp:include>
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<script>
var car_carDesc_editor = UE.getEditor('car_carDesc_edit'); //车辆详情编辑框
var basePath = "<%=basePath%>";
/*弹出修改车辆界面并初始化数据*/
function carEdit(carId) {
  car_carDesc_editor.addListener("ready", function () {
    // editor准备好之后才可以使用 
    ajaxModifyQuery(carId);
  });
}
 function ajaxModifyQuery(carId) {
	$.ajax({
		url :  basePath + "Car/" + carId + "/update",
		type : "get",
		dataType: "json",
		success : function (car, response, status) {
			if (car) {
				$("#car_carId_edit").val(car.carId);
				$("#car_carNo_edit").val(car.carNo);
				$.ajax({
					url: basePath + "CarModel/listAll",
					type: "get",
					success: function(carModels,response,status) { 
						$("#car_carModelObj_modelId_edit").empty();
						var html="";
		        		$(carModels).each(function(i,carModel){
		        			html += "<option value='" + carModel.modelId + "'>" + carModel.modelName + "</option>";
		        		});
		        		$("#car_carModelObj_modelId_edit").html(html);
		        		$("#car_carModelObj_modelId_edit").val(car.carModelObjPri);
					}
				});
				$("#car_pinpai_edit").val(car.pinpai);
				$("#car_carPhoto").val(car.carPhoto);
				$("#car_carPhotoImg").attr("src", basePath +　car.carPhoto);
				$("#car_youxing_edit").val(car.youxing);
				$("#car_haoyouliang_edit").val(car.haoyouliang);
				$("#car_chexianriqi_edit").val(car.chexianriqi);
				$("#car_zonglicheng_edit").val(car.zonglicheng);
				car_carDesc_editor.setContent(car.carDesc, false);
				$.ajax({
					url: basePath + "UserInfo/listAll",
					type: "get",
					success: function(userInfos,response,status) { 
						$("#car_userObj_user_name_edit").empty();
						var html="";
		        		$(userInfos).each(function(i,userInfo){
		        			html += "<option value='" + userInfo.user_name + "'>" + userInfo.name + "</option>";
		        		});
		        		$("#car_userObj_user_name_edit").html(html);
		        		$("#car_userObj_user_name_edit").val(car.userObjPri);
					}
				});
				$("#car_addTime_edit").val(car.addTime);
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*ajax方式提交车辆信息表单给服务器端修改*/
function ajaxCarModify() {
	$.ajax({
		url :  basePath + "Car/" + $("#car_carId_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#carEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                location.reload(true);
                $("#carQueryForm").submit();
            }else{
                alert(obj.message);
            } 
		},
		processData: false,
		contentType: false,
	});
}

$(function(){
        /*小屏幕导航点击关闭菜单*/
        $('.navbar-collapse > a').click(function(){
            $('.navbar-collapse').collapse('hide');
        });
        new WOW().init();
    /*车险日期组件*/
    $('.car_chexianriqi_edit').datetimepicker({
    	language:  'zh-CN',  //语言
    	format: 'yyyy-mm-dd',
    	minView: 2,
    	weekStart: 1,
    	todayBtn:  1,
    	autoclose: 1,
    	minuteStep: 1,
    	todayHighlight: 1,
    	startView: 2,
    	forceParse: 0
    });
    /*登记时间组件*/
    $('.car_addTime_edit').datetimepicker({
    	language:  'zh-CN',  //语言
    	format: 'yyyy-mm-dd hh:ii:ss',
    	weekStart: 1,
    	todayBtn:  1,
    	autoclose: 1,
    	minuteStep: 1,
    	todayHighlight: 1,
    	startView: 2,
    	forceParse: 0
    });
    carEdit("<%=request.getParameter("carId")%>");
 })
 </script> 
</body>
</html>

