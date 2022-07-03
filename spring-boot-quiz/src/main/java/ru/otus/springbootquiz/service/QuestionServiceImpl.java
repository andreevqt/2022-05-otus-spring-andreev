package ru.otus.springbootquiz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springbootquiz.dao.QuestionDao;
import ru.otus.springbootquiz.domain.Question;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;

  public List<Question> listAll() {
    return questionDao.findAll();
  }

}
