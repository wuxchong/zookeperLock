package com.study.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;


/**
 * 利用ZooKeeper实现分布式锁
 * 主要是利用ZooKeeper类似于文件系统的存储方式，具有排他性
 * 通过监听者watcher机制可以明确知道哪一个节点发生变化
 * ZooKeeper有四种节点：持久性节点、持久性顺序节点、临时性节点、临时性顺序节点
 * 如果使用持久性节点来实现分布式锁，若某一个线性在创建节点后由于某些原因挂了，
 * 就会导致其他线程一直等待，所以应该用临时性节点。
 * 1：临时性节点
 *      尝试创建临时性节点，成功就代表获得锁，失败就阻塞等待
 * 2：临时性顺序节点
 *      类似于银行通过票号排队，每次要保证自己获取锁的时候，自己是当前最小的号
 *
 */
public class zkEphemeralLock {

    private static final String CONNECTION = "127.0.0.1:2181";
    private ZkClient zkClient = new ZkClient(CONNECTION);
    private String lockPath = "/lockPath";

    boolean tryLock(){
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    //获取锁
    public void getLock() {
        //1、连接zkClient 创建一个/lock的临时节点
        // 2、 如果节点创建成果，直接执行业务逻辑，如果节点创建失败，进行等待
        if (tryLock()) {
            System.out.println("#####成功获取锁######");
        }else {
            //进行等待
            waitLock();
        }
        //3、使用事件通知监听该节点是否被删除    ，如果是，重新进入获取锁的资源

    }
    //创建失败 进行等待
    public void waitLock(){
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点删除数据" + s + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点修改数据" + s);
            }
        };

        // 监听事件通知
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);


        //为了不影响程序的执行 建议删除该事件监听 监听完了就删除掉
        zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);
    }

    //释放锁
    public void unLock() {
        //执行完毕 直接连接
        if (zkClient != null) {
            zkClient.close();
            System.out.println("######释放锁完毕######");
        }
    }
}
