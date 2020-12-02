package com.sq.game.handler;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.sq.game.config.CommonResult;
import com.sq.game.config.ResultState;
import com.sq.game.handler.urls.CommandEnum;
import com.sq.game.handler.urls.CommandRoute;
import com.sq.util.HttpClientUtils;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Message;

import java.util.List;

/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-13 15:47
 */
public class RoomMessageHandler implements Handler {

    // 消息处理
    @Override
    public String run(Message message) {
        String text = message.text();
        if (StrUtil.isBlank(text)) {
            return null;
        }
        String[] arr = {text};
        if (text.contains(" ")) {
            arr = text.split(" ");
        }
        String resultString = getResultString(arr, message);
        return resultString;
    }

    private String getResultString(String[] arr,Message message) {
        String resultStr = "";
        int index = 0;
        String matchValue = arr[index];
        while (matchValue.contains("@")) {
            matchValue = arr[index++];
        }
        CommandEnum node = CommandRoute.getByMatchValue(matchValue);
        // 没有匹配到指令
        if (node == null) {
            return null;
        }
        Integer type = node.getType();
        if ( type.equals(1) ) {
            // 拼装参数
            JSONObject paramObj = getParamObj(arr,message.from().getId(),message.mentionList(),node.isParam(),index);
            // 发送请求
            resultStr = getHttpResult(HttpClientUtils.httpPost(node.getUrl(), paramObj));
        } else if (type.equals(2)){
            resultStr = getHttpResult(HttpClientUtils.httpGet(node.getUrl()));
        }

        return resultStr;
    }

    private String getHttpResult(JSONObject resultObject) {
        String resultStr = "";
        CommonResult commonResult = resultObject.toBean(CommonResult.class);
        if (ResultState.success.equals(commonResult.getCode())) {
            resultStr = commonResult.getData();
        } else if (ResultState.busError.equals(commonResult.getCode())) {
            resultStr = commonResult.getMessage();
        }
        return resultStr;
    }

    private JSONObject getParamObj(String[] arr, String wxid, List<Contact> contacts,boolean isParam,int index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("wxid",wxid);
        if (CollUtil.isNotEmpty(contacts)) {
            Contact contact = contacts.get(0);
            jsonObject.accumulate("atid",contact.getId());
        }
        if (isParam) {
            String name = arr[index++];
            jsonObject.accumulate("name",name);
        }
        return jsonObject;
    }

}
