package com.product.luffy.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.product.luffy.mapper.CodeMapper;
import com.product.luffy.po.Code;
import com.product.luffy.service.CodeService;

@Service("com.product.luffy.service.impl.CodeService")
public class CodeServiceImpl implements CodeService{
	
	@Resource(name="com.product.luffy.mapper.CodeMapper")
	private CodeMapper codeMapper;
	
	public Code selectCode(Map<String, Object> paramMap) {
		return codeMapper.selectCode(paramMap);
	}
}
