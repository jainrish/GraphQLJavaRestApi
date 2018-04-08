package com.graphql.datafetcher;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import com.graphql.dao.LibraryDao;
import com.graphql.model.Book;

@Component
public class BookDataFetcher implements DataFetcher<Book>{

	@Override
	public Book get(DataFetchingEnvironment environment) {
//		Map<String, Object> arguments = environment.getArguments();
		return LibraryDao.getBook();
	}

}
