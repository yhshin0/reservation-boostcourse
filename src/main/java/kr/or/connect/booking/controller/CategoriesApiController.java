package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.service.CategoriesService;

@RestController
@RequestMapping(path = "/categories")
public class CategoriesApiController {
  @Autowired
  private CategoriesService categoriesService;

  @GetMapping
  public Map<String, Object> getCategories() {
    Map<String, Object> map = new HashMap<>();
    map.put("items", categoriesService.getItems());
    return map;
  }


}
