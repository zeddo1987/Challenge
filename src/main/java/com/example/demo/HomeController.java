package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @RequestMapping("/")
    MessageRepository messageRepository;
    @GetMapping("/add")
    public String liatMessage(Model model){
        model.addAttribute("message", messageRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }
    @PosttMapping("/process")
    public String processForm(@Valid Message message,
        BindingResult result){
            if (result.hasErrors()){
                return "messageform";
            }
            messageRepository.save(message);
            return "redirect:/";
    }
   @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model)
   {
       return "show";
    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get() );
        return "messageform";
    }

    public String delMessage(@PathVariable("id") long id){
        messageRepository.deleteById(id);
        return "redirect:/";
    }
}
