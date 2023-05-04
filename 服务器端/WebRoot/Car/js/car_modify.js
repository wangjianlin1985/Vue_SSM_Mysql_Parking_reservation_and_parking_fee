$(function () {
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	UE.delEditor('car_carDesc_edit');
	var car_carDesc_edit = UE.getEditor('car_carDesc_edit'); //车辆详情编辑器
	car_carDesc_edit.addListener("ready", function () {
		 // editor准备好之后才可以使用 
		 ajaxModifyQuery();
	}); 
  function ajaxModifyQuery() {	
	$.ajax({
		url : "Car/" + $("#car_carId_edit").val() + "/update",
		type : "get",
		data : {
			//carId : $("#car_carId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (car, response, status) {
			$.messager.progress("close");
			if (car) { 
				$("#car_carId_edit").val(car.carId);
				$("#car_carId_edit").validatebox({
					required : true,
					missingMessage : "请输入车辆id",
					editable: false
				});
				$("#car_carNo_edit").val(car.carNo);
				$("#car_carNo_edit").validatebox({
					required : true,
					missingMessage : "请输入车牌",
				});
				$("#car_carModelObj_modelId_edit").combobox({
					url:"CarModel/listAll",
					valueField:"modelId",
					textField:"modelName",
					panelHeight: "auto",
					editable: false, //不允许手动输入 
					onLoadSuccess: function () { //数据加载完毕事件
						$("#car_carModelObj_modelId_edit").combobox("select", car.carModelObjPri);
						//var data = $("#car_carModelObj_modelId_edit").combobox("getData"); 
						//if (data.length > 0) {
							//$("#car_carModelObj_modelId_edit").combobox("select", data[0].modelId);
						//}
					}
				});
				$("#car_pinpai_edit").val(car.pinpai);
				$("#car_pinpai_edit").validatebox({
					required : true,
					missingMessage : "请输入品牌",
				});
				$("#car_carPhoto").val(car.carPhoto);
				$("#car_carPhotoImg").attr("src", car.carPhoto);
				$("#car_youxing_edit").val(car.youxing);
				$("#car_youxing_edit").validatebox({
					required : true,
					missingMessage : "请输入油型",
				});
				$("#car_haoyouliang_edit").val(car.haoyouliang);
				$("#car_haoyouliang_edit").validatebox({
					required : true,
					missingMessage : "请输入耗油量",
				});
				$("#car_chexianriqi_edit").datebox({
					value: car.chexianriqi,
					required: true,
					showSeconds: true,
				});
				$("#car_zonglicheng_edit").val(car.zonglicheng);
				$("#car_zonglicheng_edit").validatebox({
					required : true,
					missingMessage : "请输入总里程",
				});
				car_carDesc_edit.setContent(car.carDesc);
				$("#car_userObj_user_name_edit").combobox({
					url:"UserInfo/listAll",
					valueField:"user_name",
					textField:"name",
					panelHeight: "auto",
					editable: false, //不允许手动输入 
					onLoadSuccess: function () { //数据加载完毕事件
						$("#car_userObj_user_name_edit").combobox("select", car.userObjPri);
						//var data = $("#car_userObj_user_name_edit").combobox("getData"); 
						//if (data.length > 0) {
							//$("#car_userObj_user_name_edit").combobox("select", data[0].user_name);
						//}
					}
				});
				$("#car_addTime_edit").datetimebox({
					value: car.addTime,
					required: true,
					showSeconds: true,
				});
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
				$(".messager-window").css("z-index",10000);
			}
		}
	});

  }

	$("#carModifyButton").click(function(){ 
		if ($("#carEditForm").form("validate")) {
			$("#carEditForm").form({
			    url:"Car/" +  $("#car_carId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#carEditForm").form("validate"))  {
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
			$("#carEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});
