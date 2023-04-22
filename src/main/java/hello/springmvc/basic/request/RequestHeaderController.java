package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          // http에서 locale은 서버에서 지원하는 언어를 뜻함
                          Locale locale,
                          // 모든 헤더 정보 다 받아오기
                          // MultiValueMap : Map과 유사하지만 하나의 키에 여러 값을 받을 때 사용
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          // 헤더 정보 중 host만 가져오기
                          @RequestHeader("host")String host,
                          // 쿠키 받아오기
                          // required=false라고 설정하면 없어도 된다는 뜻임
                          @CookieValue(value = "myCookie", required = false) String cookie) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";

    }
}
