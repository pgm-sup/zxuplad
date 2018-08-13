package com.zx.mapper;

import com.zx.model.Knowledge;
import com.zx.model.Ruler;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface OrderMapper {
    List<Map<String, Object>> queryOrderecognition5(@Param("rul") Set<Ruler> rul, @Param("hotWord") String hotWord);

    String findNewId(@Param("streetName")String streetName,
                     @Param("orderType")String orderType);

    void insertphone(String phone);

    Knowledge queryKnowledgeById(int id);

    List<Map<String, Object>> queryOrderecognition4(@Param("rul")Set<Ruler> rulers);

    List<Knowledge> queryKnowledges();
}
