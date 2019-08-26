function GetReviewWritePage() {
	this.stars = document.querySelectorAll("div.rating>input.rating_rdo");
	this.registerEvents();
}

GetReviewWritePage.prototype = {
		
	registerEvents : function() {
		// 별점 이벤트 등록
		this.stars.forEach(function(v) {
			v.addEventListener("click", function(evt) {
				this.paintStars(evt.target.value);
				document.querySelector("span.star_rank.gray_star").style.color='black';
				document.querySelector("span.star_rank.gray_star").innerText=evt.target.value;
				document.querySelector("a.review_write_info").style.display='none';
			}.bind(this));
		}.bind(this));
		
		// 글자수 입력 및 제한
		document.querySelector("textarea").addEventListener("keyup", function() {
			var reviewTextLength = document.querySelector("div.guide_review").firstElementChild.innerText;
			
			if (reviewTextLength > 400) {
				alert("400자 제한입니다");
				document.querySelector("textarea").value = document.querySelector("textarea").value.substr(0,400);
			}
			
			document.querySelector("div.guide_review").firstElementChild.innerText = document.querySelector("textarea").value.length;
		});
		
		// 리뷰 등록 버튼 활성화
		document.querySelector("textarea").addEventListener("blur", function() {
			var reviewTextLength = document.querySelector("div.guide_review").firstElementChild.innerText;
			if (reviewTextLength >= 5 && reviewTextLength <= 400) {
				document.querySelector("button.bk_btn").style.background="#0aba16";
				document.querySelector("button.bk_btn").style.cursor="pointer";
				
			} else {
				document.querySelector("button.bk_btn").style.background="#d1d1d1";
				document.querySelector("button.bk_btn").style.cursor="default";
			}
		});
		
		// form 제출
		document.querySelector("button.bk_btn").addEventListener("click", function() {
			document.querySelector("#score").value = document.querySelector("span.star_rank.gray_star").innerText;
			var reviewTextLength = document.querySelector("div.guide_review").firstElementChild.innerText;
			if (reviewTextLength >= 5 && reviewTextLength <= 400) {
				var form = document.querySelector("#form");
				form.submit();
			}
		});
		
		// 파일 첨부 및 제한
		document.querySelector("#reviewImageFileOpenInput").addEventListener("change", function(evt) {
			const image = evt.target.files[0];
			var filenameExtension = image.name.substr(image.name.lastIndexOf(".")+1).toLowerCase();
			
			if (['jpg', 'png'].indexOf(filenameExtension) <= -1) { // 형식 제한
				alert("첨부 파일 확장자는 jpg 또는 png 만 가능합니다");
				return;
			}
			
			if (image.size > 1024 * 1024 * 10) { // 10MB 제한
				alert("첨부 파일 크기는 10MB 제한입니다");
				return;
			}
			
			document.querySelector("img.item_thumb").closest("li").style.display='inline-block';
			document.querySelector("img.item_thumb").src = window.URL.createObjectURL(image);
			
		});
		
		// 첨부 파일 제거
		document.querySelector("ul.lst_thumb a.anchor").addEventListener("click", function(evt) {
			document.querySelector("#reviewImageFileOpenInput").value='';
			document.querySelector("#reviewImageFileOpenInput").src='';
			evt.target.closest("li").style.display = 'none';
		});
		
	},
	
	// 별점 색칠
	paintStars : function(val) {
		this.stars.forEach((v)=>{v.checked=false});
		
		for (var i = 0; i < val; i++) {
			this.stars[i].checked = true;
		}
	}
		
}

document.addEventListener("DOMContentLoaded", function() {
	var reviewWritePage = new GetReviewWritePage();
});