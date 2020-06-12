package com.example.finalprojectdeploy.controllers;

import com.example.finalprojectdeploy.model.ComicBook;
import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.services.ComicBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ComicBookController {

  @Autowired
  ComicBookService comicBookService;

  @GetMapping("/api/users/{userId}/collection")
  public List<ComicBook> findComicBooksForUserSortedSearch(@PathVariable("userId") Integer id, @RequestParam(defaultValue = "") String sort,
                                                           @RequestParam(defaultValue = "") String resource, @RequestParam(defaultValue = "") String query) {
    return comicBookService.findComicBooksForUserSortedSearch(id, sort, resource, query);
  }

  @GetMapping("/api/comic-books/{comicBookId}")
  public ComicBook findComicBookById(@PathVariable("comicBookId") Integer id) {
    return comicBookService.findComicBookById(id);
  }

  @PostMapping("/api/comic-books")
  public ComicBook createComicBook(@RequestBody ComicBook comicBook) {
    return comicBookService.createComicBook(comicBook);
  }

  //Updates the comic book's grade (nothing else)
  @PutMapping("/api/comic-books/{comicBookId}")
  public ComicBook updateComicBook(@PathVariable("comicBookId") Integer id, @RequestBody ComicBook updatedComicBook) {
    return comicBookService.updateComicBook(id, updatedComicBook);
  }

  @DeleteMapping("/api/comic-books/{comicBookId}")
  public void deleteComicBook(@PathVariable("comicBookId") Integer id) {
    comicBookService.deleteComicBook(id);
  }
}
