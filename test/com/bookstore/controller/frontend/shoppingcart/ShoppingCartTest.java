package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static ShoppingCart cart;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		
		Book book = new Book(1);
		
		cart.addItem(book);
		cart.addItem(book);
	}
	
	@Test
	public void testAddItem() {
	
		Map<Book, Integer> items = cart.getItems();
		int quantity = items.get(new Book(1));
		assertEquals(2, quantity);
	}

	@Test
	public void testRemoveItem() {
		cart.removeItem(new Book(1));
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		Book book2 = new Book(2);
		cart.addItem(book2);
		
		cart.removeItem(new Book(2));
		assertNull(cart.getItems().get(book2));
	}
	
	@Test
	public void testGetTotalQuantity() {
		
		Book book3 = new Book(3);
		cart.addItem(book3);
		cart.addItem(book3);
		cart.addItem(book3);
		assertEquals(5, cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount1() {
		cart = new ShoppingCart();
		assertEquals(0.0f, cart.getTotalAmount(), 0.0f);
	}
	
	@Test
	public void testClear() {
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}
}
