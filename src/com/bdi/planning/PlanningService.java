package com.bdi.planning;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
public class PlanningService {
	private Map<Integer, String> userMap = getUserMap();

	private static Connection getDBConnection() {
		Connection dBConn = null;
		try {
			DataSource ds = DataSource.getInstance();
			dBConn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Connect to DB Failed !!!");
		}
		return dBConn;
	}

	private Map<Integer, String> getUserMap() {
		// result to return
		Map<Integer, String> mapUser = new HashMap<>();

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				System.out.println("Connect to DB successfully!");
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Name FROM User WHERE 1=1 ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					// add to result
					User u = new User();
					u.setId(id);
					u.setName(name);
					mapUser.put(id, name);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return mapUser;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchGoal")
	public List<Goal> searchGoal(@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
			@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<Goal> result = new ArrayList<>();
		// input
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Parent_id, Target, Description, Date_created, User_created FROM Goal WHERE 1=1 ");
				query.append("ORDER BY Id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					int parentId = rs.getInt(2);
					String target = rs.getString(3);
					String description = rs.getString(4);
					Timestamp date_created = rs.getTimestamp(5);
					int user_created = rs.getInt(6);
					// add to result
					Goal goal = new Goal();
					goal.setId(id);
					goal.setParentId(parentId);
					goal.setTarget(target);
					goal.setDescription(description);
					goal.setDate_created(date_created);
					goal.setUser_created(user_created);
					goal.setUsername(userMap.get(user_created));
					result.add(goal);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<Goal> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchProject")
	public List<Project> searchProject(@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
			@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<Project> result = new ArrayList<>();
		// input
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Code, Goal_id, Name, Description, Date_start, Date_end, Date_created, Manager_id FROM Project WHERE 1=1 ");
				query.append("ORDER BY Date_created ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String code = rs.getString(2);
					int goalId = rs.getInt(3);
					String name = rs.getString(4);
					String description = rs.getString(5);
					Timestamp date_start = rs.getTimestamp(6);
					Timestamp date_end = rs.getTimestamp(7);
					Timestamp date_created = rs.getTimestamp(8);
					int manager_id = rs.getInt(9);
					// add to result
					Project proj = new Project();
					proj.setId(id);
					proj.setCode(code);
					proj.setGoalId(goalId);
					proj.setName(name);
					proj.setDescription(description);
					proj.setDate_created(date_created);
					proj.setDate_start(date_start);
					proj.setDate_end(date_end);
					proj.setManager_id(manager_id);
					proj.setManager_name(userMap.get(manager_id));
					result.add(proj);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<Project> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPlan")
	public List<Plan> searchPlan(@RequestParam(required = false, name = "projectId") Integer projectId,
			@RequestParam(required = false, name = "planId") Integer planId) {
		// result to return
		List<Plan> result = new ArrayList<>();
		
		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Parent_id, Name, Version_id, Date_created, User_create FROM Plan WHERE 1=1 ");
				if (projectId != null && projectId > 0) {
					query.append("AND Project_id = ? ");
				}
				if (planId != null && planId > 0) {
					query.append("AND Id = ? ");
				}
				query.append("ORDER BY Id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				if (projectId != null && projectId > 0) {
					stmt.setInt(1, projectId);
				}
				if (planId != null && planId > 0) {
					stmt.setInt(1, planId);
				}

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					int parentId = rs.getInt(2);
					String name = rs.getString(3);
					int versionId = rs.getInt(4);
					Timestamp date_created = rs.getTimestamp(5);
					int user_create = rs.getInt(6);
					// add to result
					Plan p = new Plan();
					p.setId(id);
					p.setParentId(parentId);
					p.setName(name);
					p.setVersionId(versionId);
					p.setDate_created(date_created);
					p.setUser_create(user_create);
					p.setUser_create_name(userMap.get(user_create));
					result.add(p);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPlanDetail")
	public List<PlanDetail> searchPlanDetail(@RequestParam(required = true, name = "planId") Integer planId,
									@RequestParam(required = false, name = "curVersion") Integer curVersion,
									@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
									@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<PlanDetail> result = new ArrayList<>();
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		
		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT a.Item_id, a.Item_value, b.Name, b.Unit, a.User_assigned, a.Date_start, a.Date_end, a.Cur_version " +
						"FROM Plan_Detail a JOIN Plan_Item b ON a.Item_id = b.Id WHERE 1=1 ");
				query.append("AND a.Plan_id = ? ");
				if (curVersion != null && curVersion > 0) {
					query.append("AND a.Cur_version = ? ");
				}
				query.append("ORDER BY a.Date_start ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setInt(1, planId);
				if (curVersion != null && curVersion > 0) {
					stmt.setInt(2, curVersion);
				}

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int itemId = rs.getInt(1);
					String item_value = rs.getString(2);
					String item_name = rs.getString(3);
					String item_unit = rs.getString(4);
					int user_assigned = rs.getInt(5);
					Timestamp date_start = rs.getTimestamp(6);
					Timestamp date_end = rs.getTimestamp(7);
					int cur_version = rs.getInt(8);
					// add to result
					PlanDetail p = new PlanDetail();
					p.setItemId(itemId);
					p.setItem_name(item_name);
					p.setItem_value(item_value);
					p.setItem_unit(item_unit);
					p.setUser_assigned(user_assigned);
					p.setDate_start(date_start);
					p.setDate_end(date_end);
					p.setCur_version(cur_version);
					p.setUser_assigned_name(userMap.get(user_assigned));
					result.add(p);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<PlanDetail> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchDetailRevision")
	public List<PlanDetail> searchDetailRevision(@RequestParam(required = true, name = "planId") Integer planId,
									@RequestParam(required = false, name = "curVersion") Integer curVersion,
									@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
									@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<PlanDetail> result = new ArrayList<>();
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		
		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT d.Item_id, d.Item_value, i.Name, i.Unit, d.User_assigned, d.Date_start, d.Date_end, r.Old_version " +
						"FROM (Detail_Revision r JOIN Plan_Detail d ON r.Detail_id = d.Id) JOIN Plan_Item i ON d.Item_id = i.Id WHERE 1=1 ");
				query.append("AND d.Plan_id = ? ");
				if (curVersion != null && curVersion > 0) {
					query.append("AND r.Old_version = ? ");
				}
				query.append("ORDER BY d.Date_start ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setInt(1, planId);
				if (curVersion != null && curVersion > 0) {
					stmt.setInt(2, curVersion);
				}

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int itemId = rs.getInt(1);
					String item_value = rs.getString(2);
					String item_name = rs.getString(3);
					String item_unit = rs.getString(4);
					int user_assigned = rs.getInt(5);
					Timestamp date_start = rs.getTimestamp(6);
					Timestamp date_end = rs.getTimestamp(7);
					int cur_version = rs.getInt(8);
					// add to result
					PlanDetail p = new PlanDetail();
					p.setItemId(itemId);
					p.setItem_name(item_name);
					p.setItem_value(item_value);
					p.setItem_unit(item_unit);
					p.setUser_assigned(user_assigned);
					p.setDate_start(date_start);
					p.setDate_end(date_end);
					p.setCur_version(cur_version);
					p.setUser_assigned_name(userMap.get(user_assigned));
					result.add(p);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<PlanDetail> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPlanExecution")
	public List<ExecutionLog> searchPlanExecution(@RequestParam(required = true, name = "planId") Integer planId,
									@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
									@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<ExecutionLog> result = new ArrayList<>();
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		
		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT a.Item_id, a.Item_value, b.Name, b.Unit, a.User_executed, a.Date_start, a.Date_end, a.Date_log " +
						"FROM Execution_Log a JOIN Plan_Item b ON a.Item_id = b.Id WHERE 1=1 ");
				query.append("AND a.Plan_id = ? ORDER BY a.Date_start, a.Date_log ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setInt(1, planId);

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int itemId = rs.getInt(1);
					String item_value = rs.getString(2);
					String item_name = rs.getString(3);
					String item_unit = rs.getString(4);
					int user_executed = rs.getInt(5);
					Timestamp date_start = rs.getTimestamp(6);
					Timestamp date_end = rs.getTimestamp(7);
					Timestamp date_log = rs.getTimestamp(8);
					// add to result
					ExecutionLog p = new ExecutionLog();
					p.setItemId(itemId);
					p.setItem_name(item_name);
					p.setItem_value(item_value);
					p.setItem_unit(item_unit);
					p.setUser_executed(user_executed);
					p.setDate_start(date_start);
					p.setDate_end(date_end);
					p.setDate_log(date_log);
					p.setUser_executed_name(userMap.get(user_executed));
					result.add(p);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<ExecutionLog> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchState")
	public List<State> searchState(@RequestParam(required = false, name = "projectId") Integer projectId,
			@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
			@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<State> result = new ArrayList<>();
		// input
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Name, Content, Result, Is_initial, Is_goal FROM State WHERE 1=1 ");
				if (projectId != null && projectId > 0) {
					query.append("AND Project_id = ? ");
				}
				query.append("ORDER BY Name ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				if (projectId != null && projectId > 0) {
					stmt.setInt(1, projectId);
				}

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String content = rs.getString(3);
					String state_result = rs.getString(4);
					int is_initial = rs.getInt(5);
					int is_goal = rs.getInt(6);
					// add to result
					State s = new State();
					s.setId(id);
					s.setName(name);
					s.setContent(content);
					s.setResult(state_result);
					s.setIs_initial(is_initial);
					s.setIs_goal(is_goal);
					result.add(s);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<State> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchConstraint")
	public List<Constraint> searchConstraint(@RequestParam(required = true, name = "planId") Integer planId,
									@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
									@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<Constraint> result = new ArrayList<>();
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		
		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Constraint_rule FROM Constraint_Rule WHERE 1=1 ");
				query.append("AND Plan_id = ? ORDER BY Id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setInt(1, planId);

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String constraint_rule = rs.getString(2);
					// add to result
					Constraint c = new Constraint();
					c.setId(id);
					c.setConstraint_rule(constraint_rule);
					result.add(c);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<Constraint> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPlanItem")
	public List<PlanItem> searchPlanItem(@RequestParam(required = false, name = "searchParent") Boolean isSearchParent,
			@RequestParam(required = false, name = "parentId") Integer searchParentId,
			@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
			@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		// result to return
		List<PlanItem> result = new ArrayList<>();
		// input
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Parent_id, Name, Unit, Description FROM Plan_Item WHERE 1=1 ");
				if (searchParentId != null && searchParentId > 0) {
					query.append("AND Parent_id = ? ");
				}
				if (isSearchParent != null && isSearchParent) {
					query.append("AND Parent_id IS NULL ");
				}
				query.append("ORDER BY Parent_id, Id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				if (searchParentId != null && searchParentId > 0) {
					stmt.setInt(1, searchParentId);
				}

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					int parentId = rs.getInt(2);
					String name = rs.getString(3);
					String unit = rs.getString(4);
					String description = rs.getString(5);
					// add to result
					PlanItem item = new PlanItem();
					item.setId(id);
					item.setParentId(parentId);
					item.setName(name);
					item.setUnit(unit);
					item.setDescription(description);
					result.add(item);
				}
				rs.close();
				stmt.close();
				// return result by paging
				int totalResult = result.size();
				int fromIndex = (pageIndex - 1) * pageSize;
				if (fromIndex >= totalResult) {
					fromIndex = (totalResult - 1) < 0 ? 0 : totalResult - 1;
				}
				int toIndex = fromIndex + pageSize;
				if (toIndex > totalResult) {
					toIndex = totalResult;
				}
				List<PlanItem> paging = result.subList(fromIndex, toIndex);
				// set total result to last element
				if (paging.size() > 0) {
					paging.get(paging.size() - 1).setTotalResult(totalResult);
				}
				result = paging;

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPlanRevision")
	public List<PlanRevision> searchPlanRevision(@RequestParam(required = true, name = "planId") Integer planId) {
		// result to return
		List<PlanRevision> result = new ArrayList<>();
		
		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"SELECT Id, Date_created, Date_updated, User_created, User_updated, Comment FROM Plan_Revision WHERE 1=1 ");
				query.append("AND Plan_id = ? ORDER BY Id DESC ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setInt(1, planId);

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					Timestamp date_created = rs.getTimestamp(2);
					Timestamp date_updated = rs.getTimestamp(3);
					int user_created = rs.getInt(4);
					int user_updated = rs.getInt(5);
					String comment = rs.getString(6);
					// add to result
					PlanRevision p = new PlanRevision();
					p.setId(id);
					p.setPlanId(planId);
					p.setDate_created(date_created);
					p.setDate_updated(date_updated);
					p.setUser_created(user_created);
					p.setUser_updated(user_updated);
					p.setComment(comment);
					p.setUser_created_name(userMap.get(user_created));
					p.setUser_updated_name(userMap.get(user_updated));
					result.add(p);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private Timestamp convertString2Ts(String source, String pattern) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(pattern);
		Date d = formatter.parse(source);
		return new Timestamp(d.getTime());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/newPlanExecution")
	public int newPlanExecution(
			@RequestParam(required = true, name = "planId") Integer planId,
			@RequestParam(required = true, name = "itemId") Integer itemId,
			@RequestParam(required = true, name = "itemValue") String itemValue,
			@RequestParam(required = true, name = "startDate") String startDate,
			@RequestParam(required = true, name = "endDate") String endDate) {

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"INSERT INTO Execution_Log(Plan_id, Item_id, Item_value, Date_start, Date_end, User_executed, Date_log) ");
				query.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
				
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				if (planId != null && planId > 0) {
					stmt.setInt(1, planId);
				}
				if (itemId != null && itemId > 0) {
					stmt.setInt(2, itemId);
				}
				if (itemValue != null && !itemValue.isEmpty()) {
					stmt.setString(3, itemValue);
				}
				String pattern = "yyyy/MM/dd";
				if (startDate != null && !startDate.isEmpty()) {
					stmt.setTimestamp(4, convertString2Ts(startDate, pattern));
				}
				if (endDate != null && !endDate.isEmpty()) {
					stmt.setTimestamp(5, convertString2Ts(endDate, pattern));
				}
				stmt.setInt(6, 1); // userId = 1
				// current time log
				stmt.setTimestamp(7, new Timestamp(new Date().getTime()));

				// execute insert/update
				stmt.executeUpdate();
				stmt.close();

			} catch (SQLException | ParseException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
				return 0;
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/newPlanDetail")
	public int newPlanDetail(
			@RequestParam(required = true, name = "planId") Integer planId,
			@RequestParam(required = true, name = "itemId") Integer itemId,
			@RequestParam(required = true, name = "itemValue") String itemValue,
			@RequestParam(required = true, name = "startDate") String startDate,
			@RequestParam(required = true, name = "endDate") String endDate) {

		// connect to DB
		Connection dbConn = getDBConnection();
		if (dbConn != null) {
			try {
				// check whether there existed a plan detail
				boolean isDetailExisted = false;
				long newVersionId = 0;
				
				StringBuilder query = new StringBuilder("");
				query.append("SELECT COUNT(1) FROM Plan_Detail WHERE Plan_id = ?");
				
				PreparedStatement stmt = dbConn.prepareStatement(query.toString());
				if (planId != null && planId > 0) {
					stmt.setInt(1, planId);
				}
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					int count = rs.getInt(1);
					if (count > 0) isDetailExisted = true;
				}
				rs.close();
				stmt.close();
				
				if (isDetailExisted) {
					// create a new version of this plan
					query = new StringBuilder("");
					query.append("INSERT INTO Plan_Revision(Plan_id, Date_created, Date_updated, User_created, User_updated) ");
					query.append("VALUES(?, (SELECT Date_created FROM Plan WHERE Id = ?), ?, (SELECT User_create FROM Plan WHERE Id = ?), ?)");
					
					dbConn.setAutoCommit(false);
					stmt = dbConn.prepareStatement(query.toString());
					if (planId != null && planId > 0) {
						stmt.setInt(1, planId);
						stmt.setInt(2, planId);
						stmt.setInt(4, planId);
					}
					stmt.setTimestamp(3, new Timestamp(new Date().getTime()));
					stmt.setInt(5, 1);	// User_id = 1
					stmt.executeUpdate();
					dbConn.commit();
					stmt.close();
					dbConn.setAutoCommit(true);
					
					// 2) update Version_id for table Plan
					query = new StringBuilder("SELECT MAX(Id) FROM Plan_Revision WHERE Plan_id = ?");
					stmt = dbConn.prepareStatement(query.toString());
					if (planId != null && planId > 0) {
						stmt.setInt(1, planId);
					}
					rs = stmt.executeQuery();
					if (rs.next()) {
						newVersionId = rs.getLong(1);
					}
					rs.close();
					stmt.close();
					
					query = new StringBuilder("UPDATE Plan SET Version_id = ? WHERE Id = ?");
					stmt = dbConn.prepareStatement(query.toString());
					if (planId != null && planId > 0) {
						stmt.setInt(2, planId);
					}
					if (newVersionId > 0) {
						stmt.setInt(1, (int) newVersionId);
					}
					stmt.executeUpdate();
					stmt.close();
				}
				// if existed plan detail -> add Detail_Revision then update Cur_version in Plan_detail
				if (isDetailExisted) {
					query = new StringBuilder("INSERT INTO Detail_Revision(Plan_id, Detail_id, Old_version) ");
					query.append("SELECT Plan_id, Id, Cur_version FROM Plan_Detail WHERE Plan_id = ? AND (Cur_version IS NULL OR Cur_version != ?)");
					
					stmt = dbConn.prepareStatement(query.toString());
					if (planId != null && planId > 0) {
						stmt.setInt(1, planId);
					}
					if (newVersionId > 0) {
						stmt.setInt(2, (int) newVersionId);
					}
					stmt.executeUpdate();
					stmt.close();
					
					query = new StringBuilder("UPDATE Plan_Detail SET Cur_version = ? WHERE Plan_id = ?");
					stmt = dbConn.prepareStatement(query.toString());
					if (planId != null && planId > 0) {
						stmt.setInt(2, planId);
					}
					if (newVersionId > 0) {
						stmt.setInt(1, (int) newVersionId);
					}
					stmt.executeUpdate();
					stmt.close();
				}
				
				// insert new plan detail
				query = new StringBuilder(
						"INSERT INTO Plan_Detail(Plan_id, Item_id, Item_value, Date_start, Date_end, User_assigned, Cur_version) ");
				query.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
				
				stmt = dbConn.prepareStatement(query.toString());
				if (planId != null && planId > 0) {
					stmt.setInt(1, planId);
				}
				if (itemId != null && itemId > 0) {
					stmt.setInt(2, itemId);
				}
				if (itemValue != null && !itemValue.isEmpty()) {
					stmt.setString(3, itemValue);
				}
				String pattern = "yyyy/MM/dd";
				if (startDate != null && !startDate.isEmpty()) {
					stmt.setTimestamp(4, convertString2Ts(startDate, pattern));
				}
				if (endDate != null && !endDate.isEmpty()) {
					stmt.setTimestamp(5, convertString2Ts(endDate, pattern));
				}
				stmt.setInt(6, 1); // userId = 1
				if (newVersionId > 0) {
					stmt.setInt(7, (int) newVersionId);
				}
				else {
					stmt.setNull(7, java.sql.Types.INTEGER);
				}

				// execute insert/update
				stmt.executeUpdate();
				stmt.close();

			} catch (SQLException | ParseException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
				return 0;
			} finally {
				try {
					dbConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/newItem")
	public int newItem(@RequestParam(required = false, name = "parentId") Integer parentId,
			@RequestParam(required = true, name = "itemName") String itemName,
			@RequestParam(required = true, name = "itemUnit") String itemUnit,
			@RequestParam(required = false, name = "description") String description) {

		// connect to DB
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				// make query to get detail by time and section
				StringBuilder query = new StringBuilder(
						"INSERT INTO Plan_Item(Parent_id, Name, Unit, Description) ");
				query.append("VALUES(?, ?, ?, ?)");
				
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				if (parentId != null && parentId > 0) {
					stmt.setInt(1, parentId);
				}
				else {
					stmt.setNull(1, java.sql.Types.INTEGER);
				}
				if (itemName != null && !itemName.isEmpty()) {
					stmt.setString(2, itemName);
				}
				if (itemUnit != null && !itemUnit.isEmpty()) {
					stmt.setString(3, itemUnit);
				}
				stmt.setString(4, description);

				// execute insert/update
				stmt.executeUpdate();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
				return 0;
			} finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
}
