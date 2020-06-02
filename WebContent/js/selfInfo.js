selfInfo(1);
	$("#uploadHead").click(function () { $("#modheadbut").click(); });
	
	 function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }
	
	 layui.use(['form'], function(){});
	 
		function selfInfo(pNo){
			var stuId = GetRequest().stuId;
			if(stuId==null || stuId==""){
				window.location.href="index.html";
			}
			$.ajax({
				url : "SelfInfo",
				dataType : "json",
				async : true,
				type : "POST",
				data:{
					"stuId":stuId,
					"pNo":pNo
				},
				success : function(result){
					console.log("--------");
					console.dir(result);
					console.log("--------");
					
					var uInfo = result.uInfo;
					//显示个人信息
					var selfTotal = `<img title="点击修改头像" class="userImage" src="${uInfo.uImage}" id="head" title="点击修改头像"><br />
						<div id='selftext'>
						学号：<span id="stuId">${uInfo.stuId}</span><br /> 
						用户名 ：<span id="uName">${uInfo.uName}</span><br />
						邮箱：<span id="uMail">${uInfo.uMail==null?"":uInfo.uMail}</span><br /> 
						性别 ：<span id="uSex">${uInfo.uSex==null?"":uInfo.uSex}</span><br />
						QQ ：<span id="uQQ">${uInfo.uQQ==null?"":uInfo.uQQ}</span><br /> <br />
						
						<div class="udescr">
						个性签名：
							<p id="udescr">${uInfo.uDesc==null?"":uInfo.uDesc}</p>
						</div>`;
						
						var modMine = "";//两个修改个人信息按钮
						var modHead = "";
						
						//判断是否是本人的个人信息
						if(uInfo.stuId == result.mystuId){
							modMine += `<button class="modify butt" id="modifydata" onclick="modifydata()">
							修改资料</button>
						<button class="modify butt" id="modifypwd" onclick="modifypwd()">
							修改密码</button>`;
							
							//修改头像
							modHead = `<div id="modifyhead">
								<button type="button"
										class="btn btn-primary btn-lg" 
										onclick="javascript:$('#modheaddiv').modal('show');" 
										id="openheadmodal">
								  修改头像
								</button>
						</div>`;
						/* var img = document.getElementById('showHead');
						img.src = uInfo.uImage; */
							$("#showHead").attr("src",uInfo.uImage);//把我的头像放进模态框里的img
						}
						selfTotal += modMine;
						selfTotal += `</div>
							<!-- 头像下的昵称 -->
							<div id="headN">
								<p class="" id="headName">${uInfo.uName}</p>
							</div>`;
							selfTotal += modHead
					
					$('.selftotal').html(selfTotal);
					
					//把个人信息加入到修改资料的input里
					$('#modstuId').val(uInfo.stuId);
					$('#moduName').val(uInfo.uName);
					$('#moduMail').val(uInfo.uMail);
					$('#moduSex').val(uInfo.uSex);
					$('#moduQQ').val(uInfo.uQQ);
					$('#moduDesc').val(uInfo.uDesc);
					
					//显示我的发布事件：
					var myevents = result.myEvents;
					var myPub = "";
					if(myevents.length<1){
						$('.mypub').html(`空空如也，现在<a href="pubInfo.html">发布</a>?`);
					}else {
					for(var i=0;i<myevents.length;i++){
						console.log(myevents.length);
						
							var every = myevents[i];//每个事件
							
							//事件状态
							var status = every.eStatus;
							if(status==0){
								status = "正在审核";
							}else if(status==1){
								status = "正在进行";
							}else if(status==2){
								status = "已结束";
							}
							
							myPub += `<div class="events">
								<div class="pubcontent">
								<img src="${every.eImage}" alt="" id="pubimg">
								<div class="etopic">${every.eTitle}</div>
								<div class="etopic" id="etopicup"
									onclick="window.open('eventDetail.html?eId=${every.eId}')"></div>
									<div style="position: absolute;right: -60px;top: -20px;" id="modEvent">`;
									
							//这里是修改我的事件状态div，非本人信息不显示
							var modifyMine = "";//这里面是修改事件按钮
							//如果正在进行全部显示,如果已结束不显示结束事件按钮
							if(uInfo.stuId == result.mystuId){
								if(status == "正在审核" || status == "正在进行"){
									modifyMine = `<button type="button" class="btn btn-default btn-lg" onclick='modState(${every.eId},2)'>
				                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span> 结束事件
				                    </button>
				                    <button type="button" class="btn btn-default btn-lg" onclick='modState(${every.eId},0)'>
				                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除事件
				                    </button>`;
								}else if(status == "已结束"){
									modifyMine = `<button type="button" class="btn btn-default btn-lg" onclick='modState(${every.eId},0)'>
				                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除事件
				                    </button>`;
								}
							}
			                
							myPub += modifyMine+`</div><div class="estate">${status}</div>
								<div class="eaddition">
								类型：<i class="pubtype">${every.eType}</i><br /> 发布时间：<i class="pubtime">${format(every.ePubDate)}</i></i>
							</div>
						</div>
					</div>`;
						}
						
					
					
					// 分页栏
					var page = result.page;//拿到接受的page对象
					
					var pageCount = page.totalPage;//页面总数
					if (pNo == null || pNo < 1) {
						pNo = 1;
					} else if (pNo > pageCount) {
						pNo = pageCount;
					}
					
					var divPages = `<ul class="pagination" id="divPage"><li id='liPre' class='disabled' style='cursor: pointer;opacity:0.5;'>
													<span 
													aria-hidden='true'
													onclick='selfInfo(${pNo - 1})'>&laquo;
											</span></li>`;
					for (var i = 0; i < pageCount; i++) {
						divPages += `<li><span 
													style='cursor: pointer;' 
													id='li${(i + 1)}' 
													onclick='selfInfo(${i + 1})'>
													${(i + 1)}
											</span></li>`;
					}
					divPages += `<li id='liLas' class='disabled' style='cursor: pointer;opacity:0.5;'>
									<span 
									aria-hidden='true' 
									onclick='selfInfo(${pNo + 1})'>&raquo;
								</span></li></ul>`;

								myPub += divPages;
					}
								$('#selfevent').html(myPub);
					$('#li' + pNo).css('background-color', 'rgb(238,238,238)');
					if (pNo != 1) {
						$('#liPre').removeClass("disabled");
						$('#liPre').css('opacity', 1);
					}
					if (pNo != pageCount) {
						$('#liLas').removeClass("disabled");
						$('#liLas').css('opacity', 1);
					}
				} 
			});
		}
		
		//改变事件状态,2是结束事件，其他是删除事件，默认为1正在进行
		function modState(eId,operate){
			$.ajax({
				url : "ModifyStateOfEvent",
				dataType : "json",
				async : true,
				type : "POST",
				data:{
					"eId":eId,
					"operate":operate
				},
				success : function(result){
					console.log("--------");
					console.dir(result);
					console.log("--------");
					if(result==true){
						alert('操作成功');
						location.reload();
					}else {
						alert('操作失败');
					}
				} 
			});
		}
		
		
		//修改密码
		function modifyPwd(){
			if(!checkPwd()){
				return false;
			}else{
				var oldpwd = $('#oldpwd').val();
				var newpwd = $('#newpwd1').val();
				console.log(oldpwd);
				$.ajax({
					url : "ModifyPwd",
					dataType : "json",
					async : true,
					type : "POST",
					data:{
						"oldpwd":oldpwd,
						"newpwd":newpwd
					},
					success : function(result){
							alert("密码修改成功,请重新登录！");
							logout();
						}
				});
			}
		}
	
		
		//总校验
		function checkPwd(){
			if(checkOldPwd()==false){
				return false;
			}
			
			if(checkNewPwd1()==false){
				return false;
			}
			if(checkNewPwd2()==false){
				return false;
			}
			return true;
		}
		//检查旧密码是否正确
		function checkOldPwd() {
			var flag = false;
			var stuId = GetRequest().stuId;
			var oldpwd = $('#oldpwd').val();
			$.ajax({
				url : "CheckOldPwd",
				dataType : "Json",
				async : false,
				type : "POST",
				data : {
					"oldpwd" : oldpwd,
					"stuId" : stuId
				},
				success : function(result) {
					if (result == true) {//返回值false表示不存在已有学号，可以注册
						flag = true;
					}
				}
			});
			if (flag == false || oldpwd == "") {
				layer.msg('旧密码错误！');
			}else {
				layer.msg('旧密码验证通过！');
			}
			return flag;
		}
		//判断确认密码是否一致
		function checkNewPwd1() {
			value = $('#newpwd1').val();
			reg = /^[\w\\.]{6,16}$/;//包括A-Za-z0-9和小数点
			if (reg.test(value) == false) {
				layer.msg('请输入6-16位字母或数字的新密码！');
				return false;
			}else {
				layer.msg('新密码1验证通过！');
			}
			return true;
		}
		function checkNewPwd2() {
			if ($('#newpwd1').val() != $('#newpwd2').val()) {
				layer.msg('密码不一致！');
				return false;
			}else {
				layer.msg('新密码2验证通过！');
			}
			return true;
		}
		
		//判断性别
		function checkSex(){
			var value = $('#moduSex').val();
			console.log(value);
			if(value!="男" && value!="女"){
				layer.msg('请输入正确的性别！');
				return false;
			}return true;
		}
		function checkQQ(){
			var value = $('#moduQQ').val();
			var reg = /^[\d]{5,12}$/;//5-12位的qq
			if (reg.test(value) == false){
				layer.msg('请输入5-12位qq号！');
				return false;
			}return true;
		}
		
		//验证修改的资料
		function checkInfo(){
			if(!checkuName()){
				return false;
			}
			if(!checkMail()){
				return false;
			}
			if(!checkSex()){
				return false;
			}
			if(!checkQQ()){
				return false;
			}
			if(!checkDescr()){
				return false;
			}
			return true;
		}
		//修改资料
		function modifyInfo(){
			if(!checkInfo()){
				//layer.msg('请重新验证资料的正确性！');
				return false;
			}else{
				var modstuId = $('#modstuId').val();
				var moduName = $('#moduName').val();
				var moduMail = $('#moduMail').val();
				var moduSex = $('#moduSex').val();
				var moduQQ = $('#moduQQ').val();
				var moduDesc = $('#moduDesc').val();
				$.ajax({
					url : "ModifyInfo",
					dataType : "json",
					async : true,
					type : "POST",
					data:{
						"modstuId":modstuId,
						"moduName":moduName,
						"moduMail":moduMail,
						"moduSex":moduSex,
						"moduQQ":moduQQ,
						"moduDesc":moduDesc
					},
					success : function(result){
						console.log("--------");
						console.dir(result);
						console.log("--------");
						if(result==1){
							alert("资料修改成功！");
							location.reload();
						}else if(result==0){
							layer.msg('修改失败，请重新修改！');
						}
					} 
				});
			}
			
		} 
		
		//个性签名验证
		function checkDescr(){
			var moduDesc = $('#moduDesc').val();
			if(moduDesc.length>56){
				layer.msg("个性签名不能超过56个字数！");
				return false;
			}
			return true;
		}
		
		//邮箱验证
		function checkMail(){
			var moduMail = $('#moduMail').val();
			var reg = /^[A-Za-z0-9]{5,15}@([A-Za-z0-9\-]{2,6}\.)+[A-Za-z]{2,6}$/;
			if(!reg.test(moduMail)){
				layer.msg("请输入正确的邮箱地址！");
				return false;
			}return true;
		}
		
		//检测用户名
		function checkuName() {
			var flag = false;
			var moduName = $('#moduName').val();
			$.ajax({
				url : "CheckuName",
				dataType : "Json",
				async : false,
				type : "POST",
				data : {
					"uName" : moduName
				},
				success : function(result) {
					if (result == false) {//返回值false表示不存在，可以注册
						flag = true;
					}
				}
			});
			var reg = /^[0-9a-zA-Z\u4E00-\u9FA5]{2,10}$/;//包括中文、字母和数字
			if (flag == false && moduName != "") {
				layer.msg('用户已存在！');
				return false;
			} else if (flag == true && !reg.test(moduName)) {
				layer.msg("请输入2-10位包括中文、字母和数字的用户名");
				return false;
			} 
			return flag;
		}
		
		//修改头像modifyMyHead
		function modifyMyHead(){
			var formData = new FormData(document.getElementById("modifyMyHead"));
			console.log(formData.get("modheadbut")); 
			$.ajax({
				url : "UpLoadHead",
				type : "POST",
				dataType : "json",
				contentType:false,
				async : true,
				processData:false,
				data : formData,
				success : function(result){
					if(result==true){
						alert("头像修改成功！");
						location.reload();
					}else {
						alert("修改失败，请重新检查文件大小和类型");
					}
				}
			}); 
			
		}
		//注销用户
		function logout(){
			$.ajax({
				url : "RemoveSession",
				dataType : "json",
				async : true,
				complete : function(){
					window.location.href="index.html";
				} 
			});
		}
		
		if (typeof FileReader == 'undefined') {
			document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
			document.getElementById("modheadbut").setAttribute("disabled",
					"disabled");
		}
		//选择图片，马上预览
		function xmTanUploadImg(obj) {
			var file = obj.files[0];
			console.log(obj);
			console.log(file);
			console.log("file.size = " + file.size);
			var reader = new FileReader();
			reader.onloadstart = function(e) {
				console.log("开始读取....");
			}
			reader.onprogress = function(e) {
				console.log("正在读取中....");
			}
			reader.onabort = function(e) {
				console.log("中断读取....");
			}
			reader.onerror = function(e) {
				console.log("读取异常....");
			}
			reader.onload = function(e) {
				console.log("成功读取....");
				var img = document.getElementById("showHead");
				img.src = e.target.result;
				//或者 img.src = this.result;  //e.target == this
			}
			reader.readAsDataURL(file)
		}

		//修改资料按钮
		function modifydata() {
			locate();
			$("#modpwd").css("display","none");
			$("#modifydiv").css("display","block");
			$("#modifydiv").css("height","700px");
			$("#moddata").css("display","block");
		}
		//修改密码按钮
		function modifypwd() {
			locate();
			$("#moddata").css("display","none");
			$("#modifydiv").css("display","block");
			$("#modifydiv").css("height","500px");
			$("#modpwd").css("display","block");
		}

		//点击按钮修改资料盒子消失
		function close1() {
			$("#moddata").css("display","none");
			$("#modifydiv").css("display","none");
			$("#modpwd").css("display","none");
		}

		// 修改资料详情
		function locate() {
			var a = document.getElementById("modifydiv");
			var Height = document.documentElement.clientHeight;//取得浏览器页面可视区域的宽度
			var Width = document.documentElement.clientWidth;//取得浏览器页面可视区域的宽度
			var gao1 = a.offsetHeight;
			var gao2 = a.offsetWidth;
			var Sgao1 = (Height - gao1) / 2 -350+ "px";
			var Sgao2 = (Width - gao2) / 2 -300+ "px";
			a.style.top = Sgao1;
			a.style.left = Sgao2;
		}