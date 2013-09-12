package uk.co.stearnes.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/simple")
public class SimpleController {

	private String test;

	@Autowired
	public SimpleController(@Value("${test}")String test) {
		this.test = test;
	}

	@RequestMapping("")
	public ModelAndView simple() {
		ModelAndView mav = new ModelAndView("simpleWorld");
		mav.getModel().put("message", test);
		return mav;
	}
}
