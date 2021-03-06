package com.udemy.backendninja.controller;

import com.udemy.backendninja.component.ExampleComponent;
import com.udemy.backendninja.model.Person;
import com.udemy.backendninja.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/example")
public class ExampleController {

    public static final String EXAMPLE_VIEW = "example";

    @Autowired
    @Qualifier("exampleService")
    private ExampleService exampleService;

    //Inyectamos nustro componente "ExampleComponent"
    @Autowired // índica a Spring que vamos a inyectar un componente que se encuentra en su memoria
    @Qualifier("exampleComponent") // índica a Spring el nombre del bean que está en su memoria ("ExampleComponent")
    private ExampleComponent exampleComponent;

    //Primera forma: para cuando hacemos redirecciones sin datos o muy pocos datos
//    @RequestMapping(value = "/exampleString", method = RequestMethod.GET)
    @GetMapping("/exampleString")
    public String exampleString(Model model){
        exampleComponent.sayHello();
        // Habiendo un atributo th:text"${nombre_variable}" con nombre_variable = name en ambas partes (Controller y html)
        model.addAttribute("people", exampleService.getListPeople());
        return EXAMPLE_VIEW;
    }

    //Segunda forma: para cuando hay que insertar muchos datos a las plantillas
    @GetMapping("/exampleMAV")
//    @RequestMapping(value = "/exampleMAV", method = RequestMethod.GET)
    public ModelAndView exampleMAV(){
        ModelAndView mav = new ModelAndView(EXAMPLE_VIEW);
        mav.addObject("people", exampleService.getListPeople());
        return mav;
    }
}
