package kr.or.connect.booking.dto;

import java.util.List;

public class CategoriesResponse {
  private List<CategoryDetail> items;

  public List<CategoryDetail> getItems() {
    return items;
  }

  public void setItems(List<CategoryDetail> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "DisplayInfoResponse [items=" + items + "]";
  }
  
}
