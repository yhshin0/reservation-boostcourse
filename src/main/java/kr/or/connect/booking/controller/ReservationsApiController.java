package kr.or.connect.booking.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import kr.or.connect.booking.dto.Reservations;
import kr.or.connect.booking.service.ReservationsService;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationsApiController {
  @Autowired
  private ReservationsService reservationsService;

  @GetMapping
  public Map<String, Object> getReservations(
      @RequestParam(name = "reservationEmail", required = true) String reservationEmail) {
    return reservationsService.getReservations(reservationEmail);
  }

  @PutMapping("/{reservationInfoId}")
  public int cancelReservation(@PathVariable(name = "reservationInfoId") int reservationInfoId) {
    return reservationsService.cancelReservation(reservationInfoId);
  }

  @PostMapping
  public String makeReservation(@RequestBody Reservations reservations) {
    reservationsService.makeReservation(reservations);
    return "myreservation?reservationEmail=" + reservations.getReservationEmail();
  }

  @PostMapping("/{reservationInfoId}/comments")
  public String writeComment(@PathVariable(name = "reservationInfoId") int reservationInfoId,
      @RequestParam(name = "attachedImage") MultipartFile attachedImage,
      @RequestParam(name = "comment", required = true) String comment,
      @RequestParam(name = "productId", required = true) String productId,
      @RequestParam(name = "score", required = true) int score, HttpServletResponse response,
      HttpSession session) throws IOException {

    String email = (String) session.getAttribute("reservationEmail");
    String currentTime = new SimpleDateFormat("yyyyMMddkkmmss").format(new Date());
    String filePath = "";

    if (attachedImage.getContentType().startsWith("image")) {
      File folder = new File("c:/tmp_upload");
      if (!folder.exists())
        folder.mkdir();
      filePath = (folder.getPath() + "/" + currentTime + "_" + attachedImage.getOriginalFilename())
          .toLowerCase();

      try (FileOutputStream fos = new FileOutputStream(filePath);
          InputStream is = attachedImage.getInputStream();) {
        int readCount = 0;
        byte[] buffer = new byte[1024];
        while ((readCount = is.read(buffer)) != -1) {
          fos.write(buffer, 0, readCount);
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    reservationsService.writeReview(reservationInfoId, filePath, comment, productId, score);
    response.sendRedirect("../../myreservation?reservationEmail=" + email);
    return null;
  }

}
