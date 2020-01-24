package com.wildcodequestion.skillhub.controller;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.CategoryRepository;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    private QuestionRepository questionRepository = new QuestionRepository();

/*
    @GetMapping("/questions")
    public String getAll(Model model) {

        model.addAttribute("questions", repository.findAll());

        return "questions";
    }

    @GetMapping("/question")
    public String getQuestion(Model model,
                            @RequestParam(required = false) Long id) {

        Question question = new Question();
        if (id != null) {
            question = repository.findById(id);
        }
        model.addAttribute("question", question);

        return "question";
    }
*/
    @PostMapping("/question")
    public String postQuestion(@ModelAttribute Question question) {

        if (question.getQuestionId() != null) {
            questionRepository.update(question);
        } else {
            questionRepository.save(question);
        }
        return "redirect:/questions";
    }
/*
    @GetMapping("/question/delete")
    public String deleteQuestion(@RequestParam Long id) {

        repository.deleteById(id);

        return "redirect:/questions";
    }
*/
}
