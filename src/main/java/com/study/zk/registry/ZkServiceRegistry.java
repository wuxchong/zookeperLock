package com.study.zk.registry;

import com.study.zk.Constant;
import org.I0Itec.zkclient.ZkClient;

public class ZkServiceRegistry implements ServiceRegistry{
    /**
     * ZK 地址.
     */
    private String zkAddress  = "localhost";

    /**
     * ZK 客户端.
     */
    private ZkClient zkClient;

    public void init() {
        zkClient = new ZkClient(zkAddress,
                Constant.ZK_SESSION_TIMEOUT,
                Constant.ZK_CONNECTION_TIMEOUT);

        System.out.println(">>>connect to zookeeper");
    }

    @Override
    public void registry(String serviceName, String serviceAddress) {
        // 创建registry节点（持久）
        String registryPath = Constant.ZK_REGISTRY;
        if (!zkClient.exists(registryPath)) {
            zkClient.createPersistent(registryPath);

            System.out.println(">>>create registry  node:" + registryPath);
        }

        // 创建service节点（持久）
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            System.out.println(">>>create service node:" + servicePath);
        }

        // 创建address节点（临时）
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);

        System.out.println(">>>create address node:" + addressNode);
    }
}
