package kr.or.connect.booking.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageDownloadController {

  @GetMapping("/download")
  public void download(@RequestParam(name = "filePath", required = true) String filePath,
      HttpServletResponse response) throws Exception {
    String fullPath = getFullPath(filePath);

    /*
     * Spring에서 파일 다운로드 받을 때, 한글이 깨지는 현상 해결방법
     * 출처: https://gmltjd0911.github.io/Spring-File-Download-Encoding/
     */
    response.setHeader("Content-Disposition",
        "attachment; filename=\""
            + new String(fullPath.replaceAll("\\D*\\d{14}_", "").getBytes("UTF-8"), "ISO-8859-1")
            + "\";");
    response.setHeader("Content-Transfer-Encoding", "binary");
    response.setHeader("Content-Type",
        "image/" + fullPath.substring(fullPath.lastIndexOf(".") + 1));
    response.setHeader("Pragma", "no-cache;");
    response.setHeader("Expires", "-1;");

    try (FileInputStream fis = new FileInputStream(fullPath);
        OutputStream out = response.getOutputStream();) {
      int readCount = 0;
      byte[] buffer = new byte[1024];
      while ((readCount = fis.read(buffer)) != -1) {
        out.write(buffer, 0, readCount);
      }
    } catch (Exception e) {
      throw new RuntimeException("File Save Error");
    }
  }

  private String getFullPath(String filePath) {
    return "c:/" + filePath;
  }

}
