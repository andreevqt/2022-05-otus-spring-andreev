package ru.otus.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller для работы с сущностями")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class CrudControllersTests {

  private final SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor ADMIN_USER = user("admin").roles("ADMIN");
  private final SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor GUEST_USER = user("user").roles("GUEST");

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

  @DisplayName("защищенные роуты должны возвращать корректный ответ для авторизованнного пользователя с ролью ADMIN")
  @ParameterizedTest
  @ValueSource(strings = {"books"})
  public void testAuthentificatedUserWithRoleAdmin(String resource) throws Exception {
    mockMvc.perform(get(String.format("/%s", resource)).with(ADMIN_USER))
      .andExpect(status().isOk());
    mockMvc.perform(get(String.format("/%s/edit/1", resource)).with(ADMIN_USER))
      .andExpect(status().isOk());
    mockMvc.perform(get(String.format("/%s/create", resource)).with(ADMIN_USER))
      .andExpect(status().isOk());
    mockMvc.perform(post(String.format("/%s/delete/1", resource)).with(csrf()).with(ADMIN_USER))
      .andExpect(status().isFound());
  }

  @DisplayName("защищенные роуты должны возвращать корректный ответ для авторизованнного пользователя с ролью GUEST")
  @ParameterizedTest
  @ValueSource(strings = {"books"})
  public void testAuthentificatedUserWithRoleGuest(String resource) throws Exception {
    mockMvc.perform(get(String.format("/%s", resource)).with(GUEST_USER))
      .andExpect(status().isOk());
    mockMvc.perform(get(String.format("/%s/edit/1", resource)).with(GUEST_USER))
      .andExpect(status().isForbidden());
    mockMvc.perform(get(String.format("/%s/create", resource)).with(GUEST_USER))
      .andExpect(status().isForbidden());
    mockMvc.perform(post(String.format("/%s/delete/1", resource)).with(csrf()).with(GUEST_USER))
      .andExpect(status().isForbidden());
  }

  @DisplayName("защищенные роуты должны возвращать корректный ответ для неавторизованнного пользователя")
  @ParameterizedTest
  @ValueSource(strings = {"books"})
  public void testNotAuthentificatedUser(String resource) throws Exception {
    mockMvc.perform(get(String.format("/%s", resource)).with(anonymous()))
      .andExpect(status().isUnauthorized());
    mockMvc.perform(get(String.format("/%s/edit/1", resource)).with(anonymous()))
      .andExpect(status().isUnauthorized());
    mockMvc.perform(get(String.format("/%s/create", resource)))
      .andExpect(status().isUnauthorized());
    mockMvc.perform(post(String.format("/%s/delete/1", resource)).with(anonymous())
      .with(csrf())).andExpect(status().isUnauthorized());
  }

}
