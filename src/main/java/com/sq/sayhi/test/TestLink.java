package com.sq.sayhi.test;

import io.github.wechaty.Wechaty;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-10 17:49
 */
public class TestLink {

    public static void main(String[] args) {
        Wechaty bot = Wechaty.instance("puppet_donut_9f69163efc163338")
                .onScan((qrcode, statusScanStatus, data) -> System.out.println(QrcodeUtils.getQr(qrcode)))
                .onLogin(user -> System.out.println(user))
                .onMessage(message -> {
                    Room room = message.room();
                    String text = message.text();
                    Contact from = message.from();
                    System.out.println(from);
                    if (StringUtils.equals(text, "#ding")) {
                        if (room != null) {
                            room.say("dong");
                        }
                    }
                }).start(true);
    }
}
