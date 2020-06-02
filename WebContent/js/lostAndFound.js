function GetRequest() {
	        var url = location.search; // 获取url中"?"符后的字串
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

showPage('', '', 1,GetRequest().index);
		// 获取标签栏的所有标签元素对象
		var tabs = document.getElementsByClassName('tab-head-div');
		// 获取标签栏的所有内容对象
		var divs = document.getElementsByClassName('tab-body-div');
		for (var i = 0; i < tabs.length; ++i) { // 遍历标签部分的元素对象
			tabs[i].onclick = function () { // 为标签元素对象添加鼠标滑过事件
				for (var i = 0; i < divs.length; ++i) { // 遍历标签栏的内容元素对象
					if (tabs[i] == this) { // 显示当前鼠标滑过的li元素
						divs[i].classList.add('current');
						tabs[i].classList.add('active');
					} else { // 隐藏其他li元素
						divs[i].classList.remove('current');
						tabs[i].classList.remove('active');
					}
				}
			};
		}
		
		function showPage(type, kind, pNo,index) {
			if(index == null || index == ""){
				index = "";
			}else if(GetRequest().index!="" && GetRequest().index!=null){
				index = GetRequest().index;
			}
			if($('#index').val() != "" && $('#index').val() != null){
				index = $('#index').val();
			}
			if(type == null || type==""  || type==null){
				if(GetRequest().eType=="寻人启事"){
					type = "寻人启事";
				}else if(GetRequest().eType=="寻物启事"){
					type = "寻物启事";
				}else if(GetRequest().eType=="失物招领" || GetRequest().eType==null){
					type = "失物招领";
				}
			}console.log(type+"--------");
			if(kind == null || kind == ""){
				kind = "所有";
			}
			var html = "";
			console.log(kind);
			$.ajax({
				url: "TableShow",
				dataType: "json",
				async: true,
				type: "POST",
				data: {
					"index": index,
					"eType": type,
					"eKinds": kind,
					"pNo": pNo
				},
				success: function (result) {
					$('#index').val(null);
					console.log("=============");
					console.dir(result);
					console.log("=============");
					var slide = `<div class="logo">
						<a href="#"><img src="img/logo.png" alt="logo"></a>
					</div>
					<div style="text-align: center; margin-bottom: 10px;">
						<div>
							<img id="head-img" style="width: 90px; height: 90px;border-radius: 90px;" src="img/signin.png"
								alt="" style="display:unset;">&nbsp;
						</div>
						<div class="login" id="logindiv" style="margin:20px auto; height:35px;"></div>						
					</div>
					<ul class="nav nav-sidebar" id="navSidebar" style="padding-left:5px;"]>
						<li><a href="index.html"><span
								class="glyphicon glyphicon-home"></span> &nbsp;首页</span></a></li>
								<li id='slide1'><a href="lostAndFound.html?eType=失物招领"><span
								class="glyphicon glyphicon-heart"></span> &nbsp;失物招领</a></li>
						<li id='slide2'><a href="lostAndFound.html?eType=寻物启事"><span
								class="glyphicon glyphicon-lock "></span> &nbsp;寻物启事</a></li>
								<li  id='slide3'><a href="lostAndFound.html?eType=寻人启事"><span
								class="glyphicon glyphicon-user"></span> &nbsp;寻人启事</a></li>
								<li style='cursor: pointer;'><a onclick="pubEvent()"><span
								class="glyphicon glyphicon-send"></span>&nbsp;免费发布</a></li>
						<li><a href="instructPage.html"><span
								class="glyphicon glyphicon-info-sign"></span>&nbsp; 帮助中心</a></li>
								<li><a href="contact.html"><span
								class="glyphicon glyphicon-comment"></span>&nbsp; 投诉建议</a></li>
					</ul>`;

					$('#nav').html(slide);
					//isLogin();
					var typeId = 1;
					if(type=="失物招领"){
						typeId = 1;
					}else if(type=="寻物启事"){
						typeId = 2;
					}else if(type=="寻人启事"){
						typeId = 3;
					}
					var activeSlide = "slide"+typeId;
					document.getElementById(activeSlide).className = 'active';
					

					console.dir(result);
					var e = result.event;

					console.dir(e);
					// 寻人启事和失物招领与寻物启事展示界面不同，不能分类
					var tips="";
					if(type!="寻人启事"){
						tips = ` <li role="presentation"  class="everykind" onclick="showPage('${type}','所有',1,'')" id='kind0'><a>所有</a></li>
							<li role="presentation"" class="everykind" onclick="showPage('${type}','校园卡',1,'')" id='kind1'><a>校园卡</a></li>
                            <li role="presentation" class="everykind" onclick="showPage('${type}','身份证',1,'')" id='kind2'><a>身份证</a></li>
                            <li role="presentation" class="everykind" onclick="showPage('${type}','书籍',1,'')" id='kind3'><a>书籍</a></li>
                            <li role="presentation" class="everykind" onclick="showPage('${type}','电子产品',1,'')" id='kind4'><a>电子产品</a></li>
                            <li role="presentation" class="everykind" onclick="showPage('${type}','其它',1,'')" id='kind5'><a>其它</a></li>`;
						$('#tips').html(tips);
						var kindId = 1;
						if(kind=="所有"){
							kindId = 0;
						}else if(kind=="校园卡"){
							kindId = 1;
						}else if(kind=="身份证"){
							kindId = 2;
						}else if(kind=="书籍"){
							kindId = 3;
						}else if(kind=="电子产品"){
							kindId = 4;
						}else if(kind=="其它"){
							kindId = 5;
						}
						var thisLi = '#kind' + kindId;
						console.log(thisLi);
						$(thisLi).css('backgroundColor',"#fdf4aa");
						/*document.getElementById(thisLi).className = "navthis";*/
					}else {
						$('#tips').html(tips);
					}

					var events = "";
					for (var i = 0; i < e.length; i++) {
						console.log(e.length);
						var every = e[i];
						
						events += `<div class="content">
                                    <div class="img">
                                    <img src=${every.eImage} alt=""
                                    	style="width:230px;height:120px;">
                                </div>
                                <div class="artile">
                                    <div class="conthead" style=" margin-left:20px">
                                        <h3 class="title"><a href="eventDetail.html?eId=${every.eId}">${every.eTitle}<a></h3>
                                        <div class="artilecont" >
                                            <a href="eventDetail.html?eId=${every.eId}"> 
                                            ${every.eDetail}
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="contfoot clearfix">
                                    <div class="contfootleft">
                                        <span>
                                            <i class="layui-icon layui-icon-list"></i>
                                            <a href="#">${every.ePlace}</a>
                                        </span>
                                        <span class="time">
                                            <i class="layui-icon layui-icon-console"></i>
                                            <a href="#">${format(every.eDate)}</a>
                                        </span>
                                    </div>
                                    <div class="contfootright"><a href="eventDetail.html?eId=${every.eId}">阅读原文</a></div>
                                </div>
                            </div>`;
					}
					$('#events').html(events);
					loadjscssfile('bootstrap-3.3.7-dist/css/bootstrap.min.css', "css");
					loadjscssfile('css/ljf.css', "css");
			        loadjscssfile('js/util.js', "js"); // 加载cssjs文件
			        loadjscssfile('layui/css/layui.css', "css"); 
			        loadjscssfile('css/mycss.css', "css"); 

					/* 分页栏 */
					var pageCount = result.page.totalPage;// 页面总数
					if (pNo == null || pNo < 1) {
						pNo = 1;
					} else if (pNo > pageCount) {
						pNo = pageCount;
					}
					
					var divPages = `<li id='liPre' class='disabled' style='cursor: pointer;opacity:0.5;'>
													<span 
													aria-hidden='true'
													onclick="showPage('${type}','${kind}',${pNo - 1})">&laquo;
											</span></li>`;
					for (var i = 0; i < pageCount; i++) {
						divPages += `<li><span 
													style='cursor: pointer;' 
													id='li${(i + 1)}' 
													onclick="showPage('${type}','${kind}',${i + 1})">
													${(i + 1)}
											</span></li>`;
					}
					divPages += `<li id='liLas' class='disabled' style='cursor: pointer;opacity:0.5;'>
									<span 
									aria-hidden='true' 
									onclick="showPage('${type}','${kind}',${pNo + 1})">&raquo;
								</span></li>`;

					$("#divPage").html(divPages);
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
