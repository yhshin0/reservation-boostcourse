package kr.or.connect.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.dto.PromotionResponse;
import kr.or.connect.booking.service.PromotionsService;

@RestController
@RequestMapping("/promotions")
public class PromotionsApiController {

  @Autowired
  private PromotionsService promotionsService;

  @GetMapping
  public PromotionResponse getItems() {
    return promotionsService.getPromotions();
  }

}
