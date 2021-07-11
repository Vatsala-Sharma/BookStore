package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;

	@BeforeClass
	public static void setupClass() throws Exception {
		userDAO = new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("qwerty@gmail.com");
		user1.setFullName("Mon");
		user1.setPassword("ro123");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);

	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("qqq@www.com");
		user.setFullName("zaqxwdsa");
		user.setPassword("aszq");
		
		user = userDAO.update(user);
		String expected = "aszq";
		String actual = user.getPassword();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		
		if(user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test
	public void testDeleteUsers() {
	 Integer userId = 2;
	 userDAO.delete(userId);
	 
	 Users user = userDAO.get(userId);
	 assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		 Integer userId = 2;
		 userDAO.delete(userId);
			
	}
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		assertTrue(listUsers.size()>0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertEquals(3, totalUsers);
		
	}
	
	@Test
	public void testCheckLoginSuccess() {
		 String email = "vatsala@gmail.com";
		 String password = "root123";
		 boolean actual = userDAO.checkLogin(email, password);
		 assertTrue(actual);
	}
	
	@Test
	public void testCheckLoginFail() {
		 String email = "vatsala@gmai";
		 String password = "root123";
		 boolean actual = userDAO.checkLogin(email, password);
		 assertFalse(actual);
	}
	
	@Test
	public void testFindIdByEmail() {
		String email="sam@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}

}
