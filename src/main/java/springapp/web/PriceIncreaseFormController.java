package springapp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import springapp.service.PriceIncrease;
import springapp.service.ProductManager;

@SuppressWarnings("deprecation")
public class PriceIncreaseFormController extends SimpleFormController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	private ProductManager productManager;

	@Override
	public ModelAndView onSubmit(Object command) throws ServletException {
		int increase = ((PriceIncrease) command).getPercentage();
		logger.info("Increasing prices by " + increase + "%.");
		productManager.increasePrice(increase);
		logger.info("returning from PriceIncreaseForm view to " + getSuccessView());
		String successView = getSuccessView();
		View view = new RedirectView(successView);
		return new ModelAndView(view);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		PriceIncrease priceIncrease = new PriceIncrease();
		priceIncrease.setPercentage(20);
		return priceIncrease;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public ProductManager getProductManager() {
		return productManager;
	}

}