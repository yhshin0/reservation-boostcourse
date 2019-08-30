package kr.or.connect.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.connect.booking.dao.PromotionThumbnailDao;
import kr.or.connect.booking.dto.PromotionResponse;
import kr.or.connect.booking.service.PromotionsService;

@Service
public class PromotionsServiceImpl implements PromotionsService {

  @Autowired
  private PromotionThumbnailDao promotionThumbnailDao;

  @Override
  public PromotionResponse getPromotions() {
    PromotionResponse promotionResponse = new PromotionResponse();
    promotionResponse.setItems(promotionThumbnailDao.selectAll());
    return promotionResponse;
  }


}
