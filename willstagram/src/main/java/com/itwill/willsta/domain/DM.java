package com.itwill.willsta.domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DM {
	private Integer dmNo;
	private String mId;
	private String mIdYou;
	private String dmDate;
	
	public DM() {
		// TODO Auto-generated constructor stub
	}

	public DM(Integer dmNo, String mId, String mIdYou, String dmDate) {
		this.dmNo = dmNo;
		this.mId = mId;
		this.mIdYou = mIdYou;
		this.dmDate = dmDate;
	}

	public Integer getDmNo() {
		return dmNo;
	}

	public void setDmNo(Integer dmNo) {
		this.dmNo = dmNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmIdYou() {
		return mIdYou;
	}

	public void setmIdYou(String mIdYou) {
		this.mIdYou = mIdYou;
	}

	public String getDmDate() {
		return dmDate;
	}

	public void setDmDate(String dmDate) {
		this.dmDate = dmDate;
	}

	@Override
	public String toString() {
		return "DM [dmNo=" + dmNo + ", mId=" + mId + ", mIdYou=" + mIdYou + ", dmDate=" + dmDate + "]\n";
	}
	
}
	