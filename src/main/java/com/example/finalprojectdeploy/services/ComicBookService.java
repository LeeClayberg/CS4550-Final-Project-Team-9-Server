package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.ComicBook;
import com.example.finalprojectdeploy.repositories.ComicBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ComicBookService {

  @Autowired
  ComicBookRepository comicBookRepository;

  @GetMapping("/api/users/{userId}/collection")
  public List<ComicBook> findComicBooksForUserSortedSearch(@PathVariable("userId") Integer id, @RequestParam(defaultValue = "") String sort,
                                                           @RequestParam(defaultValue = "") String resource, @RequestParam(defaultValue = "") String query) {
    return comicBookRepository.findCollectionForUser(id);
  }

  @GetMapping("/api/comic-books/{comicBookId}")
  public ComicBook findComicBookById(@PathVariable("comicBookId") Integer id) {
    return comicBookRepository.findComicBookById(id);
  }

  @PostMapping("/api/comic-books")
  public ComicBook createComicBook(@RequestBody ComicBook comicBook) {
    return comicBookRepository.save(comicBook);
  }

  @PutMapping("/api/comic-books/{comicBookId}")
  public ComicBook updateComicBookGrade(@PathVariable("comicBookId") Integer id, @RequestBody ComicBook updatedComicBook) {
    User user = userRepository.findById(id).get();
    user.updateUser(newUser);
    return userRepository.save(user);
  }

  @DeleteMapping("/api/comic-books/{comicBookId}")
  public List<ComicBook> deleteComicBook(@PathVariable("comicBookId") Integer id) {
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
