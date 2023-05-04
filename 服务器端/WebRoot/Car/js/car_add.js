$(function () {
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	UE.delEditor('car_carDesc');
	var car_carDesc_editor = UE.getEditor('car_carDesc'); //车辆详情编辑框
	$("#car_carNo").validatebox({
		required : true, 
		missingMessage : '请输入车牌',
	});

	$("#car_carModelObj_modelId").combobox({
	    url:'CarModel/listAll',
	    valueField: "modelId",
	    textField: "modelName",
	    panelHeight: "auto",
        editable: false, //不允许手动输入
        required : true,
        onLoadSuccess: function () { //数据加载完毕事件
            var data = $("#car_carModelObj_modelId").combobox("getData"); 
            if (data.length > 0) {
                $("#car_carModelObj_modelId").combobox("select", data[0].modelId);
            }
        }
	});
	$("#car_pinpai").validatebox({
		required : true, 
		missingMessage : '请输入品牌',
	});

	$("#car_youxing").validatebox({
		required : true, 
		missingMessage : '请输入油型',
	});

	$("#car_haoyouliang").validatebox({
		required : true, 
		missingMessage : '请输入耗油量',
	});

	$("#car_chexianriqi").datebox({
	    required : true, 
	    showSeconds: true,
	    editable: false
	});

	$("#car_zonglicheng").validatebox({
		required : true, 
		missingMessage : '请输入总里程',
	});

	$("#car_userObj_user_name").combobox({
	    url:'UserInfo/listAll',
	    valueField: "user_name",
	    textField: "name",
	    panelHeight: "auto",
        editable: false, //不允许手动输入
        required : true,
        onLoadSuccess: function () { //数据加载完毕事件
            var data = $("#car_userObj_user_name").combobox("getData"); 
            if (data.length > 0) {
                $("#car_userObj_user_name").combobox("select", data[0].user_name);
            }
        }
	});
	$("#car_addTime").datetimebox({
	    required : true, 
	    showSeconds: true,
	    editable: false
	});

	//单击添加按钮
	$("#carAddButton").click(function () {
		//验证表单 
		if(!$("#carAddForm").form("validate")) {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		} else {
			$("#carAddForm").form({
			    url:"Car/add",
			    onSubmit: function(){
					if($("#carAddForm").form("validate"))  { 
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
                    //此处data={"Success":true}是字符串
                	var obj = jQuery.parseJSON(data); 
                    if(obj.success){ 
                        $.messager.alert("消息","保存成功！");
                        $(".messager-window").css("z-index",10000);
                        $("#carAddForm").form("clear");
                        car_carDesc_editor.setContent("");
                    }else{
                        $.messager.alert("消息",obj.message);
                        $(".messager-window").css("z-index",10000);
                    }
			    }
			});
			//提交表单
			$("#carAddForm").submit();
		}
	});

	//单击清空按钮
	$("#carClearButton").click(function () { 
		$("#carAddForm").form("clear"); 
	});
});
