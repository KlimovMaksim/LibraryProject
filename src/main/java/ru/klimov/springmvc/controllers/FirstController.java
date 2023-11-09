package ru.klimov.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");

//        System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam("a") int a,
                            @RequestParam("b") int b,
                            @RequestParam("action") Actions actions,
                            Model model){
        double result;

        switch (actions){
            case ADDITION:
                result = a + b;
                break;
            case MULTIPLICATION:
                result = a * b;
                break;
            case SUBTRACTION:
                result = a - b;
                break;
            case DIVISION:
                result = (double) a / b;
                break;
            default:
                result = 0;
                break;

        }
        model.addAttribute("result", result);
        return "first/calculator";
    }
}
