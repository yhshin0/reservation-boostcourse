function Promotions () {
  this.isCompleted = true;
	this.transitionComplete = function(){
		this.isCompleted = true;
	}.bind(this);
  this.prev_e = document.querySelector(".event .section_visual .group_visual .container_visual .prev_e");
  this.nxt_e = document.querySelector(".event .section_visual .group_visual .container_visual .nxt_e");
  this.carousel_items = [];
  this.len = '';
  this.cnt = '';
  this.sendAjax();
}

Promotions.prototype = {
  //carousel 설정
  setCarousels : function() {
    this.carousel_items = document.querySelectorAll(".event .section_visual .group_visual .container_visual .container_visual .visual_img .item");
    this.len = this.carousel_items.length;
    this.cnt = this.len*10000;
    for(var i=0; i<this.len; i++){
      this.carousel_items[i].addEventListener("transitionend", this.transitionComplete);
    }
    this.carousel_items[this.len-1].style.opacity = 0;
    this.carousel_items[this.len-1].style.left = (-100*this.len) + "%";
  },
  				
  //다음 프로모션 이미지로 이동
  moveNext : function() {
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
    }
  },
  				
  //이전 프로모션 이미지로 이동
  movePrev : function() {
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
    }
  },
  
  sendAjax : function(){
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function(){
    	var template = document.querySelector("#promotionItem").innerHTML;
    	var ul_field = document.querySelector(".event .section_visual .group_visual .container_visual .container_visual .visual_img");
      var result = JSON.parse(oReq.responseText);
      ul_field.innerHTML = '';
      var bindTemplate = Handlebars.compile(template);
      result.items.forEach(function(v){
      	var data = {
      			productImageUrl : v.productImageUrl
      	};
      	var resultHTML = bindTemplate(data);
      	ul_field.innerHTML += resultHTML;
      });
      this.setEvents();
    }.bind(this));
    oReq.open("GET", "http://localhost:8080/booking/promotions");
    oReq.send();
  },
  
  setEvents : function(){
    this.setCarousels();
    this.nxt_e.addEventListener("click", function(){ this.moveNext();}.bind(this));
    this.prev_e.addEventListener("click",function(){ this.movePrev();}.bind(this));
    setInterval(() => {
    	this.moveNext();
    },2500);
  }
}

function Categories() {
  this.getCategories();
  this.registerEvent();
}

Categories.prototype = {
  //카테고리 조회
  getCategories : function(){
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function(){
      var result = JSON.parse(oReq.responseText);
      var tabMenu = document.querySelector(".event .section_event_tab .event_tab_lst");
      var template = document.querySelector("#categoryList").innerHTML;
      var bindTemplate = Handlebars.compile(template);
      result.items.forEach(function(obj){
      	var data = {
      			id : obj.id,
      			name : obj.name
      	};
      	var resultHTML = bindTemplate(data);
        tabMenu.innerHTML += resultHTML;
      });
      Products.getProducts('',0);
    });
    oReq.open("GET", "http://localhost:8080/booking/categories");
    oReq.send();
  },
  
  //카테고리 탭 클릭시
  registerEvent : function(){
    document.querySelector(".event .section_event_tab .event_tab_lst").addEventListener("click", function(evt){
      var box = document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box");
      box[0].innerHTML = '';
      box[1].innerHTML = '';
      var obj = evt.target.closest("a");
      if(obj != null && obj.tagName === "A"){
        document.querySelectorAll(".event .section_event_tab .event_tab_lst .item a").forEach(function(obj){
          obj.className = 'anchor';
        });
        obj.className = "anchor active";
      }
      document.querySelector(".event .section_event_lst .wrap_event_box .btn").style.display = 'inherit';
      Products.getProducts(evt.target.closest("li").getAttribute("data-category"),0);
    });
  }
  
}

var Products = {
  //상품 정보 조회
  getProducts : function(categoryId, start){
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function(){
    	var template = document.querySelector("#itemList").innerHTML;
    	var box = document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box");
    	var bindTemplate = Handlebars.compile(template);
      var result = JSON.parse(oReq.responseText);

      for(var i=0; i<result.items.length; i+=2){
        var data = {
        		productDescription : result.items[i].productDescription,
        		productImageUrl : result.items[i].productImageUrl,
        		placeName : result.items[i].placeName,
        		productContent : result.items[i].productContent,
        		displayInfoId : result.items[i].displayInfoId
        }
        var resultHTML = bindTemplate(data);
        box[0].innerHTML += resultHTML;
      }
      for(var i=1; i<result.items.length; i+=2){
      	var data = {
      			productDescription : result.items[i].productDescription,
      			productImageUrl : result.items[i].productImageUrl,
      			placeName : result.items[i].placeName,
      			productContent : result.items[i].productContent,
      			displayInfoId : result.items[i].displayInfoId
      	}
      	var resultHTML = bindTemplate(data);
        box[1].innerHTML += resultHTML;
      }

      document.querySelector(".event .section_event_lst .event_lst_txt .pink").innerHTML = result.totalCount+"개";

      if(document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box .item").length == result.totalCount){
        document.querySelector(".event .section_event_lst .wrap_event_box .btn").style.display = 'none';
      }

    });
    oReq.open("GET", "http://localhost:8080/booking/products?start="+start+"&categoryId="+categoryId);
    oReq.send();
  }
}

