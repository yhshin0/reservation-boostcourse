<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="./css/style.css" rel="stylesheet">
    <link href="./css/mainpage.css" rel="stylesheet">
</head>

<body>
	<!-- start container -->
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a class="lnk_logo" title="예약" href="/booking/"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="bookinglogin" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
            </header>
        </div>
        <hr>
        <!-- start event -->
        <div class="event">
        	<!-- start promotion -->
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <!-- <div class="prev_e">
                            <div class="prev_inn">
                                <a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="nxt_e">
                            <div class="nxt_inn">
                                <a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div> -->
                        <div>
                            <div class="container_visual">
                                <!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 -->
                                <ul class="visual_img">
                                </ul>
                            </div>
                            <span class="nxt_fix" style="display:none;"></span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end promotion -->
            
            <!-- start tab -->
            <div class="section_event_tab">
                <ul class="event_tab_lst tab_lst_min">
                    <li class="item" data-category="">
                        <a class="anchor active"> <span>전체리스트</span> </a>
                    </li>
                </ul>
            </div>
            <!-- end tab -->
            
            <!-- start content -->
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink"></span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    
                    <!-- left item -->
                    <ul class="lst_event_box">
                    </ul>
                    
                    <!-- right item -->
                    <ul class="lst_event_box">
                    </ul>
                    
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn"><span>더보기</span></button>
                    </div>
                    
                </div>
            </div>
            <!-- end content -->
        </div>
        <!-- end event -->
    </div>
    <!-- end container -->
    
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.min.js"></script>
    <script type="text/javascript" src="./js/mainpage.js"></script>

    <script type="rv-template" id="promotionItem">
    <li class="item" style="background-image: url({{productImageUrl}}); ">
        <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
        <div class="event_txt">
            <h4 class="event_txt_tit"></h4>
            <p class="event_txt_adr"></p>
            <p class="event_txt_dsc"></p>
        </div>
    </li>
    </script>
		<script type="rv-template" id="categoryList">
		<li class="item" data-category="{{id}}"><a class="anchor"><span>{{name}}</span></a></li>
		</script>
    <script type="rv-template" id="itemList">
        <li class="item">
            <a class="item_book" href="detail?displayInfoId={{displayInfoId}}">
                <div class="item_preview">
                    <img alt="{{productDescription}}" class="img_thumb" src="{{productImageUrl}}">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>{{productDescription}}</span> <small class="sm">{{placeName}}</small> </h4>
                    <p class="event_txt_dsc">{{productContent}}</p>
                </div>
            </a>
        </li>
    </script>
</body>

</html>