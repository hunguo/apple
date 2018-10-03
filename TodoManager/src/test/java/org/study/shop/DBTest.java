package org.study.shop;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"
})
public class DBTest {
	private static final Logger logger = LoggerFactory.getLogger(DBTest.class);
	
	@Autowired
	private	DataSource ds;
	
	@Autowired
	private SqlSession session;
	
	@Test
	public void testDataSource() {
		Connection conn=null;
		try {
			conn = ds.getConnection();
			logger.info(conn.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(conn);
	}
	
	@Test
	public void testSqlSession() {
	
		Assert.assertNotNull(session);
		logger.info(session.toString());
	}
	
}
