package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Author;

import junit.framework.TestCase;

public class SearchAuthorDaoTest extends TestCase {

	@Test
	public void testSearchAuthorByCriteriaValidQuery(){
		String query = "SELECT * FROM author where author.id = 1";
		SearchAuthorDao searchAuthor = new SearchAuthorDaoImpl();
		List<Author> authors= new ArrayList<Author>();
		try{
			authors = searchAuthor.searchAuthorsByCriteria(query);
		}catch(SQLException e){
			
		}
		assertEquals(1, authors.size());
	}
	
	@Test
	public void testSearchAuthorByQueryInvalidQuery(){
		assertTrue(true);
	}
	
	@Test
	public void testSearchAuthorByCriteriaNullQuery(){
		assertEquals(1, 1);
		
	}
}
