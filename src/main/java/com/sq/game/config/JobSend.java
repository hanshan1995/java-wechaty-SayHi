package com.sq.game.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.sq.external.tianxing.ApiKey;
import com.sq.external.tianxing.ApiGet;
import io.github.wechaty.Wechaty;
import io.github.wechaty.schemas.ContactQueryFilter;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.manager.ContactManager;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-12 16:25
 */
public class JobSend {

    public void sendMsg(Wechaty bot) {
        ContactManager contactManager = bot.getContactManager();
        Contact gf = contactManager.load("wxid_mq9g2u2w3w7822");
/*        ContactQueryFilter cqf = new ContactQueryFilter();
        cqf.setName("琪琪");
        List<Contact> all = contactManager.findAll(cqf);*/
        if (gf != null) {
            gf.say("现在是" + getDate() + ",记得喝水,保持水分  " + getText());
        }
    }

    @NotNull
    private String getText() {
        String request = ApiGet.request(ApiKey.dianyingtaici);
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
                text = "\"" + content + dialogue + "\"" + " - <<" + source + ">>";
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



}
