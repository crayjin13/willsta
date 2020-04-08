/*
 * 소켓 관련 함수 & 메세지 함수 자바스크립트
 */
// DOM TREE 생성 후 connetWS 실행
$(document).ready(function(){
	connectWS();
});

var socket = null;
var jsonData = {
		mId : null,
		mIdYou : null,
		msg : null,
		msgDate : null,
		dmNo : null
};
	function connectWS() {
		var ws = new WebSocket("ws://localhost/willstagram/replyEcho");
		socket = ws;
		ws.onopen = function() { // connection이 open 되었을때 실행
			console.log('Info : connection opened.');
		};
	// 메시지 전송 버튼 클릭 후 메시지를 저장하는 콜백함수
	function message_send_insert_function(jsonData){
		var messages = jsonData.mId+","+jsonData.mIdYou+","+jsonData.msg+","+jsonData.msgDate+","+jsonData.dmNo;
		var params = "messages="+messages;
		$.ajax({
			url : 'messages_insert',
			method : 'POST',
			data : params,
			dataType : 'json',
			success : function(rowCount) {
				console.log(rowCount);
			}
		});
	}
	// 메시지 송/수신 시 채팅창에 출력하는 콜백함수
	function message_insert_html(jsonData){
		var htmlData ="";
		htmlData +=	"<div class='main-message-box'>";
		htmlData +=		"<div class='message-dt st3'>";
		htmlData +=			"<div class='message-inner-dt'>";
		htmlData +=				"<p>"+jsonData.msg+"</p>";
		htmlData +=			"</div>";
		htmlData +=			"<span>"+jsonData.msgDate+"분</span>";
		htmlData +=		"</div>";
		htmlData +=		"<div class='messg-usr-img'>";
		htmlData +=			"<img src='contents/member_image/${dm.mImage}' alt=''>"
		htmlData +=		"</div>";
		htmlData +=	"</div>";
		$('.messages-line').append(htmlData);
	}
	
	// 메시지 전송 버튼 클릭 시 작동하는 콜백함수
	function message_send_function(e){
		$('#btnSend').on('click', function(event) {
			event.preventDefault();
			var d = new Date();
			//jsonData 만들어지는 시점
			jsonData.mId = $(e.target).find('.usr-mg-info h4').text();			//보낸사람
			jsonData.mIdYou = $(e.target).find('.usr-mg-info h3').text();		//받는사람
			jsonData.msg = $('#msg').val();										//내용
			jsonData.msgDate = d.getHours() + "시" + d.getMinutes();			//보낸시간
			jsonData.dmNo = $(e.target).find('#dmNo').text();					//방번호
			console.log(jsonData);
			
			if(jsonData.msg != null && jsonData.msg != "" && jsonData.msg != '&nbsp'){
			socket.send(jsonData.mId+","+jsonData.mIdYou+","+jsonData.msg+","+jsonData.msgDate);
			$("#msg").val("");
			message_insert_html(jsonData);
			message_send_insert_function(jsonData);
			}
		});
	}
	// 유저 채팅리스트 출력 콜백함수
	function message_list_function(jsonArrayData){
		console.log(jsonArrayData);
		
	}

	
	// 메세지 유저 클릭 시 작동하는 콜백함수
	function message_detail_function(e){
		var dmNo = $(e.target).find('#dmNo').text();
		var params = "dmNo=" + dmNo;
		$.ajax({
			url : 'messageRoom_detail',
			method : 'GET',
			data : params,
			dataType : 'json',
			success : function(jsonArrayData) {
				message_list_function(jsonArrayData);
				$(function() {
					message_send_function(e);
				});
			}
		});
	}
		
	
	// 채팅방 생성 콜백함수
	function message_create_function(e){
	 var mIdYou = $(e.target.parentNode.parentNode.parentNode.parentNode).find('h3').text();
		$.ajax({
			url:'sessionCheck',
			method:'GET',
			dataType: 'text',
			success:function(loginId){
				if(loginId.trim() == null){
					alert('로그인 하세요')
					location.href('/willstagram/sign_in')
				} else {
					var params = "mId="+loginId+"&"+"mIdYou="+mIdYou;
					$.ajax({
						url:'messages_room_create',
						method:'GET',
						data : params,
						dataType:'text',
						success:function(isSuccess){
							if(isSuccess.trim() == 'success'){
								location.href = "/willstagram/messages";
							} else {
								location.href = "/willstagram/messages";
							}
						}
					});
				}
			}
		});
	}
	
	// 채팅 수신 시 작동되는 콜백함수
	function message_receive(event){
		var msgArray = (event.data).split(",");
		var mIdYou = msgArray[0];	// 보낸사람 (너)
		var mId = msgArray[1];		// 받는사람 (나)
		var contents = msgArray[2];	// 내용
		var msgDate = msgArray[3]; // 시간 
		if(contents != null || contents != ""){
			var htmlData ="";
			htmlData +=	"<div class='main-message-box ta-right'>";
			htmlData +=		"<div class='message-dt'>";
			htmlData +=				"<div class='message-inner-dt'>";
			htmlData +=					"<p>"+contents+"</p>";
			htmlData +=			"</div>";
			htmlData +=			"<span>"+msgDate+"분</span>";
			htmlData +=		"</div>";
			htmlData +=		"<div class='messg-usr-img'>";
			htmlData +=			"<img src='contents/member_image/${dm.mImage}' alt=''>";
			htmlData +=		"</div>";
			htmlData +=	"</div>";
			$('.messages-line').append(htmlData);
			
			}
		}

	//채팅방 오픈
	$(function() {
		$(document).find('.messages-list .usr-msg-details ').on('click', function(e) {
			e.preventDefault();
			message_detail_function(e);
		});
	});
	//보낸사람,받는사람,내용 mId, mIdYou, contents
	ws.onmessage = function(event) { // socket.send() 후 ReplyEchoHandler가 handleTextMessage메소드로부터 메시지를 받아옴											
		event.preventDefault();
		message_receive(event);
	};
	// connection 이 close 되었을때 실행
	ws.onclose = function(event) { 
		console.log('Info: connection closed.');
		setTimeout(function() {
			connect();
		}, 1000);
	};
	$(document).find('.message-us').on('click',function(e){
		e.preventDefault();
		message_create_function(e);
	});
	// connection 이 error가 나왔을때
	ws.onerror = function(event) { 
		console.log('Info: connection closed.');
	};
}