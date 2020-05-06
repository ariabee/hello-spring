package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    // Handles requests at root path localhost:8080
    @GetMapping
    @ResponseBody
    public String helloRoot() {
        return "Hello in root, Spring!";
    }

    // Handles requests at path localhost:8080/goodbye
    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    // Handles requests of the form /helloQuery/?coder=LaunchCode
    @GetMapping("helloQuery")
    @ResponseBody
    public String helloWithQueryParamGet(@RequestParam String coder){  // handler accepts data = dynamic handler
        return "Hello, " + coder + "!";
    }

    // Handles requests of the form /hello/LaunchCode
    @GetMapping("hello/{name}")
    @ResponseBody
    public String helloAgain(@PathVariable String name) {
        return "Hello again, " + name + "!";
    }

    // Handles requests at the path /hello/secret and redirects to "hello/{name}" handler
    @GetMapping("hello/secret")
    public String helloGoHere() {
        return "redirect:/hello/redirected%20secret";
    }

    // Responds to get and post requests at /hello?coder=LaunchCoder
    @RequestMapping( value="hello", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String helloWithQueryParam(@RequestParam String coder){  // handler accepts data = dynamic handler
        return "Hello, " + coder + "!";
    }

    @GetMapping("form")
    @ResponseBody
    public String helloForm() {
        String html =
            "<html>" +
                "<body>" +
                    "<form action='hello' method='post'>" + // submit a request to /hello
                     "<input type='test' name='coder' />" + // 'coder' is the query variable
                     "<input type='submit'  value='Greet me!' />" + // clicking this will submit the form input values
                    "</form>" +
                "</body>" +
            "</html>";
        return html;
    }

}
