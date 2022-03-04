package hello.servlet.web.frontcontroller.v2;


import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v2/members/new-form : 들어온 값
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
//v2 디렉토리 하위 모든 폴더에 접근가능, 서블릿 호출
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>(); //매핑 정보, 어떤 URL을 호출하면 ControllerV1을 호출해!

    public FrontControllerServletV2() {  //서블릿 생성시 해당 값을 미리 저장하여 꺼내쓸 수 있도록 함.
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();    // '/front-controller/v2/members/*', 이 부분을 얻을 수 있음.

        ControllerV2 controller = controllerMap.get(requestURI); // 컨트롤러에서 해당하는 V2객체를 찾고 호출. MemberFormControllerV2()
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;// 404인 경우 바로 return
        }

        //new MyView("/WEB-INF/views/new-form.jsp"); 생성결과
        MyView view = controller.process(request, response);//문제 없으면 오버라이드 된 메소드(process)를 호출
        view.render(request, response); // 생성결과에 대한 render 호출
    }
}
