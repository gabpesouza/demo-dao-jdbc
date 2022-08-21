package application;

import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: SELLER findById ===");
		//Seller seller = sellerDao.findById(3);
		List<Seller>lista = sellerDao.findByDepartment(2);
		
		
		System.out.println("=== TEST 2: SELLER findByDepartment ===");
		for (Seller seller : lista) {
			System.out.println(seller);
			
		}
		
		System.out.println("=== TEST 3: SELLER findAll ===");
		List<Seller> findAll = sellerDao.findAll();
		for (Seller seller : findAll) {
			System.out.println(seller);
		}
		
		System.out.println("=== TEST 4: SELLER insert===");
		//sellerDao.insert(new Seller(null,"Rafael", "rafael@gmail", new java.util.Date(), 4000.00, new Department(3, null)));
		
		System.out.println("=== TEST 5: SELLER update===");
		Seller seller = sellerDao.findById(2) ;
		seller.setName("Fred");
		sellerDao.update(seller);
		
		sellerDao.update(new Seller(17,"Bruce","bruce@gmail", new java.util.Date(), 10000.00, new Department(1, null)));
	}

}
