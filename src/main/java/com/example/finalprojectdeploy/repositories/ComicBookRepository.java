package com.example.finalprojectdeploy.repositories;

import com.example.finalprojectdeploy.model.ComicBook;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComicBookRepository extends CrudRepository<ComicBook, Integer> {

  @Query("SELECT comicBook FROM ComicBook comicBook WHERE comicBook.userId=:userId")
  public List<ComicBook> findCollectionForUser(@Param("userId") Integer id);

  @Query("SELECT comicBook FROM ComicBook comicBook WHERE comicBook.id=:comicBookId")
  public ComicBook findComicBookById(@Param("comicBookId") Integer id);
}
