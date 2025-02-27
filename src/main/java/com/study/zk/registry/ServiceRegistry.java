package com.study.zk.registry;

public interface ServiceRegistry {

    /**
     * 注册服务.
     *
     * @param serviceName
     * @param serviceAddress
     */
    void registry(String serviceName, String serviceAddress);
}
