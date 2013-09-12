package uk.co.stearnes.mvc;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class SimpleControllerTest {

	SimpleController controller;
	
	@Before
	public void setUp() {
		controller = new SimpleController("blah");
	}
	
	@Test
	public void simpleReturnsSimpleWithModel() {
		ModelAndView actual = controller.simple();
		
		Assert.assertEquals("simpleWorld", actual.getViewName());
		Map<String, Object> model = actual.getModelMap();
		Assert.assertEquals("blah", model.get("message"));
	}
	
}
