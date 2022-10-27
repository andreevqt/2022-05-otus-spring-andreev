package ru.otus.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.domain.Book;
import ru.otus.library.mappers.AuthorMapper;
import ru.otus.library.mappers.BookMapper;
import ru.otus.library.mappers.GenreMapper;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller для работы с книгами")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BookService bookService;
  @MockBean
  private AuthorService authorService;
  @MockBean
  private GenreService genreService;
  @MockBean
  private BookMapper bookMapper;
  @MockBean
  private AuthorMapper authorMapper;
  @MockBean
  private GenreMapper genreMapper;

  @BeforeEach
  public void setUp() {
    var book = new Book(1L, "testBook", null, null);
    given(bookService.findById(1L)).willReturn(Optional.of(book));
    doNothing().when(bookService).delete(1L);
  }

  @DisplayName("защищенные роуты должны возвращать корректный ответ для авторизованнного пользователя")
  @WithMockUser(
    username = "user",
    authorities = {"ADMIN"}
  )
  @Test
  public void testAuthentificatedUser() throws Exception {
    mockMvc.perform(get("/books"))
      .andExpect(status().isOk());
    mockMvc.perform(get("/books/edit/1"))
      .andExpect(status().isOk());
    mockMvc.perform(get("/books/create"))
      .andExpect(status().isOk());
    mockMvc.perform(post("/books/delete/1").with(csrf()))
      .andExpect(status().isFound());
  }

  @DisplayName("защищенные роуты должны возвращать корректный ответ для не авторизованнного пользователя")
  @Test
  public void testNotAuthentificatedUser() throws Exception {
    mockMvc.perform(get("/books"))
      .andExpect(status().isUnauthorized());
    mockMvc.perform(get("/books/edit/1"))
      .andExpect(status().isUnauthorized());
    mockMvc.perform(get("/books/create"))
      .andExpect(status().isUnauthorized());
    mockMvc.perform(post("/books/delete/1").with(csrf()))
      .andExpect(status().isUnauthorized());
  }

}
