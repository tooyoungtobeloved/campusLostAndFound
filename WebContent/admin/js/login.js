
//点击验证码重新绘制验证码
	function reloadCode() {
		$('#check').attr("src", "../img.jsp?t=" + ((new Date().getTime())));
	}
	
	//判断验证码正确性
	function checkMyCode() {
		var checkCode = $('#code').val();
		var flag = false;
		$.ajax({
			url : "/lostandfound/CheckMyCode",
			dataType : "Json",
			async : false,
			type : "POST",
			data : {
				"checkCode" : checkCode
			},
			success : function(result) {
				if (result == true) {
					flag = true;
				}
			}
		});
		if(!flag){
			layer.msg('验证码错误！!');
      		return false;
		}
		return true;
	}
	
	
layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    // $(".loginBody .seraph").click(function(){
    //     layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
    //         time:5000
    //     });
    // })

    //登录按钮
    form.on("submit(login)",function(data){
    	if(!checkMyCode()){
    		layer.msg('验证码错误！!');
    		reloadCode();
    		return false;
    	}else {
    		$(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
            var that = this;
            // setTimeout(function(){
            //     window.location.href = "/layuicms2.0";
            // },1000);
            var admid = $("#userName").val();
            var adpwd = $("#password").val();
            var author = $("input[name='author']:checked").val();
            $.ajax({
                type: "post",
                url: "/lostandfound/AdminLogin",
                data: {"id":admid,"pwd":adpwd,"author":author},
                dataType: "json",
                success: function (response) {
                    console.log(typeof response.status)
                    console.log(that)
                    console.log(this)
                    if(response.status == "200"){
                        setTimeout(function(){
                            window.location.href = "index.html";
                            // alert("登陆成功")
                        },1000);
                    }else if(response.status == "500"){
                        layer.open({
                            title:"管理员不存在"
                            ,content:"请检查用户名和密码"
                        })
                        //移除disable状态
                        $(that).text("登录").removeAttr("disabled").removeClass("layui-disabled")
                    }
                    else if(response.status == "404"){
                        layer.open({
                            title:"管理员密码错误"
                            ,content:"请检查密码"
                        })
                        
                        //移除disable状态
                        $(that).text("登录").removeAttr("disabled").removeClass("layui-disabled")

                    }
                }
                    
                
                
            });
            return false;
    	}
        
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
