package org.study.board;

import java.sql.Connection;
import javax.sql.DataSource;  

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DataSourceTest {
	@Autowired
	DataSource ds; 
	SqlSessionFactory factory;
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Test
	public void testConnection() throws Exception {
		try(Connection conn = ds.getConnection()){
			logger.info(conn.toString());
			logger.warn("data warn : "+ conn.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSqlSessionFactoryBean() throws Exception{
		System.out.println(factory);
		
		try(SqlSession session = factory.openSession()){
			System.out.println(session);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	

}
