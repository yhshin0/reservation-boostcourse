package kr.or.connect.booking.controller;

import java.util.List;
import kr.or.connect.booking.dto.ProductListInfo;

public class ProductList {
  private int totalCount;
  private List<ProductListInfo> items;
  public int getTotalCount() {
    return totalCount;
  }
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }
  public List<ProductListInfo> getItems() {
    return items;
  }
  public void setItems(List<ProductListInfo> items) {
    this.items = items;
  }
  @Override
  public String toString() {
    return "ProductList [totalCount=" + totalCount + ", items=" + items + "]";
  }
}
