package com.sq.sayhi.test;

import com.sq.game.config.JobSend;
import io.github.wechaty.Wechaty;
import io.github.wechaty.WechatyOptions;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;

/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-10 17:49
 */
public class TestLink {

    public static void main(String[] args) {

        Wechaty bot = Wechaty.instance("token");
        bot.onScan((qrcode, statusScanStatus, data) -> System.out.println(QrcodeUtils.getQr(qrcode)));
        //  //new JobSend().sendMsg(bot);
        //            //bot.logout();
        bot.onLogin(user -> System.out.println(user));
        bot.onMessage(message -> {
            Room room = message.room();
            String text = message.text();
            System.out.println(text);
            Contact from = message.from();
            if (from != null && !from.self()) {
                System.out.println("id:" + from.getId());
                System.out.println("alias:" + from.getAlias());
                System.out.println("name:" + from.name());
                System.out.println("avatar:" + from.avatar());
                System.out.println("getWechaty:" + from.getWechaty());
                System.out.println("friend:" + from.friend());
                System.out.println("gender:" + from.gender());
                System.out.println("isReady:" + from.isReady());
                System.out.println("self:" + from.self());
                System.out.println("stranger:" + from.stranger());
                System.out.println("type:" + from.type());
        
                from.say("你好");
            }
        });
        bot.start(true);


    }
}
