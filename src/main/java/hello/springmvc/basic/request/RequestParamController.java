package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username ={}, age ={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username ={}, age ={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username ={}, age ={}", username, age);
        return "ok";
    }

    // 파라미터명과 변수명이 일치하면 @RequestParam을 생략해도 되지만
    // 명시적으로 파라미터 값을 받아온다는 것을 확인할 수 있기 때문에 붙이는게 낫겠다는 생각
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age) {
        log.info("username ={}, age ={}", username, age);
        return "ok";
    }

    /*
     * request-param?username=
     * 파라미터 이름만 있고 값이 없는 경우 빈문자로 통과
     * */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            // username이 필수로 넘어와야 함
            @RequestParam(required = true) String username,
            // age는 넘어오지 않아도 됨
            @RequestParam(required = false) Integer age) {
        log.info("username ={}, age ={}", username, age);
        return "ok";
    }

    /*
     * 빈문자의 경우에도 default값이 들어온다.
     * */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            // username이 필수로 넘어와야 함
            @RequestParam(required = true, defaultValue = "guest") String username,
            // age는 넘어오지 않아도 됨
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username ={}, age ={}", username, age);
        return "ok";
    }

    // 모든 파라미터 값들을 맵의 형태로 가져옴
    // 하나의 파라미터 키에 대한 값이 1개인게 확실하다면 Map을 사용해도 되지만, 여러개일 가능성이 있을 경우
    // MultiValueMap을 사용하자!!!
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username ={}, age ={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /*
    * HelloData 생성
    * 객체의 프로퍼티를 찾아 setter를 호출해서 값을 입력해줌
    * */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username ={}, age ={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
