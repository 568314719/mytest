package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.bean.BaseDict;
import com.itheima.mapper.BaseDictDao;
import com.itheima.service.BaseDictService;

@Service
public class BaseDictServiceImpl implements BaseDictService {
	
	@Autowired
	private BaseDictDao baseDictDao;
	
	@Override
	public List<BaseDict> getBaseDictList(String typeCode) {
		List<BaseDict> list = baseDictDao.getBaseDictList(typeCode);
		return list;
	}

}
