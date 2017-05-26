package com.itheima.mapper;

import java.util.List;
import com.itheima.bean.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getBaseDictList(String typeCode);


}