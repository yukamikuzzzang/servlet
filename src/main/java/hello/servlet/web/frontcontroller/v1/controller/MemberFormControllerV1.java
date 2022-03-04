package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath ="/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 이동할 때 사용
        dispatcher.forward(request, response);  //forward : 서블릿에서 JSP를 호출할 수 있음
        //고객의 요청이 오면 서블릿(컨트롤러) 호출.. 그리고 viewPath(뷰)로 호출(서버 안에서 호출이 일어남.)
    }
}
