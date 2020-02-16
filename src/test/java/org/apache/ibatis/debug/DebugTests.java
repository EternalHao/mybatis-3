package org.apache.ibatis.debug;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.permissions.Resource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sky
 * @date 2020/2/16 - 11:45 上午
 *
 * resource 上面是我自己创建的
 */
public class DebugTests {
  @Test
   public void testDebug() throws IOException {
     String resource = "mybatis-config.xml";
     InputStream inputStream = Resources.getResourceAsStream(resource);
     SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
     SqlSession session = sqlSessionFactory.openSession();
     BlogMapper mapper = session.getMapper(BlogMapper.class);
     Blog blog = mapper.selectBlog(101);
     session.close();
   }
}
