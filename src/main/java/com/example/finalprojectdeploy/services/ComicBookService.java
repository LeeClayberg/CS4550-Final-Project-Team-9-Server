package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.ComicBook;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class ComicBookService {
  public List<ComicBook> comicBooks = new ArrayList<>();

  @GetMapping("/api/users/{userId}/collection")
  public List<ComicBook> findComicBooksForUserSortedSearch(@PathVariable("userId") Integer id, @RequestParam(defaultValue = "") String sort,
                                                           @RequestParam(defaultValue = "") String resource, @RequestParam(defaultValue = "") String query) {
    return null;
  }

  @GetMapping("/api/users/{userId}/collection/{comicBookId}")
  public ComicBook findComicBookById(@PathVariable("userId") Integer userId, @PathVariable("comicBookId") Integer id) {
    return null;
  }

  @PostMapping("/api/users/{userId}/collection")
  public ComicBook createComicBook(@PathVariable("userId") Integer userId, @RequestBody ComicBook comicBook) {
    return null;
  }

  @PutMapping("/api/users/{userId}/collection/{comicBookId}")
  public ComicBook updateComicBookGrade(@PathVariable("userId") Integer userId, @PathVariable("userId") Integer id, @RequestBody ComicBook updatedComicBook) {
    return null;
  }

  @DeleteMapping("/api/users/{userId}/collection/{comicBookId}")
  public List<ComicBook> deleteComicBook(@PathVariable("userId") Integer userId, @PathVariable("comicBookId") Integer id) {
    return null;
  }

  /*
  public List<ComicBook> getComicBooksAdvanced(String sortBy, String resource, String query) {
    switch (sortBy) {
      case "grade": Collections.sort(comicBooks, new ComicBook.GradeComparator());
        break;
      case "coverdate": Collections.sort(comicBooks, new ComicBook.CoverDateComparator());
        break;
      case "timestamp": Collections.sort(comicBooks, new ComicBook.DateAddedComparator());
        break;
      case "title": Collections.sort(comicBooks, new ComicBook.TitleComparator());
        break;
      case "volume": Collections.sort(comicBooks, new ComicBook.VolumeComparator());
        break;
    }
    List<ComicBook> results = new ArrayList<>(this.comicBooks);
    switch (resource) {
      case "title": results.removeIf(comicBook -> !comicBook.getTitle().toLowerCase().contains(query.toLowerCase()));
        break;
      case "volume": results.removeIf(comicBook -> !comicBook.getVolume().toLowerCase().contains(query.toLowerCase()));
        break;
      case "character": results.removeIf(comicBook -> !comicBook.getCharacters().toLowerCase().contains(query.toLowerCase()));
        break;
    }
    return results;
  }
   */
}
