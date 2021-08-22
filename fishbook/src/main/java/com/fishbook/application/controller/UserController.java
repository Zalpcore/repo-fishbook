package com.fishbook.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fishbook.application.model.User;
import com.fishbook.application.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// display list of Users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listUsers", userService.getAllUsers());
		return "index";
	}

	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "newUser";
	}

	@PostMapping("/saveUser")
	public String saveEmployee(@ModelAttribute("user") User user) {
		// save User to database
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
		// get user from the service
		User user = userService.getUserById(id);
		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "updateUser";
	}

	@GetMapping("/deleteUser/{id}")

	public String deleteUser(@PathVariable(value = "id") Long id) {
		this.userService.deleteUserById(id);
		return "redirect:/";

	}

}
