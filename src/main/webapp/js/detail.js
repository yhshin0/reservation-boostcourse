function ProductDetail() {
  this.sendAjax();
  this.registerEvents();
}

ProductDetail.prototype = {
  
  sendAjax : function() {
    var displayInfoId = location.href.substr(location.href.lastIndexOf("=")+1);
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function(){
      var result = JSON.parse(oReq.responseText);
      this.getImages(result);
      this.getAverageGrade(result);
      this.getReviewItem(result);
      this.getInfoBox(result);
      this.getDescription(result.displayInfo.productContent);
    }.bind(this));
    oReq.open("GET", "http://localhost:8080/booking/products/"+displayInfoId);
    oReq.send();
  },
  
  
  //이미지 조회
  getImages : function(responseText) {
    var template = document.querySelector("#imageItem").innerHTML;
    var imageItems = document.querySelector(".group_visual .container_visual .visual_img.detail_swipe");
    imageItems.innerHTML = '';
    var bindTemplate = Handlebars.compile(template);
    responseText.productImages.forEach(function(v){
      var data = {
        saveFileName: v.saveFileName,
        productDescription: responseText.displayInfo.productDescription
      };
      var resultHTML = bindTemplate(data);
      imageItems.innerHTML += resultHTML;
    });

    //2개일 때만 carousel 적용
    if(responseText.productImages.length == 2) {
      document.querySelector("span.num.off>span").innerText=2;
      document.querySelector("div.group_visual .container_visual").nextElementSibling.classList.remove("hide");
      document.querySelector("div.group_visual .container_visual").nextElementSibling.nextElementSibling.classList.remove("hide");
      var visualImages = new VisualImages();
    }
  },
			
  //평점 조회
  getAverageGrade : function(responseText) {
    var template = document.querySelector("#averageGrade").innerHTML;
    var gradeArea = document.querySelector(".section_review_list .short_review_area .grade_area");
    gradeArea.innerHTML = '';
    var bindTemplate = Handlebars.compile(template);
    var data = {
      percentWidth: responseText.averageScore * 20,
      averageScore: parseFloat(responseText.averageScore.toFixed(1)),
      reviewCount: responseText.comments.length
    };
    var resultHTML = bindTemplate(data);
    gradeArea.innerHTML = resultHTML;
  },
			
  //리뷰 간단 조회
  getReviewItem : function(responseText) {
    var template = document.querySelector("#reviewItem").innerHTML;
    var reviewList = document.querySelector(".section_review_list .short_review_area .list_short_review");
    reviewList.innerHTML = '';
    var bindTemplate = Handlebars.compile(template);
    for (var i = 0; i < 3; i++) {
      if(i==responseText.comments.length) break;
      var data ={
        reviewImage: responseText.comments[i].commentImages.length == 0?"":responseText.comments[i].commentImages[0].saveFileName,
        reviewProductName: responseText.displayInfo.productDescription,
        reviewText: responseText.comments[i].comment,
        reviewGrade: responseText.comments[i].score.toFixed(1),
        reviewer: responseText.comments[i].reservationName.replace(/.{2}$/, "**"),
        reviewDate: responseText.comments[i].reservationDate.substring(0,responseText.comments[i].reservationDate.lastIndexOf("."))
      };
      var resultHTML = bindTemplate(data);
      reviewList.innerHTML += resultHTML;
    }
  },
			
  //하단 info box 조회
  getInfoBox : function(responseText) {
    var template = document.querySelector("#infoBox").innerHTML;
    var infoBox = document.querySelector(".detail_location");
    infoBox.innerHTML = '';
    var bindTemplate = Handlebars.compile(template);
    var data = {
      mapSrc: "img_map/"+responseText.displayInfoImage.fileName,
      title: responseText.displayInfo.productDescription,
      placeStreet: responseText.displayInfo.placeStreet,
      placeLot: responseText.displayInfo.placeLot,
      placeName: responseText.displayInfo.placeName,
      tel: responseText.displayInfo.telephone
    }
    var resultHTML = bindTemplate(data);
    infoBox.innerHTML = resultHTML;
  },
			
  //display 소개 조회
  getDescription : function(description) {
    document.querySelector("div .store_details.close3 p").innerText = description;
    document.querySelector("li.detail_info_lst p").innerText = description;
  },
  
  registerEvents : function(){
    //펼쳐보기, 접기
    document.querySelectorAll("a.bk_more").forEach(function(obj){
      obj.addEventListener("click", function(){
        document.querySelector("div.store_details").classList.toggle('close3');
        document.querySelectorAll("a.bk_more")[0].classList.toggle("hide");
        document.querySelectorAll("a.bk_more")[1].classList.toggle("hide");
      });
    });

    //상세정보, 오시는길
    document.querySelectorAll("ul.info_tab_lst>li.item").forEach(function(obj){
      obj.addEventListener("click",function(){
        if(this.classList.contains("active")) return;
        document.querySelectorAll("ul.info_tab_lst>li.item")[0].classList.toggle("active");
        document.querySelectorAll("ul.info_tab_lst>li.item")[1].classList.toggle("active");
        document.querySelectorAll("ul.info_tab_lst>li.item")[0].firstElementChild.classList.toggle("active");
        document.querySelectorAll("ul.info_tab_lst>li.item")[1].firstElementChild.classList.toggle("active");
        document.querySelector("div.detail_area_wrap").classList.toggle("hide");
        document.querySelector("div.detail_location").classList.toggle("hide");
      });
    });
  }
}

