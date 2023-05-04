<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.Region" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<Region> regionList = (List<Region>)request.getAttribute("regionList");
    int currentPage =  (Integer)request.getAttribute("currentPage"); //当前页
    int totalPage =   (Integer)request.getAttribute("totalPage");  //一共多少页
    int recordNumber =   (Integer)request.getAttribute("recordNumber");  //一共多少记录
    String regionFloor = (String)request.getAttribute("regionFloor"); //所在楼层查询关键字
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>区域查询</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="row"> 
		<div class="col-md-9 wow fadeInDown" data-wow-duration="0.5s">
			<div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
			    	<li><a href="<%=basePath %>index.jsp">首页</a></li>
			    	<li role="presentation" class="active"><a href="#regionListPanel" aria-controls="regionListPanel" role="tab" data-toggle="tab">区域列表</a></li>
			    	<li role="presentation" ><a href="<%=basePath %>Region/region_frontAdd.jsp" style="display:none;">添加区域</a></li>
				</ul>
			  	<!-- Tab panes -->
			  	<div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="regionListPanel">
				    		<div class="row">
				    			<div class="col-md-12 top5">
				    				<div class="table-responsive">
				    				<table class="table table-condensed table-hover">
				    					<tr class="success bold"><td>序号</td><td>区域id</td><td>所在楼层</td><td>区域名称</td><td>操作</td></tr>
				    					<% 
				    						/*计算起始序号*/
				    	            		int startIndex = (currentPage -1) * 5;
				    	            		/*遍历记录*/
				    	            		for(int i=0;i<regionList.size();i++) {
					    	            		int currentIndex = startIndex + i + 1; //当前记录的序号
					    	            		Region region = regionList.get(i); //获取到区域对象
 										%>
 										<tr>
 											<td><%=currentIndex %></td>
 											<td><%=region.getRegionId() %></td>
 											<td><%=region.getRegionFloor() %></td>
 											<td><%=region.getRegionName() %></td>
 											<td>
 												<a href="<%=basePath  %>Region/<%=region.getRegionId() %>/frontshow"><i class="fa fa-info"></i>&nbsp;查看</a>&nbsp;
 												<a href="#" onclick="regionEdit('<%=region.getRegionId() %>');" style="display:none;"><i class="fa fa-pencil fa-fw"></i>编辑</a>&nbsp;
 												<a href="#" onclick="regionDelete('<%=region.getRegionId() %>');" style="display:none;"><i class="fa fa-trash-o fa-fw"></i>删除</a>
 											</td> 
 										</tr>
 										<%}%>
				    				</table>
				    				</div>
				    			</div>
				    		</div>

				    		<div class="row">
					            <div class="col-md-12">
						            <nav class="pull-left">
						                <ul class="pagination">
						                    <li><a href="#" onclick="GoToPage(<%=currentPage-1 %>,<%=totalPage %>);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						                     <%
						                    	int startPage = currentPage - 5;
						                    	int endPage = currentPage + 5;
						                    	if(startPage < 1) startPage=1;
						                    	if(endPage > totalPage) endPage = totalPage;
						                    	for(int i=startPage;i<=endPage;i++) {
						                    %>
						                    <li class="<%= currentPage==i?"active":"" %>"><a href="#"  onclick="GoToPage(<%=i %>,<%=totalPage %>);"><%=i %></a></li>
						                    <%  } %> 
						                    <li><a href="#" onclick="GoToPage(<%=currentPage+1 %>,<%=totalPage %>);"><span aria-hidden="true">&raquo;</span></a></li>
						                </ul>
						            </nav>
						            <div class="pull-right" style="line-height:75px;" >共有<%=recordNumber %>条记录，当前第 <%=currentPage %>/<%=totalPage %> 页</div>
					            </div>
				            </div> 
				    </div>
				</div>
			</div>
		</div>
	<div class="col-md-3 wow fadeInRight">
		<div class="page-header">
    		<h1>区域查询</h1>
		</div>
		<form name="regionQueryForm" id="regionQueryForm" action="<%=basePath %>Region/frontlist" class="mar_t15" method="post">
			<div class="form-group">
				<label for="regionFloor">所在楼层:</label>
				<input type="text" id="regionFloor" name="regionFloor" value="<%=regionFloor %>" class="form-control" placeholder="请输入所在楼层">
			</div>






            <input type=hidden name=currentPage value="<%=currentPage %>" />
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
	</div>

		</div>
	</div> 
<div id="regionEditDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><i class="fa fa-edit"></i>&nbsp;区域信息编辑</h4>
      </div>
      <div class="modal-body" style="height:450px; overflow: scroll;">
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
		</form> 
	    <style>#regionEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      	<button type="button" class="btn btn-primary" onclick="ajaxRegionModify();">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script>
var basePath = "<%=basePath%>";
/*跳转到查询结果的某页*/
function GoToPage(currentPage,totalPage) {
    if(currentPage==0) return;
    if(currentPage>totalPage) return;
    document.regionQueryForm.currentPage.value = currentPage;
    document.regionQueryForm.submit();
}

/*可以直接跳转到某页*/
function changepage(totalPage)
{
    var pageValue=document.regionQueryForm.pageValue.value;
    if(pageValue>totalPage) {
        alert('你输入的页码超出了总页数!');
        return ;
    }
    document.regionQueryForm.currentPage.value = pageValue;
    documentregionQueryForm.submit();
}

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
				$('#regionEditDialog').modal('show');
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*删除区域信息*/
function regionDelete(regionId) {
	if(confirm("确认删除这个记录")) {
		$.ajax({
			type : "POST",
			url : basePath + "Region/deletes",
			data : {
				regionIds : regionId,
			},
			success : function (obj) {
				if (obj.success) {
					alert("删除成功");
					$("#regionQueryForm").submit();
					//location.href= basePath + "Region/frontlist";
				}
				else 
					alert(obj.message);
			},
		});
	}
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

})
</script>
</body>
</html>

