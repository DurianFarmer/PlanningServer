package com.bdi.planning;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
						"SELECT Id, Goal_id, Name, Description, Date_start, Date_end, Date_created, Manager_id FROM Project WHERE 1=1 ");
				query.append("ORDER BY Date_created ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					int goalId = rs.getInt(2);
					String name = rs.getString(3);
					String description = rs.getString(4);
					Timestamp date_start = rs.getTimestamp(5);
					Timestamp date_end = rs.getTimestamp(6);
					Timestamp date_created = rs.getTimestamp(7);
					int manager_id = rs.getInt(8);
					// add to result
					Project proj = new Project();
					proj.setId(id);
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
				query.append("AND Is_current = 1 ");
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
						"SELECT a.Item_id, a.Item_value, b.Name, b.Unit, a.User_assigned, a.Date_start, a.Date_end " +
						"FROM Plan_Detail a JOIN Plan_Item b ON a.Item_id = b.Id WHERE 1=1 ");
				query.append("AND a.Plan_id = ? ORDER BY a.Id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setInt(1, planId);

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
					// add to result
					PlanDetail p = new PlanDetail();
					p.setItemId(itemId);
					p.setItem_name(item_name);
					p.setItem_value(item_value);
					p.setItem_unit(item_unit);
					p.setUser_assigned(user_assigned);
					p.setDate_start(date_start);
					p.setDate_end(date_end);
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
						"SELECT a.Item_id, a.Item_value, b.Name, b.Unit, a.User_executed, a.Date_start, a.Date_end " +
						"FROM Execution_Log a JOIN Plan_Item b ON a.Item_id = b.Id WHERE 1=1 ");
				query.append("AND a.Plan_id = ? ORDER BY a.Id ");
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
					// add to result
					ExecutionLog p = new ExecutionLog();
					p.setItemId(itemId);
					p.setItem_name(item_name);
					p.setItem_value(item_value);
					p.setItem_unit(item_unit);
					p.setUser_executed(user_executed);
					p.setDate_start(date_start);
					p.setDate_end(date_end);
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
	public List<State> searchState(@RequestParam(required = false, name = "pageIndex") Integer pageIndex,
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
						"SELECT Id, Name, Content, Is_initial, Is_goal FROM State WHERE 1=1 ");
				query.append("ORDER BY Id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());

				// get result
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String content = rs.getString(3);
					int is_initial = rs.getInt(4);
					int is_goal = rs.getInt(5);
					// add to result
					State s = new State();
					s.setId(id);
					s.setName(name);
					s.setContent(content);
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
}
