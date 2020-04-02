/**
 * 
 * @date 2019年9月17日
 */
package com.itcast.dw.test.model;

/**
 * 链表
 * @author liudawei
 */

public class RandomListNode {
	public int label;
	public RandomListNode next = null;
	public RandomListNode random = null;
	public RandomListNode node;
	
	public RandomListNode(int lable){
		this.label = lable;
	}
	
	public void add(int val){
		node = this;
		while(node.next != null){
			node = node.next;
		}
		node.next = new RandomListNode(val);
	}
	
	public void printNodes(RandomListNode node){
		while(node != null){
			int random = node.random == null ? 0 :node.random.label;
			System.out.println(node.label + " " + random);
			node = node.next;
		}
	}


}
