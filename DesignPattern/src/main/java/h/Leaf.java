package h;

public class Leaf extends Component {

	private String name;
	
	public Leaf(String name){
		this.name = name;
	}
	
	
	
	@Override
	public void printStruct2(String preStr) {
		System.out.println(preStr + "-" + name);
	}

}
