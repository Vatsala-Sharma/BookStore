package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}


	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(38);
		Customer customer = new Customer();
		customer.setCustomerId(15);
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline("Excellent");
		review.setRating(2);
		review.setComment("I like this book");
		
		Review savedReview = reviewDao.create(review);
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testGet() {
		Integer reviewId = 15;
		Review review  = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId = 15;
		Review review  = reviewDao.get(reviewId);
		review.setHeadline("Good Content");
		
		Review updatedReview = reviewDao.update(review);
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
	}
	
	@Test
	public void testDeleteReview() {
		int reviewId = 15;
		reviewDao.delete(reviewId);
		Review review = reviewDao.get(reviewId);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		assertTrue(listReview.size() > 0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		assertEquals(2, totalReviews);
	}

	@Test
	public void testFindByCustomerAndBookNotFound() {
		Integer customerId = 100;
		Integer bookId = 99;
		
		Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
		assertNull(result);
	}
	
	@Test
	public void testFindByCustomerAndBookFound() {
		Integer customerId = 13;
		Integer bookId = 37;
		
		Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
		assertNotNull(result);
	}
	
}
