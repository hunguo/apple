package org.study.todomanager.web;

import java.beans.PropertyEditorSupport;

public class MemberIdEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String member_id) throws IllegalArgumentException {
		if (member_id.length() == 0) {
			setValue(null);
		} else {
			
			setValue(member_id.toLowerCase());
		}
	}
}