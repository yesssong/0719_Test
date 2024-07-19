package controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 컴포넌트임을 표시함 -> 어노테이션으로 마킹
//@Component
@Controller
public class TestController {
	
	public TestController() {
		// TODO Auto-generated constructor stub
		System.out.println("---TestController()---");
	}
	
	
	// 외부에서 호출할 수 있는 uri 정의?
	@RequestMapping("/test.do")
	public String test() {
						// View Resolver
		return "test";  // 이렇게만 적어도 되는 이유 : Resolves view가  이렇게 경로 작성해주기 때문 => /WEB-INF/views/ + viewName +/test.jsp 
	}

	
	@RequestMapping(value="/hello.do", produces="text/html;charset=utf-8;")
	@ResponseBody	// return 값이 응답데이터   설계 상 view로 인식하기 때문에 이것을 적어줌으로써 바디임을 알리고 view가 아닌 데이터로 사용할 수 있게 함
	public String hello() {
		return "hello : 안녕하세요";		// viewresolver에 의해 hello를 view 이름으로 인식. 앞 뒤로 이름 붙여서 포워딩 시도할 것
	}
	
	
	@RequestMapping("/hi.do")
	public String hi(Model model) {		// Model : 인터페이스(사용설명서)
		
		String msg = "Hi Everyone!!!";
		
		// model 통해서 전달 된 데이터는 DispatcherServlet에게 전달됨
		// DS는 request Binding
		// return 값이 뷰면	    : request binding   -> model.addAttribute("msg", msg); 이 데이터
		// return 값이 redirect : parameter 사용
		model.addAttribute("msg", msg);  // 자바의 모든 객체 쓸 수 있음 ArrayList...
		
		return "hi";
	}
	
	
	@RequestMapping("/bye.do")
	public ModelAndView bye() {
		
		String msg = "Bye~~";
		
		// data + view
		ModelAndView mv = new ModelAndView();
		// DS는 전달된 데이터를 request binding
		mv.addObject("msg", msg);
		
		// DS는 전달된 뷰 정보 완성시키기 위해 ViewResolver에 작업지시
		mv.setViewName("bye");	//   /WEB/views/ + bye + .jsp
		
		return mv;
		
	}
	
	// redirect : test.do
	@RequestMapping("/hi2.do")
	public String hi2(RedirectAttributes ra) {
		
		// test.do 부르기
		String name = "Tom";
		ra.addAttribute("name", name);
		
		return "redirect:test.do";
	}
	
}