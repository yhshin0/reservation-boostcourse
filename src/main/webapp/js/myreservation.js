function GetReservations(email){
	this.email = email;
	this.sendAjax(email);
	this.registerPopupEvents();
}

GetReservations.prototype = {
		sendAjax : function(email){
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function(){
				var result = JSON.parse(oReq.responseText);
				this.getReservation(result);
				this.getCountItems();
				this.registerEvents();
				this.hideSection();
			}.bind(this));
			oReq.open("get", "./reservations?reservationEmail="+email);
			oReq.send();
		},
		
		getReservation : function(responseText){
			var template = document.querySelector("#reservationArticle").innerHTML;
			var bindTemplate = Handlebars.compile(template);
			responseText.reservations.forEach(function(v){
				var cancelMessage = this.getCancelMessageInfo(v.cancelYn, v.reservationDate)[0];
				var cancelMessageLocation = this.getCancelMessageInfo(v.cancelYn, v.reservationDate)[1];
				
				var data = {
						reservationInfoId : v.reservationInfoId,
						productDescription : v.displayInfo.productDescription,
						reservationDate : v.reservationDate.substr(0, v.reservationDate.lastIndexOf(":")),
						productContent : v.displayInfo.productContent,
						placeLot : v.displayInfo.placeLot,
						placeName : v.displayInfo.placeName,
						totalPrice : v.totalPrice
				}
				
				var liSection = document.querySelector(cancelMessageLocation);
				var resultHTML = bindTemplate(data);
				resultHTML = resultHTML.replace(/{cancelMessage}/g, cancelMessage);
				liSection.innerHTML += resultHTML;
				liSection.style.display='block';
			}.bind(this));
		},
		
		getCancelMessageInfo : function(cancelYn, reservationDate){
			if(cancelYn == 1) {  //취소된 예약
				return ["", "li.card.used.cancel"];
			}
			
			var currentTime = new Date();
			var reservationTime = new Date(reservationDate);
			
			if(reservationTime > currentTime) {  //예약 확정
				return ["<div class='booking_cancel'> <button class='btn'> <span>취소</span> </button> </div>", "li.card.confirmed"];
				
			} else {  //이용 완료
				return ["<div class='booking_cancel'> <button class='btn'> <span>예매자 리뷰 남기기</span> </button> </div>", "li.card.used"];
			}
		},
		
		//상단 개수 입력
		getCountItems : function(){
			//전체 개수
			document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(1) > a > span").innerText = document.querySelectorAll("article").length;
			//예약확정
			document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(2) > a > span").innerText = document.querySelectorAll("#container > div.ct > div > div.wrap_mylist > ul > li.card.confirmed > article").length;
			//이용완료
			document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(3) > a > span").innerText = document.querySelectorAll("#container > div.ct > div > div.wrap_mylist > ul > li:nth-child(2) > article").length;
			//취소된 예약
			document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(4) > a > span").innerText = document.querySelectorAll("#container > div.ct > div > div.wrap_mylist > ul > li.card.used.cancel > article").length;
		},
		
		hideSection : function(){
			//예약확정
			if(document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(2) > a > span").innerText == 0) document.querySelector("#container > div.ct > div > div.wrap_mylist > ul > li.card.confirmed").style.display = "none";
			//이용완료
			if(document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(3) > a > span").innerText == 0) document.querySelector("#container > div.ct > div > div.wrap_mylist > ul > li:nth-child(2)").style.display = "none";
			//취소된 예약
			if(document.querySelector("#container > div.ct > div > div.my_summary > ul > li:nth-child(4) > a > span").innerText == 0) document.querySelector("#container > div.ct > div > div.wrap_mylist > ul > li.card.used.cancel").style.display = "none";
			
		},
		
		registerEvents : function(){
			//취소버튼 이벤트 등록
			document.querySelectorAll("li.card.confirmed div.booking_cancel>button.btn").forEach(function(obj){
				obj.addEventListener("click", function(){
					var popupLayer = document.querySelector("div.popup_booking_wrapper");
					var title = obj.parentElement.parentElement.querySelector("h4").innerText;
					var reserveTime = obj.parentElement.parentElement.querySelector("ul>li:nth-child(1)>em").innerText;
					var reservationInfoId=obj.parentElement.parentElement.querySelector("em.booking_number").innerText.substr(3);
					
					popupLayer.querySelector("h1>input").value = reservationInfoId;
					popupLayer.querySelector("h1>span").innerText = title;
					popupLayer.querySelector("h1>small").innerText = "예약일: " + reserveTime;
					
					popupLayer.style.display = "block";
					
				});
			});
		},
		
		registerPopupEvents : function(){
			var popupLayer = document.querySelector("div.popup_booking_wrapper");
			//close 버튼
			document.querySelector('a.popup_btn_close').addEventListener("click", ()=>{popupLayer.style.display = "none";});
			//아니오 버튼
			document.querySelector("div.popup_booking_wrapper>div.popup_booking.refund>div.pop_bottom_btnarea>div.btn_gray").addEventListener("click", ()=>{popupLayer.style.display = "none";});
			//예 버튼
			document.querySelector("div.popup_booking_wrapper>div.popup_booking.refund>div.pop_bottom_btnarea>div.btn_green").addEventListener("click", ()=>{this.cancelReservation(document.querySelector("#popupId").value)});
		},
		
		cancelReservation : function(reservationInfoId) {
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function(){
				this.initScreen();
				this.sendAjax(this.email);
				document.querySelector("div.popup_booking_wrapper").style.display='none';
			}.bind(this));
			oReq.open("put", "./reservations/"+reservationInfoId);
			oReq.send();
		},
		
		initScreen : function(){
			var template = document.querySelector("#cardDescription").innerHTML;
			var bindTemplate = Handlebars.compile(template);
			var details = {
					confirmed : ['#container > div.ct > div > div.wrap_mylist > ul > li.card.confirmed', 'ico_check2', '예약 확정'],
					used : ['#container > div.ct > div > div.wrap_mylist > ul > li:nth-child(2)', 'ico_check2', '이용 완료'],
					canceled : ['#container > div.ct > div > div.wrap_mylist > ul > li.card.used.cancel', 'ico_cancel', '취소된 예약']
			}
			for (var key in details) {
				document.querySelector(details[key][0]).innerHTML = '';
				var data = {
						icon: details[key][1],
						description: details[key][2]
				}
				var resultHTML = bindTemplate(data);
				document.querySelector(details[key][0]).innerHTML = resultHTML;
			}
			
		}
		
}



document.addEventListener("DOMContentLoaded", function(){
	var reservationEmail = document.querySelector("#container > div.header > header > a").innerText;
	var reservations = new GetReservations(reservationEmail);
});