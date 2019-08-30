package kr.or.connect.booking.service.impl;

import java.util.List;
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
import kr.or.connect.booking.dto.ProductListResponse;
import kr.or.connect.booking.dto.ProductResponse;
import kr.or.connect.booking.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

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
  public ProductListResponse getProductList(Integer categoryId, Integer start) {
    ProductListResponse productListResponse = new ProductListResponse();
    if (categoryId == null) {
      productListResponse.setTotalCount(productListInfoDao.countAll());
      productListResponse.setItems(productListInfoDao.selectAll(start, LIMIT));
    } else {
      productListResponse.setTotalCount(productListInfoDao.countByCategoryId(categoryId));
      productListResponse.setItems(productListInfoDao.selectByCategoryId(categoryId, start, LIMIT));
    }
    return productListResponse;
  }

  @Override
  public ProductResponse getProduct(Integer displayInfoId) {
    ProductResponse productResponse = new ProductResponse();
    productResponse.setDisplayInfo(productDisplayInfoDao.selectOne(displayInfoId));
    productResponse.setProductImages(productImageDao.selectImages(displayInfoId));
    productResponse.setDisplayInfoImage(productDisplayInfoImageDao.selectOne(displayInfoId)); 
    productResponse.setComments(getCommentImage(commentReservationInfoDao.selectComments(displayInfoId))); 
    productResponse.setAverageScore(commentReservationInfoDao.averageScore(displayInfoId));
    productResponse.setProductPrices(productPriceDisplayInfoDao.selectPrices(displayInfoId));
    return productResponse;
  }

  private List<CommentReservationInfo> getCommentImage(List<CommentReservationInfo> comments) {
    for (CommentReservationInfo comment : comments) {
      comment.setCommentImages(commentImageFileInfoDao.selectImages(comment.getCommentId()));
    }
    return comments;
  }


}
