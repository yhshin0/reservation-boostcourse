package kr.or.connect.booking.dto;

import java.util.List;

public class ProductResponse {
  private ProductDisplayInfo displayInfo;
  private List<ProductImage> productImages;
  private ProductDisplayInfoImage displayInfoImage; 
  private List<CommentReservationInfo> comments; 
  private double averageScore;
  private List<ProductPriceDisplayInfo> productPrices;
  public ProductDisplayInfo getDisplayInfo() {
    return displayInfo;
  }
  public void setDisplayInfo(ProductDisplayInfo displayInfo) {
    this.displayInfo = displayInfo;
  }
  public List<ProductImage> getProductImages() {
    return productImages;
  }
  public void setProductImages(List<ProductImage> productImages) {
    this.productImages = productImages;
  }
  public ProductDisplayInfoImage getDisplayInfoImage() {
    return displayInfoImage;
  }
  public void setDisplayInfoImage(ProductDisplayInfoImage displayInfoImage) {
    this.displayInfoImage = displayInfoImage;
  }
  public List<CommentReservationInfo> getComments() {
    return comments;
  }
  public void setComments(List<CommentReservationInfo> comments) {
    this.comments = comments;
  }
  public double getAverageScore() {
    return averageScore;
  }
  public void setAverageScore(double averageScore) {
    this.averageScore = averageScore;
  }
  public List<ProductPriceDisplayInfo> getProductPrices() {
    return productPrices;
  }
  public void setProductPrices(List<ProductPriceDisplayInfo> productPrices) {
    this.productPrices = productPrices;
  }
  @Override
  public String toString() {
    return "ProductResponse [displayInfo=" + displayInfo + ", productImages=" + productImages
        + ", displayInfoImage=" + displayInfoImage + ", comments=" + comments + ", averageScore="
        + averageScore + ", productPrices=" + productPrices + "]";
  }
  
}
