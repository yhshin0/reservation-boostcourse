package kr.or.connect.booking.service;

import java.util.List;
import java.util.Map;
import kr.or.connect.booking.dto.ProductListInfo;

public interface ProductsService {
  public static int LIMIT = 4;
  public List<ProductListInfo> getItems(Integer categoryId, Integer start);
  public int getCount(Integer categoryId);
  public Map<String, Object> getItem(Integer displayInfoId);
}
