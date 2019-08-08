package kr.or.connect.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {
  
  @GetMapping(path="/mainpage")
  public String main() {
    return "mainpage";
  }
  @GetMapping(path="/bookinglogin")
  public String bookingLogin() {
    return "bookinglogin";
  }
  @GetMapping(path="/detail")
  public String detail(@RequestParam(name="displayInfoId", required=true) int displayInfoId, ModelMap model) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "detail";
  }
  @GetMapping(path="/review")
  public String review(@RequestParam(name="displayInfoId", required=true) int displayInfoId, ModelMap model) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "review";
  }
  

}
