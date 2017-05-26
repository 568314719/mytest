package com.itheima.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itheima.ssm.pojo.Items;
import com.itheima.ssm.pojo.QueryVo;
import com.itheima.ssm.service.ItemService;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/*@RequestMapping("/list")
	public ModelAndView getItemList() {
		List<Items> itemList = itemService.getItemList();
		//返回模型和视图
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", itemList);
		//设置逻辑视图
		modelAndView.setViewName("itemList");
		return modelAndView;
	}*/
	
	
	/*
	 * 查询数据显示到页面
	 */
	@RequestMapping("/list")
	public String getItemList(Model model) {
		List<Items> itemList = itemService.getItemList();
		model.addAttribute("itemList", itemList);
		return "itemList";
	}
	
	
	//展示商品信息页面
	//需要接收商品id，可以从Request中取参数。
	@RequestMapping("/itemEdit")
	public /*ModelAndView*/ String showItemInfo(@RequestParam(name="id", required=true, defaultValue="1")Integer iid, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session, Model model) {
		//取商品id
		/*String id = request.getParameter("id");*/
		//根据商品id查询商品信息
		Items items = itemService.getItemById(/*Integer.parseInt(id)*/iid);
		//返回ModelAndView对象
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", items);
		modelAndView.setViewName("editItem");*/
		model.addAttribute("item", items);
		
		//return modelAndView;
		//返回逻辑视图
		return "editItem";
	}
	
	
	@RequestMapping("/updateitem")
	public String editItem(Items items, MultipartFile pictureFile) throws Exception {
		
		// 1）使用方法的形参接收图片
		// 2）生成新文件名可以使用uuid生成，取扩展名
		String fileName = UUID.randomUUID().toString();
		//取原始文件名
		String originalFilename = pictureFile.getOriginalFilename();
		//取扩展名
		String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
		fileName += extName;
		// 3）把图片保存到磁盘。
		pictureFile.transferTo(new File("D:/ruanjian/eclipse/images/" + fileName));
		// 4）把新文件名保存到数据库
		items.setPic(fileName);
		itemService.updateItem(items);
		return "123/success";
		
	}
	
	@RequestMapping("/queryitem")
	public String queryItem(QueryVo queryVo,Model model) {
		String[] ids = queryVo.getIds();
		if(ids != null && ids.length > 0) {
			for (String id : ids) {
				itemService.doDelete(id);
			}
			return "redirect:list.action";
		}else {
			List<Items> list = itemService.selectByExample(queryVo);
			model.addAttribute("itemList", list);
			return "itemList";
		}
	}
	
	@RequestMapping("/item")
	@ResponseBody
	public Items getItemById(Integer id) {
		System.out.println(id);
		Items items = itemService.getItemById(id);
		return items;
	}
	
}











