import controller.GetFlowers;
import controller.GetFriends;
import entity.Flower;
import entity.FlowerPost;
import entity.Friend;
import util.ComparatorDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class Main {

    private static ArrayList<Friend> friends = null;

    public Main() {
    }

    public void run() {
        GetFriends getFriends = new GetFriends();
        getFriends.run();
        friends = getFriends.getFriends();

        GetFlowers getFlowers = new GetFlowers(friends);
        getFlowers.run();

        ArrayList<Flower> flowers = getFlowers.getFlowers();

        String result = "";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nickName = "";
        Date plantEndDate = new Date();
        ArrayList<FlowerPost> flowerPosts = new ArrayList<FlowerPost>();

        for (Flower flower : flowers) {
            FlowerPost flowerPost = new FlowerPost();
            result = "";
            nickName = "";
            try {
                // 拿到nickName
                for (Friend friend : friends) {
                    if (flower.getPlantOpenId().equals(friend.getFriendsOpenId())) {
                        nickName = friend.getFriendsNickName();
                        break;
                    }
                }

                //防止有些人用“｜”做用户名干扰
                nickName = nickName.replace('|', '-');

                plantEndDate = flower.getPlantEndDate_convert();//

				/*if (flower.getEnergy().contains("Y")
						&& !flower.getPlantOpenId().equals("oggN1jiZF8AthajXkotabFE6lbmk")) {
					plantEndDate.setTime(plantEndDate.getTime() + 5 * 60 * 1000);
					nickName = "Y-" + nickName;
				} else {
					nickName = "N-" + nickName;
				}*/


                if (flower.getEnergy().contains("Y")){
                    if(flower.getPlantOpenId().equals("oggN1jiZF8AthajXkotabFE6lbmk")){
                        //myself自己的花有保护 ＋ 2S
                        plantEndDate.setTime(plantEndDate.getTime() + 2000);
                    }
                    else {//其它人 ＋ 5分钟
                        plantEndDate.setTime(plantEndDate.getTime() + 5 * 60 * 1000);
                    }

                    nickName = "Y-" + nickName;
                }

                else {
                    nickName = "N-" + nickName;
                }

                flowerPost.setPlantOpenId(URLEncoder.encode(flower.getPlantOpenId(), "utf-8"));
                flowerPost.setPlantEndDate(plantEndDate);
                flowerPost.setNickName(nickName);

                result = result + "plantCertNo=" + URLEncoder.encode(flower.getPlantCertNo(), "utf-8") + "&plantedId=";
                result = result + URLEncoder.encode(flower.getPlantedId(), "utf-8") + "&plantOpenId=";
                result = result + URLEncoder.encode(flower.getPlantOpenId(), "utf-8") + "&plantDate=";
                Date plantDate = flower.getPlantDate_convert();//
                String plantDateStr = format.format(plantDate);
                result = result + URLEncoder.encode(plantDateStr, "utf-8") + "&plantmDate=";
                result = result + URLEncoder.encode(flower.getPlantmDate(), "utf-8");
                result = result + "&isSteal=false&plantIndex=" + flower.getPlantIndex();
                flowerPost.setParam(result);
                flowerPosts.add(flowerPost);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int oldcount = 0; //读取原来的文件
        try {
            FileInputStream in = new FileInputStream("flowers_to_post.txt");
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            String[] strings = new String[4];
            while ((line = bufReader.readLine()) != null) {
                strings = line.split("\\|");
                FlowerPost flowerPostOld = new FlowerPost();
                flowerPostOld.setPlantOpenId(strings[0]);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date endDate = formatter.parse(strings[1]);
                flowerPostOld.setPlantEndDate(endDate);
                flowerPostOld.setNickName(strings[2]);
                flowerPostOld.setParam(strings[3]);

                if(endDate.getTime() < System.currentTimeMillis())
                    continue;

                if(!findFlowFromOld(flowerPostOld,flowerPosts)) //如果原来的文件，在新结果中没找到，加入
                {
                    flowerPosts.add(flowerPostOld);
                    oldcount ++;
                }

            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取Old flower_to_Post出错！");
        }
        System.out.println("old file has " + oldcount + " flowers has founded");


        ComparatorDate c = new ComparatorDate();
        Collections.sort(flowerPosts, c);

        File file = new File("flowers_to_post.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        FileWriter writer;
        try {
            writer = new FileWriter(file, false);
            for (FlowerPost flowerPost : flowerPosts) {
                String outPutStr = "";
                String plantEndDateStr = format.format(flowerPost.getPlantEndDate());
                outPutStr = flowerPost.getPlantOpenId() + "|";
                outPutStr = outPutStr + plantEndDateStr + "|" + flowerPost.getNickName() + "|" + flowerPost.getParam()
                        + "\n";
                writer.write(outPutStr);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public boolean findFlowFromOld(FlowerPost flowerPostOld,ArrayList<FlowerPost> flowerPosts){
        for (FlowerPost flowerPost : flowerPosts) {
            if( flowerPost.getParam().equals(flowerPostOld.getParam()) )
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Date now = new Date();
        now.setTime(System.currentTimeMillis());
        System.out.println(now + "--get flowers started!!");
        main.run();
        now.setTime(System.currentTimeMillis());
        System.out.println(now + "--get flowers ended!!");

        int hour= 2;  //读取命令行，1，2 ，分开小时获取花朵情况
        if(args.length == 1)
            hour = Integer.parseInt(args[0]);




        while (true) {
            try {

                now.setTime(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                if ((calendar.get(Calendar.HOUR_OF_DAY) % hour) == 0 && calendar.get(Calendar.MINUTE) == 0
                        && calendar.get(Calendar.SECOND) == 0) {
                    System.out.println(now + "--get flowers started!!");
                    main.run();
                    now.setTime(System.currentTimeMillis());
                    System.out.println(now + "--get flowers ended!!");
                    Thread.sleep(1010);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
