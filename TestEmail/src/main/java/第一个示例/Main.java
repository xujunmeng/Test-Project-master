package 第一个示例;

import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

/**
 * @author junmeng.xu
 * @date 2016年10月19日上午11:03:18
 */
public class Main {

	// 这里是SMTP发送服务器的协议
	public static final String HOST_NAME = "mail.chinascopefinancial.com";

	// 登陆邮件服务器的用户名、密码、昵称
	public static final String USERNAME = "junmeng.xu@chinascopefinancial.com";

	public static final String PASSWORD = "4ZKHT2VF";

	public static final String NICKNAME = "测试主题";

	/**
	 * 发送邮件（成功true、失败false）
	 * 
	 * @param receiveEmail
	 *            收件人邮箱
	 * @param receiveNick
	 *            收件人昵称(随便取)
	 * @param subject
	 *            标题
	 */
	public static final boolean sendMail(String receiveEmail,
			String receiveNick, String subject, String contents) {

		HtmlEmail email = new HtmlEmail();

		try {
			// smtp host
			email.setHostName(HOST_NAME);

			// 登陆邮件服务器的用户名和密码
			email.addTo(receiveEmail, receiveNick);

			// 发送人
			email.setFrom(USERNAME, NICKNAME);

			// 标题
			email.setSubject(subject);

			// 邮件内容
			email.setCharset("UTF-8");
			email.setHtmlMsg(contents);

			// 发送
			email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Test
	public void test() {

		while(true){
			// String receiveEmail = "xujunmeng2012@163.com";
			String receiveEmail = "448725919@qq.com";
			String receiveNick = "junmeng";
			String subject = "测试一下哈";
			String contents = "成功了，哈哈哈哈哈.吃过饭了吗？";
			sendMail(receiveEmail, receiveNick, subject, contents);
		}
	}
}
