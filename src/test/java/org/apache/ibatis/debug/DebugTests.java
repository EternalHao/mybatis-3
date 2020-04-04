package org.apache.ibatis.debug;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.global_variables_defaults.AnnotationMapperTest;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author sky
 * @date 2020/2/16 - 11:45 上午
 *
 * resource 上面是我自己创建的
 */
public class DebugTests {
  /**
   * 1、获取sqlSessionFactory对象:
   * 		解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSession；
   * 		注意：【MappedStatement】：代表一个增删改查的详细信息
   *
   * 2、获取sqlSession对象
   * 		返回一个DefaultSQlSession对象，包含Executor和Configuration;
   * 		这一步会创建Executor对象；
   *
   * 3、获取接口的代理对象（MapperProxy）
   * 		getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
   * 		代理对象里面包含了，DefaultSqlSession（Executor）
   * 4、执行增删改查方法
   *
   * 总结：
   * 	1、根据配置文件（全局，sql映射）初始化出Configuration对象
   * 	2、创建一个DefaultSqlSession对象，
   * 		他里面包含Configuration以及
   * 		Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）
   *  3、DefaultSqlSession.getMapper（）：拿到Mapper接口对应的MapperProxy；
   *  4、MapperProxy里面有（DefaultSqlSession）；
   *  5、执行增删改查方法：
   *  		1）、调用DefaultSqlSession的增删改查（Executor）；
   *  		2）、会创建一个StatementHandler对象。
   *  			（同时也会创建出ParameterHandler和ResultSetHandler）
   *  		3）、调用StatementHandler预编译参数以及设置参数值;
   *  			使用ParameterHandler来给sql设置参数
   *  		4）、调用StatementHandler的增删改查方法；
   *  		5）、ResultSetHandler封装结果
   *  注意：
   *  	四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);
   **/
  @Test
   public void testMapper() throws IOException {
     String resource = "org/apache/ibatis/debug/mybatis-config.xml";
     InputStream inputStream = Resources.getResourceAsStream(resource);
     SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
     SqlSession session = sqlSessionFactory.openSession();
     BlogMapper mapper = session.getMapper(BlogMapper.class);
     Blog blog = mapper.selectByIdAndTitle(101,"mozi");
     System.out.println(blog);
     session.close();
   }

  /**
   * 1. mybatis 允许增删改查直接定义一下类型的返回值
   *  Integer Long Boolean void
   * 2. 需要手动提交数据
   *  sqlSessionFactory.openSession() ==》手动提交
   *  sqlSessionFactory.openSession(true) ==》 自动提交
   *
   * @throws IOException
   */
  @Test
  public void testAnnotationMapper() throws IOException {
    String resource = "org/apache/ibatis/debug/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession session = sqlSessionFactory.openSession();
    BlogMapperAnnotation mapper = session.getMapper(BlogMapperAnnotation.class);

    mapper.add(new Blog(1));
    session.commit();
    Blog select = mapper.select(1);

    mapper.update(new Blog(1));
    // 手动提交
    session.commit();
    Blog update = mapper.select(1);

    boolean delete = mapper.delete(1);
    assert delete == true;
    session.commit();

    session.close();
  }

  /**
   * 插件原理
   * 在四大对象创建的时候
   * 1、每个创建出来的对象不是直接返回的，而是
   * 		interceptorChain.pluginAll(parameterHandler);
   * 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）；
   * 		调用interceptor.plugin(target);返回target包装后的对象
   * 3、插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）
   * 		我们的插件可以为四大对象创建出代理对象；
   * 		代理对象就可以拦截到四大对象的每一个执行；
   *
   public Object pluginAll(Object target) {
   for (Interceptor interceptor : interceptors) {
   target = interceptor.plugin(target);
   }
   return target;
   }

   */
  /**
   * 插件编写：
   * 1、编写Interceptor的实现类
   * 2、使用@Intercepts注解完成插件签名
   * 3、将写好的插件注册到全局配置文件中
   *
   */
  @Test
  public void testPlugin(){

  }

  @Test
  public void testPageHelper() throws IOException {
    String resource = "org/apache/ibatis/debug/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession session = sqlSessionFactory.openSession();

    BlogMapper mapper = session.getMapper(BlogMapper.class);
    PageHelper.startPage(1,1);
    List<Blog> blogs = mapper.pageBlogs();
    for (Blog blog :blogs){
      System.out.println(blog);
    }
  }
}
