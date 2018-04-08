package com.graphql.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.dao.LibraryDao;
import com.graphql.datafetcher.AllBooksDataFetcher;
import com.graphql.datafetcher.AllLibrariesDataFetcher;
import com.graphql.datafetcher.BookDataFetcher;
import com.graphql.datafetcher.LibraryDataFetcher;
import com.graphql.model.Book;
import com.graphql.model.Library;

@RestController
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class LibraryRestController {

	@Value("classpath:library.graphqls")
	private Resource schemaResource;

	private GraphQL graphql;

	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;

	@Autowired
	private BookDataFetcher bookDataFetcher;

	@Autowired
	private AllLibrariesDataFetcher allLibrariesDataFetcher;

	@Autowired
	private LibraryDataFetcher libraryDataFetcher;

	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser()
				.parse(schemaFile);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(
				typeDefinitionRegistry, runtimeWiring);
		graphql = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring
				.newRuntimeWiring()
				.type("Query",
						typeWiring -> typeWiring
								.dataFetcher("allBooks", allBooksDataFetcher)
								.dataFetcher("book", bookDataFetcher)
								.dataFetcher("allLibraries",
										allLibrariesDataFetcher)
								.dataFetcher("library", libraryDataFetcher))
				.build();
	}

	@RequestMapping(value = "/libraries", method = RequestMethod.GET)
	public ResponseEntity<String> getLibraries() {
		return ResponseEntity.ok("Hello world");
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> getBooks() {
		return LibraryDao.getBooks();
	}

	@RequestMapping(value = "/library", method = RequestMethod.POST)
	public ResponseEntity getLibrary(@RequestBody String library) {
		ExecutionResult result = graphql.execute(library);
		return ResponseEntity.ok(result.getData());
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity getBook(@RequestBody String book) {
		ExecutionResult result = graphql.execute(book);
		return ResponseEntity.ok(result.getData());
	}
}
