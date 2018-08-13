package com.zx.controller;

import com.zx.model.HotWord;
import com.zx.model.HotWordDetail;
import com.zx.service.HotWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/hw")
@Component
public class HotWordController {
    @Autowired
    HotWordService hotWordService;
    private static final String code = "e08035a289384f88a9e621dbaf09f812";


    @Scheduled(cron = "0 0 0 ? * MON")
    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam String key) {
        if (key.equals(code)) {
            try {
                hotWordService.clear();
                hotWordService.setHotWord();
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        } else {
            return null;
        }
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insertAdvice(@RequestBody List<HotWordDetail> hotWordDetails, @RequestParam String key) {
        if (key.equals(code)) {
            try {
                for (HotWordDetail hotWordDetail : hotWordDetails) {
                    hotWordService.insertAdvice(hotWordDetail);
                }
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        } else {
            return null;
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public List<HotWord> geta(@RequestParam String key) {
        if (key.equals(code)) {
            List<HotWord> s = null;
            try {
                s = hotWordService.getHotWord();
                return s;
            } catch (Exception e) {
                e.printStackTrace();
                return s;
            }
        } else {
            return null;
        }
    }


    @RequestMapping("/findHotWordStrList")
    @ResponseBody
    public List<HotWord> findHotWordList(@RequestParam String key) {
        if (key.equals(code)) {
            List<HotWord> s = null;
            try {
                s = hotWordService.findHotWordList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        } else {
            return null;
        }
    }


    @RequestMapping("/findDetailById")
    @ResponseBody
    public List<HotWordDetail> findDetailById(@RequestParam String key, @RequestParam Integer id) {
        if (key.equals(code)) {
            List<HotWordDetail> s = null;
            try {
                s = hotWordService.findDetailById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        } else {
            return null;
        }
    }
}
