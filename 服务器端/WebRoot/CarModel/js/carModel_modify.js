$(function () {
	$.ajax({
		url : "CarModel/" + $("#carModel_modelId_edit").val() + "/update",
		type : "get",
		data : {
			//modelId : $("#carModel_modelId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (carModel, response, status) {
			$.messager.progress("close");
			if (carModel) { 
				$("#carModel_modelId_edit").val(carModel.modelId);
				$("#carModel_modelId_edit").validatebox({
					required : true,
					missingMessage : "请输入车型id",
					editable: false
				});
				$("#carModel_modelName_edit").val(carModel.modelName);
				$("#carModel_modelName_edit").validatebox({
					required : true,
					missingMessage : "请输入车型名称",
				});
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#carModelModifyButton").click(function(){ 
		if ($("#carModelEditForm").form("validate")) {
			$("#carModelEditForm").form({
			    url:"CarModel/" +  $("#carModel_modelId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#carModelEditForm").form("validate"))  {
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
			$("#carModelEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});
