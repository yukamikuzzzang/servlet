package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //해당 코드는 MemberSaveServlet의 앞부분을 복사
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        //멤버 객체 생성
        Member member = new Member(username, age);
        memberRepository.save(member);  //비즈니스 로직 호출

        //Model에 데이터를 보관
        //jsp파일이 나올 수 있도록.
        request.setAttribute("member",member);  //request 내부의 Map에 저장

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);  //서버 내부에서 호출.

    }
}
