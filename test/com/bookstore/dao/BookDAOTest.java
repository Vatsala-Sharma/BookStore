package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class BookDAOTest{
	private static BookDAO bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDAO();
	}

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(35);
		Category category = new Category("Java Core");
		category.setCategoryId(1);
		existBook.setCategory(category);
		
		existBook.setTitle("Effective Java (3rd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		existBook.setPrice(40f);
		existBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new	SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\hp\\Downloads\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		
		Book updateBook = bookDao.update(existBook);
		assertEquals(existBook.getTitle(), "Effective Java (3rd Edition)");
	}

	
	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		Category category = new Category("Advance Java");
		category.setCategoryId(2);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new	SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\hp\\Downloads\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDao.create(newBook);
		assertTrue(createdBook.getBookId() > 0);
	}

	@Test
	public void testCreate2ndBook() throws ParseException, IOException {
		Book newBook = new Book();
		Category category = new Category("Java Core");
		category.setCategoryId(1);
		newBook.setCategory(category);
		
		newBook.setTitle("Java 8 in Action ");
		newBook.setAuthor("Alan Mycroft");
		newBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8.");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");
		
		DateFormat dateFormat = new	SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\hp\\Downloads\\books\\Java 8 in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDao.create(newBook);
		assertTrue(createdBook.getBookId() > 0);
	}

	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId=100;
		bookDao.delete(bookId);
		
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId = 50;
		Book book = bookDao.get(bookId);
		assertNull(book);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 35;
		Book book = bookDao.get(bookId);
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listAll = bookDao.listAll();
		
		for(Book abook : listAll) {
			System.out.println(abook.getTitle()+" - "+abook.getAuthor());
		}
		
		assertTrue(listAll.size()>0);
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		 Book findByTitle = bookDao.findByTitle(title);
		 assertNull(findByTitle);
	}
	
	@Test
	public void testFindByTitle() {
		String title = "Java 8 in Action";
		 Book findByTitle = bookDao.findByTitle(title);
		 assertNotNull(findByTitle);
	}
	
	@Test
	public void testCount() {
		long totalBooks = bookDao.count();
		assertEquals(2, totalBooks);
		
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDao.listNewBooks();
		assertEquals(listNewBooks.size(), 4);
	}
	
	@Test
	public void testSearchBookInTitle() {
		String keyword = "Kathy";
		List<Book> search = bookDao.search(keyword);
		assertEquals(1, search.size());
	}

	@Test
	public void testCountByCategory() {
		int categoryId = 1;
		long numOfBooks = bookDao.countByCategory(categoryId);
		assertTrue(numOfBooks == 1);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}

	
}
