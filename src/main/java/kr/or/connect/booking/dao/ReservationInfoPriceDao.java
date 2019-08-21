package kr.or.connect.booking.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.ReservationInfoPrice;

@Repository
public class ReservationInfoPriceDao {
  private SimpleJdbcInsert insertAction;
  
  public ReservationInfoPriceDao(DataSource dataSource) {
    this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
  }
  
  public int insert(ReservationInfoPrice reservationInfoPrice ) {
    SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
    return insertAction.execute(params);
  }
  

}
