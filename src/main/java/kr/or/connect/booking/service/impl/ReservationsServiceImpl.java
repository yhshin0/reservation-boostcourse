package kr.or.connect.booking.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.or.connect.booking.dao.FileInfoDao;
import kr.or.connect.booking.dao.ProductDisplayInfoDao;
import kr.or.connect.booking.dao.ReservationDao;
import kr.or.connect.booking.dao.ReservationInfoPriceDao;
import kr.or.connect.booking.dao.ReservationUserCommentDao;
import kr.or.connect.booking.dao.ReservationUserCommentImageDao;
import kr.or.connect.booking.dao.ReservationsInfoDao;
import kr.or.connect.booking.dto.FileInfo;
import kr.or.connect.booking.dto.Reservation;
import kr.or.connect.booking.dto.ReservationInfoPrice;
import kr.or.connect.booking.dto.ReservationResponse;
import kr.or.connect.booking.dto.ReservationUserComment;
import kr.or.connect.booking.dto.ReservationUserCommentImage;
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
  @Autowired
  private ReservationUserCommentDao reservationUserCommentDao;
  @Autowired
  private ReservationUserCommentImageDao reservationUserCommentImageDao;
  @Autowired
  private FileInfoDao fileInfoDao;


  @Override
  public ReservationResponse getReservations(String reservationEmail) {
    List<ReservationsInfo> list = new ArrayList<ReservationsInfo>();
    list = reservationsInfoDao.selectByEmail(reservationEmail);
    for (ReservationsInfo info : list) {
      info.setDisplayInfo(productDisplayInfoDao.selectOne(info.getDisplayInfoId()));
      info.setTotalPrice(reservationsInfoDao.selectTotalPrice(info.getReservationInfoId()));
    }
    
    ReservationResponse reservationResponse = new ReservationResponse();
    reservationResponse.setReservations(list);
    reservationResponse.setSize(list.size());
    return reservationResponse;
  }


  @Override
  public int cancelReservation(int reservationInfoId) {
    return reservationsInfoDao.updateCancelFlag(reservationInfoId);
  }


  @Override
  @Transactional
  public int makeReservation(Reservations reservations) {
    /*
     * LocalDateTime 출처: https://jeong-pro.tistory.com/163
     */
    LocalDateTime localDateTime = LocalDateTime.now();
    String currentTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

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

    int reservationInfoId = 0;
    reservationInfoId = reservationDao.insert(reservation);

    List<ReservationInfoPrice> list = reservations.getPrices();
    for (ReservationInfoPrice reservationInfoPrice : list) {
      reservationInfoPrice.setReservationInfoId(reservationInfoId);
      reservationInfoPriceDao.insert(reservationInfoPrice);
    }

    return reservationInfoId;
  }


  @Override
  @Transactional
  public int writeReview(int reservationInfoId, String filePath, String comment, String productId,
      int score) {

    LocalDateTime localDateTime = LocalDateTime.now();
    String currentTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    ReservationUserComment reservationUserComment = new ReservationUserComment();
    reservationUserComment.setProductId(Integer.parseInt(productId));
    reservationUserComment.setReservationInfoId(reservationInfoId);
    reservationUserComment.setScore(score);
    reservationUserComment.setComment(comment);
    reservationUserComment.setCreateDate(currentTime);
    reservationUserComment.setModifyDate(currentTime);
    int reservationUserCommentId = reservationUserCommentDao.insert(reservationUserComment);

    if (filePath == null || filePath.equals(""))
      return reservationUserCommentId;

    FileInfo fileInfo = new FileInfo();
    fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
    fileInfo.setSaveFileName(filePath.substring(filePath.indexOf("\\") + 1));
    fileInfo.setContentType("image/" + filePath.substring(filePath.lastIndexOf(".") + 1));
    fileInfo.setDeleteFlag(0);
    fileInfo.setCreateDate(currentTime);
    fileInfo.setModifyDate(currentTime);
    int fileInfoId = fileInfoDao.insert(fileInfo);

    ReservationUserCommentImage reservationUserCommentImage = new ReservationUserCommentImage();
    reservationUserCommentImage.setReservationInfoId(reservationInfoId);
    reservationUserCommentImage.setReservationUserCommentId(reservationUserCommentId);
    reservationUserCommentImage.setFileId(fileInfoId);
    reservationUserCommentImageDao.insert(reservationUserCommentImage);

    return reservationUserCommentId;
  }



}
