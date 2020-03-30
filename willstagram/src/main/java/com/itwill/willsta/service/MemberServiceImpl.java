package com.itwill.willsta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.willsta.domain.Member;
import com.itwill.willsta.repository.MemberDao;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao memberDao;
	
	@Override
	public boolean insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public Member selectById(String mId) {
		return memberDao.selectById(mId);
	}

	@Override
	public boolean updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public boolean deleteMember(String mId) {
		return memberDao.deleteMember(mId);
	}

	@Override
	public boolean existedMember(String mId) {
		return memberDao.existedMember(mId);
	}

	@Override
	public Member findId(String mEmail, String mName) {
		return memberDao.findId(mEmail, mName);
	}

	@Override
	public Member findPw(String mId, String mName) {
		return memberDao.findPw(mId, mName);
	}

	@Override
	public Member getTempPw(String mPass, String mId) {
		return memberDao.getTempPw(mPass, mId);
	}
	
	@Override
	public List<Member> memberList() {
		return memberDao.memberList();
	}

	@Override
	public List<Member> findMemberList(String mid) {
		return memberDao.findMemberList(mid);
	}

}
