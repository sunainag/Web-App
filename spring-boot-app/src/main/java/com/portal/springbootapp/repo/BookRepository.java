package com.portal.springbootapp.repo;

import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.portal.springbootapp.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	
	Stream<Book> findByTitle(String title);
	Optional<Book> findOne(long id);

}
