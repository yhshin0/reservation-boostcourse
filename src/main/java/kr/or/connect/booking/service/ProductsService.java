package kr.or.connect.booking.service;

import java.util.Map;

public interface ProductsService {
  public static int LIMIT = 4;
  public Map<String, Object> getItem(Integer displayInfoId);
  public Map<String, Object> productList(Integer categoryId, Integer start);
}
