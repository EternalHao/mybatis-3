package org.apache.ibatis.debug;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sky
 * @date 2020/2/16 - 4:38 下午
 */
public interface BlogMapper {
  Blog selectByIdAndTitle(@Param("id") int id, @Param("title") String title);

  List<Blog> pageBlogs();

}
