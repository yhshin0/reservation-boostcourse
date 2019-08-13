package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsApiController {
  @Autowired
  private ProductsService productsService;
  
  @GetMapping
  public Map<String, Object> productList(@RequestParam(name="categoryId", required=false) Integer categoryId,
                                         @RequestParam(name="start", required=false, defaultValue="0") int start) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("totalCount", productsService.getCount(categoryId));
    map.put("items", productsService.getItems(categoryId, start));
    return map;
  }
  
  @GetMapping("/{displayInfoId}")
  public Map<String, Object> selectOne(@PathVariable(name="displayInfoId") int displayInfoId){
    return productsService.getItem(displayInfoId);
  }

}
