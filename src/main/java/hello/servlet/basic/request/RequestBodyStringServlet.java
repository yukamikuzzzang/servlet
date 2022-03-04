package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("OK");

       /* 메시지 바디의 내용을 바이트코드로 받을 수 있음.
        바이트코드를 String으로 바꾸는 방법? StreamUtils
        바이트 코드를 꺼낼 때 인코딩 정보를 알려줘야 함.
        문자를 바이트코드로 바이트코드를 문자로 바꿀때의 정보를 알려주어야 함.
        POSTMAN에서 HTTP 바디에 값을 넣어서 확인해보자.
        중간에 뜨는 디버그는 무시해도 무관.
        HTTP BODY안에 있는 내용이 출력되는 모습을 확인할 수 있다.
        messageBody = Hello
        요즘은 JSON으로 주고받는다고..!
        */
    }
}
