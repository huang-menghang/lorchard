package com.ysdevelop.loarchard.merchant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.loarchard.merchant.entity.Member;
import com.ysdevelop.loarchard.merchant.mapper.MemberDao;
import com.ysdevelop.loarchard.merchant.service.MemberService;
import com.ysdevelop.lochard.common.exception.WebServiceException;
import com.ysdevelop.lochard.common.result.CodeMsg;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

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
        if((nicknameStr=queryMap.get("nicknameStr"))!=null){
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
