<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/car.css" /> 

<div id="car_manage"></div>
<div id="car_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="car_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="car_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="car_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="car_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="car_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="carQueryForm" method="post">
			车牌：<input type="text" class="textbox" id="carNo" name="carNo" style="width:110px" />
			车型：<input class="textbox" type="text" id="carModelObj_modelId_query" name="carModelObj.modelId" style="width: auto"/>
			品牌：<input type="text" class="textbox" id="pinpai" name="pinpai" style="width:110px" />
			车险日期：<input type="text" id="chexianriqi" name="chexianriqi" class="easyui-datebox" editable="false" style="width:100px">
			所属用户：<input class="textbox" type="text" id="userObj_user_name_query" name="userObj.user_name" style="width: auto"/>
			登记时间：<input type="text" id="addTime" name="addTime" class="easyui-datebox" editable="false" style="width:100px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="car_manage_tool.search();">查询</a>
		</form>	
	</div>
</div>

<div id="carEditDiv">
	<form id="carEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">车辆id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_carId_edit" name="car.carId" style="width:200px" />
			</span>
		</div>
		<div>
			<span class="label">车牌:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_carNo_edit" name="car.carNo" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车型:</span>
			<span class="inputControl">
				<input class="textbox"  id="car_carModelObj_modelId_edit" name="car.carModelObj.modelId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">品牌:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_pinpai_edit" name="car.pinpai" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车辆照片:</span>
			<span class="inputControl">
				<img id="car_carPhotoImg" width="200px" border="0px"/><br/>
    			<input type="hidden" id="car_carPhoto" name="car.carPhoto"/>
				<input id="carPhotoFile" name="carPhotoFile" type="file" size="50" />
			</span>
		</div>
		<div>
			<span class="label">油型:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_youxing_edit" name="car.youxing" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">耗油量:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_haoyouliang_edit" name="car.haoyouliang" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车险日期:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_chexianriqi_edit" name="car.chexianriqi" />

			</span>

		</div>
		<div>
			<span class="label">总里程:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_zonglicheng_edit" name="car.zonglicheng" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车辆详情:</span>
			<span class="inputControl">
				<script name="car.carDesc" id="car_carDesc_edit" type="text/plain"   style="width:100%;height:500px;"></script>

			</span>

		</div>
		<div>
			<span class="label">所属用户:</span>
			<span class="inputControl">
				<input class="textbox"  id="car_userObj_user_name_edit" name="car.userObj.user_name" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">登记时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_addTime_edit" name="car.addTime" />

			</span>

		</div>
	</form>
</div>
<script>
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var car_carDesc_editor = UE.getEditor('car_carDesc_edit'); //车辆详情编辑器
</script>
<script type="text/javascript" src="Car/js/car_manage.js"></script> 
