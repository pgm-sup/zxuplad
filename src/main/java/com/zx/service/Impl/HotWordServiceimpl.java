package com.zx.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zx.mapper.HotWordMapper;
import com.zx.model.Data;
import com.zx.model.HotWord;
import com.zx.model.HotWordChild;
import com.zx.model.HotWordDetail;
import com.zx.service.HotWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@Service
public class HotWordServiceimpl implements HotWordService {
    @Autowired
    HotWordMapper hotWordMapper;

    @Override
    public List<HotWord> getHotWord() {
        List<HotWord> hotWords = hotWordMapper.findHotWord();
        for (HotWord hotWord : hotWords) {
            hotWord.setChildren(hotWordMapper.findChildList(hotWord.getId()));
            hotWord.setDetail(hotWordMapper.findDeteilList(hotWord.getId()));
            hotWord.setAdvice(hotWordMapper.findAdviceList(hotWord.getId()));
        }
        return hotWords;
    }

    @Override
    public List<HotWord> findHotWordList() {
        return hotWordMapper.findHotWord();
    }

    @Override
    public void insertAdvice(HotWordDetail hotWordDetail) {
        hotWordMapper.insertAdvice(hotWordDetail);
    }

    @Override
    public List<HotWordDetail> findDetailById(Integer id) {
        return hotWordMapper.findDeteil(id);
    }

    @Override
    public void clear() {
        hotWordMapper.clearHotWord();
        hotWordMapper.clearHotWordChild();
        hotWordMapper.clearHotWordDetail();
    }

    @Override
    public void setHotWord() {
        List<Data> datas = JSON.parseObject(getResponseString().toString().trim(), new TypeReference<List<Data>>() {});
        for(Data data:datas){
            if(data.getName().equals("12345")){
                for (HotWord hotWord : data.getChildren()) {
                    hotWordMapper.insertHotWord(hotWord);
                    if (hotWord.getChildren() != null) {
                        for (String child : hotWord.getChildren()) {
                            HotWordChild hotWordChild = new HotWordChild();
                            hotWordChild.setHwid(hotWord.getId());
                            hotWordChild.setChild(child);
                            hotWordMapper.insertHotWordChild(hotWordChild);
                        }
                    }

                    if (hotWord.getDetail() != null) {
                        for (String detail : hotWord.getDetail()) {
                            HotWordDetail hotWordDetail = new HotWordDetail();
                            hotWordDetail.setHwid(hotWord.getId());
                            hotWordDetail.setDetail(detail);
                            hotWordMapper.insertHotWordDetail(hotWordDetail);
                        }
                    }
                }
            }
        }
    }


    private String getResponseString() {
        //设置编码
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        //RestTemplate client = new RestTemplate();  重构
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
        String url = "http://10.242.181.48:7001/pudong/hotwords/hot_word.json";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        return result;
    }


}
