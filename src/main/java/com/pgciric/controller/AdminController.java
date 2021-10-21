package com.pgciric.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pgciric.entity.ChangePassword;
import com.pgciric.entity.Authorities;
import com.pgciric.entity.Blog;
import com.pgciric.entity.Category;
import com.pgciric.entity.Client;
import com.pgciric.entity.Delivery;
import com.pgciric.entity.MilkyProduct;
import com.pgciric.entity.Order;
import com.pgciric.entity.Product;
import com.pgciric.entity.ProductResult;
import com.pgciric.entity.Slider;
import com.pgciric.entity.User;
import com.pgciric.repo.AuthoritiesRepo;
import com.pgciric.repo.BlogCategoryRepo;
import com.pgciric.repo.BlogRepo;
import com.pgciric.repo.CategoryRepo;
import com.pgciric.repo.ClientRepo;
import com.pgciric.repo.DeliveryRepo;
import com.pgciric.repo.OrderRepo;
import com.pgciric.repo.ProductRepo;
import com.pgciric.repo.SliderRepo;
import com.pgciric.repo.UserRepo;
import com.pgciric.service.BlogService;
import com.pgciric.service.ProductService;
import com.pgciric.service.SliderService;
import com.pgciric.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private DeliveryRepo deliveryRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthoritiesRepo authoritiesRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private BlogCategoryRepo blogCategoryRepo;
	@Autowired
	private BlogRepo blogRepo;
	@Autowired
	private SliderRepo sliderRepo;

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private SliderService sliderService;
	
	////////////////////////////////
	//			DELIVERY  		  //
	////////////////////////////////
	
	@GetMapping({"/",""})
	public String getHomePage(Model model, Principal principal) {	
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("dateList", deliveryRepo.findAllYearWithExistDelivery());
		
		return "admin/home";
	}
	
	@GetMapping("/delivery-list")
	public String getDeliveryList(@RequestParam int year, @RequestParam int month, Model model, Principal principal) {
		
		List<Delivery> deliveryList = deliveryRepo.findDeliveryByYearAndMonth(month, year);
		
		if(deliveryList.size() < 1) {
			return "redirect:/admin/";
		}else {
			model.addAttribute("deliveryList", deliveryList);
		}
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/delivery-list";
	}
	
	@GetMapping("/sort-delivery")
	public String sortDeliveryByDate(@RequestParam int year, @RequestParam int month, Model model, Principal principal) {

		if(year == 0) {
			model.addAttribute("deliveryList", deliveryRepo.findDeliveryByMonth(month));
		} else {
			model.addAttribute("deliveryList", deliveryRepo.findDeliveryByYearAndMonth(month,year));
		}
		
		model.addAttribute("user", getLoggedUser(principal));
		
		
		return "admin/home";
	}
	
	@GetMapping("/delivery-form")
	public String getDeliveryForm(Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("delivery", new Delivery());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/delivery-form";
	}
	
	@PostMapping("/delivery-save")
	public String saveDelivery(@ModelAttribute Delivery delivery, Principal principal, Model model) throws UnsupportedEncodingException{
		
		deliveryRepo.save(delivery);
		
		model.addAttribute("user", getLoggedUser(principal));
		
		return "redirect:";
	}
	
	@RequestMapping("/delivery-finish")
	public String finishDelivery(@RequestParam int id) {
		
		Delivery d = deliveryRepo.getOne(id);
		
		d.setIsItOver(!d.getIsItOver());
		
		deliveryRepo.save(d);
		
		return "redirect:";
	}
	
	@GetMapping("/delivery-stats-month")
	public String getStatsMonthly(@RequestParam String deliveryDate, Model model, Principal principal) {
		
		int month = Integer.parseInt(deliveryDate.substring(5, 7));
		int year = Integer.parseInt(deliveryDate.substring(0, 4));
		
		List<ProductResult> productList = productRepo.totalProductsForMonthlyDelivery(month, year);
		
		model.addAttribute("productList", productList);
//		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/products-total-sum";
	}
	
	@GetMapping("/delivery-stats-all")
	public String getAllDeliveryWithProduct(Model model, Principal principal) {
		
		List<ProductResult> productList = productRepo.totalProductsForAllDelivery();
		
		model.addAttribute("productList", productList);
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/products-total-sum";
	}
	
	@GetMapping("/delivery-stats-all-by-cateogry")
	public String getAllDeliveryWithProductByCategory(@RequestParam int categoryId, Model model, Principal principal) {
		
		List<ProductResult> productList = productRepo.totalProductsForAllDeliveryByCategory(categoryId);
		
		model.addAttribute("productList", productList);
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/products-total-sum";
	}
	
	////////////////////////////////
	//			ORDER   		  //
	////////////////////////////////
	
	@GetMapping("/order-list")
	public String getDeliveryListPage(@RequestParam int id, Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("orderList", orderRepo.getOrdersByDelivery(id));
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("deliveryId", id);
		model.addAttribute("delivery", deliveryRepo.getOne(id));
		
		return "admin/order-list";
	}
	
	@GetMapping("/order")
	public String getOrderPage(@RequestParam int id, Model model, Principal principal) throws UnsupportedEncodingException {

		Order o = orderRepo.getOne(id);
		model.addAttribute("order", o);
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/order-page";
	}
	
	// deliveryId is a id of specific day delivery
	@GetMapping("/order-form")
	public String getOrderForm(@RequestParam int deliveryId ,Model model, Principal principal) {
		
		model.addAttribute("order", new Order(deliveryRepo.getOne(deliveryId)));
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("clientList", clientRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/order-form";
	}
	
	@GetMapping("/order-form-client-search")
	public String getOrderForm(@RequestParam int deliveryId,@RequestParam String search ,Model model, Principal principal) {
		
		model.addAttribute("order", new Order(deliveryRepo.getOne(deliveryId)));
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("clientList", clientRepo.searchClient(search));
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/order-form";
	}
	
	@PostMapping("/order-save")
	public String saveOrder(@ModelAttribute Order order, Model model, Principal principal) {

		order.setDelivery(deliveryRepo.getOne(order.getDelivery().getId()));
		order.setClient(clientRepo.getOne(order.getClient().getId()));

		order.getOrderItemList().forEach( item ->{
			item.setPrice(item.getProduct().getPrice());
		});

		orderRepo.save(order);
		
		return "redirect:order-list?id="+order.getDelivery().getId();
	}
	
	@PostMapping("/order-update")
	public String updateOrder(@ModelAttribute Order order, Model model, Principal principal) {

		Order savedOrder = orderRepo.getOne(order.getId());
		savedOrder.getOrderItemList().clear();
		savedOrder.setOrderItemList(order.getOrderItemList());
		savedOrder.setDelivery(deliveryRepo.getOne(order.getDelivery().getId()));
		savedOrder.setClient(clientRepo.getOne(order.getClient().getId()));
		savedOrder.setId(order.getId());

		orderRepo.save(savedOrder);
		
		return "redirect:order-list?id="+order.getDelivery().getId();
	}
	
	@RequestMapping("/order-delete")
	public String deleteOrder(@RequestParam int id, @RequestParam int deliveryId, Principal principal) {

		orderRepo.deleteById(id);
		
		return "redirect:order-list?id="+deliveryId;
	}
	
	@GetMapping("/order-page-edit")
	public String editOrder(@RequestParam int id, Model model, Principal principal) {

		model.addAttribute("order", orderRepo.getOne(id));
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("productList", productRepo.findAll());
		
		return "admin/order-page-edit";
	}
	
	////////////////////////////////
	//			CLIENT   		  //
	////////////////////////////////
	
	@GetMapping("/client")
	public String getClientList(Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("deleteOptions", false);	
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("clientList", clientRepo.findAllClientDTO());
		
		return "admin/client-list";
	}
	
	@GetMapping("/client-list-delete")
	public String getClientListDelete(Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("deleteOptions", true);		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("clientList", clientRepo.findAllClientDTO());
		
		return "admin/client-list";
	}
	
	@GetMapping("/client-by-orders")
	public String getClientListByOrders(Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("deleteOptions", false);	
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("clientList", clientRepo.clientListOrderByOrders());
		
		return "admin/client-list";
	}
	
	@GetMapping("/client-form")
	public String getClientForm(Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("client", new Client());
		
		return "admin/client-form";
	}
	
	@GetMapping("/client-edit")
	public String getClientFormEdit(@RequestParam int id, Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("client", clientRepo.getOne(id));
		
		return "admin/client-form";
	}

	@GetMapping("/client-page")
	public String getClientPage(@RequestParam int id, Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("client", clientRepo.getOne(id));
		
		return "admin/client-page";
	}
	
	@PostMapping("/client-save")
	public String saveClient(@ModelAttribute Client client, Principal principal) throws UnsupportedEncodingException{

		if(client.getId() != 0) {
			client.setId(client.getId());
			client.setName(client.getName());
			client.setAdress(client.getAdress());
			client.setMunicipality(client.getMunicipality());
			client.setDetailed(client.getDetailed());
			client.setNumber(client.getNumber());
			clientRepo.save(client);
		}else clientRepo.save(client);
		
		return "redirect:client";
	}
	
	@RequestMapping("/client-delete")
	public String clientOrder(@RequestParam int id, Principal principal) throws UnsupportedEncodingException {

		clientRepo.deleteById(id);
		
		return "redirect:client";
	}
	
	@GetMapping("/client-search")
	public String searchClient(@RequestParam String search ,Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("deleteOptions", false);	
		model.addAttribute("user", getLoggedUser(principal));		
		model.addAttribute("clientList", clientRepo.searchClient(search));
		
		return "admin/client-list";
	}
	
	@GetMapping("/client-list-for-delivery")
	public String clientListByDelivery(@RequestParam int deliveryId, Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("clientList", clientRepo.clientListByDelivery(deliveryId));
		model.addAttribute("delivery", deliveryRepo.getOne(deliveryId));
		model.addAttribute("user", getLoggedUser(principal));		
		
		return "admin/client-for-delivery";
	}
	
	@GetMapping("/client-stats")
	public String getClientStats(@RequestParam int clientId, Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("totalStats", 1);
		model.addAttribute("client", clientRepo.getOne(clientId));
		model.addAttribute("productList", productRepo.findAllProductForClient(clientId));
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/client-stats";
	}
	
	@GetMapping("/client-stats-average")
	public String getClientStatsAverage(@RequestParam int clientId, Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("totalStats", 0);
		model.addAttribute("client", clientRepo.getOne(clientId));
		model.addAttribute("productList", productRepo.findAllProductForClientAverage(clientId));
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/client-stats";
	}
	
	////////////////////////////////
	//			PRODUCT  		  //
	////////////////////////////////
	
	@GetMapping("/product-form")
	public String getProduct(@RequestParam int id ,Model model, Principal principal) throws UnsupportedEncodingException{
		
		if(id == 0) {
			model.addAttribute("product", new Product(new Category()));
		}else {
			model.addAttribute("product", productRepo.getOne(id));
		}
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList", categoryRepo.findAll());
		
		return "admin/product-form";
	}
	
	@GetMapping("/product-list")
	public String getProductList(Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList",categoryRepo.findAll());
		
		return "admin/product-list";
	}
	
	@GetMapping("/product-list-stats")
	public String getProductListStats(Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList",categoryRepo.findAll());
		
		return "admin/product-list-stats";
	}
	
	@GetMapping("/product-stats")
	public String getProductStats(@RequestParam int id, Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("productList", productRepo.getProductStatsByMonth(id));
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList",categoryRepo.findAll());
		
		return "admin/product-stats";
	}
	
	@PostMapping("/product-save")
	public String saveProduct(@ModelAttribute Product product, BindingResult bindingResult, @RequestParam (value="file") MultipartFile multipartFile,
										Model model, Principal principal) throws Exception{
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("user", getLoggedUser(principal));
			model.addAttribute("product",product);
			
			return "product-form";
		}
			
//		for(Pricing pricingList: product.getPricingList()) {
//			pricingList.setProduct(product);
//		}

		productService.saveProduct(product, multipartFile);
//		productRepo.save(product);
		
		return"redirect:product-list";
	}
	
	@RequestMapping("/product-delete")
	public String deleteProduct(@RequestParam int id, Principal principal) throws UnsupportedEncodingException {

		productRepo.deleteById(id);
		
		return "redirect:product-list";
	}
	
	@GetMapping("/product-search")
	public String searchProduct(@RequestParam int categoryId, @RequestParam String search, 
								Model model, Principal principal) throws UnsupportedEncodingException{
			 
		if(categoryId == 0) {
			model.addAttribute("productList", productRepo.searchProduct(search));
		} else if(categoryId != 0 && search.length() <= 1) {
			model.addAttribute("productList", productRepo.productsByCategory(categoryId));
		} else {
			model.addAttribute("productList", productRepo.searchProductByCategory(search,categoryId));
		}
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList",categoryRepo.findAll());
		
		return "admin/product-list";
	}
	
	@GetMapping("/product-search-stats")
	public String searchProductStats(@RequestParam int categoryId, @RequestParam String search, 
								Model model, Principal principal) throws UnsupportedEncodingException{
			 
		if(categoryId == 0) {
			model.addAttribute("productList", productRepo.searchProduct(search));
		} else if(categoryId != 0 && search.length() <= 1) {
			model.addAttribute("productList", productRepo.productsByCategory(categoryId));
		} else {
			model.addAttribute("productList", productRepo.searchProductByCategory(search,categoryId));
		}
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList",categoryRepo.findAll());
		
		return "admin/product-list-stats";
	}
	
	@GetMapping("/products-total")
	public String getTotalProductsForDelivery(@RequestParam int deliveryId, Model model,Principal principal) throws UnsupportedEncodingException {

		List<ProductResult> productList = productRepo.totalProductsForDelivery(deliveryId);
		
		model.addAttribute("productList", productList);
		model.addAttribute("delivery", deliveryRepo.getOne(deliveryId));
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		

		return "admin/products-total";
	}
	
	@GetMapping("/other-products")
	public String getTotalOtherProductsForDeliveryByCategory(@RequestParam int deliveryId, @RequestParam int categoryId, Model model,
															 Principal principal) throws UnsupportedEncodingException {
		List<MilkyProduct> productList = productRepo.totalProductsForDeliveryByCategory(deliveryId, categoryId);
		
		model.addAttribute("productList", productList);
		model.addAttribute("delivery", deliveryRepo.getOne(deliveryId));
		model.addAttribute("user", getLoggedUser(principal));
		

		return "admin/milk-products";
	}
	
	//////////////////////////////////////////////
	//			CATEGORY						//
	//////////////////////////////////////////////
	
	@GetMapping("/category-list")
	public String getCategoryList(Model model,Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/category-list";
	}
	
	@GetMapping("/category-form")
	public String getCategory(@RequestParam int id, Model model,Principal principal) throws UnsupportedEncodingException {
		//getOne fineOne
		if(id == 0) {
			model.addAttribute("category", new Category());
		}else {
			model.addAttribute("category", categoryRepo.getOne(id));
		}
		
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/category-form";
	}
	
	@PostMapping("/category-save")
	public String saveCategory(@ModelAttribute Category category, Model model,Principal principal) throws UnsupportedEncodingException {
		
		categoryRepo.save(category);
		
		return "redirect:category-list";
	}
	
	@RequestMapping("/category-delete")
	public String deleteCategory(@RequestParam int id, Model model,Principal principal) throws UnsupportedEncodingException {
		
		categoryRepo.deleteById(id);
		
		return "redirect:category-list";
	}
	
	//////////////////////////////////////////////
	//			USERS   						//
	//////////////////////////////////////////////
	
	@GetMapping("/user-list")
	public String getUserList(Model model,Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("userList", userRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/user-list";
	}
	
	@GetMapping("/user-form")
	public String getUserForm(Model model, Principal principal) throws UnsupportedEncodingException{
		
		model.addAttribute("user", new User());		
		model.addAttribute("roles", userRepo.getRoles());
		model.addAttribute("loggedUser", getLoggedUser(principal));
		
		return "admin/user-form";
	}
	
	@PostMapping(value="/user-save", consumes = "multipart/form-data")
	public String saveUser(@ModelAttribute User user, BindingResult bindingResult, @RequestParam (value="file") MultipartFile multipartFile, 
							Model model, Principal principal) throws Exception {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("user", new User());		
			model.addAttribute("roles", userRepo.getRoles());
			model.addAttribute("loggedUser", getLoggedUser(principal));
			
			return "redirect:user-form";
		}

		String password = user.getPassword();

		String passwordEncoder = new BCryptPasswordEncoder().encode(password);

		user.setEnabled(true);
		user.setPassword("{bcrypt}" + passwordEncoder);
		
		userService.saveUser(user, multipartFile);
		
		return "redirect:user-list";
	}
	
	@RequestMapping(value="/user-edit", consumes = "multipart/form-data")
	public String updateUser(@ModelAttribute User user, Principal principal, @RequestParam (value="file") MultipartFile multipartFile) throws Exception {
		
		User u = getLoggedUser(principal);
									
		
		u.setName(user.getName());
		u.setSurname(user.getSurname());
		
		userService.saveUser(u,multipartFile);

		return "redirect:user-edit-profile";
	}
	
	@RequestMapping("/user-enabled")
	public String enabledUser(@RequestParam String username) {

		User u = userRepo.getUserByUsername(username);
		u.setEnabled(!u.getEnabled());
		userRepo.save(u);

		return "redirect:user-list";
	}
	
	@RequestMapping("/user-delete")
	public String deleteUser(@ModelAttribute User user) {
		//change delte by username
		// and metod will recive username as a request param
		userRepo.delete(user);
		
		return "redirect:user-list";
	}

	@RequestMapping("/user-edit-profile")
	public String editUserProfile(Principal principal, Model model) {
	
		model.addAttribute("user", getLoggedUser(principal));

		return "admin/user-edit-profile";
	}
	
	@RequestMapping("/user-change-password")
	public String getUserChangePassword(Principal principal, Model model) throws UnsupportedEncodingException {

		model.addAttribute("changePassword", new ChangePassword());
		model.addAttribute("user", getLoggedUser(principal));

		return "admin/user-change-password";
	}

	@RequestMapping("user-change-password-action")
	public String getUserChangePasswordAction(@ModelAttribute ChangePassword changePassword, Principal principal,
			Model model) {

		if (changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {

			User user = userRepo.getUserByUsername(principal.getName());

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(changePassword.getOldPassword(), user.getPassword().replace("{bcrypt}", ""))) {

				user.setPassword("{bcrypt}" + encoder.encode(changePassword.getNewPassword()));

				userRepo.save(user);
			} else {
//nije dobro unet stari password
				return "admin/user-change-password";
			}

		} else {
// nepoklapaju se new i confirm pass
			return "admin/user-change-password";
		}

		return "redirect:user-edit-profile";
	}
	
	@GetMapping("/user-privilages")
	public String getUserPrivilages(Model model, Principal principal) throws UnsupportedEncodingException {
		
		model.addAttribute("userList", getAllUsersWithAuthorities());
		model.addAttribute("roles", userRepo.getRoles());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/user-privilages";
	}
	
	@RequestMapping("/user-change-authority")
	public String chageUserAuthority(@RequestParam String value, @RequestParam String username) {
		
		Authorities a = authoritiesRepo.getAuthoritiesByUsername(username);
		a.setAuthority(value);
		
		authoritiesRepo.save(a);
		
		return "redirect:user-privilages";
	}
	
	@GetMapping("/user-profile")
	public String getUserProfile(Model model, Principal principal) {
		
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/user-profile";
	}
	
	//////////////////////////////////////////////
	//			BLOG     						//
	//////////////////////////////////////////////
	
	@GetMapping("/blog-form")
	public String getBlogForm(@RequestParam int id, Model model, Principal principal) {
		
		if(id == 0) {
			model.addAttribute("blog", new Blog());
		}else {
			model.addAttribute("blog", blogRepo.getOne(id));
		}
		
		model.addAttribute("user", getLoggedUser(principal));
		model.addAttribute("categoryList", blogCategoryRepo.findAll());
		
		
		return "admin/blog-form";
	}
	
	@RequestMapping(value="/blog-save", consumes = "multipart/form-data")
	public String saveBlog(@ModelAttribute Blog blog, Principal principal, BindingResult bindingResult, 
							@RequestParam (value="file") MultipartFile multipartFile, Model model) throws Exception{

		User user = userRepo.getUserByUsername(principal.getName());
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("blog", blog);
			
			return "admin/blog-form";
		}
		
        blog.setUser(user);
		        
        blogService.saveBlog(blog, multipartFile);
		
		return "redirect:/admin/";
	}
	
	@GetMapping("/blog-list")
	public String getBlogList(Principal principal, Model model) {
		
		model.addAttribute("blogList", blogRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/blog-list";
	}
	
	
	//////////////////////////////////////////////
	//			SLIDER    						//
	//////////////////////////////////////////////
	
	@GetMapping("/slider-form")
	public String getSliderForm(Principal principal, Model model, @RequestParam int id) {
		
		if(id == 0) {
			model.addAttribute("user", getLoggedUser(principal));
			model.addAttribute("slider", new Slider());
		}
		return "admin/slider-form";
	}
	
	@GetMapping("/slider-list")
	public String getSliderList(Principal principal, Model model) {
		
		model.addAttribute("sliderList", sliderRepo.findAll());
		model.addAttribute("user", getLoggedUser(principal));
		
		return "admin/slider-list";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/slider-save", consumes = "multipart/form-data")
	public String saveSlider(Principal principal, @ModelAttribute Slider slider, BindingResult bindingResult,
							 @RequestParam (value="file") MultipartFile multipartFile,
							 Model model)throws Exception {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("slider", slider);
			
			return "admin/slider-form";
		}
		
		sliderService.saveSlider(slider, multipartFile);
		
		return "redirect:/admin/slider-list?id=0";
	}

	//////////////////////////////////////////////
	//			HELP METHOD						//
	//////////////////////////////////////////////
	
	private User getLoggedUser(Principal principal) {

		User user = userRepo.getUserByUsername(principal.getName());
		
		return user;
	}

	
	public File convertMultipartFileToFile(MultipartFile multipartFile) {

        File convFile = new File(multipartFile.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

        } catch (IOException e) {

        }

        return convFile;
    }
	
	public List<User> getAllUsersWithAuthorities(){
		
		List<User> users = userRepo.findAll();
		
		List<Authorities> auth = authoritiesRepo.findAll();
		
		for(User user: users) {
			for(Authorities a: auth) {
				if(user.getUsername().equalsIgnoreCase(a.getUsername())) {
//					user.setAuthority(a.getAuthority());
				}
			}
		}
		return users;
	}

}
