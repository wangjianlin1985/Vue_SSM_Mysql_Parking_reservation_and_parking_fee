<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/region.css" />
<div id="region_editDiv">
	<form id="regionEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">区域id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="region_regionId_edit" name="region.regionId" value="<%=request.getParameter("regionId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">所在楼层:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="region_regionFloor_edit" name="region.regionFloor" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">区域名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="region_regionName_edit" name="region.regionName" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">区域说明:</span>
			<span class="inputControl">
				<textarea id="region_regionDesc_edit" name="region.regionDesc" rows="8" cols="60"></textarea>

			</span>

		</div>
		<div class="operation">
			<a id="regionModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/Region/js/region_modify.js"></script> 
