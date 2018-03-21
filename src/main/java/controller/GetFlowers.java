package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entity.Flower;
import entity.FlowerGroup;
import entity.Friend;
import util.HttpClientUtil;


public class GetFlowers {

	public ArrayList<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(ArrayList<Flower> flowers) {
		this.flowers = flowers;
	}

	private String url_getflower = "https://weixin.spdbccc.com.cn/wxrp-page-steal/ajaxQueryMyRedPacket";
	private String charset = "utf-8";
	private Map<String, String> httpHeadMap = new HashMap<String, String>();
	private HttpClientUtil httpClientUtil = null;
	private ArrayList<Flower> flowers = new ArrayList<Flower>();
	private ArrayList<Friend> friends = null;

	public void initHttpHead() {
		httpHeadMap.put("Host", "weixin.spdbccc.com.cn");
		httpHeadMap.put("Connection", "keep-alive");
		httpHeadMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
		httpHeadMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpHeadMap.put("Origin", "https://weixin.spdbccc.com.cn");
		httpHeadMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");
		httpHeadMap.put("X-Requested-With", "XMLHttpRequest");
		httpHeadMap.put("Referer", "Referer: https://weixin.spdbccc.com.cn/wxrp-page-steal/myRedPacket");
		httpHeadMap.put("Accept-Encoding", "gzip, deflate");
		httpHeadMap.put("Accept-Language", "zh-CN,zh;q=0.8,en-us;q=0.6,en;q=0.5;q=0.4");
		// cookie init
		try {
			FileInputStream in = new FileInputStream("cookie.txt");
			InputStreamReader inReader;
			inReader = new InputStreamReader(in, "UTF-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = bufReader.readLine();
			httpHeadMap.put("Cookie", line);
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpHeadMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	}

	public GetFlowers(ArrayList<Friend> friends) {
		super();
		// TODO Auto-generated constructor stub
		httpClientUtil = new HttpClientUtil();
		initHttpHead();
		this.friends = friends;
	}

	public void addFlower(String str) {
		//System.out.println(str);
		FlowerGroup flowerGroup = new Gson().fromJson(str, FlowerGroup.class);
		//System.out.println(flowerGroup.getMessage());
		
		if(flowerGroup == null)
			return;

		if (flowerGroup.getMessage().equals("亲，已经没有更多的数据了哦")){
			return;
		}
		
		if(flowerGroup.getMessage().contains("000002")){
			return ;
		}

		JsonParser parser = new JsonParser();
		// 将JSON的String 转成一个JsonArray对象
		JsonArray jsonArray = parser.parse(flowerGroup.getMessage()).getAsJsonArray();

		Gson gson = new Gson();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{

			//for循环遍历JsonArray
			for (JsonElement flowerelm : jsonArray) {
				// 使用GSON，直接转成Bean对象
				Flower flower = gson.fromJson(flowerelm, Flower.class);
				// System.out.println(friend.getEncryptCertNo());
				flower.setPlantDate_convert(format.parse(flower.getPlantDate()));
				flower.setPlantEndDate_convert(format.parse(flower.getPlantEndDate()));
				flower.setPlantEnergyEndDate_convert(format.parse(flower.getPlantEnergyEndDate()));
				
				flowers.add(flower);
			}
		}catch (Exception e) {
			System.out.println("**" + str);
			System.out.println("**" + flowerGroup.getMessage());
			e.printStackTrace();
		}

	}

	public void run() {
		String content = "";
		// 自己的花
		// cookie openid
		String openid = "";
		try {
			FileInputStream in = new FileInputStream("openid.txt");
			InputStreamReader inReader;
			inReader = new InputStreamReader(in, "UTF-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			openid = bufReader.readLine();
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		String myself = "plantOpenId=" + openid + "&plantCertNo=&type=";
		String postResult = httpClientUtil.doPost(url_getflower, httpHeadMap, charset, myself);
		//System.out.println("myself:" + postResult);
		addFlower(postResult);

		// 好友的花
		for (int i = 0; i < friends.size(); i++) {
			// plantOpenId=oggN1jlKQFvGiFJC0ZikPpY6r8D4&plantCertNo=BpLnQMcURD9AXckjmSXOX0is9DGCb0eN&type=1
			content = "";
			String postResutFriend = "";
			try {
				
				content += "plantOpenId=";
				content += friends.get(i).getFriendsOpenId();
				content += "&plantCertNo=";
				content += friends.get(i).getEncryptCertNo();
				content += "&type=1";
				postResutFriend = httpClientUtil.doPost(url_getflower, httpHeadMap, charset, content);

				if (postResutFriend.contains("{\"message\":\"亲，已经没有更多的数据了哦\",\"success\":true,\"flag\":false}")){
					continue;
				}
				// System.out.println(postResutFriend);
				addFlower(postResutFriend);
				//Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(content);
				System.out.println(postResutFriend);
				//System.out.println(friends.get(i).o);
				//e.printStackTrace();
			}
		}
	}

}
