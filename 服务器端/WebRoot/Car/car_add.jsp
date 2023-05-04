<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/car.css" />
<div id="carAddDiv">
	<form id="carAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">车牌:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_carNo" name="car.carNo" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车型:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_carModelObj_modelId" name="car.carModelObj.modelId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">品牌:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_pinpai" name="car.pinpai" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车辆照片:</span>
			<span class="inputControl">
				<input id="carPhotoFile" name="carPhotoFile" type="file" size="50" />
			</span>
		</div>
		<div>
			<span class="label">油型:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_youxing" name="car.youxing" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">耗油量:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_haoyouliang" name="car.haoyouliang" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车险日期:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_chexianriqi" name="car.chexianriqi" />

			</span>

		</div>
		<div>
			<span class="label">总里程:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_zonglicheng" name="car.zonglicheng" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">车辆详情:</span>
			<span class="inputControl">
				<script name="car.carDesc" id="car_carDesc" type="text/plain"   style="width:750px;height:500px;"></script>
			</span>

		</div>
		<div>
			<span class="label">所属用户:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_userObj_user_name" name="car.userObj.user_name" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">登记时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="car_addTime" name="car.addTime" />

			</span>

		</div>
		<div class="operation">
			<a id="carAddButton" class="easyui-linkbutton">添加</a>
			<a id="carClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/Car/js/car_add.js"></script> 
