package com.zx.mapper;

import com.zx.model.HotWord;
import com.zx.model.HotWordChild;
import com.zx.model.HotWordDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotWordMapper {

    int insertHotWord(HotWord record);

    int insertHotWordChild(HotWordChild hotWordChild);

    int insertHotWordDetail(HotWordDetail hotWordDetail);

    List<HotWord> findHotWord();

    List<HotWordChild> findChild(Integer hwid);

    List<HotWordDetail> findDeteil(Integer hwid);

    List<String> findChildList(Integer hwid);

    List<String> findDeteilList(Integer hwid);

    List<String> findAdviceList(Integer hwid);

    List<String> findAllHotWord(Integer hwid);

    int updateByPrimaryKeySelective(HotWord record);

    void insertAdvice(HotWordDetail hotWordDetail);

    void clearHotWord();

    void clearHotWordChild();

    void clearHotWordDetail();
}