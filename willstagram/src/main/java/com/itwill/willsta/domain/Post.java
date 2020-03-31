package com.itwill.willsta.domain;

import java.util.Arrays;

public class Post {
	private Integer pNo;
	private String mId;
	private String mName;
	private String pTitle;
	private String pContents;
	private String hasTag;
	private Integer pViewCount;
	private String pDate;
	private Integer likeCount; //좋아요갯수
	private String[] tagArray;
	private String fileName;
	private String aGo;
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(Integer pNo, String mId, String mName, String pTitle, String pContents, String hasTag, Integer pViewCount,
			String pDate, Integer likeCount, String fileName, String aGo) {
		super();
		this.pNo = pNo;
		this.mId = mId;
		this.mName = mName;
		this.pTitle = pTitle;
		this.pContents = pContents;
		this.hasTag = hasTag;
		this.pViewCount = pViewCount;
		this.pDate = pDate;
		this.likeCount = likeCount;
		this.fileName = fileName;
		this.aGo = aGo;
	}

	public Integer getpNo() {
		return pNo;
	}

	public void setpNo(Integer pNo) {
		this.pNo = pNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpContents() {
		return pContents;
	}

	public void setpContents(String pContents) {
		this.pContents = pContents;
	}

	public String getHasTag() {
		return hasTag;
	}

	public void setHasTag(String hasTag) {
		this.hasTag = hasTag;
	}

	public Integer getpViewCount() {
		return pViewCount;
	}

	public void setpViewCount(Integer pViewCount) {
		this.pViewCount = pViewCount;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	
	
	
	public String getaGo() {
		return aGo;
	}

	public void setaGo(String aGo) {
		this.aGo = aGo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getTagArray() {
		return tagArray;
	}

	public void setTagArray(String[] tagArray) {
		this.tagArray = tagArray;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Post [pNo=" + pNo + ", mId=" + mId + ", mName=" + mName + ", pTitle=" + pTitle + ", pContents="
				+ pContents + ", hasTag=" + hasTag + ", pViewCount=" + pViewCount + ", pDate=" + pDate + ", likeCount="
				+ likeCount + ", tagArray=" + Arrays.toString(tagArray) + ", fileName=" + fileName + "]\n";
	}




	
	
}