function VisualImages () {
  this.isCompleted = true;
  this.transitionComplete = function(){
    this.isCompleted = true;
  }.bind(this);
  this.prev = document.querySelector("div.group_visual .prev");
  this.nxt = document.querySelector("div.group_visual .nxt");
  this.carousel_items = [];
  this.len = '';
  this.cnt = '';
  this.setCarousels();
  this.registerEvents();
}

VisualImages.prototype = {
  //carousel 설정
  setCarousels : function(){
    document.querySelectorAll("ul.visual_img>li.item").forEach(function(obj){
      document.querySelector("ul.visual_img").appendChild(obj.cloneNode(true));
    });
    this.carousel_items = document.querySelectorAll("ul.visual_img>li.item");
    this.len = this.carousel_items.length;
    this.cnt = this.len*10000;
    for(var i=0; i<this.len; i++){
      this.carousel_items[i].addEventListener("transitionend", this.transitionComplete);
    }
    this.carousel_items[this.len-1].style.opacity = 1;
    this.carousel_items[this.len-1].style.left = (-100*this.len) + "%";
  },
  
  //다음 프로모션 이미지로 이동
  moveNext : function(){
    if(this.isCompleted === true){
      this.isCompleted = false;
      for(var i=0; i<this.len; i++){
        var pre = this.carousel_items[i].style.left === ''? 0 : parseInt(this.carousel_items[i].style.left);
        if(i === (this.cnt-1) % this.len){
          this.carousel_items[i].style.left = pre + (this.len-1)*100 + "%";
          this.carousel_items[i].style.opacity = 0;
        }else{
          this.carousel_items[i].style.left = (pre - 100) + "%";
          this.carousel_items[i].style.opacity = 1;
        }
      }
      this.cnt++;
      document.querySelector("div.figure_pagination").firstElementChild.innerText = (this.cnt%2+1);
    }
  },

  //이전 프로모션 이미지로 이동
  movePrev : function(){
    if(this.isCompleted === true){
      this.isCompleted = false;
      for(var i=0; i<this.len; i++){
        var pre = this.carousel_items[i].style.left === ''? 0 : parseInt(this.carousel_items[i].style.left);
        if(i !== (this.cnt-2) % this.len){
          this.carousel_items[i].style.left = (pre + 100) + "%";
          this.carousel_items[i].style.opacity = 1;
        }else{
          this.carousel_items[i].style.left = pre - (this.len-1)*100 + "%";
          this.carousel_items[i].style.opacity = 0;
        }
      }
      this.cnt--;
      document.querySelector("div.figure_pagination").firstElementChild.innerText = (this.cnt%2+1);
    }
  },
  
  registerEvents : function(){
    this.nxt.addEventListener("click", function(){ this.moveNext();}.bind(this));
    this.prev.addEventListener("click",function(){ this.movePrev();}.bind(this));
  }
  
}

document.addEventListener("DOMContentLoaded", function(){
	var productDetail = new ProductDetail();
});

