package kr.or.connect.booking.dao;

public class ProductImageDaoSqls {
  public static String SELECT_IMAGES = "SELECT img.product_id, img.id as product_image_id, img.type, f.id as file_info_id, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date FROM product_image img " 
      + "INNER JOIN display_info d ON img.product_id = d.product_id " 
      + "INNER JOIN file_info f ON f.id = img.file_id " 
      + "WHERE d.id = :displayInfoId AND img.type NOT IN('th') "
      + "ORDER BY file_info_id LIMIT 0,2";
  
}
