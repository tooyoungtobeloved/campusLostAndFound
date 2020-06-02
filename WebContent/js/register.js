//点击验证码重新绘制验证码
	function reloadCode() {
		$('#check').attr("src", "img.jsp?t=" + ((new Date().getTime())));
	}
	
	layui.use(['form'], function(){
		  var form = layui.form;
		  
		  	form.verify({
				stuId: function(value){
				    var reg = /^[\d]{11}$/;//任意11位的数字
			      	if(reg.test(value)==false){
			        	return '请输入11位学号';
			      	}
				    if(checkStuId()==false){
				    	return '学号已存在，请重新输入！';
				    }
				},
		  	
			  	uName: function(value){
			  		var reg = /^[0-9a-zA-Z\u4E00-\u9FA5]{2,10}$/;//包括2-10位的中文、字母和数字
			      	if(reg.test(value)==false){
			        	return '请输入2-10位的中文、字母或者数字的用户名';
			      	}
				},
				pwd1: function(value){
					var reg = /^[\w\\.]{6,16}$/;//包括A-Za-z0-9和小数点
			      	if(reg.test(value)==false){
			        	return '请输入6-16位的大小写字母或者小数点组成的密码';
			      	}
				},
				pwdAgain: function(value){
					if(value != $('#pwd1').val()){
						return '密码不一致';
					}
				}
			  });
	});
		//注册
		function register() {
			var flag = false;
			if(check()){
				var stuId = $('#stuId').val();
				var uName = $('#uName').val();
				var uPwd = $('#pwd1').val();
				$.ajax({
					url : "Register",
					dataType : "json",
					async : false,
					type : "post",
					data : {
						"stuId" : stuId,
						"uPwd" : uPwd,
						"uName" : uName
					},
					success : function(result) {
						if (result == true) {//如果注册成功,自动登录
							flag = true;
							
						}
					}
				});
				if(flag){
					alert('注册成功！');
					window.location.href="index.html";
				}
			}else{
				reloadCode();
			}
		}
		
		function check() {
			 //验证学号
			if (!checkStuId()) {
				return false;
			}
			//验证用户名格式
			if (!checkuName()) {
				return false;
			}
			//验证密码格式
			if (!checkPwd1()) {
				return false;
			}
			//确认密码
			if (!checkPwd2()) {
				return false;
			} 
			if(!checkMyCode()){
				return false;
			}
			return true;
		}


		//判断验证码正确性
		function checkMyCode() {
			var checkCode = $('#checkCode').val();
			var flag = false;
			$.ajax({
				url : "CheckMyCode",
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
				reloadCode();
	      		return false;
			}
			return true;
		}

		//判断学号是否存在与格式
		function checkStuId() {
			var stuId = $('#stuId').val();
			var reg = /^[\d]{11}$/;//任意11位的数字
	      	if(reg.test(stuId)==false){
	      		layer.msg('请输入11位学号!');
	      		return false;
	      	}else {
	      		var flag = false;
				$.ajax({
					url : "CheckStuId",
					dataType : "Json",
					async : false,
					type : "POST",
					data : {
						"stuId" : stuId
					},
					success : function(result) {
						if (result == false) {//返回值false表示不存在已有学号，可以注册
							flag = true;
						}
					}
				});
				if(flag==false){
					layer.msg('学号已存在，请重新输入!');
					return false;
				}
	      	}return true;
		}

		//判断用户名是否存在
		function checkuName() {
			var uName = $('#uName').val();
			var reg = /^[0-9a-zA-Z\u4E00-\u9FA5]{2,10}$/;//包括2-10位的中文、字母和数字
	      	if(reg.test(uName)==false){
	      		layer.msg('请输入2-10位的中文、字母或者数字的用户名!');
	        	return false;
	      	}else {
	      		var flag = false;
				$.ajax({
					url : "CheckuName",
					dataType : "Json",
					async : false,
					type : "POST",
					data : {
						"uName" : uName
					},
					success : function(result) {
						if (result == false) {//返回值false表示不存在，可以注册
							flag = true;
						}
					}
				});
				if(flag == false){
					layer.msg('用户名已存在，请重新输入!');
					return false;
				}
	      	}return true;
		}

		
		
		//验证密码格式
		function checkPwd1() {
			var reg = /^[0-9a-zA-Z\\.]{6,16}$/;//包括A-Za-z0-9和小数点
			var value = $('#pwd1').val();
			console.log(value);
			if (reg.test(value)==false) {
				layer.msg('请输入6-16位的大小写字母或者小数点组成的密码!');
				return false;
			}
			return true;
		}
		function checkPwd2() {
			if ($('#pwd1').val() != $('#pwd2').val()) {
				layer.msg('密码不一致!');
				return false;
			}
			return true;
		}
		//背景特效
		!
		function() {
		    function n(n, e, t) {
		        return n.getAttribute(e) || t
		    }
		    function e(n) {
		        return document.getElementsByTagName(n)
		    }
		    function t() {
		        var t = e("script"),
		        o = t.length,
		        i = t[o - 1];
		        return {
		            l: o,
		            z: n(i, "zIndex", -1),
		            o: n(i, "opacity", .5),
		            c: n(i, "color", "0,0,0"),
		            n: n(i, "count", 99)
		        }
		    }
		    function o() {
		        a = m.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
		        c = m.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
		    }
		    function i() {
		        r.clearRect(0, 0, a, c);
		        var n, e, t, o, m, l;
		        s.forEach(function(i, x) {
		            for (i.x += i.xa, i.y += i.ya, i.xa *= i.x > a || i.x < 0 ? -1 : 1, i.ya *= i.y > c || i.y < 0 ? -1 : 1, r.fillRect(i.x - .5, i.y - .5, 1, 1), e = x + 1; e < u.length; e++) n = u[e],
		            null !== n.x && null !== n.y && (o = i.x - n.x, m = i.y - n.y, l = o * o + m * m, l < n.max && (n === y && l >= n.max / 2 && (i.x -= .03 * o, i.y -= .03 * m), t = (n.max - l) / n.max, r.beginPath(), r.lineWidth = t / 2, r.strokeStyle = "rgba(" + d.c + "," + (t + .2) + ")", r.moveTo(i.x, i.y), r.lineTo(n.x, n.y), r.stroke()))
		        }),
		        x(i)
		    }
		    var a, c, u, m = document.createElement("canvas"),
		    d = t(),
		    l = "c_n" + d.l,
		    r = m.getContext("2d"),
		    x = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
		    function(n) {
		        window.setTimeout(n, 1e3 / 45)
		    },
		    w = Math.random,
		    y = {
		        x: null,
		        y: null,
		        max: 2e4
		    };
		    m.id = l,
		    m.style.cssText = "position:fixed;top:0;left:0;z-index:" + d.z + ";opacity:" + d.o,
		    e("body")[0].appendChild(m),
		    o(),
		    window.onresize = o,
		    window.onmousemove = function(n) {
		        n = n || window.event,
		        y.x = n.clientX,
		        y.y = n.clientY
		    },
		    window.onmouseout = function() {
		        y.x = null,
		        y.y = null
		    };
		    for (var s = [], f = 0; d.n > f; f++) {
		        var h = w() * a,
		        g = w() * c,
		        v = 2 * w() - 1,
		        p = 2 * w() - 1;
		        s.push({
		            x: h,
		            y: g,
		            xa: v,
		            ya: p,
		            max: 6e3
		        })
		    }
		    u = s.concat([y]),
		    setTimeout(function() {
		        i()
		    },
		    100)
		} ();