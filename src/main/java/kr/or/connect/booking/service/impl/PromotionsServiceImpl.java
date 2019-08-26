package kr.or.connect.booking.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.connect.booking.dao.PromotionThumbnailDao;
import kr.or.connect.booking.dto.PromotionThumbnail;
import kr.or.connect.booking.service.PromotionsService;

@Service
public class PromotionsServiceImpl implements PromotionsService {

  @Autowired
  private PromotionThumbnailDao promotionThumbnailDao;

  @Override
  public List<PromotionThumbnail> getItems() {
    return promotionThumbnailDao.selectAll();
  }


}
