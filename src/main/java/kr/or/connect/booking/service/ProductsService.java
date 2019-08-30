package kr.or.connect.booking.service;

import kr.or.connect.booking.dto.ProductListResponse;
import kr.or.connect.booking.dto.ProductResponse;

public interface ProductsService {
  public static int LIMIT = 4;

  public ProductResponse getProduct(Integer displayInfoId);

  public ProductListResponse getProductList(Integer categoryId, Integer start);
}
