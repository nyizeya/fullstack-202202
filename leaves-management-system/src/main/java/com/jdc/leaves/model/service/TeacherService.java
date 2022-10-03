package com.jdc.leaves.model.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leaves.model.dto.input.TeacherForm;
import com.jdc.leaves.model.dto.output.IdWithName;
import com.jdc.leaves.model.dto.output.TeacherListVO;

@Service
public class TeacherService {
	
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert accountInsert;
	private SimpleJdbcInsert teacherInsert;
	
	@Autowired
	private PasswordEncoder encoder;	
	
	private static final String SELECT_PROJECTION = """
			 select a.name, a.email, t.phone, t.id, t.assign_date assignDate, count(c.teacher_id) classCount
			 from account a join teacher t on a.id = t.id
			 left join classes c on c.teacher_id = t.id where 1 = 1
			""";
	
	private static final String GROUP_BY = """
			 group by t.id, a.name, a.email, t.phone, t.assign_date 
			""";

	public TeacherService(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
		
		accountInsert = new SimpleJdbcInsert(ds);
		accountInsert.setTableName("account");
		accountInsert.setGeneratedKeyName("id");
		accountInsert.setColumnNames(List.of("name", "role", "email", "password"));
		
		
		teacherInsert = new SimpleJdbcInsert(ds);
		teacherInsert.setTableName("teacher");
	}

	public List<TeacherListVO> search(Optional<String> name, Optional<String> phone, Optional<String> email) {
		var sb = new StringBuffer(SELECT_PROJECTION);
		var params = new HashMap<String, Object>();
		
		sb.append(email.filter(StringUtils::hasLength).map(a -> {
			params.put("email", a.toLowerCase().concat("%"));
			return " and a.email like :email";
		}).orElse(""));
		
		sb.append(phone.filter(StringUtils::hasLength).map(a -> {
			params.put("phone", a.concat("%"));
			return " and t.phone like :phone ";
		}).orElse(""));
		
		sb.append(name.filter(StringUtils::hasLength).map(a -> {
			params.put("name", a.toLowerCase().concat("%"));
			return " and lower(a.name) like :name ";
		}).orElse(""));
		
		sb.append(GROUP_BY);
		return template.query(sb.toString(), params, 
				new BeanPropertyRowMapper<>(TeacherListVO.class));
	}

	public TeacherListVO findById(int id) {
		var sql = "%s and %s %s".formatted(SELECT_PROJECTION, "t.id = :id", GROUP_BY);
		return template.queryForObject(sql, Map.of("id", id), 
				new BeanPropertyRowMapper<>(TeacherListVO.class));
	}
	
	public List<IdWithName> getAvailableTeachers() {
		return template.query("""
				 select t.id, a.name from teacher t join account a on t.id = a.id where a.deleted = :deleted
				""", 
				Map.of("deleted", false), 
				new BeanPropertyRowMapper<>(IdWithName.class));
	}

	@Transactional
	public int save(TeacherForm form) {
		if (form.getId() == 0) {
			// Add New
			return insert(form);
		}
		
		// update
		return update(form);
	}

	private int insert(TeacherForm form) {
		
		var generatedKey = accountInsert.executeAndReturnKey(Map.of(
				"name", form.getName(),
				"role", "Teacher",
				"email", form.getEmail(),
				"password", encoder.encode(form.getPhone())
				));
		
		teacherInsert.execute(Map.of(
					"id", generatedKey.intValue(),
					"phone", form.getPhone(),
					"assign_date", Date.valueOf(form.getAssignDate())
					
				));
		
		return generatedKey.intValue();
	}
	
	private int update(TeacherForm form) {
		// update account table
		template.update("update account set name = :name where id = :id", 
				Map.of("name", form.getName(), "id", form.getId()));
		
		// update teacher table
		template.update("update teacher set phone = :phone, assign_date = :assign where id = :id", 
				Map.of(
						"phone", form.getPhone(),
						"assign", Date.valueOf(form.getAssignDate()),
						"id", form.getId()
					));
	
		return form.getId();
	}

}