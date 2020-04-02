/**
 * 
 * @date 2019年8月22日
 */
package com.itcast.dw.test.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树（Binary Search Tree），也称有序二叉树（ordered binary tree）,排序二叉树（sorted binary tree），是指一棵空树或者具有下列性质的二叉树：
		1. 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
		2. 若任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
		3. 任意节点的左、右子树也分别为二叉查找树。
		4. 没有键值相等的节点（no duplicate nodes）。
 * @author liudawei
 */

public class TreeNode {

	 public int val = 0;
	 public TreeNode left = null;
	 public TreeNode right = null;
	 
	 public TreeNode node = null;
	 public TreeNode(){}
	 public TreeNode(int val) { this.val = val; node = this; }
	 
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
		 zxSort(node.left);
		 System.out.println(node.val);
		 zxSort(node.right);
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
	 public void hxSort(TreeNode node){
		 if(node == null) return;
		 hxSort(node.left);
		 hxSort(node.right);
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
	 public void cxSort(TreeNode node){
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
	public TreeNode addVal(int val) {
		//this.node = this;
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
	
/*	public static TreeNode addVal(TreeNode node, int val) {
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
*/
	
	/**
	 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
	 * 从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
	 * 序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
	 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
	 */
	
	/**
	 * 
	 * 序列化
	 * @param root
	 * @return 
	 * @date 2019年9月24日
	 * @author liudawei
	 */
	public String Serialize(TreeNode root) {
		StringBuffer sb = new StringBuffer();
		if(root == null){
			sb.append("#,");
			return sb.toString();
		}
		sb.append(root.val + ",");
		sb.append(Serialize(root.left));
		sb.append(Serialize(root.right));
		
		return sb.toString();
	}

	/**
	 * 
	 * 反序列化
	 * @param str
	 * @return 
	 * @date 2019年9月24日
	 * @author liudawei
	 */
	private int index = -1;
	public TreeNode Deserialize(String str) {
		index++;
		TreeNode head = null;
		if(str == null || str.length() == 0){
			return head;
		}
		
		String[] nodeValue = str.split(",");
		TreeNode nodes = null;
		if(!"#".equals(nodeValue[index])){
			nodes = new TreeNode(Integer.parseInt(nodeValue[index]));
			nodes.left = Deserialize(str);
			nodes.right = Deserialize(str);
		}
		
		return nodes;
	}
	    
}
