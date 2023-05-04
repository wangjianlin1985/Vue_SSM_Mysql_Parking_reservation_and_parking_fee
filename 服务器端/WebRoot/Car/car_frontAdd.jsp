<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.CarModel" %>
<%@ page import="com.chengxusheji.po.UserInfo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>车辆添加</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="col-md-12 wow fadeInLeft">
		<ul class="breadcrumb">
  			<li><a href="<%=basePath %>index.jsp">首页</a></li>
  			<li><a href="<%=basePath %>Car/frontlist">车辆管理</a></li>
  			<li class="active">添加车辆</li>
		</ul>
		<div class="row">
			<div class="col-md-10">
		      	<form class="form-horizontal" name="carAddForm" id="carAddForm" enctype="multipart/form-data" method="post"  class="mar_t15">
				  <div class="form-group">
				  	 <label for="car_carNo" class="col-md-2 text-right">车牌:</label>
				  	 <div class="col-md-8">
					    <input type="text" id="car_carNo" name="car.carNo" class="form-control" placeholder="请输入车牌">
					 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_carModelObj_modelId" class="col-md-2 text-right">车型:</label>
				  	 <div class="col-md-8">
					    <select id="car_carModelObj_modelId" name="car.carModelObj.modelId" class="form-control">
					    </select>
				  	 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_pinpai" class="col-md-2 text-right">品牌:</label>
				  	 <div class="col-md-8">
					    <input type="text" id="car_pinpai" name="car.pinpai" class="form-control" placeholder="请输入品牌">
					 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_carPhoto" class="col-md-2 text-right">车辆照片:</label>
				  	 <div class="col-md-8">
					    <img  class="img-responsive" id="car_carPhotoImg" border="0px"/><br/>
					    <input type="hidden" id="car_carPhoto" name="car.carPhoto"/>
					    <input id="carPhotoFile" name="carPhotoFile" type="file" size="50" />
				  	 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_youxing" class="col-md-2 text-right">油型:</label>
				  	 <div class="col-md-8">
					    <input type="text" id="car_youxing" name="car.youxing" class="form-control" placeholder="请输入油型">
					 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_haoyouliang" class="col-md-2 text-right">耗油量:</label>
				  	 <div class="col-md-8">
					    <input type="text" id="car_haoyouliang" name="car.haoyouliang" class="form-control" placeholder="请输入耗油量">
					 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_chexianriqiDiv" class="col-md-2 text-right">车险日期:</label>
				  	 <div class="col-md-8">
		                <div id="car_chexianriqiDiv" class="input-group date car_chexianriqi col-md-12" data-link-field="car_chexianriqi" data-link-format="yyyy-mm-dd">
		                    <input class="form-control" id="car_chexianriqi" name="car.chexianriqi" size="16" type="text" value="" placeholder="请选择车险日期" readonly>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
				  	 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_zonglicheng" class="col-md-2 text-right">总里程:</label>
				  	 <div class="col-md-8">
					    <input type="text" id="car_zonglicheng" name="car.zonglicheng" class="form-control" placeholder="请输入总里程">
					 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_carDesc" class="col-md-2 text-right">车辆详情:</label>
				  	 <div class="col-md-8">
							    <textarea name="car.carDesc" id="car_carDesc" style="width:100%;height:500px;"></textarea>
					 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_userObj_user_name" class="col-md-2 text-right">所属用户:</label>
				  	 <div class="col-md-8">
					    <select id="car_userObj_user_name" name="car.userObj.user_name" class="form-control">
					    </select>
				  	 </div>
				  </div>
				  <div class="form-group">
				  	 <label for="car_addTimeDiv" class="col-md-2 text-right">登记时间:</label>
				  	 <div class="col-md-8">
		                <div id="car_addTimeDiv" class="input-group date car_addTime col-md-12" data-link-field="car_addTime">
		                    <input class="form-control" id="car_addTime" name="car.addTime" size="16" type="text" value="" placeholder="请选择登记时间" readonly>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
				  	 </div>
				  </div>
		          <div class="form-group">
		             <span class="col-md-2""></span>
		             <span onclick="ajaxCarAdd();" class="btn btn-primary bottom5 top5">添加</span>
		          </div> 
		          <style>#carAddForm .form-group {margin:5px;}  </style>  
				</form> 
			</div>
			<div class="col-md-2"></div> 
	    </div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<script>
