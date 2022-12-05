package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") // URL
    public String hello(Model model){ // model에 데이터를 실어서 View에 넘긴다.
        model.addAttribute("data", "hello!!");
        return "hello"; // 화면 이름 (html 자동으로 붙는다)
    }

}
