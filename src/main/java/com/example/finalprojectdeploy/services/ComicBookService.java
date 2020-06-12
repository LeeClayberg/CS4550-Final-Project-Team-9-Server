package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.ComicBook;
import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.repositories.ComicBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ComicBookService {

  @Autowired
  ComicBookRepository comicBookRepository;
  @Autowired
  HistoryService historyService = new HistoryService();


  public List<ComicBook> findComicBooksForUserSortedSearch(Integer id, String sort, String resource, String query) {
    return this.getComicBooksAdvanced(comicBookRepository.findCollectionForUser(id), sort, resource, query);
  }

  public ComicBook findComicBookById(Integer id) {
    return comicBookRepository.findComicBookById(id);
  }

  public ComicBook createComicBook(ComicBook comicBook) {
    historyService.createHistoryAction(new HistoryAction(comicBook.getUserId(), "added", comicBook.getIssueId()));
    return comicBookRepository.save(comicBook);
  }

  //Updates the comic book's grade (nothing else)
  public ComicBook updateComicBook(Integer id, ComicBook updatedComicBook) {
    historyService.createHistoryAction(new HistoryAction(updatedComicBook.getUserId(), "updated", updatedComicBook.getIssueId()));
    ComicBook comicBook = comicBookRepository.findById(id).get();
    comicBook.updateComicBook(updatedComicBook);
    return comicBookRepository.save(comicBook);
  }

  public void deleteComicBook(Integer id) {
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
