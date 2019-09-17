/**
 * 
 * @date 2019年8月22日
 */
package com.itcast.dw.test.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一句话功能简述
 * @author liudawei
 */

public class TreeNode {

	 public int val = 0;
	 public TreeNode left = null;
	 public TreeNode right = null;
	 public TreeNode(){}
	 public TreeNode(int val) { this.val = val; }
	 
	 /**
	  *      A
	  *     / \
	  *    B   C
	  *   / \   /\
	  *  D  E  F  G
	  * /\  /
	  * H I J
	  */
	 
	 
	 /**
	  * 
	  * 前序遍历
	  * 从二叉树得根结点出发、当第一次到达结点时就输出结点数据、按照先左在右得方向访问
	  * eg:ABDHIEJCFG
	  * @param node 
	  * @date 2019年9月5日
	  * @author liudawei
	  */
	 public static void qxSort(TreeNode node){
		 if(node == null) return;
		 System.out.println(node.val);
		 qxSort(node.left);
		 qxSort(node.right);
	 }
	 /**
	  * 
	  * 中序遍历
	  * 从二叉树根结点出发，当第二次到达结点时就输出结点数据、按照先向左在向右得方向访问
	  * eg:HDIBJEAFCG
	  * @param node 
	  * @date 2019年9月5日
	  * @author liudawei
	  */
	 public static void zxSort(TreeNode node){
		 if(node == null) return;
		 qxSort(node.left);
		 System.out.println(node.val);
		 qxSort(node.right);
	 }
	 /**
	  * 
	  * 后序遍历
	  * 从二叉树根结点出发，当第三次到达结点时就输出结点数据、按照先向左在向右得方向访问
	  * eg:HIDJEBFGCA
	  * @param node 
	  * @date 2019年9月5日
	  * @author liudawei
	  */
	 public static void hxSort(TreeNode node){
		 if(node == null) return;
		 qxSort(node.left);
		 qxSort(node.right);
		 System.out.println(node.val);
	 }
	 /**
	  * 
	  * 层序遍历
	  * eg: ABCDEFGHIJ
	  * @param node 
	  * @date 2019年9月5日
	  * @author liudawei
	  */
	 public static void cxSort(TreeNode node){
		 if(node == null){
			 return;
		 }
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		
		q.add(node);
		StringBuffer s = new StringBuffer("");
		
		while(!q.isEmpty()){
			TreeNode poll = q.poll();
			if(poll.left != null){
				q.add(poll.left);
			}
			if(poll.right != null){
				q.add(poll.right);
			}
			s.append(poll.val);
		}
		 
		System.out.println(s);
	 }
	 
	 /**
	  * 
	  * 层序添加元素
	  * @param node
	  * @param val 
	  * @date 2019年9月10日
	  * @author liudawei
	  */
	public static TreeNode addVal(TreeNode node, int val) {
		if (node == null) {
			return new TreeNode(val);
		} else {
			Queue<TreeNode> q = new LinkedList<TreeNode>();

			q.add(node);

			while (!q.isEmpty()) {
				TreeNode poll = q.poll();
				if (poll.left == null) {
					poll.left = new TreeNode(val);
					return node;
				}else{
					q.add(poll.left);
				}
				if (poll.right == null) {
					poll.right = new TreeNode(val);
					return node;
				}else{
					q.add(poll.right);
				}
			}
			return node;
		}

	}
	 
	 
}
