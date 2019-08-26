function GetReservePage(displayInfoId) {
	this.sendAjax(displayInfoId);
	this.getReserveTime();
	this.registerEvents();
	this.itemMinimumCount = 0;
	this.itemMaximumCount = 99999;
}

GetReservePage.prototype = {
	sendAjax : function(displayInfoId) {
		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function() {
			var responseText = JSON.parse(oReq.responseText);
			this.getDisplayInfo(responseText);
			this.getPriceInfo(responseText.productPrices);
			this.registerPriceButtonEvents();
		}.bind(this));
		oReq.open("get", "./products/"+ displayInfoId);
		oReq.send();
	},
	
	// 상품 정보 출력
	getDisplayInfo : function(responseText) {
		var template = document.querySelector("#displayInfo").innerHTML;
		var bindTemplate = Handlebars.compile(template);
		var data = {
				productDescription : responseText.displayInfo.productDescription,
				saveFileName : responseText.productImages[0].saveFileName,
				minimumPrice : this.getMinimumPrice(responseText.productPrices),
				openingHours : responseText.displayInfo.openingHours,
				placeName : responseText.displayInfo.placeName,
				prices : responseText.productPrices,
				productId : responseText.displayInfo.productId
		}
		var resultHTML = bindTemplate(data);
		var location = document.querySelector("div.top_title")
		location.insertAdjacentHTML("afterend",resultHTML);
	},
	
	// 최저 가격
	getMinimumPrice : function(productPrices) {
		var min = Number.MAX_SAFE_INTEGER;
		productPrices.forEach(function(obj) {
			if (obj.price < min) min = obj.price;
		});
		return min;
	},
	
	// 티켓 가격 출력
	getPriceInfo : function(productPrices) {
		var template = document.querySelector("#priceInfo").innerText;
		var bindTemplate = Handlebars.compile(template);
		var resultHTML = productPrices.reduce(function(prev, next) {
			return prev + bindTemplate(next);
		},"");
		var location = document.querySelector(".ticket_body");
		location.innerHTML = resultHTML;
	},
	
	// 상품 가격 정보 공간의 버튼 이벤트 등록
	registerPriceButtonEvents : function() {
		
		// input value 초기화
		document.querySelectorAll("input.count_control_input").forEach(function(v) {
			v.value = 0;
		});
		
		// +버튼 누를 시
		document.querySelectorAll("a.btn_plus_minus.spr_book2.ico_plus3").forEach(function(btn) {
			btn.addEventListener("click", function() {
				var count = btn.parentElement.querySelector("input");
				if (count.value == this.itemMaximumCount) {
					this.checkCount(count.value, btn);
					return;
				}
				count.value++;
				this.checkCount(count.value, btn);
				this.toggleReserveButton();
			}.bind(this));
		}.bind(this));
		
		// -버튼 누를 시
		document.querySelectorAll("a.btn_plus_minus.spr_book2.ico_minus3").forEach(function(btn) {
			btn.addEventListener("click", function() {
				var count = btn.parentElement.querySelector("input");
				if (count.value == this.itemMinimumCount) {
					this.checkCount(count.value, btn);
					return;
				}
				count.value--;
				this.checkCount(count.value, btn);
				this.toggleReserveButton();
			}.bind(this));
		}.bind(this));
	},
	
	// 티켓 count 확인
	checkCount : function(count, btn) {
		if (count == this.itemMaximumCount) {
			btn.parentElement.querySelector("input").classList.add("disabled");
			btn.parentElement.querySelector("a.ico_plus3").classList.add("disabled");
			
		} else if (count == this.itemMinimumCount) {
			btn.parentElement.querySelector("input").classList.add("disabled");
			btn.parentElement.querySelector("a.ico_minus3").classList.add("disabled");
			
		} else if (count > this.itemMinimumCount && count < this.itemMaximumCount) {
			btn.parentElement.querySelector("input").classList.remove("disabled");
			btn.parentElement.querySelector("a.ico_plus3").classList.remove("disabled");
			btn.parentElement.querySelector("a.ico_minus3").classList.remove("disabled");
		}
		
		var totalCount = 0;
		document.querySelectorAll("input.count_control_input").forEach((v)=>{totalCount = parseInt(totalCount) + parseInt(v.value)});
		document.querySelector("#totalCount").innerText = totalCount;
		
		var price = btn.closest("div.qty").querySelector("strong.product_price>span.price").innerText;
		this.calPrice(price, count, btn);
	},
	
	// 선택한 티켓 가격 계산
	calPrice : function(price, count, btn) {
		btn.closest("div.count_control").querySelector("span.total_price").innerText = price*count;
	},
	
	// 예약일 랜덤 지정
	getReserveTime : function() {
		var year = new Date().getFullYear();
		var month = new Date().getMonth()+1;
		var date = new Date().getDate() + Math.floor(Math.random()*5) + 1;
		var reserveTime = year + '.' + month + '.' + date + '.';
		document.querySelector("#reservationDate").innerText = reserveTime;
	},
	
	// 이벤트 등록
	registerEvents : function() {
		var name = document.querySelector("#name");
		var tel = document.querySelector("#tel");
		var email = document.querySelector("#email");
		var chk3 = document.querySelector("#chk3");
		var nameCheck = document.querySelector("#nameCheck");
		var telCheck = document.querySelector("#telCheck");
		var emailCheck = document.querySelector("#emailCheck");
		var chk3Check = document.querySelector("#chk3Check");
		
		var agree1 = document.querySelector("#container > div.ct > div > div.section_booking_form > div.section_booking_agreement > div:nth-child(2) > a");
		var agree2 = document.querySelector("#container > div.ct > div > div.section_booking_form > div.section_booking_agreement > div:nth-child(3) > a");
		
		
		/*
		 * (3) blur, focus - focus는 엘리먼트에 포커스가 생겼을 때(input의 text 프로퍼티 테두리 내부를 클릭했을때),
		 * blur은 포커스가 사라졌을 때 발생(input의 text 프로퍼티 테두리 외부를 클릭했을 때)하는 이벤트다.
		 * - 다음 태그를 제외한 모든 태그에서 발생한다.
		 * <base>, <bdo>, <br>, <head>, <html>, <iframe>, <meta>, <param>, <script>, <style>, <title>
		 * 출처: https://devbox.tistory.com/entry/JavaScript-이벤트-타입 [장인개발자를 꿈꾸는 :: 기록하는 공간]
		 */
		name.addEventListener("blur", function() {
			var regExp = /^[a-zA-Z가-힣]{2,}$/;
			this.checkValidation(name, regExp, nameCheck);
			this.toggleReserveButton();
		}.bind(this));
		
		tel.addEventListener("blur", function() {
			var regExp = /^(02|03[1-3]|04[1-4]|05[1-5]|06[1-4])-\d{3,4}-\d{4}$|^01[16789]-[0-9]{3,4}-[0-9]{4}$|^010-[0-9]{4}-[0-9]{4}$/;
			this.checkValidation(tel, regExp, telCheck);
			this.toggleReserveButton();
		}.bind(this));
		
		email.addEventListener("blur", function() {
			var regExp = /^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{1,2}\.[a-z]{1,2}$|^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{3}$/;
			this.checkValidation(email, regExp, emailCheck);
			this.toggleReserveButton();
		}.bind(this));
		
		chk3.addEventListener("change", function() {
			chk3Check.value++;
			this.toggleReserveButton();
		}.bind(this));
		
		agree1.addEventListener("click", ()=>{this.toggleAgreement(agree1);});
		agree2.addEventListener("click", ()=>{this.toggleAgreement(agree2);});
		
		// 예약하기 버튼 클릭 시
		document.querySelector("#container > div.ct > div > div.box_bk_btn > div > button").addEventListener("click", function() {
			if (parseInt(nameCheck.value) + parseInt(telCheck.value) + parseInt(emailCheck.value) + parseInt(chk3Check.value)%2 !== 4) return;
			this.sendData();
		}.bind(this));
	},
	
	// 이용약관 펼치기/접기
	toggleAgreement : function(agreement) {
		if (agreement.parentElement.classList.toggle("open")) { // 펼쳐질 때
			agreement.firstElementChild.innerText = "접기";
			agreement.lastElementChild.className = "fn fn-up2";
			
		} else {
			agreement.firstElementChild.innerText = "보기";
			agreement.lastElementChild.className = "fn fn-down2";
		};
	},
	
	// 예약하기 버튼 활성화 및 비활성화
	toggleReserveButton : function() {
		var nameValue = document.querySelector("#nameCheck").value;
		var telValue = document.querySelector("#telCheck").value;
		var emailValue = document.querySelector("#emailCheck").value;
		var chk3Value = document.querySelector("#chk3Check").value;

		if (parseInt(nameValue) + parseInt(telValue) + parseInt(emailValue) + parseInt(chk3Value)%2 === 4 && document.querySelector("#totalCount").innerText != 0) {
			document.querySelector("#container > div.ct > div > div.box_bk_btn > div").classList.remove("disable");
		} else {
			document.querySelector("#container > div.ct > div > div.box_bk_btn > div").classList.add("disable");
		}
	},
	
	// validation 체크
	checkValidation : function(target, regExp, checkFlag) {
		checkFlag.value = 0;
		if (target.value.match(regExp) !== null) {
			checkFlag.value = 1;
			
		// 형식 오류 메시지 출력
		} else {
			setTimeout(()=>target.nextElementSibling.style.visibility="hidden",1000);
			target.nextElementSibling.style.visibility = "visible";
		}
	},
	
	// 예약정보 전송
	sendData : function() {
		var displayInfoId =parseInt(document.querySelector("#displayInfoId").value);
		var productId = parseInt(document.querySelector("#productId").value);
		var name = document.querySelector("#name").value;
		var tel = document.querySelector("#tel").value;
		var email = document.querySelector("#email").value;
		var reservationDate = document.querySelector("#reservationDate").innerText;
		var prices = [];
		
		// 예약 티켓 정보
		document.querySelectorAll("div.qty").forEach(function(v) {
			if (v.querySelector("input.count_control_input").value == 0) return;
    	    var obj = {};
    	    obj.count=parseInt(v.querySelector("input.count_control_input").value);
    	    obj.productPriceId=parseInt(v.querySelector("#productPriceId").value);
    	    prices.push(obj);
		});
		
		var data = {
			  displayInfoId: displayInfoId,
			  prices: prices,
			  productId: productId,
			  reservationEmail: email,
			  reservationName: name,
			  reservationTelephone: tel,
			  reservationYearMonthDay: reservationDate
		}
		
		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function() {
			location.href = oReq.responseText;
		});
		oReq.open("POST", "reservations");
		oReq.setRequestHeader("content-type","application/json");
		oReq.send(JSON.stringify(data));
	}
		
}


document.addEventListener("DOMContentLoaded", function() {
	var displayInfoId = location.href.substr(location.href.lastIndexOf("=")+1);
	var reservePage = new GetReservePage(displayInfoId);
});