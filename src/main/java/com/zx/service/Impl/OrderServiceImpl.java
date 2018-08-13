package com.zx.service.Impl;

import com.zx.mapper.OrderMapper;
import com.zx.model.Knowledge;
import com.zx.model.Ruler;
import com.zx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Map<String, Object>> queryOrderecognition(Knowledge knowledge) {
        String word = knowledge.getHotWord();

        String hotWord = "%" + knowledge.getHotWord() + "%";
        Set<Ruler> rulers = knowledge.getRulers();
//        List<Map<String,String>> list = new ArrayList<>();
        List<Map<String, Object>> list;
        if(word == null || "".equals(word)){
            list = orderMapper.queryOrderecognition4(rulers);
        }else{
            list = orderMapper.queryOrderecognition5(rulers, hotWord);
        }
        return list;
    }

    @Override
    public String findNewId(String streetName,String orderType) {
        if(orderType!=null&&!"".equals(orderType)){
            orderType = "%"+orderType+"%";
        }else{
            orderType = null;
        }
        if(streetName!=null&&!"".equals(streetName)){
        }else{
            streetName = null;
        }
        return orderMapper.findNewId(streetName,orderType);
    }

    @Override
    public void insertphone(String phone) {
        orderMapper.insertphone(phone);
    }

    @Override
    public Knowledge queryKnowledgeById(int id) {
        return orderMapper.queryKnowledgeById(id);
    }

    @Override
    public String queryOrderecognitions(Set<Ruler> rulers, String hotWord) {
        String attr;
        List<Map<String, Object>> list;
        List<Knowledge> knowledges = orderMapper.queryKnowledges();
        for (Knowledge knowledge : knowledges) {
            System.out.println(knowledge.getRulers());
            Set<Ruler>  rulerSet = knowledge.getRulers();
            System.out.println(isSetEqual(rulerSet, rulers));
            if(isSetEqual(rulerSet, rulers) && hotWord.equals(knowledge.getHotWord())){
                attr = knowledge.getDepartment();
                System.out.println(attr);
                return attr;
            }
        }
        if(hotWord == null || "".equals(hotWord)){
            list = orderMapper.queryOrderecognition4(rulers);
        }else{
            list = orderMapper.queryOrderecognition5(rulers, "%" + hotWord + "%");
        }
        System.out.println(list);
        return list.toString();
    }

    /**
     * 判断两个Set是否相等
     * @param set1
     * @param set2
     * @return
     */

    public static boolean isSetEqual(Set set1, Set set2) {

        if (set1 == null && set2 == null) {
            return true; // Both are null
        }

        if (set1 == null || set2 == null || set1.size() != set2.size()
                || set1.size() == 0 || set2.size() == 0) {
            return false;
        }

        Iterator ite1 = set1.iterator();
        Iterator ite2 = set2.iterator();

        boolean isFullEqual = true;

        while (ite2.hasNext()) {
            if (!set1.contains(ite2.next())) {
                isFullEqual = false;
            }
        }

        return isFullEqual;
    }
}
