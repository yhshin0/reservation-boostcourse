package kr.or.connect.booking.dto;

import java.util.List;

public class PromotionResponse {
  private List<PromotionThumbnail> items;

  public List<PromotionThumbnail> getItems() {
    return items;
  }

  public void setItems(List<PromotionThumbnail> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "PromotionResponse [items=" + items + "]";
  }
}
