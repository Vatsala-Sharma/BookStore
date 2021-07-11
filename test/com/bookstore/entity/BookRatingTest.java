package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRating() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(5.0, averageRating,0.0);
	}

	@Test
	public void testAverageRating2() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		Review review2 = new Review();
		review2.setRating(4);
		reviews.add(review2);
		
		Review review3 = new Review();
		review3.setRating(3);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(4.0, averageRating,0.0);
	}

	@Test
	public void testRatingString1() {
		float averageRating = 0.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		
		String expect = "off,off,off,off,off";
		assertEquals(expect, ratingString);
	}
	
	@Test
	public void testRatingString2() {
		float averageRating = 5.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		
		String expect = "on,on,on,on,on";
		assertEquals(expect, ratingString);
	}
	
	@Test
	public void testRatingString3() {
		float averageRating = 3.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		
		String expect = "on,on,on,off,off";
		assertEquals(expect, ratingString);
	}
	
	@Test
	public void testRatingString4() {
		float averageRating = 4.5f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		
		String expect = "on,on,on,on,half";
		assertEquals(expect, ratingString);
	}
	
	@Test
	public void testRatingString5() {
		float averageRating = 3.5f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		
		String expect = "on,on,on,half,off";
		assertEquals(expect, ratingString);
	}
}

