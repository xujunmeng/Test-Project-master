package b;


/**
 * 状态模式
 * @author junmeng.xu
 *
 */
public class Context {
	//持有一个State类型的对象实例
	private State state;
	
	public void setState(State state){
		this.state = state;
	}
	
	/**
	 * 用户感兴趣的接口方法
	 */
	public void request(String sampleRarameter){
		state.handle(sampleRarameter);
	}
}
