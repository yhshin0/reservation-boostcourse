function ReviewPage() {
  this.sendAjax();
}

ReviewPage.prototype = {
  //review 목록 조회
  sendAjax : function(){
    var displayInfoId = location.href.substr(location.href.lastIndexOf("=")+1);
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function(){
      var result = JSON.parse(oReq.responseText);
      this.getAverageGrade(result);
      this.getReviewItem(result);
    }.bind(this));
    oReq.open("GET", "http://localhost:8080/booking/products/"+displayInfoId);
    oReq.send();
  },
  
  //평점 조회
  getAverageGrade : function(responseText) {
    var template = document.querySelector("#gradeArea").innerHTML;
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
	
  //리뷰 조회
  getReviewItem : function(responseText) {
    var template = document.querySelector("#reviewItem").innerHTML;
    var reviewList = document.querySelector(".section_review_list .short_review_area .list_short_review");
    reviewList.innerHTML = '';
    var bindTemplate = Handlebars.compile(template);
    responseText.comments.forEach(function(obj){
      var data ={
        reviewImage: obj.commentImages.length == 0?"":obj.commentImages[0].saveFileName,
        reviewProductName: responseText.displayInfo.productDescription,
        reviewText: obj.comment,
        reviewGrade: obj.score.toFixed(1),
        reviewer: obj.reservationName.replace(/.{2}$/, "**"),
        reviewDate: obj.reservationDate.substring(0,obj.reservationDate.lastIndexOf("."))
      };
      var resultHTML = bindTemplate(data);
      reviewList.innerHTML += resultHTML;
    });
  }
}

document.addEventListener("DOMContentLoaded", function(){
  var reviewPage = new ReviewPage();
});

/*
document.addEventListener("DOMContentLoaded", function(){
	
	//review 목록 조회
	var getReviewList = (function(){
		var displayInfoId = location.href.substr(location.href.lastIndexOf("=")+1);
		
		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function(){
			var result = JSON.parse(this.responseText);
			getAverageGrade(result);
			getReviewItem(result);
		});
		oReq.open("GET", "http://localhost:8080/booking/products/"+displayInfoId);
		oReq.send();
	})();
	
	//평점 조회
	var getAverageGrade = function(responseText) {
		var template = document.querySelector("#gradeArea").innerHTML;
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
	
	//리뷰 조회
	var getReviewItem = function(responseText) {
		var template = document.querySelector("#reviewItem").innerHTML;
		var reviewList = document.querySelector(".section_review_list .short_review_area .list_short_review");
		reviewList.innerHTML = '';
		var bindTemplate = Handlebars.compile(template);
		responseText.comments.forEach(function(obj){
			var data ={
					reviewImage: obj.commentImages.length == 0?"":obj.commentImages[0].saveFileName,
					reviewProductName: responseText.displayInfo.productDescription,
					reviewText: obj.comment,
					reviewGrade: obj.score.toFixed(1),
					reviewer: obj.reservationName.replace(/.{2}$/, "**"),
					reviewDate: obj.reservationDate.substring(0,obj.reservationDate.lastIndexOf("."))
			};
			var resultHTML = bindTemplate(data);
			reviewList.innerHTML += resultHTML;
		});
	};

});
*/