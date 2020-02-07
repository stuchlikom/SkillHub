package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.entity.Answer;
import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.AnswerRepository;
import com.wildcodeschool.skillhub.repository.CategoryRepository;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private CrudDao<Question> questionRepository = new QuestionRepository();
    private CrudDao<Answer> answerRepository = new AnswerRepository();
    private CrudDao<Category> categoryRepository = new CategoryRepository();

    @GetMapping("/")
    public String start() {

        return "index";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }
    
    @GetMapping("/questions")
    public String getAll(Model model, @RequestParam(required = false) Long catSelected) {

        if (catSelected == null) catSelected = 0L;
        //catSelected++;
        model.addAttribute("catSelected", catSelected);
        model.addAttribute("categories", categoryRepository.findAll(null));
        model.addAttribute("questions", questionRepository.findAll(catSelected));
        //model.addAttribute("answers", answerRepository.findAll(null));

/*
    WebContext ctx = 
        new WebContext(request, response, servletContext, request.getLocale());
    ctx.setVariable("catSelected", catSelected);        
*/
        return "questions";
        //return "filter: " + catSelected;
    }    

/*
    private CategoryRepository categoryRepository = new CategoryRepository();

    @RequestMapping(value = { "/categoryList" }, method = RequestMethod.GET)
    public String categoryList(Model model) {
 
        Category form = new Category();
        model.addAttribute("category", form);
 
        List<Category> list = categoryRepository.findAll();
        model.addAttribute("categories", list);
 
        return "categoryList";
    }
*/

/*
    @GetMapping("/question")
    public String getQuestion(Model model,
                            @RequestParam(required = false) Long questionId) {

        Question question = new Question();
        if (questionId != null) {
            question = questionRepository.findById(questionId);
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
        return "redirect:/questions?catSelected=" + question.getCategory();
    }

    @PostMapping("/answer")
    public String postAnswer(@ModelAttribute Answer answer) {

        if (answer.getAnswerId() != null) {
            answerRepository.update(answer);
        } else {
            answerRepository.save(answer);
        }
        return "redirect:/questions?catSelected=" + answer.getCategory();
    }
/*
    @PostMapping("/ServerRequestInfoOperations operations = new ServerRequestInfoOperations();")
    public String postQuestion(@ModelAttribute Question question) {

        if (question.getQuestionId() != null) {
            questionRepository.update(question);
        } else {
            questionRepository.save(question);
        }
        return "redirect:/questions";
    }
*/
    @GetMapping("/question/delete")
    public String deleteQuestion(@RequestParam Long questionId) {

        questionRepository.deleteById(questionId);

        return "redirect:/questions";
    }

}
