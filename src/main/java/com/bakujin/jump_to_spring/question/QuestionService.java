package com.bakujin.jump_to_spring.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bakujin.jump_to_spring.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
  private final QuestionRepository questionRepository;

  public List<Question> getList() {
    return this.questionRepository.findAll();
  }

  public Question getQuestion(Integer id) {
    Optional<Question> question = this.questionRepository.findById(id);
    if (question.isPresent()) {
      return question.get();
    } else {
      throw new DataNotFoundException("question not found");
    }
  }
}