package com.graphql.datafetcher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.dao.LibraryDao;
import com.graphql.model.Book;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{

	@Override
	public List<Book> get(DataFetchingEnvironment environment) {
		return LibraryDao.getBooks();
	}

}
