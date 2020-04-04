package org.apache.ibatis.debug;

/**
 * @author sky
 * @date 2020/2/16 - 4:39 下午
 */
public class Blog {
  private int id;
  private String title;
  private String authorName;


  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public Blog(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Blog{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", authorName='" + authorName + '\'' +
      '}';
  }
}
