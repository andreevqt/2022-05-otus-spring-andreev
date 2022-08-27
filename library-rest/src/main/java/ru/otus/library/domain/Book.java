package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title")
  private String title;
  @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;
  @JoinColumn(name = "genre_id")
  @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
  private Genre genre;

}
