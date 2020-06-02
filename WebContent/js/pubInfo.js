isLogin();



function justify() {
	var type = $("input[name='types']:checked").val();
	console.log(type);
	if (type == "寻人启事") {
		$("#kinds").attr("disabled", true);
		$("#kinds").css("opacity", "0.4");
	}else {
		$("#kinds").attr("disabled", false);
		$("#kinds").css("opacity", "1");
	}
	var contactPerson = $('#contact').val();
	if (contactPerson == "管理员") {
		$("#tel").attr("disabled", true);
		$("#tel").css("opacity", "0.4");
		$("#contactName").attr("disabled", true);
		$("#contactName").css("opacity", "0.4");
	}else {
		$("#tel").attr("disabled", false);
		$("#tel").css("opacity", "1");
		$("#contactName").attr("disabled", false);
		$("#contactName").css("opacity", "1");
	}
}



//判断是否登录状态
function isLogin() {
	$.ajax({
		url : "GetSession2",
		dataType : "json",
		async : true,//设置为false就可以操作ajax外层的变量
		type : "POST",
		success : function(result) {
			if (result == null) {
				//showTips( "正在下载，请稍候", 50, 3 );
				alert("请先登录再进行操作！");
				window.location.href = "index.html";
			}
		}
	});
}

function pubInfo() {
	if (checkForm() == true) {
		var radio = document.getElementsByName("eType");
		var selectvalue = null; //  selectvalue为radio中选中的值
		for (var i = 0; i < radio.length; i++) {
			if (radio[i].checked == true) {
				selectvalue = radio[i].value;
				break;
			}
		}
		var file = $('#file')[0].files[0];

		var formData = new FormData(document.getElementById("pubForm"));
		console.log(formData.get("title"));
		$.ajax({
			url : "PubInfo",
			type : "POST",
			dataType : "json",
			contentType : false,
			async : true,
			processData : false,
			data : formData,
			success : function(result) {
				window.location.href = "index.html";
			}
		});
		alert("发布成功！");
	} else {
		console.log("请重新检查数据");
	}
}
//详细描述字数限制展示
function cal1() {
	var length = $("#pdetail").val().length;
	$(".th1 i").text(length);
}
function cal2() {
	var length = $("#detail").val().length;
	$(".th2 i").text(length);
}

//上传图片
function changepic() {
	var reads = new FileReader();
	f = document.getElementById('file').files[0];
	reads.readAsDataURL(f);
	reads.onload = function(e) {
		document.getElementById('eimage').src = this.result;
		$("#eimage").css("display", "block");
	};
}

//判断非空
sessionStorage.setItem('3', 'false');
function checktTxtActivityTitle() {
	var txtActivityTitle = $("#txtActivityTitle").val();
	if ($.trim(txtActivityTitle) == "") {
		return "▲请填写活动名称！";
	}
	return "";
}

layui.use([ 'form' ], function() {
});

//检查标题规范
function checkTitle() {
	var value = $('#title').val();
	console.log("----------" + value);
	if (value == null || value == "") {
		layer.msg('请输入标题！');
		return false;
	} else if (value.length > 20) {
		layer.msg('标题不能超过20字！');
		return false;
	}
	return true;
}

//检查物品种类非空
function checkKinds() {
	var type = $("input[name='types']:checked").val();

	if (type != '寻人启事' && $('#kinds').val() == 0) {
		layer.msg('请选择物品种类！');
		return false;
	}
	return true;
}

//验证地点非空
function checkPlace() {
	var place = $("#place").val();
	var pdetail = $('#pdetail').val();
	console.log(place);
	if (place == 0) {
		layer.msg('请选择发生地点！');
		return false;
	}
	if (pdetail.length < 1 || pdetail.length > 15) {
		layer.msg('详细地址限定1-15个字之内！');
		return false;
	}
	return true;
}

//发生时间验证
function checkDate() {
	var date = $('#edate').val();
	//2019-12-31T23:59
	var reg = /^[\d]{4}[-][\d]{2}[-][\d]{2}[T][\d]{2}[:][\d]{2}$/;
	if (!reg.test(date)) {
		layer.msg('请选择正确的时间！');
		return false;
	}
	return true;
}

//发生时间验证
function checkDetail() {
	var detail = $('#detail').val();
	console.log("-=-=-= " + detail);
	if (detail.length < 1 || detail.length > 128) {
		layer.msg('详情描述限定1-128个字之内！');
		return false;
	}
	return true;
}

//联系方式验证
function checkContactPerson() {
	var contactName = $('#contactName').val();
	if ($('#contact').val() == "本人" && contactName.length < 1) {
		layer.msg('联系人姓名不能为空！');
		return false;
	}
	return true;
}
//联系电话验证
function checkTel() {
	var tel = $('#tel').val();
	reg = /^[1][35789][\d]{9}$/;
	if ($('#contact').val() == "本人" && !reg.test(tel)) {
		layer.msg('请输入11位正确的手机号！');
		return false;
	}
	return true;
}

function checkForm() {
	//标题验证
	if (!checkTitle()) {
		return false;
	}
	//类型验证
	if (!checkKinds()) {
		return false;
	}
	//具体地点空验证
	if (!checkPlace()) {
		return false;
	}
	//日期验证
	if (!checkDate()) {
		return false;
	}
	//详细描述验证
	if (!checkDetail()) {
		return false;
	}
	//联系人验证
	if (!checkContactPerson()) {
		return false;
	}
	//手机号码验证
	if (!checkTel()) {
		return false;
	}
	return true;
}
