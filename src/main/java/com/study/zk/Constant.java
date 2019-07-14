package com.study.zk;

public interface Constant {

	/**会话超时时间*/
    int ZK_SESSION_TIMEOUT = 5000;

    /**连接超时时间*/
    int ZK_CONNECTION_TIMEOUT = 1000;

    String ZK_REGISTRY = "/registry";
}
