package com.graphql.datafetcher;

import org.springframework.stereotype.Component;

import com.graphql.dao.LibraryDao;
import com.graphql.model.Library;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class LibraryDataFetcher implements DataFetcher<Library>{

	@Override
	public Library get(DataFetchingEnvironment environment) {
		return LibraryDao.getLibrary();
	}
	
	

}
