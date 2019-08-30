package kr.or.connect.booking.dto;

import java.util.List;

public class ProductListResponse {
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
    return "ProductListResponse [totalCount=" + totalCount + ", items=" + items + "]";
  }
  
}
