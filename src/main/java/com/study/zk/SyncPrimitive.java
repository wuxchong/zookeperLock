package com.study.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

//ZooKeeper 实现 barrier 和  producer-consumer queue
public class SyncPrimitive implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
