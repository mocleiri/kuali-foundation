package org.kuali.common.util.strip;

public class Strip {

	/**
	 * @param args
	 */
	public static synchronized void main(String[] args) {
		new Strip().exec(args);
	}
	
	public void exec(String[] args) {
		try {
			System.out.println("hello world");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
