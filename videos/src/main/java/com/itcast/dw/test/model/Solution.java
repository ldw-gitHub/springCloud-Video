/**
 * 
 * @date 2019年9月4日
 */
package com.itcast.dw.test.model;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 
 * @author liudawei
 */

public class Solution {

	Stack<Integer> dateStack = new Stack<Integer>();
	Stack<Integer> minStack = new Stack<Integer>();

	public void push(int node) {
		dateStack.push(node);
		if (minStack.isEmpty()) {
			minStack.push(node);
		} else if (dateStack.peek() < minStack.peek()) {
			minStack.push(dateStack.peek());
		} else {
			minStack.push(minStack.peek());
		}
	}

	public void pop() {
		dateStack.pop();
		minStack.pop();
	}

	public int top() {
		return dateStack.peek();
	}

	public int min() {
		return minStack.peek();
	}

}
