package com.graphql.datafetcher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.dao.LibraryDao;
import com.graphql.model.Library;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllLibrariesDataFetcher implements DataFetcher<List<Library>>{

	@Override
	public List<Library> get(DataFetchingEnvironment environment) {
		return LibraryDao.getLibraries();
	}

	
	
	
}
