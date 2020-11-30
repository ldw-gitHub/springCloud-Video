package com.itcasts;

import org.springframework.util.unit.DataSize;

/**
 * @description
 * @author: liudawei
 * @date: 2020/8/17 16:31
 */
public class SpringTest {

  public static void main(String[] args) {
    System.out.println(DataSize.parse("2048MB"));
  }
}
