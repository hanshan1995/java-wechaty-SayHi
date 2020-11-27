package com.sq.sayhi.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import com.sq.game.config.JobSend;
import com.sq.game.handler.Handler;
import com.sq.game.handler.RoomMessageHandler;
import com.sq.game.handler.SimpleMessageHandler;
import com.sq.util.HttpClientUtils;
import io.github.wechaty.Wechaty;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Future;

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
