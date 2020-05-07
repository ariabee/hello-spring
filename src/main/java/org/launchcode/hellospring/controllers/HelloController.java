package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class HelloController {

    // Handles requests at root path localhost:8080
    @GetMapping
    public String helloRoot() {
        return "Hello in root, Spring!";
    }

    // Handles requests at path localhost:8080/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    // Handles requests of the form /helloQuery/?coder=LaunchCode
    @GetMapping("helloQuery")
    public String helloWithQueryParamGet(@RequestParam String coder) {  // handler accepts data = dynamic handler
        return "Hello, " + coder + "!";
    }

    // Handles requests of the form /hello/LaunchCode
    @GetMapping("hello/{name}")
    public String helloAgain(@PathVariable String name) {
        return "Hello again, " + name + "!";
    }

    // Handles requests at the path /hello/secret and redirects to "hello/{name}" handler
    @GetMapping("hello/secret")
    public String helloGoHere() {
        return "redirect:/hello/redirected-secret";
    }

    // Responds to get and post requests at /hellocoder?coder=LaunchCoder
    //@RequestMapping( value="hellocoder", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("hellocoder")
    public String helloWithQueryParam(@RequestParam String coder) {  // handler accepts data = dynamic handler
        return "Hello, " + coder + "!";
    }

    @GetMapping("form")
    public String helloForm() {
        String html =
                "<html>" +
                        "<body>" +
                        "<form action='hellocoder' method='post'>" + // submit a request to /hello
                        "<input type='test' name='coder' />" + // 'coder' is the query variable
                        "<input type='submit'  value='Greet me!' />" + // clicking this will submit the form input values
                        "</form>" +
                        "</body>" +
                        "</html>";
        return html;
    }


    // ****** Exercises: Controllers and Routing ******
    // 1) at "hello" path, use a GET request handler method to display a form
    //    --> form is submitted via a "POST" request
    //    --> form should ask for Name in a text input, and Language in select element
    //    --> form should have a submit button to submit the form values
    //
    // 2) at "hello" path, use a POST (or post/get) request handler method to handle the user's input
    //    --> method takes in name and language from request
    //    --> build greeting based on the values

    @GetMapping("hello")
    @ResponseBody
    public String helloLangForm() {
        String html = "<html>" +
                "<form method='post' action=''>" +
                "<input type='text' name='name' />" +
                "<select name='language'>" +
                "<option value='english'>English</option>" +
                "<option value='french'>French</option>" +
                "<option value='german'>German</option>" +
                "<option value='spanish'>Spanish</option>" +
                "<option value='arabic'>Arabic</option>" +
                "<input type='submit' value='Greet me!'/>" +
                "</form>" +
                "</html>";

        return html;
    }

    //    @PostMapping("hello")
    //    @RequestMapping(value="hello", method={RequestMethod.POST. RequestMethod.GET})
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public static String createMessage(@RequestParam String name, @RequestParam String language) {
        // assign default name if name is null
        if (name == null || name == "") {
            name = "World";
        }
        // create a greeting variable
        String greeting = "";
        String direction = "ltr";

        // check the value of language to choose the greeting
        if (language.equals("english")) {
            greeting = "Hello";
        } else if (language.equals("french")) {
            greeting = "Bonjour";
        } else if (language.equals("german")) {
            greeting = "Hallo";
        } else if (language.equals("spanish")) {
            greeting = "hola";
        } else if (language.equals("arabic")) {
            greeting = "مرحبا";
            direction = "rtl";
        }

        return "<html><body><h1 style='color: green; direction: " + direction + "'>" +
                greeting + ", " + name + "!" +
                "</h1></body></html>";
    }

}
