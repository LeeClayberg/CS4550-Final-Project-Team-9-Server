package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.ComicBook;
import com.example.finalprojectdeploy.model.HistoryAction;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ComicBookService {

  @Autowired
  ComicBookRepository comicBookRepository;

  HistoryService historyService = new HistoryService();

  //needs updating
  @GetMapping("/api/users/{userId}/collection")
  public List<ComicBook> findComicBooksForUserSortedSearch(@PathVariable("userId") Integer id, @RequestParam(defaultValue = "") String sort,
                                                           @RequestParam(defaultValue = "") String resource, @RequestParam(defaultValue = "") String query) {
    return this.getComicBooksAdvanced(comicBookRepository.findCollectionForUser(id), sort, resource, query);
  }

  @GetMapping("/api/comic-books/{comicBookId}")
  public ComicBook findComicBookById(@PathVariable("comicBookId") Integer id) {
    return comicBookRepository.findComicBookById(id);
  }

  @PostMapping("/api/comic-books")
  public ComicBook createComicBook(@RequestBody ComicBook comicBook) {
    historyService.createHistoryAction(new HistoryAction(comicBook.getUserId(), "added", comicBook.getIssueId()));
    return comicBookRepository.save(comicBook);
  }

  //Updates the comic book's grade (nothing else)
  @PutMapping("/api/comic-books/{comicBookId}")
  public ComicBook updateComicBook(@PathVariable("comicBookId") Integer id, @RequestBody ComicBook updatedComicBook) {
    historyService.createHistoryAction(new HistoryAction(updatedComicBook.getUserId(), "updated", updatedComicBook.getIssueId()));
    ComicBook comicBook = comicBookRepository.findById(id).get();
    comicBook.updateComicBook(updatedComicBook);
    return comicBookRepository.save(comicBook);
  }

  @DeleteMapping("/api/comic-books/{comicBookId}")
  public void deleteComicBook(@PathVariable("comicBookId") Integer id) {
    ComicBook beingDeleted = comicBookRepository.findComicBookById(id);
    historyService.createHistoryAction(new HistoryAction(beingDeleted.getUserId(), "removed", beingDeleted.getIssueId()));
    comicBookRepository.deleteById(id);
  }


  private List<ComicBook> getComicBooksAdvanced(List<ComicBook> collection, String sortBy, String resource, String query) {
    List<ComicBook> results = new ArrayList<>(collection);
    switch (sortBy) {
      case "grade": Collections.sort(results, new ComicBook.GradeComparator());
        break;
      case "coverdate": Collections.sort(results, new ComicBook.CoverDateComparator());
        break;
      case "timestamp": Collections.sort(results, new ComicBook.DateAddedComparator());
        break;
      case "title": Collections.sort(results, new ComicBook.TitleComparator());
        break;
      case "volume": Collections.sort(results, new ComicBook.VolumeComparator());
        break;
    }
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

}
