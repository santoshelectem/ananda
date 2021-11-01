package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.model.Product;
import net.codejava.service.ProductService;

@RestController
public class AppController {
	/**
	 * service
	 */
	@Autowired
	private ProductService service;
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String viewHomePage(final Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/new")
	public String showNewProductForm(final Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	/**
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product")final  Product product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") final Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") final Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
