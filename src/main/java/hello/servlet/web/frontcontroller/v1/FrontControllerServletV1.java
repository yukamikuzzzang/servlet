package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")   //v1 렉토리 하위 모든 폴더에 접근가능
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>(); //매핑 정보, 어떤 URL을 호출하면 ControllerV1을 호출해!

    public FrontControllerServletV1() {  //서블릿 생성시 해당 값을 미리 저장하여 꺼내쓸 수 있도록 함.
       controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
       controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
       controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();    // '/front-controller/v1/members/*', 이 부분을 얻을 수 있음.

        //ControllerV1 controller = new MemberListControllerV1(); //부모는 자식을 받을 수 있다. <다형성>
        ControllerV1 controller = controllerMap.get(requestURI); // 해당하는 객체가 있으면 가져옴.
        //만약 없다면(NOT FOUND: 404)
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;// 404인 경우 바로 return
        }

        controller.process(request,response); //문제 없으면 오버라이드 된 메소드를 호출
        //다형성 부분을 잘 이해할 수 있어야 함.

    }
}
