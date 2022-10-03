package com.jdc.leaves.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leaves.model.dto.input.ClassForm;
import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.service.ClassService;
import com.jdc.leaves.model.service.RegistrationService;
import com.jdc.leaves.model.service.TeacherService;

@Controller
@RequestMapping("classes")
public class ClassController {
	
	@Autowired
	private ClassService classService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public String index(
			@RequestParam Optional<String> teacher, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to,
			ModelMap model) {
		
		var result = classService.search(teacher, from, to);
		model.put("list", result);
		
		return "classes";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam Optional<Integer> id, ModelMap model) {
		model.put("teachers", teacherService.getAvailableTeachers());
		return "classes-edit";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute(name = "classForm") ClassForm form, BindingResult result,
			ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("teachers", teacherService.getAvailableTeachers());
			return "classes-edit";
		}
		
		var id = classService.save(form);
		return "redirect:/classes/%d".formatted(id);
	}
	
	@GetMapping("{id}")
	public String showDetails(@PathVariable int id, ModelMap model) {
		var result = classService.findDetailsById(id);
		model.put("dto", result);
		
		return "classes-details";
	}
	
	@GetMapping("registration")
	public String editRegistration(
			@RequestParam(required = false, defaultValue = "0") int classId,
			@RequestParam(required = false, defaultValue = "0") int studentId) {
		
		/*
		 * You Need To Get Classes Information That You Want To Register
		 */
		
		return "registrations-edit";
	}
	
	@PostMapping("registration")
	public String saveRegistration(
			@Valid @ModelAttribute(name = "registForm") RegistrationForm form, 
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "registrations-edit";
		}
		
		registrationService.save(form);
		return "redirect:/classes/registration/%d/%d".formatted(form.getClassId(), form.getStudentId());
	}

	@GetMapping("registration/{classId}/{studentId}")
	public String showRegistrationDetails(
			@PathVariable int classId,
			@PathVariable int studentId, 
			ModelMap model) {
		var result = registrationService.findDetailsById(classId, studentId);
		model.put("dto", result);
		
		return "registrations-details";
	}
	
	@ModelAttribute(name = "classForm")
	public ClassForm classForm(@RequestParam Optional<Integer> id) {
		return id.filter(a -> a > 0)
				.map(classService::findById)
				.orElse(new ClassForm());
	}
	
	@ModelAttribute(name = "registForm")
	public RegistrationForm registForm(
				@RequestParam(required = false, defaultValue = "0") int classId,
				@RequestParam(required = false, defaultValue = "0") int studentId
			) {
		
		// EDIT REGISTRATION
		if (studentId > 0) {
			return registrationService.getFormById(classId, studentId);
		}
		
		/*
		 * Links inside classes-details.jsp page
		 * 
		 * /classes/registration for adding new registration
		 * <c:param name="classId" value="1"></c:param>
		 * if we click this url there will be no studentId, so it will lead us to add new registration page
		 * 
		 * 
		 * /classes/registration/1 for editing existing registration with new students
		 * <c:param name="classId" value="1"></c:param>
		 * <c:param name="studentId" value="2"></c:param>
		 * if we click this url there will be studentId, so it will lead us to edit registration page
		 * 
		 */
		
		// ADD NEW REGISTRATION
		var form = new RegistrationForm();
		form.setClassId(classId);
		return form;
	}

}