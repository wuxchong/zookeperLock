package com.study.zk.discovery;

public interface ServiceDiscovery {
    /**
     * 服务发现.
     *
     * @param name
     * @return
     */
    String discover(String name);
}
