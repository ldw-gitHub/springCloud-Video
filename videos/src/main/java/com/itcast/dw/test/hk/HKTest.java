/*
package com.itcast.dw.test.hk;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

public class HKTest {
	*/
/**
	 * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
	 *//*

	static{
		ArtemisConfig.host = "10.0.0.37:443"; // artemis网关服务器ip端口
//		ArtemisConfig.host = "portal.szwelot.com:8900"; // artemis网关服务器ip端口
//		ArtemisConfig.host = "dev.szwelot.com"; // artemis网关服务器ip端口
		ArtemisConfig.appKey = "28437181"; // 秘钥appkey
		ArtemisConfig.appSecret = "gCuQ49HonPKRSa8WoexF";// 秘钥appSecret

		//深林安防
//		ArtemisConfig.host = "192.168.1.100:443"; // artemis网关服务器ip端口
////		ArtemisConfig.host = "portal.szwelot.com:8900"; // artemis网关服务器ip端口
////		ArtemisConfig.host = "dev.szwelot.com"; // artemis网关服务器ip端口
////		ArtemisConfig.appKey = "24107448"; // 秘钥appkey
////		ArtemisConfig.appSecret = "3hvaBSj4abxkTzKaHx7Z";// 秘钥appSecret 对内
//		ArtemisConfig.appKey = "27276948"; // 秘钥appkey
//		ArtemisConfig.appSecret = "O6op7KaNSOlyU2E5FHq6";// 秘钥appSecret

		//育新学校
//		ArtemisConfig.host = "192.168.40.241"; // artemis网关服务器ip端口
//		ArtemisConfig.appKey = "20745025"; // 秘钥appkey
//		ArtemisConfig.appSecret = "1A4hvyEz0l0iIF4PhyVG";// 秘钥appSecret
	}
	*/
/**
	 * STEP2：设置OpenAPI接口的上下文
	 *//*

	static final String ARTEMIS_PATH = "/artemis";
	*/
