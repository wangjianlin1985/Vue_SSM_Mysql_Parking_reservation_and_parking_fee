<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/car.css" />
<div id="car_editDiv">
	<form id="carEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">车辆id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_carId_edit" name="car.carId" value="<%=request.getParameter("carId") %>" style="width:200px" />
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
				<script id="car_carDesc_edit" name="car.carDesc" type="text/plain"   style="width:750px;height:500px;"></script>

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
		<div class="operation">
			<a id="carModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/Car/js/car_modify.js"></script> 
