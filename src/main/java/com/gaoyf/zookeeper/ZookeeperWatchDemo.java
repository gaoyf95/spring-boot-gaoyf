package com.gaoyf.zookeeper;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;


/**
 * Created by 高宇飞 on 2019/10/16 13:20:16
 */
public class ZookeeperWatchDemo {
    private ZooKeeper zookeeper;
    private String oldValue = "";

    public ZooKeeper zkConnect() throws IOException {
        String path = "127.0.0.1:2181";
        zookeeper = new ZooKeeper(path, 20 * 1000, null);
        return zookeeper;
    }

    public void createZnode(String path, byte[] value, Watcher watcher, CreateMode node) throws KeeperException, InterruptedException {
        zookeeper.create(path, value, ZooDefs.Ids.OPEN_ACL_UNSAFE, node);
    }

    public String getZnodeValue(final String path) throws KeeperException, InterruptedException {
        byte[] data = zookeeper.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                triggerWatch(path);
            }
        }, new Stat());
        oldValue = new String(data);
        return new String(data);
    }

    public boolean triggerWatch(String path) {
        byte[] data = new byte[0];
        try {
            data = zookeeper.getData(path, watchedEvent -> triggerWatch(path), new Stat());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String newValue = new String(data);
        if (oldValue.equals(newValue)) {
            System.out.println("on change");
            return false;
        } else {
            System.out.println("oldvalue: " + oldValue + "new value: " + newValue);
            oldValue = newValue;
            return true;
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建
        ZookeeperWatchDemo zookeeperWatchDemo = new ZookeeperWatchDemo();
        ZooKeeper zooKeeper = zookeeperWatchDemo.zkConnect();
        String path = "/username";
        String value = "hahahahaha";
        if (zooKeeper.exists(path, false) == null) {
            zookeeperWatchDemo.createZnode(path, value.getBytes(), null, CreateMode.PERSISTENT);
        }

        String znodeValue = zookeeperWatchDemo.getZnodeValue(path);
        System.out.println(znodeValue);

        Thread.sleep(1000 * 60 * 50);
    }
}