package kr.or.connect.booking.dao;

public class ProductListInfoDaoSqls {
  public static String SELECT_ALL = "SELECT d.id as display_info_id, p.id as product_id, p.description as product_description, d.place_name, p.content as product_content, f.save_file_name as product_image_url FROM product p " 
      + "INNER JOIN display_info d ON p.id = d.product_id " 
      + "INNER JOIN product_image img ON p.id = img.product_id " 
      + "INNER JOIN file_info f ON img.file_id = f.id " 
      + "WHERE type='th' "
      + "ORDER BY d.id LIMIT :start, :limit";
  
  public static String SELECT_BY_CATEGORYID = "SELECT d.id as display_info_id, p.id as product_id, p.description as product_description, d.place_name, p.content as product_content, f.save_file_name as product_image_url FROM product p " 
      + "INNER JOIN display_info d ON p.id = d.product_id " 
      + "INNER JOIN product_image img ON p.id = img.product_id " 
      + "INNER JOIN file_info f ON img.file_id = f.id " 
      + "WHERE type='th' AND p.category_id = :categoryId "
      + "ORDER BY d.id LIMIT :start, :limit";
  
  public static String COUNT_BY_CATEGORYID = "SELECT count(*) FROM display_info d "
      + "INNER JOIN product p ON p.id = d.product_id "
      + "WHERE p.category_id = :categoryId";
  
  public static String COUNT_ALL = "SELECT count(*) FROM display_info d "
      + "INNER JOIN product p ON p.id = d.product_id ";
}
