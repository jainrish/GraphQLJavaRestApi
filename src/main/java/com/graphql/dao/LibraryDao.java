package com.graphql.dao;

import java.util.ArrayList;
import java.util.List;

import com.graphql.model.Book;
import com.graphql.model.Library;

public class LibraryDao {

	private static Book book;
	private static List<Book> bookList;
	private static Library library;
	private static List<Library> libraries;
	
	static {
		
		bookList = new ArrayList<Book>();
		
		book = new Book();
		book.setAuthor("R.D.Sharma");
		book.setId("18");
		book.setPages(200);
		book.setTitle("Mathematics");
		
		Book b1 = new Book();
		b1.setAuthor("H.C.Verma");
		b1.setId("9");
		b1.setPages(200);
		b1.setTitle("Physics");
		
		List<Book> books = new ArrayList<Book>();
		books = new ArrayList<Book>();
		books.add(b1);
		books.add(book);
		
		library = new Library();
		library.setBooks(books);
		library.setBooksCount(2);
		library.setId(1);
		library.setName("City Library");
		
		Book b2 = new Book();
		b2.setAuthor("O.P. Tondon");
		b2.setId("36");
		b2.setPages(250);
		b2.setTitle("Chemistry");
		
		Book b3 = new Book();
		b3.setAuthor("John Oliver");
		b3.setId("27");
		b3.setPages(400);
		b3.setTitle("English");
		
		Book b4 = new Book();
		b4.setAuthor("Premchand");
		b4.setId("45");
		b4.setPages(500);
		b4.setTitle("Hindi");
		
		List<Book> books2 = new ArrayList<Book>();
		books2.add(b2);
		books2.add(b3);
		books2.add(b4);
		
		Library library2 = new Library();
		library2.setBooks(books2);
		library2.setBooksCount(3);
		library2.setId(2);
		library2.setName("Central Library");
		
		libraries = new ArrayList<Library>();
		libraries.add(library);
		libraries.add(library2);
		
		bookList.add(book);
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
	}
	
	public static List<Library> getLibraries(){
		return libraries;
	}
	
	public static List<Book> getBooks() {
		return bookList;
	}
	
	public static Book getBook() {
		return book;
	}
	
	public static Library getLibrary() {
		return library;
	}
}
