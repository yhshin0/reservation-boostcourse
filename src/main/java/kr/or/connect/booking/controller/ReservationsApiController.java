package kr.or.connect.booking.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.booking.dto.Reservations;
import kr.or.connect.booking.service.ReservationsService;

@RestController
@RequestMapping(path="/reservations")
public class ReservationsApiController {
  @Autowired
  private ReservationsService reservationsService;
  
  @GetMapping
  public Map<String, Object> getReservations(@RequestParam(name="reservationEmail", required=true) String reservationEmail) {
    return reservationsService.getReservations(reservationEmail);
  }
  
  @PutMapping("/{reservationInfoId}")
  public int cancelReservation(@PathVariable(name="reservationInfoId") int reservationInfoId) {
    return reservationsService.cancelReservation(reservationInfoId);
  }
  
  @PostMapping
  public String makeReservation(@RequestBody Reservations reservations) {
    reservationsService.makeReservation(reservations);
    return "myreservation?reservationEmail="+reservations.getReservationEmail();
  }
  
}
