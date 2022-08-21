package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;

public class Program2 {
	
	public static void main(String[] args) {
		
		System.out.println("=== TEST 1 : Department insert ===");
		DepartmentDao dep = DaoFactory.creatDepartmentDao();
		//dep.insert(new Department(null, "Developer"));
		
		System.out.println("=== TEST 2 : Department update===");
		dep.update(new Department(3, "Cats"));
		
		System.out.println("=== TEST 3 : Department deleteById===");
		dep.deleteById(5);
		
		System.out.println("=== TEST 4 : Department findById===");
		Department findById = dep.findById(4);
		System.out.println(findById);
		
		System.out.println("=== TEST 5 : Department findAll===");
		List<Department> findAll = dep.findAll();
		for (Department department : findAll) {
			System.out.println(department);
		}
		
		
		
		
	}

}
