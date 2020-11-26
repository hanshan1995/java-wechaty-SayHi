package com.sq.sayhi.test;

import cn.hutool.json.JSONObject;
import com.sq.game.config.JobSend;
import com.sq.util.HttpClientUtils;
import io.github.wechaty.Wechaty;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;

/**
 * @program: sayhi
 * @description:  puppet_donut_d4b6faef9eaa268e   puppet_donut_9f69163efc163338
 *  // wxid_9zn6ga9c6q1m21  => 潮汐的永恒
 *                 // wxid_mq9g2u2w3w7822  => 琪琪
 *                 // wxid_bil9bkbyn47522  => 学文Zhang
 * @author: zxw_
 * @create: 2020-11-10 17:49
 */
public class TestLink {

    public static void main(String[] args) {
        Wechaty bot = Wechaty.instance("puppet_donut_d4b6faef9eaa268e");
        bot.onScan((qrcode, statusScanStatus, data) -> System.out.println(QrcodeUtils.getQr(qrcode)));
        bot.onLogin(user -> /*new JobSend(bot)*/ System.out.println(user));
        bot.onMessage(message -> {
            Room room = message.room();
            String text = message.text();
            Contact from = message.from();
            if (from != null && !from.self()) {
                if (!text.contains(" ")) {
                    System.out.println("id:" + from.getId());
                    //from.say("你好,[" + from.name() + "]:[" + text + "]");
                    if ("背包".equals(text)) {
                        String url = "http://zxw000zxw.uicp.top:31378/sys/backpack/BPContent";
                        JSONObject obj = new JSONObject();
                        obj.accumulate("wxid","wxid_00001");
                        JSONObject jsonObject = HttpClientUtils.httpPost(url, obj);
                        // String result = ApiPost.request(url, jsonParam);
                        System.out.println(jsonObject);
                        if ((Integer)jsonObject.get("code") == 200) {
                            from.say(jsonObject.get("data"));
                        }
                    }
                    if ("称号列表".equals(text)) {
                        String url = "http://zxw000zxw.uicp.top:31378/sys/title/list";
                        JSONObject jsonObject = HttpClientUtils.httpGet(url);
                        // String result = ApiPost.request(url, jsonParam);
                        System.out.println(jsonObject);
                        if ((Integer)jsonObject.get("code") == 200) {
                            from.say(jsonObject.get("data"));
                        }
                    }
                    if ("我".equals(text)) {
                        String url = "http://zxw000zxw.uicp.top:31378/sys/role/info";
                        JSONObject obj = new JSONObject();
                        obj.accumulate("wxid","wxid_00001");
                        JSONObject jsonObject = HttpClientUtils.httpPost(url, obj);
                        System.out.println(jsonObject);
                        if ((Integer)jsonObject.get("code") == 200) {
                            from.say(jsonObject.get("data"));
                        }
                    }
                }
                // 重构方式
                if (text.contains(" ")) {
                    String[] s = text.split(" ");
                    if (s.length > 1) {
                        String command = s[0];
                        String param = s[1];
                        if ("装备".equals(command)) {
                            String url = "http://zxw000zxw.uicp.top:31378/sys/backpack/wear";
                            JSONObject obj = new JSONObject();
                            obj.accumulate("wxid","wxid_00001");
                            obj.append("ids",param);
                            JSONObject jsonObject = HttpClientUtils.httpPost(url, obj);
                            System.out.println(jsonObject);
                            if ((Integer)jsonObject.get("code") == 200) {
                                from.say(jsonObject.get("data"));
                            } else if((Integer)jsonObject.get("code") == 501) {
                                from.say(jsonObject.get("message"));
                            }
                        }
                    }
                }
            }
        });
        bot.start(true);
    }
}
