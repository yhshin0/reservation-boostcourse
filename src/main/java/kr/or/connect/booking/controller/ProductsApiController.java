package kr.or.connect.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.dto.ProductListResponse;
import kr.or.connect.booking.dto.ProductResponse;
import kr.or.connect.booking.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsApiController {
  @Autowired
  private ProductsService productsService;

  @GetMapping
  public ProductListResponse getProductList(
      @RequestParam(name = "categoryId", required = false) Integer categoryId,
      @RequestParam(name = "start", required = false, defaultValue = "0") Integer start) {
    return productsService.getProductList(categoryId, start);
  }

  @GetMapping("/{displayInfoId}")
  public ProductResponse getProduct(@PathVariable(name = "displayInfoId") int displayInfoId) {
    return productsService.getProduct(displayInfoId);
  }

}
