package com.itcast.dw.test.thread.threadpool;

import net.minidev.json.JSONUtil;

/**
 * @description
 * @author: liudawei
 * @date: 2020/8/25 9:27
 */
public class PoolTest {

  public static void main(String[] args) {
    DefaultThreadPool defaultThreadPool = new DefaultThreadPool(8);

    for (int i = 0; i < 100; i++) {
      int finalI = i;
      defaultThreadPool.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println("i ========================== " + finalI);
        }

      });
    }

    System.out.println(defaultThreadPool.getJobSize());

  }
}
