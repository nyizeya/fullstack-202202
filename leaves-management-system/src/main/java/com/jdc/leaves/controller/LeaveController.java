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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leaves.model.dto.input.LeaveForm;
import com.jdc.leaves.model.service.ClassService;
import com.jdc.leaves.model.service.LeaveService;
import com.jdc.leaves.model.service.StudentService;

@Controller
@RequestMapping("leaves")
public class LeaveController {
	
	@Autowired
	private LeaveService service;
	@Autowired
	private ClassService clsService;
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public String index(
			@RequestParam Optional<String> studentId,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> from, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to,
			ModelMap model) {
		
		model.put("dto", service.search(Optional.empty(), from, to));
		
		return "leaves";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam int classId, @RequestParam int studentId, ModelMap model) {
		
		model.put("classInfo", clsService.getClassByClassId(classId));
		model.put("studentInfo", studentService.findInfoById(studentId));
		
		return "leaves-edit";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute(name = "leaveForm") LeaveForm form, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("classInfo", clsService.getClassByClassId(form.getClassId()));
			model.put("studentInfo", studentService.findInfoById(form.getStudent()));
			return "leaves-edit";
		}
		
		service.save(form);
		
		return "redirect:/leaves";
	}

	@ModelAttribute(name = "leaveForm")
	public LeaveForm leaveForm(
			@RequestParam(required = false) Integer classId, 
			@RequestParam(required = false) Integer studentId) {
		
		if (null != classId && null != studentId) {
			var form = new LeaveForm(classId, studentId);
			form.setApplyDate(LocalDate.now());
			return form;
		}
		
		return new LeaveForm();		
	}
	
}