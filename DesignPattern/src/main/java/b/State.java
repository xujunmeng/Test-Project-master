package b;
/**
 * 抽象状态类
 * @author junmeng.xu
 *
 */
public interface State {
	/**
	 * 状态对应的处理
	 */
	public void handle(String sampleParameter);
}
