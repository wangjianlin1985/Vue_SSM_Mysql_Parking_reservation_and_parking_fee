<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carModel.css" />
<div id="carModelAddDiv">
	<form id="carModelAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">车型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="carModel_modelName" name="carModel.modelName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="carModelAddButton" class="easyui-linkbutton">添加</a>
			<a id="carModelClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/CarModel/js/carModel_add.js"></script> 
