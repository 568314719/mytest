package com.itheima.ssm.pojo;

import java.util.List;

public class QueryVo {

	private Items items;
	
	private String[] ids;
	
	private List<Items> itemList;
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public List<Items> getItemList() {
		return itemList;
	}
	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}
	
}
