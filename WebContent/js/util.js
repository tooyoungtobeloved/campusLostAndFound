function format(time){
    var d = new Date(time);
    var year = ''+d.getFullYear(); //获取年 
    var month = ''+(d.getMonth()+1);//获取月  
    var day = ''+d.getDate(); //获取当日
    var hour = ''+d.getHours()//获取小时
    var min = ''+d.getMinutes()//获取分钟
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    if (hour.length < 2) hour = '0' + hour;
    if (min.length < 2) min = '0' + min;
    var result = year+"-"
                 +month+"-"
                 +day+" "
                 +hour+":"
                 +min;
    return result;
 }

//在js中回调js或css文件
function loadjscssfile(filename, filetype) {
    if (filetype == "js") { //判定文件类型
        var fileref = document.createElement('script');//创建标签
        fileref.setAttribute("type", "text/javascript");//定义属性type的值为text/javascript
        fileref.setAttribute("src", filename);//文件的地址
    }
    else if (filetype == "css") { //判定文件类型
        var fileref = document.createElement("link");
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", filename);
    }
    if (typeof fileref != "undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}
isLogin();
//判断是否登录状态
function isLogin(){
	var html = "";
	var state = false;
	$.ajax({
		url : "GetSession2",
		dataType : "json",
		async : false,//设置为false就可以操作ajax外层的变量
		type : "POST",
		success : function(result){
			console.dir(result);
			if(result!=null){
				state = true;
				$('#head-img').attr("src",result.uImage);
				html +=`<span><a>欢迎您</a>   <a href='selfInfo.html?stuId=${result.stuId}'>${result.uName}</a></span>
					<span><a href='' onclick='logout()'>注销</a></span>`;
			}else {
				html += `<div style='margin: 0 auto;'><a href='register.html' style='font-size: 18px;color:#000;'>注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						<a onclick="javascript:$('#myModal').modal('show');" 
							style='font-size: 18px;color:#000;cursor: pointer;' >登录</a></div>;`;
			}
			$('#logindiv').html(html);
			} 
	});
	
	loadjscssfile("bootstrap-3.3.7-dist/js/bootstrap.min.js", "js");
		
	console.log(state);return state;
}

//注销
function logout(){
	 $.ajax({
			url : "RemoveSession",
			dataType : "json",
			async : true,
			complete:function(){
				alert('注销成功！');
				isLogin();
		    } 
		});
}

layui.use(['form'], function(){});

//登录
function login() {
	var flag = false;
	var stuId = $('#stuId').val();
	var pwd = $('#uPwd').val();
	console.log(stuId);
	$.ajax({
		url : "UserLogin",
		dataType : "json",
		async : false,
		type : "POST",
		data : {
			"stuId" : stuId,
			"uPwd" : pwd
		},
		success : function(result) {
			flag = result;
		}
	});
	if(flag==true){
		alert('登录成功！');
		location.reload();
	}else {
		layer.msg('账号或密码输入有误，请重新输入！');
		$('#uPwd').val(null);
	}
}

//发布事件
function pubEvent(){
	if(isLogin()==true){
		window.location.href='pubInfo.html';
	}else if(isLogin()==false){
		var r = confirm('发布事件需要登录，是否现在登录！');
		if(r==true){
			$("#myModal").modal('show');
		}
	}
}
