package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*[status-line]
        response.setStatus(200); //매직 넘버, 보다 확실하게 정해져있는 상수를 쓰면 이해에 훨씬 도움이 된다.*/
        response.setStatus(HttpServletResponse.SC_OK);
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);


        //[response-header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

       /* [Header 편의 메소드]
        content(response);
        cookie(response);
        redirect(response);*/

        //[Message Body]
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
        //response.getWriter().write("ok");
    }

    private void content(HttpServletResponse response) {
        /*Content-Type: text/plain;charset=utf-8
        Content-Length: 2
        response.setHeader("Content-Type", "text/plain;charset=utf-8");*/
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        /*Set-Cookie: myCookie=good; Max-Age=600;
        이 쿠키는 600초 동안 유효
        response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");*/
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        /*Status Code 302
        Location: /basic/hello-form.html

        response.setStatus(HttpServletResponse.SC_FOUND); //302
        response.setHeader("Location", "/basic/hello-form.html");*/

        response.sendRedirect("/basic/hello-form.html");
        //위와 아래에 있는 내용이 다 같다.
    }

}
