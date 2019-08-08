package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.service.PromotionsService;

@RestController
@RequestMapping("/promotions")
public class PromotionsApiController {
  
  @Autowired
  PromotionsService promotionsService;
  
  @GetMapping
  public Map<String, Object> getItems() {
    Map<String, Object> map = new HashMap<>();
    map.put("items", promotionsService.getItems());
    return map;
  }

}
