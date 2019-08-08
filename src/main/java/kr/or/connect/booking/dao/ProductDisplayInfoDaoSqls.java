package kr.or.connect.booking.dao;

public class ProductDisplayInfoDaoSqls {
  public static String SELECT_ONE = "SELECT p.id as product_id, p.category_id, d.id as display_info_id, c.name as category_name, p.description as product_description, p.content as product_content, p.event as product_event, d.opening_hours, d.place_name, d.place_lot, d.place_street, d.tel as telephone, d.homepage, d.email, d.create_date, d.modify_date FROM product p " 
      + "INNER JOIN category c ON c.id = p.category_id " 
      + "INNER JOIN display_info d ON p.id = d.product_id " 
      + "WHERE d.id = :displayInfoId";
}
