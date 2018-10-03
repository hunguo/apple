package org.study.todomanager.dao;

import java.util.List;

import org.study.todomanager.model.Member;

import org.study.todomanager.model.Schedule;

public interface ShopDao {

	public void init();
	
	public void insertTodo(Schedule schedule) throws Exception;
	
	public void insertMember(Member member) throws Exception;
	
	public boolean existMemberTable();
	
	public void createMemberTable();

	public boolean existMemberId(String id);
	
	public List<Member> readMember() throws Exception;
	
	public Member readMember(String id) throws Exception;
	
	public boolean updateMember(Member member) throws Exception;
	
	public List<Schedule> listSales() throws Exception;
}