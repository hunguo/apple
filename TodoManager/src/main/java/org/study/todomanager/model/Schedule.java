package org.study.todomanager.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class Schedule {

	@NotNull(message="접속중인 아이디를 적어주세요")
	private String member_id;
	@NotNull(message="필수입력")
	private String sche_name;
	@NotNull(message="필수입력")
	private Date sche_time;
	@NotNull(message="필수입력")
	private String sche_check;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getSche_name() {
		return sche_name;
	}
	public void setSche_name(String sche_name) {
		this.sche_name = sche_name;
	}
	public Date getSche_time() {
		return sche_time;
	}
	public void setSche_time(Date sche_time) {
		this.sche_time = sche_time;
	}
	public String getSche_check() {
		return sche_check;
	}
	public void setSche_check(String sche_check) {
		this.sche_check = sche_check;
	}
	@Override
	public String toString() {
		return "Schedule [member_id=" + member_id + ", sche_name=" + sche_name + ", sche_time=" + sche_time
				+ ", sche_check=" + sche_check + "]";
	}
	
	
}
