package kr.or.connect.booking.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import kr.or.connect.booking.config.ApplicationConfig;

public class DaoTest {

  public static void main(String[] args) {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    //ZEXProductDao dao = ac.getBean(ZEXProductDao.class);
    //ZEXFileInfoDao dao2 = ac.getBean(ZEXFileInfoDao.class);
    //ZEXDisplayInfoDao dao3 = ac.getBean(ZEXDisplayInfoDao.class);
    //ZEXCategoryDao dao4 = ac.getBean(ZEXCategoryDao.class);
    //ZEXProductInfoDao dao5 = ac.getBean(ZEXProductInfoDao.class);
    //ZEXPromotionsDao dao6 = ac.getBean(ZEXPromotionsDao.class);
    //ZEXProductImageDao dao7 = ac.getBean(ZEXProductImageDao.class);
    
    //System.out.println(dao.selectRange(0, 3));
    //System.out.println(dao4.selectAll());
    //System.out.println(dao.selectCountCategory());
    //System.out.println(dao3.selectRange(1, 3));
    //System.out.println(dao5.selectRange(0, 4, "2"));
    //System.out.println(dao5.selectCount("3"));
    //System.out.println(dao6.selectAll());
    //System.out.println(dao7.selectOneTypeMA(7));
    //System.out.println(dao2.selectOne((int) dao7.selectOneTypeMA(7).getFileId()));
    //System.out.println((int)dao5.selectRange(1, 4, 3).get(0).getId());
    //System.out.println(dao.selectWithProductId(0, 4, (int)dao5.selectRange(7, 4, 3).get(0).getId()));
    
    //CategoryDetailDao dao = ac.getBean(CategoryDetailDao.class);
    //System.out.println(dao.selectAll());
    //PromotionThumbnailDao dao = ac.getBean(PromotionThumbnailDao.class);
    //System.out.println(dao.selectAll());
    //ProductListInfoDao dao = ac.getBean(ProductListInfoDao.class);
    //System.out.println(dao.selectAll(5));
    //ProductDisplayInfoDao dao = ac.getBean(ProductDisplayInfoDao.class);
    //System.out.println(dao.selectOne(59));
    //ProductImageDao dao = ac.getBean(ProductImageDao.class);
    //System.out.println(dao.selectImages(2));
    //ProductDisplayInfoImageDao dao = ac.getBean(ProductDisplayInfoImageDao.class);
    //System.out.println(dao.selectOne(2));
    //CommentImageFileInfoDao dao = ac.getBean(CommentImageFileInfoDao.class);
    //System.out.println(dao.selectImages(1));
    //CommentReservationInfoDao dao = ac.getBean(CommentReservationInfoDao.class);
    //System.out.println(dao.selectComments(1));
    //ProductPriceDisplayInfoDao dao = ac.getBean(ProductPriceDisplayInfoDao.class);
    //System.out.println(dao.selectPrices(1));
  }
}
