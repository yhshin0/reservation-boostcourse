package kr.or.connect.booking.dto;


public class ProductDisplayInfoImage {
  private int displayInfoImageId;
  private int displayInfoId;
  private int fileId;
  private String fileName;
  private String saveFileName;
  private String contentType;
  private boolean deleteFlag;
  private String createDate;
  private String modifyDate;
  public int getDisplayInfoImageId() {
    return displayInfoImageId;
  }
  public void setDisplayInfoImageId(int displayInfoImageId) {
    this.displayInfoImageId = displayInfoImageId;
  }
  public int getDisplayInfoId() {
    return displayInfoId;
  }
  public void setDisplayInfoId(int displayInfoId) {
    this.displayInfoId = displayInfoId;
  }
  public int getFileId() {
    return fileId;
  }
  public void setFileId(int fileId) {
    this.fileId = fileId;
  }
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public String getSaveFileName() {
    return saveFileName;
  }
  public void setSaveFileName(String saveFileName) {
    this.saveFileName = saveFileName;
  }
  public String getContentType() {
    return contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  public boolean isDeleteFlag() {
    return deleteFlag;
  }
  public void setDeleteFlag(boolean deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getModifyDate() {
    return modifyDate;
  }
  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
  }
  @Override
  public String toString() {
    return "ProductDisplayInfoImage [displayInfoImageId=" + displayInfoImageId + ", displayInfoId="
        + displayInfoId + ", fileId=" + fileId + ", fileName=" + fileName + ", saveFileName="
        + saveFileName + ", contentType=" + contentType + ", deleteFlag=" + deleteFlag
        + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
  }

}
