package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title")
  private String title;
  @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id")
  private Author author;
  @JoinColumn(name = "genre_id")
  @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
  private Genre genre;
  @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "book")
  private final List<Comment> comments = new ArrayList<>();

}
