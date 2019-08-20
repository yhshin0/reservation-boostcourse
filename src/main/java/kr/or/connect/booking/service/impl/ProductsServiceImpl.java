package kr.or.connect.booking.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.connect.booking.dao.CommentImageFileInfoDao;
import kr.or.connect.booking.dao.CommentReservationInfoDao;
import kr.or.connect.booking.dao.ProductDisplayInfoDao;
import kr.or.connect.booking.dao.ProductDisplayInfoImageDao;
import kr.or.connect.booking.dao.ProductImageDao;
import kr.or.connect.booking.dao.ProductListInfoDao;
import kr.or.connect.booking.dao.ProductPriceDisplayInfoDao;
import kr.or.connect.booking.dto.CommentReservationInfo;
import kr.or.connect.booking.dto.ProductListInfo;
import kr.or.connect.booking.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService{

  @Autowired
  private ProductListInfoDao productListInfoDao;
  @Autowired
  private ProductDisplayInfoDao productDisplayInfoDao;
  @Autowired
  private ProductImageDao productImageDao;
  @Autowired
  private ProductDisplayInfoImageDao productDisplayInfoImageDao;
  @Autowired
  private CommentReservationInfoDao commentReservationInfoDao;
  @Autowired
  private CommentImageFileInfoDao commentImageFileInfoDao;
  @Autowired
  private ProductPriceDisplayInfoDao productPriceDisplayInfoDao; 
  
  
  @Override
  public Map<String, Object> productList(Integer categoryId, Integer start) {
    Map<String, Object> map = new HashMap<String, Object>();
    if(categoryId == null) {
      map.put("totalCount", productListInfoDao.countAll());
      map.put("items", productListInfoDao.selectAll(start, LIMIT));
    } else {
      map.put("totalCount", productListInfoDao.countByCategoryId(categoryId));
      map.put("items", productListInfoDao.selectByCategoryId(categoryId, start, LIMIT));
    }
    return map;
  }

  @Override
  public Map<String, Object> getItem(Integer displayInfoId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("displayInfo", productDisplayInfoDao.selectOne(displayInfoId));
    map.put("productImages", productImageDao.selectImages(displayInfoId));
    map.put("displayInfoImage", productDisplayInfoImageDao.selectOne(displayInfoId));
    map.put("comments", getCommentImage(commentReservationInfoDao.selectComments(displayInfoId)));
    map.put("averageScore", commentReservationInfoDao.averageScore(displayInfoId));
    map.put("productPrices", productPriceDisplayInfoDao.selectPrices(displayInfoId));
    return map;
  }
  
  private List<CommentReservationInfo> getCommentImage(List<CommentReservationInfo> comments) {
    for (CommentReservationInfo comment : comments) {
      comment.setCommentImages(commentImageFileInfoDao.selectImages(comment.getCommentId()));
    }
    return comments;
  }
  

}
