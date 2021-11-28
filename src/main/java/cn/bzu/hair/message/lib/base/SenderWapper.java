package cn.bzu.hair.message.lib.base;


import cn.bzu.hair.message.entity.DataStore;

/**
 * 包装类 ADDRESSBOOKMail、ADDRESSBOOKMessage、MAILSend、MAILXSend、MESSAGEXsend等父类
 * @author submail
 *
 */
public abstract class SenderWapper {

	protected DataStore requestData = new DataStore();

	public abstract ISender getSender();
}
