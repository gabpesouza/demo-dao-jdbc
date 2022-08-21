package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
	
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
		
		
	}

}
