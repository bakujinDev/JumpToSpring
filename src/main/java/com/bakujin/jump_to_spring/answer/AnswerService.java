package com.bakujin.jump_to_spring.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.bakujin.jump_to_spring.question.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
  private final AnswerRepository answerRepository;

  public void create(Question question, String content) {
    Answer answer = new Answer();
    answer.setContent(content);
    answer.setCreateDate(LocalDateTime.now());
    answer.setQuestion(question);
    this.answerRepository.save(answer);
  }

}
