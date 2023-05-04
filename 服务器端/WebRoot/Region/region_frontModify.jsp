<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.Region" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Region region = (Region)request.getAttribute("region");

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
  <TITLE>修改区域信息</TITLE>
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
  		<li class="active">区域信息修改</li>
	</ul>
		<div class="row"> 
      	<form class="form-horizontal" name="regionEditForm" id="regionEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="region_regionId_edit" class="col-md-3 text-right">区域id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="region_regionId_edit" name="region.regionId" class="form-control" placeholder="请输入区域id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="region_regionFloor_edit" class="col-md-3 text-right">所在楼层:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="region_regionFloor_edit" name="region.regionFloor" class="form-control" placeholder="请输入所在楼层">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="region_regionName_edit" class="col-md-3 text-right">区域名称:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="region_regionName_edit" name="region.regionName" class="form-control" placeholder="请输入区域名称">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="region_regionDesc_edit" class="col-md-3 text-right">区域说明:</label>
		  	 <div class="col-md-9">
			    <textarea id="region_regionDesc_edit" name="region.regionDesc" rows="8" class="form-control" placeholder="请输入区域说明"></textarea>
			 </div>
		  </div>
			  <div class="form-group">
			  	<span class="col-md-3""></span>
			  	<span onclick="ajaxRegionModify();" class="btn btn-primary bottom5 top5">修改</span>
			  </div>
		</form> 
	    <style>#regionEditForm .form-group {margin-bottom:5px;}  </style>
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
<script>
var basePath = "<%=basePath%>";
/*弹出修改区域界面并初始化数据*/
function regionEdit(regionId) {
	$.ajax({
		url :  basePath + "Region/" + regionId + "/update",
		type : "get",
		dataType: "json",
		success : function (region, response, status) {
			if (region) {
				$("#region_regionId_edit").val(region.regionId);
				$("#region_regionFloor_edit").val(region.regionFloor);
				$("#region_regionName_edit").val(region.regionName);
				$("#region_regionDesc_edit").val(region.regionDesc);
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*ajax方式提交区域信息表单给服务器端修改*/
function ajaxRegionModify() {
	$.ajax({
		url :  basePath + "Region/" + $("#region_regionId_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#regionEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                location.reload(true);
                $("#regionQueryForm").submit();
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
    regionEdit("<%=request.getParameter("regionId")%>");
 })
 </script> 
</body>
</html>

