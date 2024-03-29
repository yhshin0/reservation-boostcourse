package kr.or.connect.booking.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import kr.or.connect.booking.service.ReservationsService;

@Controller
public class BookingController {
  @Autowired
  private ReservationsService reservationsService;

  @GetMapping(path = "/mainpage")
  public String main() {
    return "mainpage";
  }

  @GetMapping(path = "/bookinglogin")
  public String bookingLogin() {
    return "bookinglogin";
  }

  @GetMapping(path = "/detail")
  public String detail(@RequestParam(name = "displayInfoId", required = true) int displayInfoId,
      ModelMap model) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "detail";
  }

  @GetMapping(path = "/review")
  public String review(@RequestParam(name = "displayInfoId", required = true) int displayInfoId,
      ModelMap model) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "review";
  }

  @GetMapping(path = "/reserve")
  public String reserve(@RequestParam(name = "displayInfoId", required = true) int displayInfoId,
      ModelMap model) {
    model.addAttribute("displayInfoId", displayInfoId);
    
    //예약일 랜덤 지정
    LocalDateTime randomDate = LocalDateTime.now().plusDays((long) (Math.random()*6));
    String randomDateText = randomDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    model.addAttribute("randomDateText", randomDateText);
    
    return "reserve";
  }

  @GetMapping(path = "/myreservation")
  public String myreservation(
      @RequestParam(name = "reservationEmail", required = true) String reservationEmail,
      HttpSession session, RedirectAttributes redirectAttr) {
    // url에 잘못된 이메일 주소를 입력하여 접속한 경우
    if (reservationsService.getReservations(reservationEmail).getSize() == 0) {
      redirectAttr.addFlashAttribute("message", "wrong");
      return "redirect:bookinglogin";
    }
    session.setAttribute("reservationEmail", reservationEmail);
    return "myreservation";
  }

  @GetMapping(path = "logout")
  public String logout(HttpSession session) {
    session.removeAttribute("reservationEmail");
    return "mainpage";
  }

  @GetMapping(path = "reviewWrite")
  public String reviewWrite(
      @RequestParam(name = "reservationInfoId", required = true) int reservationInfoId,
      @RequestParam(name = "productId", required = true) int productId, ModelMap model) {
    model.addAttribute("reservationInfoId", reservationInfoId);
    model.addAttribute("productId", productId);
    return "reviewWrite";
  }

}
