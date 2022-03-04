package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
// "/hello"에서 이를 실행
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* Ctrl + o : OverrideMethod
        HttpServletRequest, HttpServletResponse 모두 인터페이스를 상속받는 인터페이스임.
        WAS가 서블릿 표준 스펙을 구현하는 데 필요한 구현체들. 해당 구현체가 밑에 request, response에 대한 로그로 남는 것.*/
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

         /*http://localhost:8080/hello?username=kim
         ?뒤에 있는 것 : query parameter*/
        String username = request.getParameter("username");//request.getParameter : Ctrl+ALt+v
        System.out.println("username = " + username);            //Shift+F6 : name refactoring

        response.setContentType("text/plain");  
        response.setCharacterEncoding("utf-8"); //euc-kr < utf-8
        //HTTP Header에 들어가는 값
        response.getWriter().write("hello " + username); 
        /*getWriter() write() 메소드를 가져옴
        write() HTTP 바디에 데이터를 씀
        HTTP message Body에 값을 집어넣음*/
    }
}

/*
 //요청이 오면 request, response 객체를 만들어서 내려줌.
 request = org.apache.catalina.connector.RequestFacade@6a2a1e4d
 response = org.apache.catalina.connector.ResponseFacade@790dea41

 응답을 보내기
 response HTTP message에 값이 담겨 나가게 됨.

 웹브라우저에 이름 값이 출력.
 Request Headers는 무시.
 Response Header : Content-Type 우리가 정해준 Type, Encoding이 들어감.
 Query-Parameter : 우리가 보낸 파라미터 값(Response 탭을 통해서도 확인할 수 있음.)

 response length에 대한 부분 등등.. 톰캣이 자동으로 잡아줌.
*/

/*
 HTTP Spec, 요청/응답 메세지를 다 맞춰주는 서블릿!
 우리가 자주쓰는 기능들을 서블릿으로 묶어준다.
*/

/*
@WebServlet
name: 서블릿 이름
urlPatterns : URL 매핑
(중복 불가)

HTTP 요청을 통해 매핑된 URL 호출시 
서블릿 컨테이너가 service 메서드를 실행
*/

/*
모든 HTTP 요청을 눈으로 보고 싶은 경우..?
main > resousrce > application.properties
...> logging.level.org.apache.cayate.http11 = debug
서버 재시작 후 요청하면 해당 과정이 다 나옴.

해당 옵션을 넣으면 편하게 확인할 수 있음.
그러나 운영서버에 넣는 것은 유의(성능저하 우려), 개발서버에만
 */

/*
스프링 부트가 내장 톰캣을 띄움.
서블릿 컨테이너를 통해 서블릿 생성.

**HTTP 요청/응답 메시지
스프링 부트 실행 후 내장 톰캣 서버를 띄움.
내부에 있는 서블릿 컨테이너를 통해 서블릿 생성(helloServlet).
---

localhost:8080/hello, http 요청/응답 메시지가 들어옴.
WAS에서 만든 request, response 객체를 만든 뒤 helloServlet의 서비스 메서드를 호출(싱글톤 형태)
작업 이후 (HTTP 응답 메시지 작성 이후) WAS가 response 정보를 가지고 웹 브라우저에 뿌림.

Content-Length, Date(부가적인) 같은 것들은 WAS가 자동으로 생성
 */

/*
Welcome Page 개설
main > webapp 페이지 만들기
index.html 도메인에서의 첫 화면.
basic.html에서 우리가 공부하는 내용들을 다 볼 수 있도록 해둠.
--HttpServlet의 요청에 대한 부분!
 */
