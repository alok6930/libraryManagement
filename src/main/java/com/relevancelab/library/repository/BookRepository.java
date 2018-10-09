package com.relevancelab.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.relevancelab.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findAllByBooknameIgnoreCase(String bookname);

	List<Book> findAllByAuthornameIgnoreCase(String authorname);

	List<Book> getAllBycategoryIgnoreCase(String category);

}
