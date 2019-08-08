package kr.or.connect.booking.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import kr.or.connect.booking.config.ApplicationConfig;
import kr.or.connect.booking.service.CategoriesService;
import kr.or.connect.booking.service.ProductsService;
import kr.or.connect.booking.service.PromotionsService;

public class ServiceImplTest {

  public static void main(String[] args) {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    CategoriesService categoriesService = ac.getBean(CategoriesService.class);
    ProductsService productsService = ac.getBean(ProductsService.class);
    PromotionsService promotionsService = ac.getBean(PromotionsService.class);
    
    //System.out.println(categoriesService.getItems());
    //System.out.println(productsService.getCount(1));
    //System.out.println(productsService.getItems(3,4,1));
    //System.out.println(promotionsService.getItems());
    //System.out.println(productsService.getItemsInfo(8, 4, 5));
    //System.out.println(promotionsService.getItems());
    //System.out.println(productsService.getAllItems(0));
    //System.out.println(productsService.getItem(1));
  }
}
