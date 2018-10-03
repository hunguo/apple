package org.study.shop.dao;

import java.sql.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.study.todomanager.dao.ShopDao;
import org.study.todomanager.model.Member;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DaoTest {

	private static final Logger logger = LoggerFactory.getLogger(DaoTest.class);
	
	@Autowired
	ShopDao dao;
	
	@Test
	public void testInsertMember() throws Exception {
		Member member = new Member();
		member.setMember_id("user1");
		member.setMember_name("User 1");
		member.setMember_pw("1234");
	
		
		dao.insertMember(member);
	}
	
	/*@Test
	public void testCreateMemberTable() {
		dao.createMemberTable();
	}
	
	@Test
	public void testExistMemberTable() {
		if (dao.existMemberTable()) {
			logger.info("table exists");
		} else {
			logger.info("table NOT ..");
		}
	}*/
}