/*
document.addEventListener("DOMContentLoaded", function(){
	
			
			//displayInfo 출력
			var getDisplayInfoDetail = (function(){
				var displayInfoId = location.href.substr(location.href.lastIndexOf("=")+1);
				
				var oReq = new XMLHttpRequest();
				oReq.addEventListener("load", function(){
					var result = JSON.parse(this.responseText);
					getImages(result);
					getAverageGrade(result);
					getReviewItem(result);
					getInfoBox(result);
					getDescription(result.displayInfo.productContent);
				});
				oReq.open("GET", "http://localhost:8080/booking/products/"+displayInfoId);
				oReq.send();
			})();
			
			//이미지 조회
			var getImages = function(responseText) {
				var template = document.querySelector("#imageItem").innerHTML;
				var imageItems = document.querySelector(".group_visual .container_visual .visual_img.detail_swipe");
				imageItems.innerHTML = '';
				var bindTemplate = Handlebars.compile(template);
				responseText.productImages.forEach(function(v){
					var data = {
							saveFileName: v.saveFileName,
							productDescription: responseText.displayInfo.productDescription
					};
					var resultHTML = bindTemplate(data);
					imageItems.innerHTML += resultHTML;
				});
				
				if(responseText.productImages.length == 2) {
					document.querySelector("span.num.off>span").innerText=2;
					document.querySelector("div.group_visual .container_visual").nextElementSibling.classList.remove("hide");
					document.querySelector("div.group_visual .container_visual").nextElementSibling.nextElementSibling.classList.remove("hide");
					setProductImageCarousel();
				}
			};
			
			//평점 조회
			var getAverageGrade = function(responseText) {
				var template = document.querySelector("#averageGrade").innerHTML;
				var gradeArea = document.querySelector(".section_review_list .short_review_area .grade_area");
				gradeArea.innerHTML = '';
				var bindTemplate = Handlebars.compile(template);
				var data = {
						percentWidth: responseText.averageScore * 20,
						averageScore: parseFloat(responseText.averageScore.toFixed(1)),
						reviewCount: responseText.comments.length
				};
				var resultHTML = bindTemplate(data);
				gradeArea.innerHTML = resultHTML;
			};
			
			//리뷰 간단 조회
			var getReviewItem = function(responseText) {
				var template = document.querySelector("#reviewItem").innerHTML;
				var reviewList = document.querySelector(".section_review_list .short_review_area .list_short_review");
				reviewList.innerHTML = '';
				var bindTemplate = Handlebars.compile(template);
				for (var i = 0; i < 3; i++) {
					if(i==responseText.comments.length) break;
					var data ={
							reviewImage: responseText.comments[i].commentImages.length == 0?"":responseText.comments[i].commentImages[0].saveFileName,
							reviewProductName: responseText.displayInfo.productDescription,
							reviewText: responseText.comments[i].comment,
							reviewGrade: responseText.comments[i].score.toFixed(1),
							reviewer: responseText.comments[i].reservationName.replace(/.{2}$/, "**"),
							reviewDate: responseText.comments[i].reservationDate.substring(0,responseText.comments[i].reservationDate.lastIndexOf("."))
					};
					var resultHTML = bindTemplate(data);
					reviewList.innerHTML += resultHTML;
				}
			};
			
			//하단 info box 조회
			var getInfoBox = function(responseText) {
				var template = document.querySelector("#infoBox").innerHTML;
				var infoBox = document.querySelector(".detail_location");
				infoBox.innerHTML = '';
				var bindTemplate = Handlebars.compile(template);
				var data = {
						mapSrc: "img_map/"+responseText.displayInfoImage.fileName,
						title: responseText.displayInfo.productDescription,
						placeStreet: responseText.displayInfo.placeStreet,
						placeLot: responseText.displayInfo.placeLot,
						placeName: responseText.displayInfo.placeName,
						tel: responseText.displayInfo.telephone
				}
				var resultHTML = bindTemplate(data);
				infoBox.innerHTML = resultHTML;
			};
			
			//display 소개 조회
			var getDescription = function(description) {
				document.querySelector("div .store_details.close3 p").innerText = description;
				document.querySelector("li.detail_info_lst p").innerText = description;
			};
			
			//펼쳐보기, 접기, 상세정보, 오시는길 
			var additionalInfo = (function(){
				//펼쳐보기, 접기
				document.querySelectorAll("a.bk_more").forEach(function(obj){
					obj.addEventListener("click", function(){
						document.querySelector("div.store_details").classList.toggle('close3');
						document.querySelectorAll("a.bk_more")[0].classList.toggle("hide");
						document.querySelectorAll("a.bk_more")[1].classList.toggle("hide");
					});
				});
				
				//상세정보, 오시는길
				document.querySelectorAll("ul.info_tab_lst>li.item").forEach(function(obj){
			    obj.addEventListener("click",function(){
			    	if(this.classList.contains("active")) return;
			      document.querySelectorAll("ul.info_tab_lst>li.item")[0].classList.toggle("active");
			      document.querySelectorAll("ul.info_tab_lst>li.item")[1].classList.toggle("active");
			      document.querySelectorAll("ul.info_tab_lst>li.item")[0].firstElementChild.classList.toggle("active");
			      document.querySelectorAll("ul.info_tab_lst>li.item")[1].firstElementChild.classList.toggle("active");
			      document.querySelector("div.detail_area_wrap").classList.toggle("hide");
			      document.querySelector("div.detail_location").classList.toggle("hide");
			    });
			  });
			})();
			
		  //carousel 설정
		  var setProductImageCarousel = function(){
		  	var isCompleted = true;
		  	var transitionComplete = function(){
		  		isCompleted = true;
		  	}
		  	var prev = document.querySelector("div.group_visual .prev");
		  	var nxt = document.querySelector("div.group_visual .nxt");
		  	var carousel_items = [];
		  	var	len = '';
		  	var cnt = '';
		  	
				setCarousels();
		  		
				nxt.addEventListener("click", function(){ moveNext()});
				prev.addEventListener("click",function(){ movePrev()});
		  	
		  	//carousel 설정
		  	function setCarousels(){
		  		document.querySelectorAll("ul.visual_img>li.item").forEach(function(obj){
		  			document.querySelector("ul.visual_img").appendChild(obj.cloneNode(true));
		  		});
		  		carousel_items = document.querySelectorAll("ul.visual_img>li.item");
		  		len = carousel_items.length;
		  		cnt = len*10000;
		  		for(var i=0; i<len; i++){
		  			carousel_items[i].addEventListener("transitionend", transitionComplete);
		  		}
		  		carousel_items[len-1].style.opacity = 1;
		  		carousel_items[len-1].style.left = (-100*len) + "%";
		  	}
		  	
		  	//다음 프로모션 이미지로 이동
		  	function moveNext(){
		  		if(isCompleted === true){
		  			isCompleted = false;
		  			for(var i=0; i<len; i++){
		  				var pre = carousel_items[i].style.left === ''? 0 : parseInt(carousel_items[i].style.left);
		  				if(i === (cnt-1)%len){
		  					carousel_items[i].style.left = pre + (len-1)*100 + "%";
		  					carousel_items[i].style.opacity = 0;
		  				}else{
		  					carousel_items[i].style.left = (pre - 100) + "%";
		  					carousel_items[i].style.opacity = 1;
		  				}
		  			}
		  			cnt++;
		  			document.querySelector("div.figure_pagination").firstElementChild.innerText = (cnt%2+1);
		  		}
		  	}
		  	
		  	//이전 프로모션 이미지로 이동
		  	function movePrev(){
		  		if(isCompleted === true){
		  			isCompleted = false;
		  			for(var i=0; i<len; i++){
		  				var pre = carousel_items[i].style.left === ''? 0 : parseInt(carousel_items[i].style.left);
		  				if(i !== (cnt-2)%len){
		  					carousel_items[i].style.left = (pre + 100) + "%";
		  					carousel_items[i].style.opacity = 1;
		  				}else{
		  					carousel_items[i].style.left = pre - (len-1)*100 + "%";
		  					carousel_items[i].style.opacity = 0;
		  				}
		  			}
		  			cnt--;
		  			document.querySelector("div.figure_pagination").firstElementChild.innerText = (cnt%2+1);
		  		}
		  	}
		  };
	});*/