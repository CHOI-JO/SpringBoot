package org.mbc.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("hello.......");

        model.addAttribute("msg", "HELLO WORLD");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        List<String> list = Arrays.asList("최장옥", "조은아", "김채하", "윤제석", "이은진");
        model.addAttribute("list", list);
    }

    // 이너 클래스 생성, 안쪽에 클래스를 선언할 때 활용된다.
    class SampleDTO {
        //필드
        private String p1, p2, p3;

        // 생성자

        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }

        public String getP3() {
            return p3;
        }
        // 메서드
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        log.info("ex/ex2...........");

        // int형으로 정수를 뿌림 1~10까지 정수를 생성한다.
        List<String> stringList = IntStream.range(1,10)
                .mapToObj(i->"Data" +i)
                .collect(Collectors.toList()); //리스트에 정수(숫자) 문자열이 생성됨
       // [데이터 1, 데아터 2,...... 데이터 9]
        model.addAttribute("list", stringList); // 만든 객체 값

        Map<String, String> map = new HashMap<>(); // 모두 string형이어서 다 string으로 한다.
        map.put("id", "choi");
        map.put("pw", "1234"); // key, value

        model.addAttribute("map", map); // 만든 객체 값

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1="value -- p1";
        sampleDTO.p2="value -- p2";
        sampleDTO.p3="value -- p3";
        // 최종적으로 객체 3개 완성 됨

        model.addAttribute("dto", sampleDTO); // 만든 객체 값
    }
}
