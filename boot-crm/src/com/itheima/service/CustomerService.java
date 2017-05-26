package com.itheima.service;

import org.springframework.ui.Model;

import com.itheima.bean.Customer;
import com.itheima.bean.QueryVo;
import com.itheima.utils.Page;

public interface CustomerService {

	Page<Customer> getCustomerList(QueryVo queryVo, int page);
	Customer getCustomerById(String id);
	void updateCustomer(Customer customer);

}
