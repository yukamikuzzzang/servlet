package hello.servlet.web.frontcontroller.v3;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/V3/members/new-form : 들어온 값
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
//V3 디렉토리 하위 모든 폴더에 접근가능, 서블릿 호출
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>(); //매핑 정보, 어떤 URL을 호출하면 ControllerV1을 호출해!

    public FrontControllerServletV3() {  //서블릿 생성시 해당 값을 미리 저장하여 꺼내쓸 수 있도록 함.
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();    // '/front-controller/v3/members/*', 이 부분을 얻을 수 있음.

        ControllerV3 controller = controllerMap.get(requestURI); // 컨트롤러에서 해당하는 V3객체를 찾고 호출. MemberFormControllerV3()
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;// 404인 경우 바로 return
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        //new-form
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
