package com.sq.game.config;

import com.sq.external.tianxing.ApiKey;
import com.sq.external.tianxing.WangYiYunApi;
import io.github.wechaty.Wechaty;
import io.github.wechaty.schemas.ContactQueryFilter;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.manager.ContactManager;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

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
        //cqf.setId("wxid_mq9g2u2w3w7822");
        if (gf != null) {
            String request = WangYiYunApi.request(ApiKey.wangyiyun);
            System.out.println(request);
            // gf.say("现在是" + getDate() + ",记得喝水,保持水分");
        }
    }

    private static String getDate() {
        LocalDateTime now = LocalDateTime.now();
        return  now.getHour()  + "点" +  now.getMinute() + "分";
    }



}
