package ru.otus.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;
  private final Map<Integer, Boolean> answers;

  @Autowired
  public QuestionServiceImpl(QuestionDao questionDao) {
    this.questionDao = questionDao;
    this.answers = new HashMap<>();
  }

  public void answer(int questionId, int answer) {
    var questions = questionDao.findAll();
    questions.stream()
      .filter(q -> q.getId() == questionId)
      .findFirst()
      .ifPresent(question -> answers.put(
        questionId,
        question.getCorrectAnswer() == answer
      ));
  }

  public List<Question> listAll() {
    return questionDao.findAll();
  }

  public int getScore() {
    return answers.values().stream().reduce(0, (acc, value) -> acc + (value ? 1 : 0), Integer::sum);
  }
}
