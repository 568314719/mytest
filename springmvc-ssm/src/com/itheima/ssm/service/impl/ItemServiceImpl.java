package com.itheima.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.ssm.converter.DateConverter;
import com.itheima.ssm.mapper.ItemsMapper;
import com.itheima.ssm.pojo.Items;
import com.itheima.ssm.pojo.ItemsExample;
import com.itheima.ssm.pojo.ItemsExample.Criteria;
import com.itheima.ssm.pojo.QueryVo;
import com.itheima.ssm.service.ItemService;
import com.mysql.jdbc.StringUtils;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public List<Items> getItemList() {
		ItemsExample example = new ItemsExample();
		List<Items> list = itemsMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public Items getItemById(int id) {
		Items items = itemsMapper.selectByPrimaryKey(id);
		return items;
	}

	@Override
	public void updateItem(Items items) {
		itemsMapper.updateByPrimaryKey(items);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Items> selectByExample(QueryVo queryVo) {
		ItemsExample example = new ItemsExample();
		Items items = queryVo.getItems();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isNullOrEmpty(items.getName())) {
			criteria.andNameLike(items.getName());
		}
		if(items.getPrice() != null) {
			criteria.andPriceEqualTo(queryVo.getItems().getPrice());
		}
		
		List<Items> list = itemsMapper.selectByExample(example);
		return list;
	}


	@Override
	public void doDelete(String id) {
		itemsMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

}
