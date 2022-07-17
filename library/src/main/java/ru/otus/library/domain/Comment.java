package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "book_id", nullable = false)
  private Long bookId;
  @Column(name = "content")
  private String content;
  @JoinColumn(name = "book_id", insertable = false, updatable = false)
  @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
  private Book book;

  public Comment(Long id, Long bookId, String content) {
    this(id, bookId, content, null);
  }

}
