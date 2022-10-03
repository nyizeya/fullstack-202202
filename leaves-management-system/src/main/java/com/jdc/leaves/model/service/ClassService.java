package com.jdc.leaves.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.leaves.model.dto.input.ClassForm;
import com.jdc.leaves.model.dto.output.ClassDetailsVO;
import com.jdc.leaves.model.dto.output.ClassListVO;

@Service
public class ClassService {
	
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert classInsert;
	
	@Autowired
	private RegistrationService regService;
	@Autowired
	private LeaveService leaveService;
	
	private static final String SELECT_PROJECTION = """
			 select c.id, t.id teacherId, a.name teacherName, t.phone teacherPhone, c.start_date startDate,
			 c.months, c.description, count(r.student_id) studentCount
			 from teacher t join account a on t.id = a.id
			 join classes c on t.id = c.teacher_id
			 left join registration r on c.id = r.classes_id
			 where 1 = 1 
			""";
	
	private static final String GROUP_BY = """
			 group by c.id, t.id, a.name, t.phone, c.start_date, c.months, c.description
			""";

	public ClassService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		
		classInsert = new SimpleJdbcInsert(dataSource);
		classInsert.setTableName("classes");
		classInsert.setGeneratedKeyName("id");
		classInsert.setColumnNames(List.of("teacher_id", "start_date", "months", "description"));
	}

	public List<ClassListVO> search(Optional<String> teacher, Optional<LocalDate> from, Optional<LocalDate> to) {
		var sb = new StringBuffer(SELECT_PROJECTION);
		var params = new HashMap<String, Object>();
		
		sb.append(teacher.filter(StringUtils::hasLength).map(a -> {
			params.put("teacher", a.toLowerCase().concat("%"));
			return " and lower(a.name) like :teacher ";
		}).orElse(""));
		
		sb.append(from.map(f -> {
			params.put("from", Date.valueOf(f));
			return " and c.start_date >= :from ";
		}).orElse(""));
		
		sb.append(to.map(t -> {
			params.put("to", Date.valueOf(t));
			return " and c.start_date <= :to ";
		}).orElse(""));
		
		sb.append(GROUP_BY);
		
		return template.query(sb.toString(), params,
				new BeanPropertyRowMapper<>(ClassListVO.class));
	}
	
	public ClassListVO getClassByClassId(int id) {
		var sql = " %s and c.id = :id ".formatted(SELECT_PROJECTION);
		return template.queryForObject(sql, Map.of("id", id), 
				new BeanPropertyRowMapper<>(ClassListVO.class));
	}

	public ClassForm findById(int id) {
		var sql = """
				 select id, teacher_id teacher, start_date start, months, description 
				 from classes where id = :id  
				""";
		return template.queryForObject(sql, Map.of("id", id), 
				new BeanPropertyRowMapper<>(ClassForm.class));
	}
	
	public ClassDetailsVO findDetailsById(int id) {
		var classDetailsVO = new ClassDetailsVO();
		var sql = "%s and c.id = :id %s".formatted(SELECT_PROJECTION, GROUP_BY);
		
		var classListVO = template.queryForObject(sql, Map.of("id", id), 
				new BeanPropertyRowMapper<>(ClassListVO.class));
		
		classDetailsVO.setClassInfo(classListVO);
		
		classDetailsVO.setRegistrations(regService.searchByClassId(id));
		
		classDetailsVO.setLeaves(leaveService.search(
				Optional.of(id), Optional.empty(), Optional.empty()));
		
		return classDetailsVO;
	}

	public int save(ClassForm form) {
		
		// insert/addNew
		if (form.getId() == 0) {
			return insert(form);
		}
		
		// edit
		return update(form);
	}
	
	private int update(ClassForm form) {
	
		template.update("""
				 update classes set teacher_id = :teacher_id, start_date = :start_date,
				 months = :months, description = :description where id = :id
				""", 
				Map.of(
					"id", form.getId(),
					"teacher_id", form.getTeacher(), 
					"start_date", Date.valueOf(form.getStart()),
					"months", form.getMonths(),
					"description", form.getDescription()
				));
		
		return form.getId();
	}

	private int insert(ClassForm form) {
		return classInsert.executeAndReturnKey(
				Map.of(
					"teacher_id", form.getTeacher(), 
					"start_date", Date.valueOf(form.getStart()),
					"months", form.getMonths(),
					"description", form.getDescription()
				)).intValue();
	}	

}