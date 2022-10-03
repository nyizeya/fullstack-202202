package com.jdc.leaves.model.service;

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

import com.jdc.leaves.model.dto.input.StudentForm;
import com.jdc.leaves.model.dto.output.StudentDetailsVO;
import com.jdc.leaves.model.dto.output.StudentListVO;

@Service
public class StudentService {
	
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert studentInsert;
	private SimpleJdbcInsert accountInsert;
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RegistrationService regService;
	
	private static final String SELECT_PROJECTION = """
			 select s.id, s.phone, s.education, a.name, a.email, count(r.classes_id) classCount
			 from student s join account a on s.id = a.id
			 left join registration r on s.id = r.student_id where 1 = 1 			 
			""";
	
	private static final String GROUP_BY = """
			 group by s.id, s.phone, s.education, a.name, a.email 
			""";

	public StudentService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		
		accountInsert = new SimpleJdbcInsert(dataSource);
		accountInsert.setTableName("account");
		accountInsert.setGeneratedKeyName("id");
		accountInsert.setColumnNames(List.of("name", "role", "email", "password"));
		
		studentInsert = new SimpleJdbcInsert(dataSource);
		studentInsert.setTableName("student");
	}

	public List<StudentListVO> search(Optional<String> name, Optional<String> phone, Optional<String> email) {
		var sb = new StringBuffer(SELECT_PROJECTION);
		var params = new HashMap<String, Object>();
		
		sb.append(name.filter(StringUtils::hasLength).map(a -> {
			params.put("name", a.toLowerCase().concat("%"));
			return " and lower(a.name) like :name ";
		}).orElse(""));
		
		sb.append(phone.filter(StringUtils::hasLength).map(a -> {
			params.put("phone", a.concat("%"));
			return " and s.phone like :phone ";
		}).orElse(""));
		
		sb.append(email.filter(StringUtils::hasLength).map(a -> {
			params.put("email", a.toLowerCase().concat("%"));
			return " and a.email like :email ";
		}).orElse(""));
		
		sb.append(GROUP_BY);
		return template.query(sb.toString(), params, new BeanPropertyRowMapper<>(StudentListVO.class));
	}
	
	public StudentListVO getStudentByClassId(int classId, int studentId) {
		var sql = " %s and r.classes_id = :class and r.student_id = :student %s "
				.formatted(SELECT_PROJECTION, GROUP_BY);
		return template.queryForObject(sql, Map.of("class", classId, "student", studentId), 
				new BeanPropertyRowMapper<>(StudentListVO.class));
	}
	
	public Integer findStudentByEmail(String email) {
		return template.queryForList(
				"select s.id from student s join account a on s.id = a.id where email = :email", 
				Map.of("email", email), Integer.class).stream().findFirst().orElse(null);
	}
	
	public StudentListVO findInfoById(int id) {
		var sql = "%s and r.student_id = :id %s".formatted(SELECT_PROJECTION, GROUP_BY);
		return template.queryForObject(sql, Map.of("id", id), new BeanPropertyRowMapper<>(StudentListVO.class));
	}
	
	@Transactional
	public int save(StudentForm form) {
		if (form.getId() == 0){
			// add new student
			return insert(form);
		}
		// edit existing student
		return update(form);
	}

	private int insert(StudentForm form) {
		
		// check the username already existed in the database
		var id = findStudentByEmail(form.getEmail());
		
		// if so, we're going to return the existed student's id and we won't create a new account for 
		// the student with the same email address which is the username in this application
		if (null != id) {
			return id;
		}
		
		var generatedKey = accountInsert.executeAndReturnKey(Map.of(
					"name", form.getName(),
					"role", "Student",
					"email", form.getEmail(),
					"password", encoder.encode(form.getPhone())
				));
		
		studentInsert.execute(Map.of(
					"id", generatedKey.intValue(),
					"phone", form.getPhone(),
					"education", form.getEducation()
				));
		
		return generatedKey.intValue();
	}

	private int update(StudentForm form) {
		// update account
		template.update("update account set name = :name where id = :id ", 
				Map.of("name", form.getName(), "id", form.getId()));
		
		// update student
		template.update("update student set phone = :phone, education = :education where id = :id", 
				Map.of("phone", form.getPhone(), "education", form.getEducation(), "id", form.getId()));
		
		return form.getId();
	}

	public StudentDetailsVO finidDetailsByLoginId(String studentEmail) {
		var details = new StudentDetailsVO();
		
		var sql = "%s and a.email = :email %s".formatted(SELECT_PROJECTION, GROUP_BY);
		
		details.setStudent(template.queryForObject(sql, 
				Map.of("email", studentEmail), new BeanPropertyRowMapper<>(StudentListVO.class)));
		
		details.setRegistrations(regService.searchByEmail(studentEmail));
		
		return details;
	}

}