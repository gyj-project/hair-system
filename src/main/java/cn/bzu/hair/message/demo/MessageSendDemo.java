package cn.bzu.hair.message.demo;

import cn.bzu.hair.message.config.AppConfig;
import cn.bzu.hair.message.lib.MessageSend;

public class MessageSendDemo {
	public static void main(String[] args) {
		AppConfig config = new AppConfig();
		MessageSend submail = new MessageSend(config);
		submail.addTo("17854337608");
		submail.addContent("【短信测试】您的验证码是：123456，请在3分钟内输入！");
		submail.send();
	}

}
