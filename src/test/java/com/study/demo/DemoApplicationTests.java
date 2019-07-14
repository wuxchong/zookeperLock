package com.study.demo;

import com.study.zk.discovery.ZkServiceDiscovery;
import com.study.zk.registry.ZkServiceRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private static final String SERVER_NAME = "waylau.com";
    private static final String SERVER_ADDRESS = "localhost:2181";

    @Test
    public void testClient() throws Exception {

        ZkServiceRegistry registry = new ZkServiceRegistry();
        registry.init();
        registry.registry(SERVER_NAME, SERVER_ADDRESS);

        ZkServiceDiscovery discovery = new ZkServiceDiscovery();
        discovery.init();
        discovery.discover(SERVER_NAME);

        // 永不停止
        while(true) {
        }

    }

}
