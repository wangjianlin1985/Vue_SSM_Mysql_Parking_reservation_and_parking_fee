<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/region.css" />
<div id="regionAddDiv">
	<form id="regionAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">所在楼层:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="region_regionFloor" name="region.regionFloor" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">区域名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="region_regionName" name="region.regionName" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">区域说明:</span>
			<span class="inputControl">
				<textarea id="region_regionDesc" name="region.regionDesc" rows="6" cols="80"></textarea>

			</span>

		</div>
		<div class="operation">
			<a id="regionAddButton" class="easyui-linkbutton">添加</a>
			<a id="regionClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/Region/js/region_add.js"></script> 
