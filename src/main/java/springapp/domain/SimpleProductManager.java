package springapp.domain;

import java.util.List;

@SuppressWarnings("serial")
public class SimpleProductManager implements ProductManager {

	@Override
	public List<Product> getProducts() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void increasePrice(int percentage) {
		throw new UnsupportedOperationException();
	}

	public void setProducts(List<Product> products) {
		throw new UnsupportedOperationException();
	}

}