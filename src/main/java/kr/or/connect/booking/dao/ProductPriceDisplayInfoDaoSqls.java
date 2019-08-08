package kr.or.connect.booking.dao;

public class ProductPriceDisplayInfoDaoSqls {
  public static String SELECT_PRICES = "SELECT p.id as product_price_id, p.product_id, p.price_type_name, p.price, p.discount_rate, p.create_date, p.modify_date FROM product_price p " 
      + "INNER JOIN display_info d ON p.product_id = d.product_id " 
      + "WHERE d.id = :displayInfoId "
      + "ORDER BY product_price_id DESC";
}
