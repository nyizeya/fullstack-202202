package com.jdc.project.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.dto.ProjectVO;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectHelper helper;
	
	@Autowired
	private SimpleJdbcInsert projectInsert;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Project> rowMapper;
	
	public ProjectService() {
		this.rowMapper = new BeanPropertyRowMapper<>(Project.class);
	}
	
	public int create(Project project) {
		helper.validate(project);
		var params = helper.insertParams(project);
		var id = projectInsert.executeAndReturnKey(params);
		
		return id.intValue();
	}

	public Project findById(int id) {
		
		var sql = """
				select p.id, p.name, p.description, p.start startDate, p.months,
				m.id managerId, m.name managerName, m.login_id managerLogin
				from project p inner join member m on p.manager = m.id where p.id = :id
				""";
		
		return template.queryForObject(sql, Map.of("id", id), rowMapper);
	}

	public List<ProjectVO> search(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {
		
		var sb = new StringBuffer("""
				select p.id, p.name, p.description, p.start startDate, p.months,
				m.id managerId, m.name managerName, m.login_id managerLogin
				from project p inner join member m on p.manager = m.id where 1 = 1
				""");
		
		var params = new HashMap<String, Object>();
		
		if (null != project) {
			sb.append("and lower(p.name) like :project");
			params.put("project", project.toLowerCase().concat("%"));
		}
		
		if (null != manager) {
			sb.append("and lower(m.name) like :manager");
			params.put("manager", manager.toLowerCase().concat("%"));
		}
		
		if (null != dateFrom && null != dateTo) {
			sb.append("and p.start between :dateFrom and :dateTo");
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
		}
		
		if (null != dateFrom) {
			sb.append("and p.start >= :dateFrom");
			params.put("dateFrom", dateFrom);
		}
		
		if (null != dateTo) {
			sb.append("and p.start <= :dateTo");
			params.put("dateTo", dateTo);
		}
		
		return template.queryForStream(sb.toString(), params, rowMapper).map(a -> (ProjectVO)a).toList();
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		var sql = """
				update project set name = :name, description = :description, start = :start, months = :months where id = :id
				""";
		return template.update(sql, Map.of("name", name, 
				"description", description, "start", startDate, "months", month, "id", id));
	}

	public int deleteById(int id) {
		return template.update("delete from project where id = :id", Map.of("id", id));
	}

}
