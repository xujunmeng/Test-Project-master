package extension;

/**
 * @author saber.wang
 * @version 1.0
 * 异常基类
 * */
public abstract class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String code; //异常代码，对应资源文件中的
	
	private Object[] values; //格式化资源文件字符串信息
	
	private Integer responseCode; //异常客户端返回代码
	
	public BaseException(String message, Throwable cause, String code, Integer responseCode, Object[] values) {
		super(message, cause);
		this.setCode(code);
		this.setValues(values);
		this.setResponseCode(responseCode);
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Object[] getValues() {
		return this.values;
	}
	
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	
	public Integer getResponseCode() {
		return this.responseCode;
	}
	
}
