window.onload=getDetail(1);
    

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
    
    	function getDetail(pNo){
    		var eId = GetRequest().eId;
    		console.log(eId);
    			$.ajax({
        			url : "EventDetail",
        			dataType : "json",
        			async : true,
        			type : "POST",
        			data:{
        				"eId":eId,
        				"pNo":pNo
        			},
        			success : function(result){
        				console.log("--------");
        				console.dir(result);
        				console.log("--------");
        				
        				//事件详情主体
        				var html = "";
        				var title = "";
        				title += `<div class="head">
                        <h1 class="msgtitle">${result.events.eTitle}</h1>
                    </div>
                    <div class="tabs">
                       <div class="i1"> <i class="glyphicon glyphicon-user"></i>&nbsp;发帖人:&nbsp;<a href='selfInfo.html?stuId=${result.events.stuId}'><span class="user">${result.events.uName}</span></a></div>
                      <div class="i2">  <i class="glyphicon glyphicon-dashboard"></i>&nbsp;时间:&nbsp;<span class="pubtime">${format(result.events.eDate)}</span></div>
                      <div class="i3">  <i class="glyphicon glyphicon-map-marker"></i>&nbsp;地点:&nbsp;<span class="place">${result.events.ePlace}</span></div>
                     <div class="i4">   <i class="glyphicon glyphicon-earphone"></i>&nbsp;联系方式:&nbsp;<span class="contact">${result.events.eContact}</span></div>
                    </div>`; 
        				
                            html += `<div role="alert" class="maincontent">${result.events.eDetail}
                    </div>
                    <div class="pubimg">
                        <img src="${result.events.eImage}" alt="事件图片">
                    </div>`;
        				$('.title').html(title);
        				$('#content').html(html);
        				
        				
        				var total1 = `<strong class="total">${result.page.totalCount}</strong><span>评论</span>`;
        				$(".total1").html(total1);
        				
        				//评论页,发布评论不在这里
        				var marks = result.marks;
        				var comments = "";
        				for(var i=0;i<marks.length;i++){
        					var mark = marks[i];
        					var headPic = mark.uImage;
        					
        					comments += `<div class="commentdetail">
                            <div class="commenleft">
                                <div class="visitorimg">
                                    <a href="selfInfo.html?stuId=${mark.stuId}"><img src="${headPic}" alt="${mark.uName}的头像"></a>
                                </div>
                            </div>
                            <div class="commentmain">
                                <div class="username"><a href="selfInfo.html?stuId=${mark.stuId}"><strong>${mark.uName}</strong></a></div>
                                <div class="main">
                                    <p>${mark.markDetail}</p>
                                    <span class="time">${format(mark.markTime)}</span>
                                </div>
                            </div>
                        </div>`;
        				}
        				$('.commentlist').html(comments);
        				
        				
        				
        				var pageCount = result.page.totalPage;//页面总数
        				if (pNo == null || pNo < 1) {
    						pNo = 1;
    					} else if (pNo > pageCount) {
    						pNo = pageCount;
    					}
        				
        				/* 分页栏 */
						var divPages = `<ul class="pagination"><li id='liPre' class='disabled' style='cursor: pointer;opacity:0.5;'>
														<span 
														aria-hidden='true'
														onclick='getDetail(${pNo-1})'>&laquo;
												</span></li>`;
						for (var i = 0; i < pageCount; i++) {
							divPages += `<li><span 
														style='cursor: pointer;' 
														id='li${(i + 1)}' 
														onclick='getDetail(${i + 1})'>
														${(i + 1)}
												</span></li>`;
						}
						divPages += `<li id='liLas' class='disabled' style='cursor: pointer;opacity:0.5;'>
										<span 
										aria-hidden='true' 
										onclick='getDetail(${pNo + 1})'>&raquo;
									</span></li></ul>`;
						
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
						
						//发布评论区
						var sendcomment = `<div class="userimg">
	                        <img src="${result.u.uImage}" alt="${result.u.uName}的头像">
		                    </div>
		                    <textarea name="msg" id="inputmsg" cols="80" rows="5"
		                        placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。最多两百字" onclick="canComment()"></textarea>

		                    <div class="submitmsg clearfix">
		                        <span class="warn" >你还没有评论</span>
		                        <button  class="submsg btn btn-primary" onclick="sendComment()">发送评论</button>
		                    </div>`;
		                    $('.sendcomment').html(sendcomment);
		                    
		                    //感谢信区
		                    var mail = result.thanks;
		                    var thanks = "";
		                    
		                    for(var i = 0;i<mail.length;i++){
		                    	console.log(mail.tId);
		                    	thanks += `<div><li class="rli"><img src="img/thanks.jpg"><p class="rcontent"  onclick='noteDetail(${mail[i].tId})'><a href="#">${mail[i].tTitle}</a></p></li></div>`;
		                    }
		                    $('#thanks').html(thanks);
		                    
		                    loadjscssfile('css/detail.css', "css"); 
						
        			} 
        		});
    	} 
    	
    	//显示感谢信
    	function noteDetail(noteId){
    		$.ajax({
    			url : "NoteDetail",
    			dataType : "json",
    			async : true,
    			type : "POST",
    			data:{
    				"noteId":noteId
    			},
    			success : function(result){
    				console.log("--------");
    				console.dir(result);
    				console.log("--------");
    				var html = "";
    				var title = "";
    				console.dir(result);
    				html = `<h1 style="text-align: center;font-style: inherit;">${result.thanks.tTitle}</h1>
    		      		<p style="line-height: 60px;text-indent:2em;font-size: 22px;">${result.thanks.tDetail}</p>
    		      		<span class="glyphicon glyphicon-time"></span>发布时间:${format(result.thanks.tDate)} `;
    				title=`<button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    				       <h4 class="modal-title" id="myModalLabel">感谢信详情</h4>`;
    				$('#showNote').html(html);
    				$('#beforeDetail').html(title);
    			} 
    		});$('#noteModal').modal('show');
    	}
    	
    	//判断登录状态
    	function canComment(){
    		console.log(1111111);
    		if(isLogin()==false){
    			var r = confirm("评论需要进行登录，是否登录？");
    			if(r==true){
    				$('#myModal').modal('show');
    			}
    		}
    	}
    	
    	function sendComment(){
    		if(isLogin()!=null && isLogin()!=""){
    		var eId = GetRequest().eId;
    		var markDetail = $('#inputmsg').val();
    		if(markDetail.length<1){
    			alert("评论不能为空");
    		}else if(markDetail.length>200){
    			alert("评论字数不能超过200字");
    		}else{
    			console.log(markDetail);
        		//判断是否登录状态
        			console.log(markDetail);
        			if(markDetail!=null){//判断是否评论区为空
        				$.ajax({
        	    			url : "SendComment",
        	    			dataType : "json",
        	    			async : true,
        	    			type : "POST",
        	    			data:{
        	    				"eId":eId,
        	    				"markDetail":markDetail
        	    			},
        	    			success : function(result){
        	    				console.log("--------");
        	    				console.dir(result);
        	    				console.log("--------");
        	    				$('#inputmsg').val(null);
        	    				getDetail(1);
        	    			} 
        	    		});
        			}
        		}
    		}else if(isLogin()==false) {
    			var r = confirm("评论需要进行登录，是否登录？");
    			if(r==true){
    				$('#myModal').modal('show');
    			}
    		}
    	}
    	isLogin();
    	function isLogin(){
    		var state = false;
    		var html = "";
    		$.ajax({
    			url : "GetSession2",
    			dataType : "json",
    			async : false,//设置为false就可以操作ajax外层的变量
    			type : "POST",
    			success : function(result){
    				console.log(result);
    				if(result!=null){
    					state = true;
    					html += `<div class="rightt1"><img id="head-img" style="width: 90px; height: 90px;border-radius: 90px;"
								src="${result.uImage}" alt="" style="display:unset;">&nbsp;</div>
						<div class="login" id="logindiv"
							style="margin: 20px auto; height: 35px;"><span><a>欢迎您</a>   <a href='selfInfo.html?stuId=${result.stuId}'>${result.uName}</a></span>
					<span><a href='' onclick='logout()'>注销</a></span></div>`;
    					$('.righttop').css("backgroundColor","rgba(0,0,0,0)");
    					
    				}else {
    					html += `<div class="rightt1"> <p>登录寻找代号007,寻物启事，失物招领，寻人启事，节约时间，享受便捷服务</p></div>
              <button  type="button" onclick="" class="btn"><a href="javascript:$('#myModal').modal('show');">用户登录</a></button>`;
    				}$('.righttop').html(html);
    			} 
    		});
    		
    		return state;
    	}