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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.leaves.model.dto.input.LeaveForm;
import com.jdc.leaves.model.dto.output.LeaveListVO;
import com.jdc.leaves.model.dto.output.LeaveSummaryVO;

@Service
public class LeaveService {

	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert leaveInsert;
	private SimpleJdbcInsert leaveDayInsert;
	
	@Autowired
	private ClassService clsService;
	
	private static final String LEAVE_DAY_COUNT_SQL = """
			 select count(leave_date) from leaves_day 
			 where leave_date = :target and leaves_classes_id = :class 
			""";
	
	private static final String SEARCH_LEAVES = """
			 select distinct l.apply_date applyDate, l.classes_id classId, l.student_id studentId, l.start_date startDate, 
			 l.days, l.reason, sa.name student, s.phone studentPhone, c.teacher_id teacherId, ta.name teacher, 
			 c.start_date classStart, c.description classInfo 
			 from leaves l join classes c on l.classes_id = c.id 
			 join teacher t on c.teacher_id = t.id 
			 join account ta on t.id = ta.id 
			 join student s on l.student_id = s.id 
			 join account sa on s.id = sa.id 
			 join leaves_day ld on l.apply_date = ld.leaves_apply_date and l.classes_id = ld.leaves_classes_id and l.student_id = ld.leaves_student_id
			 where 1 = 1 
			""";
	
	public LeaveService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		
		leaveInsert = new SimpleJdbcInsert(dataSource);
		leaveInsert.setTableName("leaves");
		
		leaveDayInsert = new SimpleJdbcInsert(dataSource);
		leaveDayInsert.setTableName("leaves_day");
	}

	public List<LeaveListVO> search(Optional<Integer> classId, Optional<LocalDate> from, Optional<LocalDate> to) {
		var sb = new StringBuffer(SEARCH_LEAVES);
		var params = new HashMap<String, Object>();
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication.isAuthenticated() && 
				authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Student"))) {
			
			params.put("email", authentication.getName());
			sb.append(" and sa.email = :email ");
			
		}
		
		sb.append(classId.filter(a -> a != null && a > 0).map(a -> {
			params.put("classId", a);
			return " and l.classes_id = :classId";
		}).orElse(""));
		
		sb.append(from.filter(a -> a != null).map(a -> {
			params.put("from", Date.valueOf(a));
			return " and ld.leave_date >= :from";
		}).orElse(""));
		
		sb.append(to.filter(a -> a != null).map(a -> {
			params.put("to", Date.valueOf(a));
			return " and ld.leave_date <= :to";
		}).orElse(""));
		
		sb.append(" order by l.start_date, l.apply_date, sa.name ");
		
		return template.query(sb.toString(), params,
				new BeanPropertyRowMapper<>(LeaveListVO.class));
	}

	@Transactional
	public void save(LeaveForm form) {
		// insert into leaves
		leaveInsert.execute(Map.of(
				"apply_date", Date.valueOf(form.getApplyDate()),
				"classes_id", form.getClassId(),
				"student_id", form.getStudent(),
				"start_date", Date.valueOf(form.getStartDate()),
				"days", form.getDays(),
				"reason", form.getReason()
			));
				
		// insert into leaves_day
		for (int i = 0; i < form.getDays(); i ++) {
			leaveDayInsert.execute(Map.of(
					"leaves_apply_date", Date.valueOf(form.getApplyDate()),
					"leaves_classes_id", form.getClassId(),
					"leaves_student_id", form.getStudent(),
					"leave_date", Date.valueOf(form.getStartDate().plusDays(i))
					));
		}
	}	
	
	public LeaveForm getFormById(int classId, int studentId) {
		return null;
	}

	public List<LeaveSummaryVO> searchSummary(Optional<LocalDate> target) {
		
		// Find Classes
		var classes = clsService.search(
				Optional.ofNullable(null), 
				Optional.ofNullable(null), 
				Optional.ofNullable(null));
		
		var result = classes.stream().map(LeaveSummaryVO::new).toList(); // List<LeaveSummaryVO>
		
		for (var vo: result) {
			vo.setLeaves(findLeavesForClasses(vo.getClassId(), target.orElse(LocalDate.now())));
		}
		
		return result;
	}

	private long findLeavesForClasses(int classId, LocalDate date) {
		return template.queryForObject(LEAVE_DAY_COUNT_SQL, 
				Map.of("target", Date.valueOf(date), "class", classId), Long.class);
	}

}