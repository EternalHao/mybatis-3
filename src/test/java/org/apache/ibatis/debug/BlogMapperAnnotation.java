package org.apache.ibatis.debug;

import org.apache.ibatis.annotations.*;

/**
 * @author sky
 * @date 2020/2/16 - 4:38 下午
 */
public interface BlogMapperAnnotation {
  @Select("select * from blog where id > #{id}")
  Blog select(@Param("id") int id);

  @Insert("insert into blog(id) values(#{id,jdbcType=NULL})")
  void add(Blog blog);

  @Update("update blog set id=#{id} where id = #{id}")
  void update(Blog blog);

  @Delete("delete from blog where id = #{id}")
  boolean delete(int id);
}
