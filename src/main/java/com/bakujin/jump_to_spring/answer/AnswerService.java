package com.bakujin.jump_to_spring.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bakujin.jump_to_spring.DataNotFoundException;
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

  public Answer getAnswer(Integer id) {
    Optional<Answer> answer = this.answerRepository.findById(id);
    if (answer.isPresent()) {
      return answer.get();
    } else {
      throw new DataNotFoundException("answer not found");
    }
  }

  public void modify(Answer answer, String content) {
    answer.setContent(content);
    answer.setModifyDate(LocalDateTime.now());
    this.answerRepository.save(answer);
  }

  public void delete(Answer answer) {
    this.answerRepository.delete(answer);
  }

  public void vote(Answer ansewr, SiteUser siteUser) {
    ansewr.getVoter().add(siteUser);
    this.answerRepository.save(ansewr);
  }
}
