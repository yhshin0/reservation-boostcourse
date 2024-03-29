<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTDHTML4.01Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
      @charset "UTF-8";
      [ng\:cloak],
      [ng-cloak],
      [data-ng-cloak],
      [x-ng-cloak],
      .ng-cloak,
      .x-ng-cloak,
      .ng-hide:not(.ng-hide-animate) {
        display: none !important;
      }
      ng\:form {
        display: block;
      }
      .ng-animate-shim {
        visibility: hidden;
      }
      .ng-anchor {
        position: absolute;
      }
    </style>
    <!--<base href="/">-->
    <base href=".">
    <!--[if IE]>
        <script type="text/javascript">
         // Fix for IE ignoring relative base tags.
         (function () {
           var baseTag = document.getElementsByTagName('base')[ 0 ];
           baseTag.href = baseTag.href;
         })();
        </script>
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
    <meta http-equiv="pragma" content="no-cache">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <title translate="CM-NBOOKING">네이버 예약</title>
    <link rel="stylesheet" href="./css/bookinglogin.css">
  </head>
  <body class="biz  ko">
  <c:if test="${reservationEmail ne null }">
	  <c:redirect url="./mainpage"/>
  </c:if>
    <app>
      <toast-alert class="top_info hide booking_alert">
        <p class="top_info_txt">
          <i class="fn fn-info1" aria-hidden="true"></i>
          <span class="_toast_alert_text"></span>
        </p>
        <a href="mainpage" class="top_info_close" data-tst_alt_close="0">
          <i class="fn fn-close" aria-hidden="true"></i>
          <span class="sr_only" translate="CM-CLOSE">닫기</span>
        </a>
      </toast-alert>
      <!---->
      <div class="_view_content" ui-view="content" id="container">
        <booked-confirm>
          <div class="booking_login">
            <h1 class="login_header">
              <a href="./mainpage" class="nbooking_logo spr_bi txt_logo">
                <span translate="CM-NBOOKING">네이버 예약</span>
              </a>
            </h1>
            <!---->
            <div>
              <form name="confirm_form" class="ng-pristine ng-valid" id="form1" action="./myreservation">
                <h2 class="login_header_sub border_bottom">
                  <span translate="CM-NON_MEMBER_BK_CONFIRMATION">비회원 예약확인</span>
                </h2>
                <div class="login_form">
                  <label class="label_form" for="resrv_id" translate="CM-BOOKING_NUMBER">예약자 이메일 입력</label>
                  <input type="text" class="login_input ng-pristine ng-untouched ng-valid ng-empty" id="resrv_id" name="reservationEmail" aria-invalid="false" placeholder="crong@naver.com" title="예매자이메일">
                </div>
                <button onclick="return false;" class="login_btn confirm">
                  <span translate="CM-MY_BOOKING_CHECK">내 예약 확인</span>
                </button>
                <!---->
              <!---->
                <!---->
                <!---->
              </form>
            </div>
            <!---->
            <!---->
          </div>
        </booked-confirm>
      </div>
      <footer aria-hidden="false">
        <!---->
        <!-- <go-to-top  style="display:none;">
                 <div class="gototop"  aria-hidden="false"> <a href="https://m.booking.naver.com/#" class="lnk_top" > <span class="lnk_top_text" translate="CM-TOP">TOP</span> </a> </div>
              </go-to-top> -->
        <!---->
        <!---->
          <div id="footer" class="footer"> <!-- <ul class="lst_nav" style="display:none;">
                     
                      <li class="lnk_item" > <a href="" class="anchor _logout_link"> <span translate="CM-LOGIN">로그인</span> </a> </li>
                      
                      <li class="lnk_item">
                       
                      </li>
                      
                      <li class="lnk_item"> <a href="http://m.naver.com/services.html" class="anchor"> <span translate="CM-WHOLE_SERVICE">전체서비스</span> </a> </li>
                     </ul> -->
          <p class="dsc_footer" translate="CM-FOOTER_DESC">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
          <!-- <dl class="box_vcard" style="display:none;">
                    <dt class="tit_dt"> <a href="https://m.booking.naver.com/#" class="lnk_naver"   title="펼쳐보기"> <span translate="CM-NAVER_INC">(주)네이버 사업자정보</span> <i class="fn fn-down2" aria-hidden="true" ></i> </a> </dt>
                    
                   </dl> -->
          <!-- <ul class="lst_link" style="display:none;">
                    <li class="item"> <a class="anchor" ui-sref="policy" href="https://m.booking.naver.com/policy"> <span translate="CM-TERMS">이용약관</span> </a> </li>
                    <li class="item"> <a class="anchor" href="https://nid.naver.com/mobile/user/help/termAgree.nhn?m=viewTermAgree3"> <strong class="policy_em" translate="CM-PRIVACY_POLICY">개인정보처리방침</strong> </a> </li>
                    <li class="item"> <a href="https://m.booking.naver.com/#" class="anchor" > <span translate="CM-CUSTOMER_CENTER_NBOOKING">네이버 예약 고객센터</span> </a> </li>
                   </ul> -->
          <span class="copyright" translate="CM-COPYRIGHT_NAVER">© NAVER Corp.</span>
        </div>
        <!---->
        <!---->
      </footer>
      <dialog-alert>
        <!---->
      </dialog-alert>
      <c:choose>
    	  <c:when test="${message ne null }">
     		  <div class="modal">
      	</c:when>
      	<c:otherwise>
      		<div class="modal" style="display:none;">
      	</c:otherwise>
      </c:choose>
        <div class="sel_box_on">
           <div class="dimm_dark"  role="button" tabindex="0"></div>
           <div class="select_layer">
            <div class="select_tit">
             <h5 class="header_h5"> <span>해당하는 이메일이 없습니다</span> </h5>
             <a class="close"  title="MBK-selectBox-CLOSE">X</a> 
            </div>
           </div>
        </div>
      </div>
    </app>
    <script type="text/javascript" src="./js/bookingLogin.js"></script>
  </body>
</html>