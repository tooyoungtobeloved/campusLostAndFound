function noteDetail(type,noteId){
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
				if(type==1){//检测为公告
					html = `<h1 style="text-align: center;font-style: inherit;">${result.notes.noteTitle}</h1>
			      		<p style="line-height: 60px;text-indent:2em;font-size: 22px;">${result.notes.noteContent}</p>
			      		<span class="glyphicon glyphicon-time"></span>发布时间:${format(result.notes.notePubDate)} `;
					title=`<button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					       <h4 class="modal-title" id="myModalLabel">公告详情</h4>`;
						
				}else if(type==2){
					console.dir(result);
					html = `<h1 style="text-align: center;font-style: inherit;">${result.thanks.tTitle}</h1>
			      		<p style="line-height: 60px;text-indent:2em;font-size: 22px;">${result.thanks.tDetail}</p>
			      		<span class="glyphicon glyphicon-time"></span>发布时间:${format(result.thanks.tDate)} `;
					title=`<button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					       <h4 class="modal-title" id="myModalLabel">感谢信详情</h4>`;
				}$('#showNote').html(html);
				$('#beforeDetail').html(title);
			} 
		});$('#noteModal').modal('show');
	}

function pubMail(){
	$('#pubMail').modal('show');
}

function pubMyMail(){
	var title = $('#recipient-name').val();
	var detail = $('#message-text').val();
	if(title.length<1 || detail.length<1){
		alert("标题或内容不能为空！");
		return false;
	}
	$.ajax({
		url : "PubThanksMail",
		dataType : "json",
		async : true,
		type : "POST",
		data:{
			"title":title,
			"detail":detail
		},
		success : function(result){
			console.log("--------");
			console.dir(result);
			console.log("--------");
			if(result==true){
				alert("发布成功！");
				location.reload();
			}else {
				alert("发布失败！");
			}
		} 
	});
}

window.onload = TipsOfEvents();

	function TipsOfEvents(){
		$('#btn').val(null);
		$.ajax({
			url : "TipsOfIndex",
			dataType : "json",
			async : true,
			type : "POST",
			success : function(result) {
				console.dir(result);
				
				//失物招领
				var gotTips = "";
				for (var i = 0; i <result.gotTips.length; i++) {
					var e = result.gotTips[i];
					gotTips += `<li class='sort1'><a href='eventDetail.html?eId=${e.eId}'> 
					           <h3>${e.eKinds}：</h3> 
					           <p>${e.eTitle}</p> 
					       </a></li>`;
				}
				$("#gotTips").html(gotTips);
				
				//寻物启事
				var lostTips = "";
				for (var i = 0; i <result.lostTips.length; i++) {
					var e = result.lostTips[i];
					lostTips += `<li class='sort1'><a href='eventDetail.html?eId=${e.eId}'> 
					           <h3>${e.eKinds}：</h3> 
					           <p>${e.eTitle}</p> 
					       </a></li>`;
				}
				$("#lostTips").html(lostTips);

				//轮播图
				var carousel = "";
				for (var i = 0; i <result.carousel.length; i++) {
					var e = result.carousel[i];
					carousel += `<li class='picture${i+1}'>
							<a href="eventDetail.html?eId=${e.eId}"> <img src="${e.eImage}" alt="" width="248" height="285" /></a>
							</li>`;
				}
				$("#carousel").html(carousel);
				
				//忘记密码，联系管理员模态框
				var forForgettingPwd = "";
				//联系我们
				var contacts = "";
				for (var i = 0; i <result.contacts.length; i++) {
					console.log(result.contacts.length);
					var e = result.contacts[i];
					contacts += `<li>${e.service}:&nbsp;&nbsp;&nbsp;${e.sQQ}</li>`;
					forForgettingPwd += ` <li class="list-group-item">${e.service}:&nbsp;&nbsp;&nbsp;${e.sQQ}</li><br/>`;
				}
				$("#contactus").html(contacts); 
				$('#contactAdmin').html(forForgettingPwd);
				//忘记密码，联系管理员模态框
				
				//主页的公告
				var notes = "";
				//onclick='noteDetail(1,${note.noteId})
				for(var i=0;i<result.notes.length;i++){
					var note = result.notes[i];
					notes += `<li style='display:block;width:173px;white-space:nowrap;
                                overflow:hidden;
                                line-height: 25px !important;
                                text-overflow: ellipsis;
                                -o-text-overflow:ellipsis;'><a style='color:#000;' href='#' onclick='noteDetail(1,${note.noteId})'>
								${note.noteTitle}</a></li>`;
				}
				$('#demo1').html(notes); 
				
				//感谢信
				var thanksNote = "";
				for(var i=0;i<result.thanks.length;i++){
					var note = result.thanks[i];
					thanksNote += `<li style='display:block;width:173px;white-space:nowrap;
                                overflow:hidden;
                                line-height: 25px !important;
                                text-overflow: ellipsis;
                                -o-text-overflow:ellipsis;'><a style='color:#000;' href='#' 
						onclick='noteDetail(2,${note.tId})'>
						${note.tTitle}</a></li>`;
				}
				$('#thanksNote').html(thanksNote);
				
				//校园卡轮播图
				var ucards = "";
				var cards = result.cards;
				for(var i=0;i<cards.length;i++){
					ucards += ` <li><a href="eventDetail.html?eId=${cards[i].eId}"><img src="${cards[i].eImage}"></a></li>`;
				}
				$('#ucards').html(ucards);
				
				
				loadjscssfile('css/mycss.css', "css"); 
				loadjscssfile(' js/carousel.js', "js");
		        loadjscssfile('css/index.css', "css"); //加载你的js文件
		        loadjscssfile('bootstrap-3.3.7-dist/css/bootstrap.min.css', "css"); //加载你的js文件 
			}
		});
	}
	
	//忘记密码
	function forgetpwd(){
		$('#myModal').modal('hide');
		$('#forgetpwd').modal('show');
	}
	
	//搜索事件
	function findInfo(){
		var value = $('#btn').val();
		var kinds = $('#kinds').val();
		console.log(kinds);
		console.log(value);
		window.open("lostAndFound.html?eType="+kinds+"&index="+value);
	}
	
