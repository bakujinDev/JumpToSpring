package com.bakujin.jump_to_spring.question;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.bakujin.jump_to_spring.answer.AnswerForm;
import com.bakujin.jump_to_spring.user.SiteUser;
import com.bakujin.jump_to_spring.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
  private final QuestionService questionService;
  private final UserService userService;

  @GetMapping("/list")
  public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "kw", defaultValue = "") String kw) {
    Page<Question> paging = this.questionService.getList(page, kw);
    model.addAttribute("paging", paging);
    model.addAttribute("kw", kw);
    return "question_list";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
    Question question = this.questionService.getQuestion(id);
    model.addAttribute("question", question);
    return "question_detail";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/create")
  public String questionCreate(QuestionForm questionForm) {
    return "question_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/create")
  public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
    if (bindingResult.hasErrors()) {
      return "question_form";
    }
    SiteUser siteUser = this.userService.getUser(principal.getName());
    this.questionService.create(questionForm.getSubject(),
        questionForm.getContent(), siteUser);
    return "redirect:/question/list";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/delete/{id}")
  public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
    Question question = this.questionService.getQuestion(id);
    if (!question.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
    }

    this.questionService.delete(question);
    return "redirect:/";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/vote/{id}")
  public String questionVote(Principal principal, @PathVariable("id") Integer id) {
    Question question = this.questionService.getQuestion(id);
    SiteUser siteUser = this.userService.getUser(principal.getName());
    this.questionService.vote(question, siteUser);
    return String.format("redirect:/question/detail/%s", id);
  }
}
