package cn.bzu.hair.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102300747207";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIr4ohsjY8DACHe4QCh5vSevk9FV2Alx8sB1XHzOdVsrZhhI+jpVJeDTRtL7+wuIvxjurroEYJ0+ulsRcT5Y3oosiNpBylhIzP4KoUZF8FbPipw0i/dNhON+2/xsl1xijZglOwSheNUt7MtBZ13y9ZrP+BVHjbVmgare9iGzgGQWL5D3eqUTT1ODvcv+PSxURl69niUtfglnmcUX3GYtpyLsM9XcBHzzAlXQsQ1a79Hvk5jbbDqCDRj9YNbU+jzFJR0oq3TlbqlPLdmyHm1p84bNTEblecIYh0jDyOv0l33Z63WMAWo/f7dhD7LdBUvHFhLBUgQ+TWKWKz1ZNhQMcVAgMBAAECggEABxhlqT4hBpjw5heJUMBNXG25xmv3gJdXrOdskoSWdaJjSUiiVDJexjcVQ52466mIyi9LDpQxTo1LrDw3IHdIGI1qr7+XJRhuXtQgwBI9xIBVoCpc+mNMXzMUCmJ9TrnMoQ3c3kfaVSG6IxIuIExHnvYWBVanN2uS19+zj3ouPFTQx+Vc2zB44fQ/0sya1yfVgaVc6qR1KhdkGAldhV0oCZUJ2QscLkYLX70kozZakkLzgQa4Xb+j3wQbsmK155+r6EwhngOYjGMWqiQazxte/I6dpGTxmvHzxQedthYDxAJoBqxHyqbmOmR2JWKezsSdQbOOwSIcZ8rLbcGfJIZdQQKBgQDkuCHDysi84Bw5MO7HBN0WBBOCNqlmsL4OPuKNSeZexEEuux583Ved3lHZ8zLFuEP5jz6/WDXLx29zmj86wSKGGF9v/25qPwJ7jwNLhOcAVuiBahCEHV/bLJKuixvy2kncRESgxVibVLb92MltHo9mRfbu2JsbcCB3YuDW9caGfQKBgQCY/TFe8yYJbt9gM/yiUMp7oLBEZ/vuNfldaV0QL9S4k4J3m075RBKc1CxsiVHO4p12Qr9ve7M55nWqDPXF2RAsPt1dE+OlCJu3x2Lwx4KAVbuLQ0vEJwxr4/BRiSbi4SN77zfBfG+Q7ZRxW9NKk2D2w9tEU+9n4c5IbyCdQV/ueQKBgQCtYgxukT4FOFsM29RW2JAEzYhAcNGQQDGDORajBr91XY8QLtdYzq5JxawE7cCydBJLj9SAVw1I8mHBn8+I6boTL0zB6T+5mRR3uB1GVXSMJFXTLSViEaaFzs9PM7M3MVVGfhYMTi3yapDh9EIVz1HjtOwRefBzJ1flSPaq3dvmPQKBgFL1qVoFm/CtkK+IQUx7ZvFXF35grLplxk0jH3XxmfZk9vCI5XBBu54TZ+uLIzN+q0g/tbNsbqnNEK0fzf51UFHN3Rfl+yG440fq9PoAFgIle0dvPJynWBMBTYXJS67nyugftipToeQDEOXPl2nvNTtaxYHtyfqY9dVNfXAIzU5xAoGARELBFJIkMdSjaSgd5r7nS9hHdJv9HYArUcaOwzBlOCJHrZcqItDBZahvYzY3SKijLTYJRAepfuy78qyAmkBf0V66QANZzcc7fBvH9AGxR+zksCAUfY2Rwu9akOJY+hU/M/EKCoDIcc/mPk0lTahrQPTF9PUjh9L4CQh/AqyiWIA=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA25zdP8kaC8p/QZG+4Taoo4CHFMnrBpvDa/yanA0lg7I7kvVlfZcBleLLmL422YdxZnmDs1MMoVTBwdroUCcp17A4htrvSELNO8Eg3oRyOLW6zVbC7Adivx9GEH9/KtCbg1KNYur+2GyGufdovYlDvM+t7s/iNMt9qBN3tIQ4mFRn66cl6yNWqjVikMV4CC4wzmvfZfe1Ff6vKDEWESMn8JBApnRlNfu38zOa2g4b6f7ozwqA9lwHUDAj6H6LXZro5LzynVw99M7PlNTdrQdWf0ASTYlj/TE/4w4QV0KOL2Fk8FXwxaC/1IlCSUQXTL1ioOettuxl7KCTiUWwO0EXjQIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8081/alipay/back";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8081/alipay/back";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

