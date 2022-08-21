package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection con;

	public SellerDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
					+ " WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dp = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dp);
				return obj;
			}
			return null;
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}

	}
	
	public Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	
	public Seller instantiateSeller(ResultSet rs,Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
		
	}
	
	public List<Seller> findByDepartment(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Seller> lista = new ArrayList();
		Map<Integer,Department> map = new HashMap();
		
		try {
			ps = con.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department on seller.DepartmentId = department.Id "
					+ "WHERE department.Id = ? "
					+ "ORDER BY Name");
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					
								
					
					while(rs.next()) {
						
					Department dep = map.get(rs.getInt("Id"));
					if( dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("Id"), dep);
					}
					
					Seller seller = instantiateSeller(rs, dep);
					lista.add(seller);
				
					}
					return lista;
					
					
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
