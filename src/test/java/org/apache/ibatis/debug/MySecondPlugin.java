package org.apache.ibatis.debug;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author sky
 * @date 2020/2/20 - 5:06 下午
 */
@Intercepts(
  @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
)
public class MySecondPlugin implements Interceptor {
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("MySecondPlugin...intercept:"+invocation.getMethod());
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    // TODO Auto-generated method stub
    System.out.println("MySecondPlugin...plugin:"+target);
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
    // TODO Auto-generated method stub
  }
}
