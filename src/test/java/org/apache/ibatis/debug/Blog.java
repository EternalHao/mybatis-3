package org.apache.ibatis.debug;

import org.apache.ibatis.submitted.parent_reference_3level.Post;

import java.util.List;

/**
 * @author sky
 * @date 2020/2/16 - 4:39 下午
 */
public class Blog {
  private int id;
  private String title;
  private List<Post> posts;

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

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }
}
