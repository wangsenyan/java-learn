package com.wsy.netty;

import io.netty.util.NettyRuntime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaNettyLearnApplicationTests {

	@Test
	void contextLoads() {
		//电脑几核
		System.out.println(NettyRuntime.availableProcessors());
	}

}
