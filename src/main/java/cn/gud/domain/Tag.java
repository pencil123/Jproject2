package cn.gud.domain;

/**
 * @author 张鲁燕
 * @since 2019/8/8 13:32
 */
public class Tag {
  private int id;
  private String projectName;
  private String tagVersion;
  private  int timestamp;

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTagVersion() {
    return tagVersion;
  }

  public void setTagVersion(String tagVersion) {
    this.tagVersion = tagVersion;
  }

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

}