//实例化编辑器
var car_carDesc_editor = UE.getEditor('car_carDesc'); //车辆详情编辑器
var basePath = "<%=basePath%>";
	//提交添加车辆信息
	function ajaxCarAdd() { 
		//提交之前先验证表单
		$("#carAddForm").data('bootstrapValidator').validate();
		if(!$("#carAddForm").data('bootstrapValidator').isValid()){
			return;
		}
		jQuery.ajax({
			type : "post",
			url : basePath + "Car/add",
			dataType : "json" , 
			data: new FormData($("#carAddForm")[0]),
			success : function(obj) {
				if(obj.success){ 
					alert("保存成功！");
					$("#carAddForm").find("input").val("");
					$("#carAddForm").find("textarea").val("");
					car_carDesc_editor.setContent("");
				} else {
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
	//验证车辆添加表单字段
	$('#carAddForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			"car.carNo": {
				validators: {
					notEmpty: {
						message: "车牌不能为空",
					}
				}
			},
			"car.pinpai": {
				validators: {
					notEmpty: {
						message: "品牌不能为空",
					}
				}
			},
			"car.youxing": {
				validators: {
					notEmpty: {
						message: "油型不能为空",
					}
				}
			},
			"car.haoyouliang": {
				validators: {
					notEmpty: {
						message: "耗油量不能为空",
					}
				}
			},
			"car.chexianriqi": {
				validators: {
					notEmpty: {
						message: "车险日期不能为空",
					}
				}
			},
			"car.zonglicheng": {
				validators: {
					notEmpty: {
						message: "总里程不能为空",
					}
				}
			},
			"car.addTime": {
				validators: {
					notEmpty: {
						message: "登记时间不能为空",
					}
				}
			},
		}
	}); 
	//初始化车型下拉框值 
	$.ajax({
		url: basePath + "CarModel/listAll",
		type: "get",
		success: function(carModels,response,status) { 
			$("#car_carModelObj_modelId").empty();
			var html="";
    		$(carModels).each(function(i,carModel){
    			html += "<option value='" + carModel.modelId + "'>" + carModel.modelName + "</option>";
    		});
    		$("#car_carModelObj_modelId").html(html);
    	}
	});
	//初始化所属用户下拉框值 
	$.ajax({
		url: basePath + "UserInfo/listAll",
		type: "get",
		success: function(userInfos,response,status) { 
			$("#car_userObj_user_name").empty();
			var html="";
    		$(userInfos).each(function(i,userInfo){
    			html += "<option value='" + userInfo.user_name + "'>" + userInfo.name + "</option>";
    		});
    		$("#car_userObj_user_name").html(html);
    	}
	});
	//车险日期组件
	$('#car_chexianriqiDiv').datetimepicker({
		language:  'zh-CN',  //显示语言
		format: 'yyyy-mm-dd',
		minView: 2,
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		minuteStep: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	}).on('hide',function(e) {
		//下面这行代码解决日期组件改变日期后不验证的问题
		$('#carAddForm').data('bootstrapValidator').updateStatus('car.chexianriqi', 'NOT_VALIDATED',null).validateField('car.chexianriqi');
	});
	//登记时间组件
	$('#car_addTimeDiv').datetimepicker({
		language:  'zh-CN',  //显示语言
		format: 'yyyy-mm-dd hh:ii:ss',
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		minuteStep: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	}).on('hide',function(e) {
		//下面这行代码解决日期组件改变日期后不验证的问题
		$('#carAddForm').data('bootstrapValidator').updateStatus('car.addTime', 'NOT_VALIDATED',null).validateField('car.addTime');
	});
})
</script>
</body>
</html>
