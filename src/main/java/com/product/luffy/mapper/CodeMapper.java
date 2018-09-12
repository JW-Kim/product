package com.product.luffy.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.product.luffy.po.Code;

@Repository("com.product.luffy.mapper.CodeMapper")
public interface CodeMapper {
	public Code selectCode(Map<String, Object> paramMap);
}