/**
	 * STEP3：设置参数提交方式
	 *//*

	static final String contentType = "application/json";

	//组织架构url
	static final String org_Url = ARTEMIS_PATH + "/api/resource/v1/org/orgList";
	//获取组织下人员
	static final String people_Url = ARTEMIS_PATH + "/api/resource/v2/person/orgIndexCode/personList";
	//根据身份证获取人员
	static final String idcode_people_Url = ARTEMIS_PATH + "/api/resource/v1/person/certificateNo/personInfo";
	//根据手机号获取人员
	static final String phone_people_Url = ARTEMIS_PATH + "/api/resource/v1/person/phoneNo/personInfo";
	//预约免登入接口
	static final String APPOINTMENT_REGISTRATION_URL = ARTEMIS_PATH + "/api/visitor/v1/appointment/registration";
	//取消预约
	static final String APPOINTMENT_CANCEL_URL = ARTEMIS_PATH + "/api/visitor/v1/appointment/cancel";
	//人脸检测
	static final String PICTURE_CHECK_URL = ARTEMIS_PATH + "/api/frs/v1/face/picture/check";

	// /api/visitor/v1/appointment/records   访客预约记录
	// /api/visitor/v2/appointment/records
	// /api/visitor/v1/visiting/records   返回数据少  访客来访记录
	// /api/visitor/v2/visiting/records   返回数据多
	//访客来访记录
	static final String VISITING_RECORDS_V1_URL = ARTEMIS_PATH + "/api/visitor/v1/visiting/records";
	//来访记录
	static final String VISITING_RECORDS_V2_URL = ARTEMIS_PATH + "/api/visitor/v2/visiting/records";
	//访客签离
	static final String VISITING_OUT_URL = ARTEMIS_PATH + "/api/visitor/v1/visitor/out";

	static final String AUTH_CONFIG_DELETE = ARTEMIS_PATH + "/api/acps/v1/auth_config/delete";
	static final String AUTH_CONFIG_SEARCH = ARTEMIS_PATH + "/api/acps/v1/auth_config/search";
	static final String AUTH_CONFIG_ADD = ARTEMIS_PATH + "/api/acps/v1/auth_config/add";


	static final String ORG_LIST = ARTEMIS_PATH + "/api/resource/v1/org/advance/orgList";

	static final String ORG_DETAIL_LIST = ARTEMIS_PATH + "/api/resource/v1/org/orgIndexCode/orgInfo";

	//门禁列表
	static final String ACS_DEVICE_LIST = ARTEMIS_PATH + "/api/resource/v1/acsDevice/acsDeviceList";

	//查询人员列表v2
	static final String PERSON_LIST = ARTEMIS_PATH + "/api/resource/v2/person/advance/personList";

	//获取APP推送列表
	static final String bforestfire = ARTEMIS_PATH + "/api/bforestfire/v1/app/alarms";

	// 根据海康平台的人员id获取人员
	static final String PEOSON_ID_URL = ARTEMIS_PATH + "/api/resource/v1/person/personId/personInfo";

	//智能应用平台事件订阅
	static final String EVENT_GETTOPICINFO = ARTEMIS_PATH + "/api/common/v1/event/getTopicInfo";



	public static String PostHKServer(String url, JSONObject params) {
		Map<String, String> path = new HashMap<String, String>();
		path.put("https://", url);

		String result = ArtemisHttpUtil.doPostStringArtemis(path, params.toString(), null, null, contentType, null);// post请求application/json类型参数
		return result;
	}

	public static String PostHKServerArray(String url, JSONArray params) {
		Map<String, String> path = new HashMap<String, String>();
		path.put("https://", url);

		String result = ArtemisHttpUtil.doPostStringArtemis(path, params.toString(), null, null, contentType, null);// post请求application/json类型参数
		return result;
	}



	public static void main(String[] args) {
		JSONObject jsonBody = new JSONObject();

		//事件订阅
*/
/*		JSONArray eventTypes = new JSONArray();
		eventTypes.add(3187675137l);

		jsonBody.put("eventTypes", eventTypes);
		String data = PostHKServer(EVENT_GETTOPICINFO, jsonBody);*//*




*/
/*		jsonBody.put("personId", "d08a567a4e324fab8bbc15c77cfb8123");
		String data = PostHKServer(PEOSON_ID_URL, jsonBody);*//*


*/
/*		jsonBody.put("type", 0);
		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 20);
		String result = PostHKServer(bforestfire,jsonBody);*//*


		//查询人员列表v2
*/
/*		JSONArray expressions = new JSONArray();
		JSONObject expression = new JSONObject();
		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 100);

		//全体教职员工_在编人员_非编人员_党员_行政人员
		//allStaff_staff_nonStaff_partyMember_adminStaff@163.com

		//资源属性名，支持按jobNo、phoneNo、email、updateTim、cerateTime查询，例如：key传updateTime，operator传between可以查询特定时间段更新的数据，
		//考虑到校时和夏令时，建议值查询过去一天的数据变更
//		expression.put("key", "personName");//资源属性名，key传updateTime，operator传between可以查询特定时间段更新的数据，考虑到校时和夏令时，建议值查询过去一天的数据变更
		expression.put("key", "email");//资源属性名，key传updateTime，operator传between可以查询特定时间段更新的数据，考虑到校时和夏令时，建议值查询过去一天的数据变更
		expression.put("operator", 6);//操作运算符， 0 ：= 1 ：>= 2 ：<= 3 ：in 4 ：not in 5 ：between 6 ：like 7 ：pre like 8 ：suffix like
		*//*
*/
/**
		 * 资源属性值，=、>=、<=、like、values数组长度只能是1； in、not in，values数组长度大于1，最大不超时20； in_array用于查询key值有多个value的情况，
		 * 例如行车监控添加的设备类型为encodeDevice、encodeDeviceMss两个类型，
		 * 使用encodeDevice或者encodeDeviceMss都可以查询到；	between只能用于整形、日期 ； like只能用于字符串。
		 *//*
*/
/*
		JSONArray values = new JSONArray();
		values.add("allStaff");
		expression.put("values", values);
		expressions.add(expression);
		jsonBody.put("expressions", expressions);
		String result = PostHKServer(PERSON_LIST,jsonBody);
		System.out.println(result);*//*


		//组织架构
*/
/*		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 20);
		String result = PostHKServer(org_Url,jsonBody);
		System.out.println(result);*//*


		//门禁设备
*/
/*		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 10);
		String result = PostHKServer(ACS_DEVICE_LIST,jsonBody);
		System.out.println(result);*//*


		//root000000
		//f7012be5-7666-4774-b7cb-ded145b08f80

		//根据组织架构获取人员
*/
/*		jsonBody.put("orgIndexCode", "f7012be5-7666-4774-b7cb-ded145b08f80");
		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 1000);
		String result = PostHKServer(people_Url,jsonBody);
		System.out.println(result);*//*


		//获取组织信息
*/
/*		jsonBody.put("orgIndexCode", "root000000");
		String result = PostHKServer(ORG_DETAIL_LIST,jsonBody);
		System.out.println(result);*//*


		//根据组织名称获取人员
*/
/*		jsonBody.put("orgName", "育新学校/老师");
		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 10);
		String result = PostHKServer(ORG_LIST,jsonBody);
		System.out.println(result);*//*


		//手机号获取
//		jsonBody.put("phoneNo", "18674053757");
//		String result = PostHKServer(phone_people_Url,jsonBody);
//		System.out.println(result);
		//fd394352b7f94d9db5cc74cc75f32fc1
		//31f2569f04fb44c5ad7ce1e08ac92783

		//身份证号获取
*/
/*		jsonBody.put("certificateNo", "18627963718");
//		jsonBody.put("certificateNo", "18664570196");
//		jsonBody.put("certificateNo", "18627963718");
		jsonBody.put("certificateType", 111);
		String result = PostHKServer(idcode_people_Url,jsonBody);
		System.out.println(result);*//*


		//预约
		//receptionistId被访问唯一标识
		//visitStartTime来访时间
		//visitEndTime来访时间

		*/
