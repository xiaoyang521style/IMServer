package cn.appservice.httpclient;
public class RequestHttpClient  {


	/**
	 * 服务端发验证服务端发送的短信
	 * @return
	 * @throws Exception
	 */
	public  String checkcode(String phone,String zone,String code , String appkey) throws Exception{

		String address = "https://webapi.sms.mob.com/sms/verify";
		MobClient client = null;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			String result = client.post();
			return result;
		} finally {
			client.release();
		}
	}
}
