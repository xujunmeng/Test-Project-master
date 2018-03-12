package b;

public class ConcreteStateA implements State {

	public void handle(String sampleParameter) {
		System.out.println("ConcreteStateA handle : " + sampleParameter);
	}

}
