package com.zx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.model.Knowledge;
import com.zx.model.Ruler;
import com.zx.service.OrderService;
import com.zx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @RequestMapping(value = "/queryOrderecognition", method = RequestMethod.GET)
//    public String queryOrderecognition(int id) {
//        Knowledge knowledge = orderService.queryKnowledgeById(id);
//        List<Map<String, Object>> list;
//        if (knowledge == null) {
//            return "规则不存在！";
//        }
//        list = orderService.queryOrderecognition(knowledge);
//        if (list.isEmpty()) {
//            return "未匹配到数据";
//        }
//        String attr = JSON.toJSONString(list);
//        return attr;
//    }

    @ResponseBody
    @RequestMapping(value = "/queryOrderecognition", method = RequestMethod.POST, consumes = "application/json")
    public String queryOrderecognition(@RequestBody JSONObject object) {
//        System.out.println(object);
        String hotWord = object.getString("hotWord");
        JSONArray createArray = object.getJSONArray("rulers");
        Set<Ruler> rulers = new HashSet<>();
        for(int i=0;i<createArray.size();i++) {
            String col = JSONObject.parseObject(JSONObject.toJSONString(createArray.get(i))).getString("col");
            String val = JSONObject.parseObject(JSONObject.toJSONString(createArray.get(i))).getString("val");
            //创建热门搜索对象
            Ruler ruler = new Ruler(col, val);
            rulers.add(ruler);
        }
//        System.out.println(rulers);
        String attr = orderService.queryOrderecognitions(rulers, hotWord);
        return attr;
    }


    @ResponseBody
    @RequestMapping(value = "/send")
    public String send(@RequestParam String phone,@RequestParam String streetName,
                       @RequestParam String orderType) {
        if (phone == null || "".equals(phone)) {
            return "手机号不能为空";
        }
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
//        String appcode = "你自己的AppCode"; 这个没关系
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + "8351687bdb604d54841ceb0efb72b9dd");
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("mobile", "18930502299");
        querys.put("mobile", phone);
        String orderId = orderService.findNewId(streetName,orderType);
        orderService.insertphone(phone);
        querys.put("param", "code:" + "工单" + orderId);
        querys.put("tpl_id", "TP1806115");
        Map<String, String> bodys = new HashMap<String, String>();

        try {
//            //**
//     * 重要提示如下:
//     * HttpUtils请从
//     * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//     * 下载
//     *
//     * 相应的依赖请参照
//     * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml*

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
