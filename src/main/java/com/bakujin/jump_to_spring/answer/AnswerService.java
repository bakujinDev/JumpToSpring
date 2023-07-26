package com.bakujin.jump_to_spring.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.bakujin.jump_to_spring.question.Question;
import com.bakujin.jump_to_spring.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
  private final AnswerRepository answerRepository;

  public Answer create(Question question, String content, SiteUser author) {
    Answer answer = new Answer();
    answer.setContent(content);
    answer.setCreateDate(LocalDateTime.now());
    answer.setQuestion(question);
    answer.setAuthor(author);
    this.answerRepository.save(answer);
    return answer;
  }

}
