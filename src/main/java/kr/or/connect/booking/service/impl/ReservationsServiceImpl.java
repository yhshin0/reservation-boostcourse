package kr.or.connect.booking.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.or.connect.booking.dao.ProductDisplayInfoDao;
import kr.or.connect.booking.dao.ReservationDao;
import kr.or.connect.booking.dao.ReservationInfoPriceDao;
import kr.or.connect.booking.dao.ReservationsInfoDao;
import kr.or.connect.booking.dto.Reservation;
import kr.or.connect.booking.dto.ReservationInfoPrice;
import kr.or.connect.booking.dto.Reservations;
import kr.or.connect.booking.dto.ReservationsInfo;
import kr.or.connect.booking.service.ReservationsService;

@Service
public class ReservationsServiceImpl implements ReservationsService {
  @Autowired
  private ReservationsInfoDao reservationsInfoDao;
  @Autowired
  private ProductDisplayInfoDao productDisplayInfoDao;
  @Autowired
  private ReservationInfoPriceDao reservationInfoPriceDao;
  @Autowired
  private ReservationDao reservationDao;


  @Override
  public Map<String, Object> getReservations(String reservationEmail) {
    Map<String, Object> map = new HashMap<String, Object>();
    List<ReservationsInfo> list = new ArrayList<ReservationsInfo>();
    list = reservationsInfoDao.selectByEmail(reservationEmail);
    for (ReservationsInfo info : list) {
      int displayInfoId = info.getDisplayInfoId();
      int reservationInfoId = info.getReservationInfoId();
      info.setDisplayInfo(productDisplayInfoDao.selectOne(displayInfoId));
      info.setTotalPrice(reservationsInfoDao.selectTotalPrice(reservationInfoId));
    }
    map.put("reservations", list);
    map.put("size", list.size());
    return map;
  }


  @Override
  public int cancelReservation(int reservationInfoId) {
    return reservationsInfoDao.updateCancelFlag(reservationInfoId);
  }


  @Override
  @Transactional(readOnly=false)
  public int makeReservation(Reservations reservations) {
    int reservationInfoId = 0;
    
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    String currentTime = sdf.format(date);
    
    Reservation reservation = new Reservation();
    reservation.setProductId(reservations.getProductId());
    reservation.setDisplayInfoId(reservations.getDisplayInfoId());
    reservation.setReservationEmail(reservations.getReservationEmail());
    reservation.setReservationName(reservations.getReservationName());
    reservation.setReservationTel(reservations.getReservationTelephone());
    reservation.setReservationDate(reservations.getReservationYearMonthDay());
    reservation.setCancelFlag(0);
    reservation.setCreateDate(currentTime);
    reservation.setModifyDate(currentTime);
    
    reservationInfoId = reservationDao.insert(reservation);
    
    List<ReservationInfoPrice> list = reservations.getPrices();
    for(ReservationInfoPrice reservationInfoPrice : list) {
      reservationInfoPrice.setReservationInfoId(reservationInfoId);
      reservationInfoPriceDao.insert(reservationInfoPrice);
    }
    
    return reservationInfoId;
  }
  
  

}