/**
		 * visitorInfo访客信息
		 *   visitorName访客姓名，支持1-32位中英文字符，不能包含 ‘ / \ : * ? “ < > |
		 *   gender访客性别，1-男,2-女
		 *   phoneNo联系电话建议填写手机号码，仅支持1-20位纯数字
		 *   plateNo车牌号，1-16位，不能包含 ‘ / \ : * ? “ < > |
		 *   certificateType证件类型，参考附录A.11
		 *   certificateNo证件号码，1~20个数字、字母组成；证件号码非空时，证件类型必填
		 *   visitorPhoto头像base64编码的字节流，图片最大200K，仅支持jpg格式。 访客头像需要秘钥加密传输，由于访客头像需要下发到设备，因此该接口会对访客头像进行质量检测，只有人脸评分大于等于75时，接口才会返回成功
		 *//*

*/
/*		jsonBody.put("receptionistId", "fd394352b7f94d9db5cc74cc75f32fc1");
		jsonBody.put("visitStartTime", "2019-12-13T12:00:00+08:00");
		jsonBody.put("visitEndTime", "2019-12-13T13:00:00+08:00");

		JSONObject visitorInfo = new JSONObject();
		visitorInfo.put("visitorName", "石凯");
		visitorInfo.put("gender", "1");
		visitorInfo.put("phoneNo", "18674053745");
		visitorInfo.put("certificateType", "111");
		visitorInfo.put("certificateNo", "11111111111111");
		jsonBody.put("visitorInfo", visitorInfo);

		JSONObject visitorPermissionSet = new JSONObject();
		visitorPermissionSet.put("defaultPrivilegeGroupFlag", "1");
		jsonBody.put("visitorPermissionSet", visitorPermissionSet);
		String result = PostHKServer(APPOINTMENT_REGISTRATION_URL,jsonBody);

		System.out.println(result);*//*


		*/
/**
		 * 预约来访查询
		 *//*

*/
/*		jsonBody.put("receptionistId", "fd394352b7f94d9db5cc74cc75f32fc1");
		jsonBody.put("visitorName", "石凯");
//		jsonBody.put("phoneNo", "18674053757");
		jsonBody.put("orderId", "40feb6da-1d58-11ea-a9c3-9785090aa485");
		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 20);
		String result = PostHKServer(VISITING_RECORDS_V1_URL,jsonBody);
		System.out.println(result);*//*



		*/
/**
		 * 取消预约
		 *//*

*/
/*		JSONArray array = new JSONArray();
		array.add("6990");
		System.out.println(array.toJSONString());
		jsonBody.put("appointRecordIds", array);
		System.out.println(jsonBody.toJSONString());
		String results = PostHKServer(APPOINTMENT_CANCEL_URL,jsonBody);
		String result = PostHKServerArray(APPOINTMENT_CANCEL_URL,array);
		System.out.println(results);
		System.out.println(result);*//*

		*/
/*JSONObject rtJson = JSON.parseObject(result);
		System.out.println("result结果示例: " + rtJson.get("data"));*//*


		*/
/**
		 * 访客签离
		 *//*

*/
/*		jsonBody.put("orderId", "40feb6da-1d58-11ea-a9c3-9785090aa485");
		String postHKServer = PostHKServer(VISITING_OUT_URL,jsonBody);
		System.out.println(postHKServer);*//*




		*/
/**
		 * 下发门禁权限
		 * {
			    "callbackUrl": "http://ip:port/configRcv",
			    "personDatas": [
			        {
			            "indexCodes": [
			                "c35f4a4e-8baa-4e88-b6e3-c4060135e7e6"
			            ],
			            "personDataType": "person"
			        }
			    ],
			    "resourceInfos": [
			        {
			            "resourceIndexCode": "0a869b06f4ff4da29949acf006405c39",
			            "resourceType": "acsDevice",
			            "channelNos": [
			                1
			            ]
			        }
			    ],
			    "startTime": "2018-09-03T17:30:08+08:00",
			    "endTime": "2018-09-06T17:30:08+08:00"
			}
		 *//*



	}

}
*/
