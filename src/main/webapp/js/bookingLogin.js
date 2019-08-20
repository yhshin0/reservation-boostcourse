
var checkEmail = {
		sendAjax : function(email){
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function(){
				var result = JSON.parse(oReq.responseText);
				if(result.size <= 0) {
					//이메일 형식 체크
					if(this.checkValidation(email)) {
						document.querySelector("body > app > div.modal > div > div.select_layer > div > h5 > span").innerText = "해당하는 이메일이 없습니다";
					//해당 이메일 없음
					} else {
						document.querySelector("body > app > div.modal > div > div.select_layer > div > h5 > span").innerText = "이메일 형식이 알맞지 않습니다";
					}
					document.querySelector('.modal').style.display='block';
				} else {
					document.querySelector("#form1").submit();
				}
			}.bind(this));
			oReq.open("GET", "./reservations?reservationEmail="+email);
			oReq.send();
		},
		
		checkValidation : function(email){
			return email.match(/^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{1,2}\.[a-z]{1,2}$|^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{3}$/);
		}
}


document.addEventListener("DOMContentLoaded", function(){
	document.querySelector("#form1 > button").addEventListener("click", function(){
		checkEmail.sendAjax(document.querySelector("#resrv_id").value);
	});
	
	document.querySelector('.modal').addEventListener("click", function(){
		 document.querySelector('.modal').style.display='none';
	});
	
});