//더보기 버튼 클릭 시
var clickMore = function(){
  document.querySelector(".event .section_event_lst .wrap_event_box .btn").addEventListener("click", function(){
    Products.getProducts(document.querySelector("a.anchor.active").parentElement.getAttribute("data-category"), document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box .item").length);
  });
};


  document.addEventListener("DOMContentLoaded", function(){
  	var promotions = new Promotions();
  	var categories = new Categories();
  	clickMore();
/*  			
  	    //프로모션 정보 조회
  			var getPromotions = (function(){
  				var isCompleted = true;
  				var transitionComplete = function(){
  					isCompleted = true;
  				}
  				var prev_e = document.querySelector(".event .section_visual .group_visual .container_visual .prev_e");
  				var nxt_e = document.querySelector(".event .section_visual .group_visual .container_visual .nxt_e");
  				var carousel_items = [];
  				var	len = '';
  				var cnt = '';
  				var template = document.querySelector("#promotionItem").innerHTML;
  				var ul_field = document.querySelector(".event .section_visual .group_visual .container_visual .container_visual .visual_img");
  				ul_field.innerHTML = '';
  				
  				var oReq = new XMLHttpRequest();
  				oReq.addEventListener("load", function(){
  					var result = JSON.parse(this.responseText);
  					result.items.forEach(function(v){
  						obj = template.replace(/{productImageUrl}/g, v.productImageUrl);
  						ul_field.innerHTML += obj;
  					});
  					
  					setCarousels();
  					
  					nxt_e.addEventListener("click", function(){ moveNext()});
  					prev_e.addEventListener("click",function(){ movePrev()});
  					setInterval(() => {
  						moveNext();
  					},2500);
  					
  				});
  				oReq.open("GET", "http://localhost:8080/booking/promotions");
  				oReq.send();
  				
  				//carousel 설정
  				function setCarousels(){
  					carousel_items = document.querySelectorAll(".event .section_visual .group_visual .container_visual .container_visual .visual_img .item");
  					len = carousel_items.length;
  					cnt = len*10000;
  					for(var i=0; i<len; i++){
  						carousel_items[i].addEventListener("transitionend", transitionComplete);
  					}
  					carousel_items[len-1].style.opacity = 0;
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
  					}
  				}
  			})();
  			
  			//카테고리 조회
  			var getCategories = (function(){
  				var oReq = new XMLHttpRequest();
  				oReq.addEventListener("load", function(){
  					var result = JSON.parse(this.responseText);
  					result.items.forEach(function(obj){
  						document.querySelector(".event .section_event_tab .event_tab_lst").innerHTML += '<li class="item" data-category="'
  							+obj["id"]+'"><a class="anchor"><span>'+obj.name+'</span></a></li>';
  					});
  					getProducts('',0);
  					
  				});
  				oReq.open("GET", "http://localhost:8080/booking/categories");
  				oReq.send();
  				
  				//카테고리 탭 클릭시
  				document.querySelector(".event .section_event_tab .event_tab_lst").addEventListener("click", function(evt){
  					var box = document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box");
  					box[0].innerHTML = '';
  					box[1].innerHTML = '';
  					var obj = evt.target.closest("a");
  					if(obj != null && obj.tagName === "A"){
  						document.querySelectorAll(".event .section_event_tab .event_tab_lst .item a").forEach(function(obj){
  							obj.className = 'anchor';
  						});
  						obj.className = "anchor active";
  					}
  					document.querySelector(".event .section_event_lst .wrap_event_box .btn").style.display = 'inherit';
  					getProducts(evt.target.closest("li").getAttribute("data-category"),0);
  				});
  			})();
  			
  	    //상품 정보 조회
  			var getProducts = function(categoryId, start){
  				var oReq = new XMLHttpRequest();
  	    	var template = document.querySelector("#itemList").innerHTML;
  	    	var box = document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box");
  	    	oReq.addEventListener("load", function(){
  	    		var result = JSON.parse(this.responseText);
  	    		
  	    		for(var i=0; i<result.items.length; i+=2){
  	    			obj = template.replace(/{productDescription}/g, result.items[i].productDescription)
  	    			.replace("{productImageUrl}", result.items[i].productImageUrl)
  	    			.replace("{placeName}", result.items[i].placeName)
  	    			.replace("{productContent}", result.items[i].productContent)
  	    			.replace("{displayInfoId}", result.items[i].displayInfoId);
  	    			box[0].innerHTML += obj;
  	    		}
  	    		for(var i=1; i<result.items.length; i+=2){
  	    			obj = template.replace(/{productDescription}/g, result.items[i].productDescription)
  	    			.replace("{productImageUrl}", result.items[i].productImageUrl)
  	    			.replace("{placeName}", result.items[i].placeName)
  	    			.replace("{productContent}", result.items[i].productContent)
  	    			.replace("{displayInfoId}", result.items[i].displayInfoId);
  	    			box[1].innerHTML += obj;
  	    		}
  	    		
  	    		document.querySelector(".event .section_event_lst .event_lst_txt .pink").innerHTML = result.totalCount+"개";
  	    		
  	    		if(document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box .item").length == result.totalCount){
  	    			document.querySelector(".event .section_event_lst .wrap_event_box .btn").style.display = 'none';
  	    		}
  	    		
  	    	});
  	    	oReq.open("GET", "http://localhost:8080/booking/products?start="+start+"&categoryId="+categoryId);
  	    	oReq.send();
  			};
  			
  	    //더보기 버튼 클릭 시
  			var clickMore = (function(){
  		    document.querySelector(".event .section_event_lst .wrap_event_box .btn").addEventListener("click", function(){
  		    	getProducts(document.querySelector("a.anchor.active").parentElement.getAttribute("data-category"), document.querySelectorAll(".event .section_event_lst .wrap_event_box .lst_event_box .item").length);
  		    });
  			})();
    */
  });
