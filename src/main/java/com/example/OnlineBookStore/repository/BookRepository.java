package com.example.OnlineBookStore.repository;

import com.example.OnlineBookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
}

