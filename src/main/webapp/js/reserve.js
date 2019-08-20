function GetReservePage(displayInfoId){
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
		
		getMinimumPrice : function(productPrices) {
			var min = Number.MAX_SAFE_INTEGER;
			productPrices.forEach(function(obj){
				if(obj.price < min) min = obj.price;
			});
			return min;
		},
		
		getPriceInfo : function(productPrices){
			var template = document.querySelector("#priceInfo").innerText;
			var bindTemplate = Handlebars.compile(template);
			var resultHTML = productPrices.reduce(function(prev, next) {
				return prev + bindTemplate(next);
			},"");
			var location = document.querySelector(".ticket_body");
			location.innerHTML = resultHTML;
		},
		
		registerPriceButtonEvents : function(){
			
			//input value 초기화
			document.querySelectorAll("input.count_control_input").forEach(function(v){
				v.value = 0;
			});
			
			//+버튼 누를 시
			document.querySelectorAll("a.btn_plus_minus.spr_book2.ico_plus3").forEach(function(btn){
				btn.addEventListener("click", function(){
					var count = btn.parentElement.querySelector("input");
					if(count.value == this.itemMaximumCount) {
						this.checkCount(count.value, btn);
						return;
					}
					count.value++;
					this.checkCount(count.value, btn);
				}.bind(this));
			}.bind(this));
			
			//-버튼 누를 시
			document.querySelectorAll("a.btn_plus_minus.spr_book2.ico_minus3").forEach(function(btn){
				btn.addEventListener("click", function(){
					var count = btn.parentElement.querySelector("input");
					if(count.value == this.itemMinimumCount) {
						this.checkCount(count.value, btn);
						return;
					}
					count.value--;
					this.checkCount(count.value, btn);
				}.bind(this));
			}.bind(this));
		},
		
		checkCount : function(count, btn){
			if(count == this.itemMaximumCount) {
				btn.parentElement.querySelector("input").classList.add("disabled");
				btn.parentElement.querySelector("a.ico_plus3").classList.add("disabled");
				
			} else if (count == this.itemMinimumCount) {
				btn.parentElement.querySelector("input").classList.add("disabled");
				btn.parentElement.querySelector("a.ico_minus3").classList.add("disabled");
				
			} else if(count > this.itemMinimumCount && count < this.itemMaximumCount) {
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
		
		calPrice : function(price, count, btn){
			btn.closest("div.count_control").querySelector("span.total_price").innerText = price*count;
		},
		
		getReserveTime : function(){
			var year = new Date().getFullYear();
			var month = new Date().getMonth()+1;
			var date = new Date().getDate() + Math.floor(Math.random()*5) + 1;
			var reserveTime = year + '.' + month + '.' + date + '.';
			//document.querySelector("div.inline_form.last>div.inline_control>p.inline_txt.selected").insertAdjacentHTML("afterbegin", reserveTime);
			document.querySelector("#reservationDate").innerText = reserveTime;
		},
		
		registerEvents : function(){
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
			
			name.addEventListener("change", function(){
				nameCheck.value = 0;
				if(name.value.match(/^[a-zA-Z가-힣]{2,}$/) !== null) {
					nameCheck.value = 1;
				} else {
					setTimeout(()=>name.nextElementSibling.style.visibility="hidden",1000);
					name.nextElementSibling.style.visibility = "visible";
				}
				this.toggleReserveButton(nameCheck.value, telCheck.value, emailCheck.value, chk3Check.value);
			}.bind(this));
			
			tel.addEventListener("change", function(){
				telCheck.value = 0;
				if(tel.value.match(/^01[16789]-[0-9]{3,4}-[0-9]{4}$|^010-[0-9]{4}-[0-9]{4}$/) !== null) {
					telCheck.value = 1;
				} else {
					setTimeout(()=>tel.nextElementSibling.style.visibility="hidden",1000);
					tel.nextElementSibling.style.visibility = "visible";
				}
				this.toggleReserveButton(nameCheck.value, telCheck.value, emailCheck.value, chk3Check.value);
			}.bind(this));
			
			email.addEventListener("change", function(){
				emailCheck.value = 0;
				if(email.value.match(/^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{1,2}\.[a-z]{1,2}$|^[A-Za-z0-9_-]+@[A-Za-z0-9]+\.[a-z]{3}$/) !== null) {
					emailCheck.value=1;
				} else {
					setTimeout(()=>email.nextElementSibling.style.visibility="hidden",1000);
					email.nextElementSibling.style.visibility = "visible";
				}
				this.toggleReserveButton(nameCheck.value, telCheck.value, emailCheck.value, chk3Check.value);
			}.bind(this));
			
			chk3.addEventListener("change", function(){
				chk3Check.value++;
				this.toggleReserveButton(nameCheck.value, telCheck.value, emailCheck.value, chk3Check.value);
			}.bind(this));
			
			agree1.addEventListener("click", ()=>{agree1.parentElement.classList.toggle("open");});
			agree2.addEventListener("click", ()=>{agree2.parentElement.classList.toggle("open");});
			
			document.querySelector("#container > div.ct > div > div.box_bk_btn > div > button").addEventListener("click", function(){
				if(parseInt(nameCheck.value) + parseInt(telCheck.value) + parseInt(emailCheck.value) + parseInt(chk3Check.value)%2 !== 4) return;
				if(document.querySelector("#totalCount").innerText == 0) {
					alert("상품을 선택해 주세요");
					return;
				}
				this.sendData();
			}.bind(this));
		},
		
		toggleReserveButton : function(nameValue, telValue, emailValue, chk3Value){
			if(parseInt(nameValue) + parseInt(telValue) + parseInt(emailValue) + parseInt(chk3Value)%2 === 4) {
				document.querySelector("#container > div.ct > div > div.box_bk_btn > div").classList.remove("disable");
			} else {
				document.querySelector("#container > div.ct > div > div.box_bk_btn > div").classList.add("disable");
			}
		},
		
		sendData : function(){
			var displayInfoId =parseInt(document.querySelector("#displayInfoId").value);
			var productId = parseInt(document.querySelector("#productId").value);
			var name = document.querySelector("#name").value;
			var tel = document.querySelector("#tel").value;
			var email = document.querySelector("#email").value;
			var reservationDate = document.querySelector("#reservationDate").innerText;
			var prices = [];
			
			document.querySelectorAll("div.qty").forEach(function(v){
				if(v.querySelector("input.count_control_input").value == 0) return;
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
			oReq.addEventListener("load", function(){
				location.href = oReq.responseText;
			});
			oReq.open("POST", "reservations");
			oReq.setRequestHeader("content-type","application/json");
			oReq.send(JSON.stringify(data));
		}
		
}


document.addEventListener("DOMContentLoaded", function(){
	var displayInfoId = location.href.substr(location.href.lastIndexOf("=")+1);
	var reservePage = new GetReservePage(displayInfoId);
});