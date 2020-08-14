package com.itcast.dw.test.exam;

/**
 * @description
 * @author: liudawei
 * @date: 2020/4/7 9:59
 */
public class DoSomeThingTest {

  public static void main(String[] args) {
    Integer a = 3;
    Integer b = 3;
    Integer c = new Integer(3);
    int d = 3;
    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a == d);
  }
}
