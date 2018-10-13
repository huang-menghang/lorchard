package com.ysdevelop.lorchard.merchant.service.impl;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.merchant.entity.Member;
import com.ysdevelop.lorchard.merchant.mapper.MemberDao;
import com.ysdevelop.lorchard.merchant.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	private static final String NICKNAMESTR="nicknameStr";

	@Override
	public PageInfo<Member> list(Map<String, String> queryMap) {
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);
		String nicknameStr = null;
		byte[] nickname = new byte[1024];
        if((nicknameStr=queryMap.get(NICKNAMESTR))!=null){
			nickname = nicknameStr.getBytes();
        }
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);

		List<Member> members = memberDao.list(queryMap,nickname);
		// 将二进制昵称转化为String类型
		PageInfo<Member> pageInfo = new PageInfo<>(members);
		return pageInfo;
	}

	@Override
	public Member getById(Integer id) {
		if(id == null){
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return memberDao.getById(id);
	}

}
