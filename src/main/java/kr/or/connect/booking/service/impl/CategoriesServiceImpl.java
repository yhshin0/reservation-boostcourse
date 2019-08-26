package kr.or.connect.booking.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.connect.booking.dao.CategoryDetailDao;
import kr.or.connect.booking.dto.CategoryDetail;
import kr.or.connect.booking.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

  @Autowired
  private CategoryDetailDao categoryDetailDao;

  @Override
  public List<CategoryDetail> getItems() {
    return categoryDetailDao.selectAll();
  }



}
