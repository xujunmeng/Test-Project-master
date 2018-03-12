package h;

import java.util.List;

public abstract class Component {
	
	public abstract void printStruct2(String preStr);
	
	public void addChild(Component child){
		throw new UnsupportedOperationException("对象不支持此功能");
	}
	public void removeChild(int index){
		throw new UnsupportedOperationException("对象不支持此功能");
	}
	public List<Component> getChild(){
		throw new UnsupportedOperationException("对象不支持此功能");
	}
}
