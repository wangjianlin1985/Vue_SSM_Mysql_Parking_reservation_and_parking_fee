<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carModel.css" />
<div id="carModel_editDiv">
	<form id="carModelEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">车型id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="carModel_modelId_edit" name="carModel.modelId" value="<%=request.getParameter("modelId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">车型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="carModel_modelName_edit" name="carModel.modelName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="carModelModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/CarModel/js/carModel_modify.js"></script> 
