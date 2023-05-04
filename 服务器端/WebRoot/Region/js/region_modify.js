$(function () {
	$.ajax({
		url : "Region/" + $("#region_regionId_edit").val() + "/update",
		type : "get",
		data : {
			//regionId : $("#region_regionId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (region, response, status) {
			$.messager.progress("close");
			if (region) { 
				$("#region_regionId_edit").val(region.regionId);
				$("#region_regionId_edit").validatebox({
					required : true,
					missingMessage : "请输入区域id",
					editable: false
				});
				$("#region_regionFloor_edit").val(region.regionFloor);
				$("#region_regionFloor_edit").validatebox({
					required : true,
					missingMessage : "请输入所在楼层",
				});
				$("#region_regionName_edit").val(region.regionName);
				$("#region_regionName_edit").validatebox({
					required : true,
					missingMessage : "请输入区域名称",
				});
				$("#region_regionDesc_edit").val(region.regionDesc);
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#regionModifyButton").click(function(){ 
		if ($("#regionEditForm").form("validate")) {
			$("#regionEditForm").form({
			    url:"Region/" +  $("#region_regionId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#regionEditForm").form("validate"))  {
	                	$.messager.progress({
							text : "正在提交数据中...",
						});
	                	return true;
	                } else {
	                    return false;
	                }
			    },
			    success:function(data){
			    	$.messager.progress("close");
                	var obj = jQuery.parseJSON(data);
                    if(obj.success){
                        $.messager.alert("消息","信息修改成功！");
                        $(".messager-window").css("z-index",10000);
                        //location.href="frontlist";
                    }else{
                        $.messager.alert("消息",obj.message);
                        $(".messager-window").css("z-index",10000);
                    } 
			    }
			});
			//提交表单
			$("#regionEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});
