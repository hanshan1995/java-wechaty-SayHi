package com.sq.sayhi;

import cn.hutool.core.collection.CollUtil;
import com.sq.game.handler.Handler;
import com.sq.game.handler.RoomMessageHandler;
import com.sq.game.handler.SimpleMessageHandler;
import io.github.wechaty.Wechaty;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;

/**
 * @program: sayhi
 * @description:  启动
 * @author: zxw_
 * @create: 2020-11-10 17:49
 */
public class BotStart {

    public static void main(String[] args) {
        Wechaty bot = Wechaty.instance("puppet_donut_d4b6faef9eaa268e");
        bot.onScan((qrcode, statusScanStatus, data) -> System.out.println(QrcodeUtils.getQr(qrcode)));
        bot.onLogin(user -> /*new JobSend(bot)*/ System.out.println(user));
        bot.onMessage(message -> {
            Room room = message.room();
            Handler handler;
            if (room != null) {
                handler = new RoomMessageHandler();
            } else {
                handler = new SimpleMessageHandler();
            }
            String resultStr = handler.run(message);
            if (resultStr != null) {
                if (room != null) {
                    room.say(resultStr, CollUtil.newArrayList(message.from()));
                } else {
                    Contact from = message.from();
                    from.say(resultStr);
                }
            }
        });
        bot.start(true);
    }
}
