package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.Article;

public class HandleArticle {

	private Connection conn;
	PreparedStatement stmt_article, stmt_author_article_mapping;
	
	public HandleArticle() throws SQLException{
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_article = conn
				.prepareStatement("insert into article(article_key, title, year, journal, url) "
						+ "values (?,?,?,?,?)");
		
		stmt_author_article_mapping = conn
				.prepareStatement("insert into author_article_mapping(name, article_key) values (?, ?)");
	}

	public boolean insertArticleRecords(List<Article> articles) throws SQLException{
		
		for(Article a: articles){
			stmt_article.setString(1, a.getKey());
			stmt_article.setString(2, a.getTitle());
			stmt_article.setString(3, a.getYear());
			stmt_article.setString(4, a.getJournal());
			stmt_article.setString(5, a.getUrl());
			stmt_article.addBatch();
			
			for(String auth: a.getAuthors()){
				stmt_author_article_mapping.setString(1, auth);
				stmt_author_article_mapping.setString(2, a.getKey());
				stmt_author_article_mapping.addBatch();
			}
		}
		stmt_article.executeBatch();
		stmt_author_article_mapping.executeBatch();
		conn.commit();
		return true;
	}
}
