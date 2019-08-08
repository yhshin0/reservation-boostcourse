package kr.or.connect.booking.dao;

public class CategoryDetailDaoSqls {
  public static String SELECT_ALL = "SELECT c.id as id, c.name as name, COUNT(*) as count FROM display_info d "
                                  + "INNER JOIN product p ON d.product_id = p.id "
                                  + "INNER JOIN category c ON p.category_id = c.id "
                                  + "GROUP BY c.id ORDER BY id";
}
