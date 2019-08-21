function GetBookingLogin() {
	this.registerEvents();
}

GetBookingLogin.prototype = {
		sendAjax : function(email){
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function(){
				var result = JSON.parse(oReq.responseText);
				var modalWarningTextArea = document.querySelector("body > app > div.modal > div > div.select_layer > div > h5 > span");
				
				//result.size는 해당 email의 예약 건수
				if(result.size == 0) {
					if(this.checkValidation(email)) {
						
						//modal layer warning text 변경
						modalWarningTextArea.innerText = "해당하는 이메일이 없습니다";
						
					} else {
						modalWarningTextArea.innerText = "이메일 형식이 알맞지 않습니다";
					}
					
					//modal layer 화면에 표시
					document.querySelector('.modal').style.display='block';
					
				//myreservation 페이지로 이동하기 위해 form 제출
				} else if(result.size >= 1) {
					document.querySelector("#form1").submit();
				}
			}.bind(this));
			oReq.open("GET", "./reservations?reservationEmail="+email);
			oReq.send();
		},
		
		//email validation 체크
		checkValidation : function(email){
			return email.match(/^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{1,2}\.[a-z]{1,2}$|^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{3}$/);
		},
		
		registerEvents : function(){
			document.querySelector("#form1 > button").addEventListener("click", function(){
				this.sendAjax(document.querySelector("#resrv_id").value);
			}.bind(this));
			
			//클릭 시 화면 사라짐
			document.querySelector('.modal').addEventListener("click", function(){
				document.querySelector('.modal').style.display='none';
			});
		}
}



document.addEventListener("DOMContentLoaded", function(){
	var bookingLogin = new GetBookingLogin();
});