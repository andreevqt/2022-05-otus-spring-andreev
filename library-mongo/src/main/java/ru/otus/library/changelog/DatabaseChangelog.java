package ru.otus.library.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import lombok.AllArgsConstructor;
import ru.otus.library.domain.Author;
import ru.otus.library.repository.AuthorRepository;

@AllArgsConstructor
@ChangeLog
public class DatabaseChangelog {

  private final AuthorRepository authorRepository;

  @ChangeSet(order = "001", id = "initialSeed", author = "qprquo")
  public void insertAuthors() {
    var caroll = new Author(null, "Lewis Carroll");
    var rowling = new Author(null, "J. K. Rowling");
    var austen = new Author(null, "Jane Austen");

    authorRepository.save(caroll);
    authorRepository.save(rowling);
    authorRepository.save(austen);
  }
}
