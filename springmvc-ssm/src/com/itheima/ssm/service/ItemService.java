package com.itheima.ssm.service;

import java.util.List;

import com.itheima.ssm.pojo.Items;
import com.itheima.ssm.pojo.QueryVo;

public interface ItemService {

	List<Items> getItemList();
	Items getItemById(int id);
	void updateItem(Items items);
	List<Items> selectByExample(QueryVo queryVo);
	void doDelete(String id);
}
