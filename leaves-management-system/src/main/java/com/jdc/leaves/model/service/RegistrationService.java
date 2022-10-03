package com.jdc.leaves.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.dto.output.RegistrationDetailsVO;
import com.jdc.leaves.model.dto.output.RegistrationListVO;

@Service
public class RegistrationService {
	
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert registInsert;
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassService classService;
	
	private static final String SELECT_PROJECTION = """
			 select c.id classId, c.start_date startDate, c.description classInfo, t.id teacherId, ta.name teacher,
			 s.id studentId, sa.name student, s.phone studentPhone, r.registration_date registrationDate 
			 from classes c join teacher t on c.teacher_id = t.id
			 join account ta on t.id = ta.id
			 join registration r on r.classes_id = c.id
			 join student s on r.student_id = s.id
			 join account sa on s.id = sa.id 
			""";
	
	private static final String SELECT_REGISTRATION = """
			 select a.name studentName, a.email, s.phone, s.education, r.classes_id classId, 
			 r.student_id studentId, r.registration_date registDate  
			 from account a join student s on a.id = s.id 
			 left join registration r on s.id = r.student_id  
			 where r.classes_id = :class and r.student_id = :student
			""";

	public RegistrationService(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
		
		registInsert = new SimpleJdbcInsert(ds);
		registInsert.setTableName("registration");
	}

	public List<RegistrationListVO> searchByClassId(int id) {
		var sql = "%s where c.id = :class ".formatted(SELECT_PROJECTION);
		
		return template.query(sql, Map.of("class", id), 
				new BeanPropertyRowMapper<>(RegistrationListVO.class));
	}
	
	public List<RegistrationListVO> searchByEmail(String studentEmail) {
		var sql = "%s where sa.email = :email ".formatted(SELECT_PROJECTION);
		
		return template.query(sql, Map.of("email", studentEmail), 
				new BeanPropertyRowMapper<>(RegistrationListVO.class));
	}

	public void save(RegistrationForm form) {
		if (form.getStudentId() == 0) {
			// insert/create
			insert(form);
			return;
		}
		
		// edit/update
		update(form);
	}

	public RegistrationDetailsVO findDetailsById(int classId, int studentId) {
		var details = new RegistrationDetailsVO();
		
		var rDate = template.queryForObject("""
				 select registration_date from registration where classes_id = :class 
				 and student_id = :student 
				""", 
				Map.of("class", classId, "student", studentId), LocalDate.class);
		
		
		details.setRegistDate(rDate);
		details.setClassInfo(classService.getClassByClassId(classId));
		details.setStudent(studentService.getStudentByClassId(classId, studentId));
		
		return details;
	}
	
	public RegistrationForm getFormById(int classId, int studentId) {
		return template.queryForObject(SELECT_REGISTRATION,
			Map.of(
				"class", classId,
				"student", studentId
			), 
			new BeanPropertyRowMapper<>(RegistrationForm.class));
	}
	
	private void insert(RegistrationForm form) {
		
		var studentId = studentService.save(form.studentForm());
		form.setStudentId(studentId);

		registInsert.execute(Map.of(
					"classes_id", form.getClassId(),
					"student_id", form.getStudentId(),
					"registration_date", Date.valueOf(LocalDate.now())
				));
	}

	private void update(RegistrationForm form) {
		
		var studentId = studentService.save(form.studentForm());
		form.setStudentId(studentId);
		
		template.update("""
				 update registration set registration_date = :registration_date where classes_id = :classes_id 
				 and student_id = :student_id 
				""", Map.of(
				"classes_id", form.getClassId(),
				"student_id", form.getStudentId(),
				"registration_date", Date.valueOf(form.getRegistDate())
			));
		
	}

}