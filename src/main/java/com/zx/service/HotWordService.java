package com.zx.service;

import com.zx.model.HotWord;
import com.zx.model.HotWordDetail;

import java.util.List;

public interface HotWordService {
    void setHotWord();

    List<HotWord> getHotWord();

    List<HotWord> findHotWordList();

    void insertAdvice(HotWordDetail hotWordDetail);

    List<HotWordDetail> findDetailById(Integer id);

    void clear();
}
