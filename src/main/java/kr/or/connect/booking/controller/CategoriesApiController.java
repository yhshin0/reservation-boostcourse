package kr.or.connect.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.dto.CategoriesResponse;
import kr.or.connect.booking.service.CategoriesService;

@RestController
@RequestMapping(path = "/categories")
public class CategoriesApiController {
  @Autowired
  private CategoriesService categoriesService;

  @GetMapping
  public CategoriesResponse getCategories() {
    return categoriesService.getCategories();
  }


}
