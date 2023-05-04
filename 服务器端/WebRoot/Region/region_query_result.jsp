<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/region.css" /> 

<div id="region_manage"></div>
<div id="region_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="region_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="region_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="region_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="region_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="region_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="regionQueryForm" method="post">
			所在楼层：<input type="text" class="textbox" id="regionFloor" name="regionFloor" style="width:110px" />
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="region_manage_tool.search();">查询</a>
		</form>	
	</div>
</div>

<div id="regionEditDiv">
	<form id="regionEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">区域id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="region_regionId_edit" name="region.regionId" style="width:200px" />
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
	</form>
</div>
<script type="text/javascript" src="Region/js/region_manage.js"></script> 
