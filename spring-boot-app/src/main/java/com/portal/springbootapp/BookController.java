package com.portal.springbootapp;

import java.util.List;

import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portal.springbootapp.model.Book;
import com.portal.springbootapp.repo.BookRepository;



/**
 * The Spring framework supports two ways of creating RESTful services:

	using MVC with ModelAndView
	using HTTP message converters
 * 
 * The ModelAndView approach is older. The new approach, based on HttpMessageConverter and annotations, is much more lightweight and easy to implement. Configuration is minimal, and it provides sensible defaults for what you would expect from a RESTful service
 * @RequestBody will bind the parameters of the method to the body of the HTTP request, whereas 
 * @ResponseBody does the same for the response and return type.
 * They also ensure that the resource will be marshalled and unmarshalled using the correct HTTP converter. 
 * Content negotiation will take place to choose which one of the active converters will be used, 
 * based mostly on the Accept header, although other HTTP headers may be used to determine the representation as well.
 * 
 * The new @EnableWebMvc annotation does some useful things – specifically, in the case of REST, it detects the existence of Jackson and JAXB 2 on the classpath and automatically creates and registers default JSON and XML converters.
 * 
 * @RestController annotation – is equivalent to a @Controller along with @ResponseBody – so that each method marshalls the returned resource right to the HTTP response.
 * 
 * @author sungoyal0
 *
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping
	public Iterable<Book> findAll(){
		return bookRepo.findAll();
	}
	
	@GetMapping("/title/{bookTitle}")
	//@ExceptionHandler(Book)
	public List<Book> findByTitle(@PathVariable("bookTitle") String bookTitle) {
		return bookRepo.findByTitle(bookTitle);
	}
	
	@GetMapping("/{id}")
	public Book findOne(@PathVariable("id") long id) {
		return bookRepo.findOne(id).orElseThrow(BookNotFoundException::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteBook(@PathVariable("id") long id) {
		bookRepo.findOne(id).orElseThrow(BookNotFoundException::new);
		bookRepo.delete(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateBook(@RequestBody Book book,@PathVariable("id") long id) {
		if(book.getId()!=id)
			throw new BookIdMismatchException();
		bookRepo.findOne(id).orElseThrow(BookNotFoundException::new);
		bookRepo.save(book);
	}
}
