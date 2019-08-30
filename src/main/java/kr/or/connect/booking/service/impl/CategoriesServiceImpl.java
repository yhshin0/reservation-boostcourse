package kr.or.connect.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.connect.booking.dao.CategoryDetailDao;
import kr.or.connect.booking.dto.CategoriesResponse;
import kr.or.connect.booking.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

  @Autowired
  private CategoryDetailDao categoryDetailDao;

  @Override
  public CategoriesResponse getCategories() {
    CategoriesResponse categoriesResponse = new CategoriesResponse();
    categoriesResponse.setItems(categoryDetailDao.selectAll());
    return categoriesResponse;
  }



}
