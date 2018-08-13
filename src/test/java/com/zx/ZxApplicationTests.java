package com.zx;

import com.zx.mapper.OrderMapper;
import com.zx.model.Knowledge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZxApplicationTests {

	@Autowired
	private OrderMapper orderMapper;
	@Test
	public void contextLoads() {
		List<Knowledge> knowledges = orderMapper.queryKnowledges();
		System.out.println(knowledges);

	}

}
