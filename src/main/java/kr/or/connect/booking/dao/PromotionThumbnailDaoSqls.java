package kr.or.connect.booking.dao;

public class PromotionThumbnailDaoSqls {
  public static String SELECT_ALL = "SELECT prom.id as id, prom.product_id as product_id, f.save_file_name as product_image_url FROM promotion prom " 
                                  + "INNER JOIN product_image img ON img.product_id = prom.product_id " 
                                  + "INNER JOIN file_info f ON f.id = img.file_id " 
                                  + "WHERE img.type = 'th' ORDER BY id";
}
