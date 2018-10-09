package com.relevancelab.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.relevancelab.library.model.Book;
import com.relevancelab.library.repository.BookRepository;
import com.relevanelab.library.ResourceNotFoundException.ResourceNotFoundException;

@Controller
@RestController
@RequestMapping("/BookManagement")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	//Add or create new books
	@PostMapping("/Books/Book")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	//Get All Books
	@GetMapping("/Books/Book" )
	public List<com.relevancelab.library.model.Book> getAllBooks(){
		return bookRepository.findAll();
	}
    
	//Get book by id
	@GetMapping("/Books/Book/{id}")
	   public Book getBookById(@PathVariable(value = "id") Long bookId) {
		   return bookRepository.findById(bookId)
				   .orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
    }
	
	//Get book by book name
	@RequestMapping(value="/Books/Searchbook/{bookname}", method=RequestMethod.GET)
	   public List<com.relevancelab.library.model.Book> getAllByBooknameIgnoreCase(@PathVariable(value="bookname") String bookname){
			return bookRepository.findAllByBooknameIgnoreCase(bookname);
	}
		
	//get book by author name
	@RequestMapping(value="/Books/Searchauthor/{authorname}", method=RequestMethod.GET)
	   public List<com.relevancelab.library.model.Book> getAllByAuthornameIgnoreCase(@PathVariable(value="authorname") String authorname){
			return bookRepository.findAllByAuthornameIgnoreCase(authorname);
	}
		
	//search books by category
	@RequestMapping(value="/Books/Searchcattegory/{category}", method=RequestMethod.GET)
	    public List<com.relevancelab.library.model.Book> getAllBycategoryIgnoreCase(@PathVariable(value="category") String category){
			return bookRepository.getAllBycategoryIgnoreCase(category);
	}
		
	//delete the book by id
	@DeleteMapping("/Books/Book/{id}")
	   public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
		bookRepository.delete(book);
		return ResponseEntity.ok().build();
	}
	
	
	//update the book by id
	@PutMapping("/Books/Book/{id}")
	public Book updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book bookDetails) {
	     Book book = bookRepository.findById(bookId)
	    		 .orElseThrow(() -> new ResourceNotFoundException("Book", "id", "bookId"));
	     book.setBookname(bookDetails.getBookname());
	     book.setAuthorname(book.getAuthorname());
	     book.setCategory(bookDetails.getAuthorname());
	     
	     Book updateBook = bookRepository.save(book);
	     return updateBook;
	}
}
