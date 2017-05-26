package com.itheima.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itheima.bean.BaseDict;
import com.itheima.bean.Customer;
import com.itheima.bean.QueryVo;
import com.itheima.service.BaseDictService;
import com.itheima.service.CustomerService;
import com.itheima.utils.Page;


/**
 * 客户管理
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class CustomerController {
		
	@Value("${cust.typecode.source}")
	private String custSource;
	@Value("${cust.typecode.industry}")
	private String custIndustry;
	@Value("${cust.typecode.level}")
	private String custLevel;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BaseDictService baseDictService;
	
	@RequestMapping("list")
	public String list(QueryVo queryVo,Model model,@RequestParam(defaultValue="1") Integer page) throws Exception {
		//解决get乱码的问题
		if(queryVo.getCustName() != null && !"".equals(queryVo.getCustName())) {
			String custName = new String(queryVo.getCustName().getBytes("iso-8859-1"), "utf-8");
			queryVo.setCustName(custName);
		}
		
		//查询条件初始化
		//查询客户来源
		List<BaseDict> source = baseDictService.getBaseDictList(custSource);
		List<BaseDict> industry = baseDictService.getBaseDictList(custIndustry);
		List<BaseDict> level = baseDictService.getBaseDictList(custLevel);
		
		//把数据传给jsp
		model.addAttribute("fromType", source);
		model.addAttribute("industryType", industry);
		model.addAttribute("levelType", level);
		
		////查询客户列表
		Page<Customer> customerList = customerService.getCustomerList(queryVo,page);
		//传递给jsp
		model.addAttribute("page", customerList);
		//查询条件回显
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		return "customer";
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public Customer getCustomerById(String id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public void updateCustomer(Customer customer) {
		customerService.updateCustomer(customer);
	}
}
