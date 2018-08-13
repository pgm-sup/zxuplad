package com.zx.service;


import com.zx.model.Knowledge;
import com.zx.model.Ruler;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {

    List<Map<String,Object>> queryOrderecognition(Knowledge knowledge);

    String findNewId(String streetName,String orderType);

    void insertphone(String phone);

    Knowledge queryKnowledgeById(int id);

    String queryOrderecognitions(Set<Ruler> rulers, String hotWord);
}
