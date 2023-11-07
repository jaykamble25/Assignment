package in.co.vwits.ems.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.co.vwits.ems.model.Employee;

public class EmployeeDaoImpl {

	public int save(Employee e) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","25012001@Jay");
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO new_table VALUES(?,?,?)"); 
				){
			
			pstmt.setInt(1, e.getId()); 
			pstmt.setString(2, e.getName()); 
			pstmt.setDouble(3, e.getSalary()); 
			int noOfRowsUpdated = pstmt.executeUpdate();
			return noOfRowsUpdated;
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return 0;
	}
	public List<Employee> findAll() {
		List<Employee> allEmployees = new ArrayList<Employee>();

		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","25012001@Jay");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM new_table"); 
				){

			ResultSet rs = pstmt.executeQuery(); // firing query
			while(rs.next()) {
				Employee s = new Employee();
				s.setName(rs.getString(2));
				s.setId(rs.getInt(1));
				s.setSalary(rs.getInt(3));


				allEmployees.add(s);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allEmployees;
	}

	public void updateSalary() {

	}
	public void delete(int id) {

		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","25012001@Jay");
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM new_table WHERE id=? "); 
				)
		{

			// Before actually firing the QUERY you must set the values for all ?
			pstmt.setInt(1, id);

			int noOfRowUpdated = pstmt.executeUpdate(); // firing QUERY

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Optional<Employee> findById(int id){
		Employee foundEmployee = new Employee();
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","2512001@Jay");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM new_table WHERE id = ?"); 
				){
			// Before actually firing the query we must set the values for all the ? marks
			pstmt.setInt(1, id); // col, rollno

			ResultSet rs = pstmt.executeQuery(); // firing query
			if(rs.next()) {		

				foundEmployee.setName(rs.getString(2));
				foundEmployee.setId(rs.getInt(1));

				foundEmployee.setSalary(rs.getInt(3));

				return Optional.of(foundEmployee);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.empty();
	}
	public void updateName(int id, String updateName) {

		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","25012001@Jay");
				PreparedStatement pstmt = con.prepareStatement("UPDATE new_table SET name=? WHERE id=? "); 
				)
		{

			// Before actually firing the QUERY you must set the values for all ?
			pstmt.setString(1, updateName);
			pstmt.setInt(2, id); 


			int noOfRowUpdated = pstmt.executeUpdate(); // firing QUERY
			System.out.println("No of Records Updated are "+ noOfRowUpdated);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		}
	
}
