package springapp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class InventoryControllerTest {

	@Test
	public void testHandleRequestView() throws Exception {
		InventoryController controller = new InventoryController();
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("hello", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());
		String nowValue = (String) modelAndView.getModel().get("now");
		assertNotNull(nowValue);
	}
}
