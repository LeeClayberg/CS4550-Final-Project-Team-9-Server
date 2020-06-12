package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.ComicBook;
import com.example.finalprojectdeploy.repositories.ComicBookRepository;

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
@CrossOrigin(origins = "http://localhost:3000")
public class ComicBookService {

  @Autowired
  ComicBookRepository comicBookRepository;

  //needs updating
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

  //Updates the comic book's grade (nothing else)
  @PutMapping("/api/comic-books/{comicBookId}")
  public ComicBook updateComicBook(@PathVariable("comicBookId") Integer id, @RequestBody ComicBook updatedComicBook) {
    ComicBook comicBook = comicBookRepository.findById(id).get();
    comicBook.updateComicBook(updatedComicBook);
    return comicBookRepository.save(comicBook);
  }

  @DeleteMapping("/api/comic-books/{comicBookId}")
  public void deleteComicBook(@PathVariable("comicBookId") Integer id) {
    comicBookRepository.deleteById(id);
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
