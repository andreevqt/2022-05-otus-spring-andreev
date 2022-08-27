package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.BookDto;
import ru.otus.library.exceptions.ResourceNotFoundException;
import ru.otus.library.mappers.AuthorMapper;
import ru.otus.library.mappers.BookMapper;
import ru.otus.library.mappers.GenreMapper;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final BookMapper bookMapper;
  private final AuthorMapper authorMapper;
  private final GenreMapper genreMapper;

  @GetMapping(path = "")
  public String list(Model model) {
    var books = bookMapper.booksToBookDtos(bookService.findAll());
    model.addAttribute("title", "Books");
    model.addAttribute("books", books);
    return "books/list";
  }

  @GetMapping(path = "/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    try {
      bookService.delete(id);
      return "redirect:/books";
    } catch (Exception e) {
      throw new ResourceNotFoundException();
    }
  }

  @GetMapping(path = "/edit/{id}")
  public String edit(@PathVariable("id") Long id, Model model) {
    return bookService.findById(id).map((book) -> {
      model.addAttribute("title", "Edit book");
      model.addAttribute("book", bookMapper.bookToBookDto(book));
      model.addAttribute("authors", authorMapper.authorsToAuthorDtos(authorService.findAll()));
      model.addAttribute("genres", genreMapper.genresToGenreDtos(genreService.findAll()));
      return "books/edit";
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @GetMapping(path = "/create")
  public String create(Model model) {
    model.addAttribute("title", "Create book");
    model.addAttribute("authors", authorMapper.authorsToAuthorDtos(authorService.findAll()));
    model.addAttribute("genres", genreMapper.genresToGenreDtos(genreService.findAll()));
    return "books/edit";
  }

  @PostMapping(path = "/save")
  public String save(@Valid @ModelAttribute("book") BookDto book,
                     BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", "Create book");
      model.addAttribute("book", book);
      model.addAttribute("authors", authorMapper.authorsToAuthorDtos(authorService.findAll()));
      model.addAttribute("genres", genreMapper.genresToGenreDtos(genreService.findAll()));
      return "books/edit";
    }

    bookService.save(bookMapper.bookDtoToBook(book));
    return "redirect:/books";
  }
}
