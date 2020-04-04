package org.apache.ibatis.debug;

import org.apache.ibatis.annotations.Select;

/**
 * @author sky
 * @date 2020/2/16 - 4:38 下午
 */
public interface BlogMapper {
  @Select("select * from Blog where id = #{id}")
  Blog select(int i);
}
