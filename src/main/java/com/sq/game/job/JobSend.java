
package com.sq.game.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.sq.external.tianxing.ApiGet;
import com.sq.external.tianxing.ApiKey;
import io.github.wechaty.Wechaty;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.manager.ContactManager;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-12 16:25
 */

/**
 * Java Timer 实现的任务调度  固定时间发送给特定人
 */
public class JobSend {

    private Wechaty wechaty;

    public JobSend(Wechaty wechaty) {
        this.wechaty = wechaty;
        Timer timer = new Timer();
        timer.schedule(new ITask(),10 * 1000, 60 * 60 * 1000);
    }

    public void sendMsg() {
        Wechaty bot  = this.wechaty;
        ContactManager contactManager = bot.getContactManager();
        Contact gf = contactManager.load("wxid_mq9g2u2w3w7822");

        if (gf != null) {
            gf.say("现在是" + getDate() + ",记得喝水,保持开心,     \n\n" + getText());
        }
    }

    @NotNull
    private String getText() {
        String request = ApiGet.request(ApiKey.wangyiyun);
        Map map = JSONUtil.toBean(request, Map.class);
        Integer code = (Integer)map.get("code");
        String text = "";
        if (code.equals(200)) {
            JSONArray newList = (JSONArray)map.get("newslist");
            List<Map> nl = JSONUtil.toList(newList, Map.class);
            if (CollUtil.isNotEmpty(nl)) {
                Map<String, String> smp = nl.get(0);
                String content = smp.get("content");
                String dialogue = smp.get("dialogue");
                String source = smp.get("source");
                text = "\\" + content + dialogue + "\\" + "\n -      <<" + source + ">>";
                text = text.replaceAll("null","");
                System.out.println(text);
            }
        }
        return text;
    }

    private static String getDate() {
        LocalDateTime now = LocalDateTime.now();
        return  now.getHour()  + "点" +  now.getMinute() + "分";
    }

    class ITask extends TimerTask {
        @Override
        public void run() {
           sendMsg();
        }
    }

}
