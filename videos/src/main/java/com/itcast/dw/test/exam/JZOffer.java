/**
 * 
 * @date 2019年8月21日
 */
package com.itcast.dw.test.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import com.itcast.dw.test.model.ListNode;
import com.itcast.dw.test.model.TreeNode;

/**
 * 剑指office1-4
 * 
 * @author liudawei
 */

public class JZOffer {

	public static void main(String[] args) {
		/*
		 * int target = 15; //int[][] arr =
		 * {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}}; int[][] arr = {{}};
		 * boolean find = Find(target,arr); System.out.println(find);
		 */

		/*
		 * StringBuffer s = new StringBuffer("We Are Happy");
		 * System.out.println(replaceSpace(s));
		 */

		/*
		 * ListNode l = new ListNode(1); l.next = new ListNode(2); l = l.next;
		 * l.next = new ListNode(3); l = l.next; l.next = new ListNode(4);
		 * ArrayList<Integer> printListFromTailToHead =
		 * printListFromTailToHead(l);
		 * printListFromTailToHead.stream().forEach(e -> System.out.println(e));
		 */

		/*
		 * int [] pre = {1,2,4,7,3,5,6,8}; int [] in = {4,7,2,1,5,3,8,6};
		 * reConstructBinaryTree(pre,in);
		 */

		/*
		 * int[] a = {1,2,3,4}; int[] b = {5,6,7,8}; int[] c = {9,10,11,12};
		 * int[] d = {13,14,15,16}; int[][] e ={a,b,c,d};
		 */

		/*
		 * int[][] e = {{1},{2},{3},{4},{5}};
		 * 
		 * ArrayList<Integer> printMatrix = printMatrix(e);
		 * printMatrix.stream().forEach(f -> System.out.println(f));
		 */

		/*
		 * Stack<Integer> dateStack = new Stack<Integer>(); dateStack.push(1);
		 * dateStack.push(2); dateStack.push(3);
		 * System.out.println(dateStack.peek());
		 * System.out.println(dateStack.size());
		 * System.out.println(dateStack.pop());
		 * System.out.println(dateStack.size());
		 */

		// PrintFromTopToBottom(new TreeNode());
		TreeNode t = new TreeNode(1);
		
		TreeNode.addVal(t, 2);
		TreeNode.addVal(t, 2);
		TreeNode.addVal(t, 3);
		TreeNode.addVal(t, 5);
		TreeNode.addVal(t, 5);
		TreeNode.addVal(t, 3);
		/*for(int i = 2 ;i < 8;i++){
		TreeNode.addVal(t, i);
		}*/
		
		TreeNode.cxSort(t);
		//TreeNode.qxSort(t);
		ArrayList<ArrayList<Integer>> findPath = new JZOffer().FindPath(t,6);
		findPath.stream().forEach(e -> {
			e.stream().forEach(f -> {
				System.out.println(f);
			});
			System.out.println("===============");
		});
	}
	
	/**
	 * 
	 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
	 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
	 * (注意: 在返回值的list中，数组长度大的数组靠前)
	 * @param root
	 * @param target
	 * @return 
	 * @date 2019年9月10日
	 * @author liudawei
	 */
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		ArrayList<ArrayList<Integer>> rt = new ArrayList<ArrayList<Integer>>();
		if(root == null) return rt;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        
        findResultPath(root,rt,temp,target);
		
		return rt;
	}
	
	private void findResultPath(TreeNode node,ArrayList<ArrayList<Integer>> list,ArrayList<Integer> temp,
			int target){
		temp.add(node.val);
		target -= node.val;
		
		if(node.left == null && node.right == null){
			if(target == 0){
				list.add(new ArrayList<Integer>(temp));
			}
			temp.remove(temp.size() - 1);
			return;
		}
		
		if(node.left != null){
			findResultPath(node.left,list,temp,target);
		}
		if(node.right != null){
			findResultPath(node.right,list,temp,target);
		}
		
		temp.remove(temp.size() - 1);
		
	}
	

	/**
	 * 
	 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
	 * 
	 * 所有左子树值小于根结点、右子树值大于根结点
	 * @param sequence
	 * @return
	 * @date 2019年9月5日
	 * @author liudawei
	 */
	public static boolean VerifySquenceOfBST(int[] sequence) {
		if(sequence.length == 0){
			return false;
		}
		
		return judgeSquenceOfBST(sequence,0,sequence.length - 1);
	}
	
	public static boolean judgeSquenceOfBST(int[] a,int start,int last){
		if(start >= last){
			return true;
		}
		
		//后续遍历、最后元素为根结点
		int i = last;
		//除去最后元素，找到左子树和右子树、左子树小于根结点、右子树大于根结点
		while(i > start && a[i - 1] > a[last]){
			i--;
		}
		
		for(int j=i-1;j>=start;--j){
			if(a[j] > a[last]){
				return false;
			}
		}
		
		return judgeSquenceOfBST(a,start,i-1)&&judgeSquenceOfBST(a,i,last-1);
	}

	/**
	 * 
	 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
	 * 
	 * @param root
	 * @return
	 * @date 2019年9月4日
	 * @author liudawei
	 */
	public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> rt = new ArrayList<Integer>();
		ArrayList<TreeNode> treeList = new ArrayList<TreeNode>();
		if (root == null) {
			return rt;
		}
		treeList.add(root);

		for (int i = 0; i < treeList.size(); i++) {
			TreeNode treeNode = treeList.get(i);

			if (treeNode.left != null) {
				treeList.add(treeNode.left);
			}
			if (treeNode.right != null) {
				treeList.add(treeNode.right);
			}
		}

		treeList.stream().forEach(e -> rt.add(e.val));

		return rt;
	}

	/**
	 * 
	 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
	 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
	 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
	 * 
	 * @param pushA
	 * @param popA
	 * @return
	 * @date 2019年9月4日
	 * @author liudawei
	 */
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		/*
		 * Stack<Integer> assistant = new Stack<Integer>(); int index =
		 * 0;//弹出序列位置 for(int i = 0;i < pushA.length;i++){
		 * assistant.push(pushA[i]);
		 * 
		 * while(!assistant.isEmpty() && popA[index] == assistant.peek()){
		 * assistant.pop(); index++; } }
		 * 
		 * return assistant.isEmpty();
		 */
		ArrayList<Integer> a = new ArrayList<Integer>();
		int index = 0;
		for (int i = 0; i < pushA.length; i++) {
			a.add(pushA[i]);
			while (!a.isEmpty() && popA[index] == a.get(a.size() - 1)) {
				a.remove(a.size() - 1);
				index++;
			}
		}
		return a.isEmpty();
	}

	// TODO 未完成

	/**
	 * 
	 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如， 如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11
	 * 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
	 * 
	 * @param matrix
	 * @return
	 * @date 2019年9月3日
	 * @author liudawei
	 */
	public static ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> rt = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0)
			return rt;

		int m = matrix[0].length;
		int n = matrix.length;
		// System.out.println(n);

		int layers = (int) Math.ceil(n / 2d);
		System.out.println(layers);

		for (int i = 0; i < layers; i++) {
			// 从左到右
			for (int k = i; k < m - i; k++)
				rt.add(matrix[i][k]);
			// 从右上到右下
			// for(int k = i+1;k < n - i;k++) rt.add(matrix[k][n-i-1]);
			// 从右下到左下
			// for(int k = m - i - 1;k >= i && n - i -1 != i;k--)
			// rt.add(matrix[n - i - 1][k]);
			// 左下至左上
			// for(int k = n - i - 2;k > i;k--) rt.add(matrix[k][i]);
		}

		return rt;
	}

	/**
	 * 操作给定的二叉树，将其变换为源二叉树的镜像。 二叉树的镜像定义：源二叉树 
	 * 8 
	 * / \ 
	 * 6 10 
	 * / \ / \ 
	 * 5 7 9 11 
	 * 镜像二叉树 
	 * 8
	 * / \ 
	 * 10 6
	 * / \ / \
	 * 11 9 7 5
	 * 一句话功能简述
	 * 
	 * @param root
	 * @date 2019年9月3日
	 * @author liudawei
	 */
	public static void Mirror(TreeNode root) {
		if (root == null)
			return;
		changeTreeNodes(root);
	}

	public static void changeTreeNodes(TreeNode root) {
		if (root.left == null && root.right == null) {
			return;
		}

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		if (root.left != null) {
			changeTreeNodes(root.left);
		}
		if (root.right != null) {
			changeTreeNodes(root.right);
		}
	}

	/**
	 * 
	 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 * @date 2019年9月2日
	 * @author liudawei
	 */
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null)
			return false;
		return isSubtree(root1, root2) || isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
	}

	public static boolean isSubtree(TreeNode root1, TreeNode root2) {
		if (root2 == null) {
			return true;
		}

		if (root1 == null) {
			return false;
		}

		if (root1.val == root2.val) {
			return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
		}

		return false;
	}

	/**
	 * 
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 * 
	 * 		eg: p1: 1 -> 5 -> 7 -> 9 p2: 2 -> 4 -> 8 -> 9
	 * 
	 *         return 1 -> 2 -> 4 -> 5 -> 7 -> 8 -> 9 -> 9
	 * 
	 * @date 2019年9月2日
	 * @author liudawei
	 */
	public static ListNode Merge(ListNode list1, ListNode list2) {
		ListNode list = null;
		if (list1 == null && list2 == null)
			return null;
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;
		if (list1.val < list2.val) {
			list = list1;
			list.next = Merge(list1.next, list2);
		} else {
			list = list2;
			list.next = Merge(list1, list2.next);
		}
		return list;
	}

	/**
	 * 
	 * 输入一个链表，反转链表后，输出新链表的表头。 eg:将 1 -> 2 -> 3 -> 4 转换为 1 <- 2 <- 3 <- 4
	 * 
	 * @param head
	 * @return
	 * @date 2019年9月2日
	 * @author liudawei
	 */
	public static ListNode ReverseList(ListNode head) {
		if (head == null)
			return null;
		//
		ListNode p1 = null;
		ListNode p2 = null;

		while (head != null) {
			p1 = head.next;
			head.next = p2;

			p2 = head;
			head = p1;

		}

		return p2;

	}

	/**
	 * 
	 * 输入一个链表，输出该链表中倒数第k个结点。
	 * 
	 * @param head
	 * @param k
	 * @return
	 * @date 2019年8月30日
	 * @author liudawei
	 */
	public static ListNode FindKthToTail(ListNode head, int k) {
		if (head == null)
			return null;
		ListNode p1 = head, p2 = head;
		while (k > 0 && p2 != null) {
			p2 = p2.next;
			k--;
		}

		// //p2 指向head说明k<=0,p2==null && k>0说明 k超过了链表的长度
		if (p2 == head || (p2 == null && k > 0))
			return null;

		while (p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}

		return p1;
	}

	/**
	 * 
	 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
	 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
	 * 
	 * @param array
	 * @date 2019年8月30日
	 * @author liudawei
	 */
	public static void reOrderArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int j = i;

			if (array[i] % 2 == 0) {// 偶数，找到后面基数位
				while (j < array.length && array[j] % 2 == 0) {
					j++;
				}
				if (j >= array.length) {
					return;
				}

				for (; j > i; j--) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
				i = j;
			}

		}

	}

	/**
	 * 
	 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
	 * 
	 * @param array
	 * @date 2019年8月30日
	 * @author liudawei
	 */
	public static void reOrderArray1(int[] array) {
		int i = 0;
		int j = array.length - 1;

		while (i < j) {
			if (array[i] % 2 != 0) {
				i++;
				continue;
			}

			if (array[j] % 2 == 0) {
				j--;
				continue;
			}
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}

	}

	/**
	 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
	 * 保证base和exponent不同时为0
	 * 
	 * @param base
	 * @param exponent
	 * @return
	 * @date 2019年8月29日
	 * @author liudawei
	 */
	public static double Power(double base, int exponent) {
		if (base == 0) {
			return 0;
		}
		if (exponent == 0) {
			return 1;
		}

		return base * Power(base, exponent - 1);
	}

	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
	 * 
	 * @param n
	 * @return
	 * @date 2019年8月29日
	 * @author liudawei
	 */
	public static int NumberOf1(int n) {
		int number = 0;
		String string = Integer.toBinaryString(n);
		char[] chars = string.toCharArray();
		int i = 0;
		for (; i < string.length(); i++) {
			if (chars[i] == '1')
				number++;
		}

		return number++;
	}

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
	 * 
	 * @param target
	 * @return
	 * @date 2019年8月29日
	 * @author liudawei
	 */

	// 递归算法
	public static int JumpFloor(int target) {
		if (target < 1) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}
		return JumpFloor(target - 1) + JumpFloor(target - 2);
	}

	// 备忘录算法
	public static int JumpFloor2(int target, HashMap<Integer, Integer> map) {
		if (target < 1) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}

		if (map.containsKey(target)) {
			return map.get(target);
		} else {
			int value = JumpFloor2(target - 1, map) + JumpFloor2(target - 2, map);
			map.put(target, value);
			return value;
		}
	}

	/**
	 * 
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 * 
	 * @param target
	 * @return
	 * @date 2019年8月29日
	 * @author liudawei
	 */
	public static int JumpFloorII(int target) {
		if (target < 1) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}

		int rt = 1;
		while (target > 0) {
			rt += JumpFloorII(target - 1);
			target--;
		}

		return rt;
	}

	/**
	 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？ 斐波那契数列
	 * 
	 * @param target
	 * @return
	 * @date 2019年8月29日
	 * @author liudawei
	 */
	public static int RectCover(int target) {
		if (target < 1) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}
		return RectCover(target - 1) + RectCover(target - 2);
	}

	/**
	 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
	 */

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		return stack2.pop();
	}

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
	 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 * 
	 * @param array
	 * @return
	 * @date 2019年8月23日
	 * @author liudawei
	 */
	public static int minNumberInRotateArray(int[] array) {
		int low = 0;
		int high = array.length - 1;

		while (low < high) {
			System.out.println("low == " + low + " high == " + high);
			int mid = (low + high) / 2;
			if (array[mid] > array[high]) {// 中数大于最大数，最小数+1
				low = mid + 1;
			} else if (array[mid] == array[high]) {
				low = low - 1;
				high = high - 1;
			} else {
				high = mid;
			}

		}

		return array[low];
	}

	/**
	 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
	 * 
	 * 斐波那契数列：F(0) = 0, F(1) = 1,F(n) = F(n - 1) + F(n - 2) ;(n>=2,n--N*)
	 * 
	 * @param n
	 * @return
	 * @date 2019年8月23日
	 * @author liudawei
	 */
	public static int Fibonacci(int n) {
		if (n > 39 || n < 2) {
			return n;
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。 一句话功能简述
	 * 
	 * @param pre
	 * @param in
	 * @return
	 * @date 2019年8月22日
	 * @author liudawei
	 */
	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);// 0，7
	}

	public static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn,
			int endIn) {
		if (startPre > endPre || startIn > endIn) {
			return null;
		}
		TreeNode root = new TreeNode(pre[startPre]);// 1

		for (int i = startIn; i <= endIn; i++) {// 0，7
			if (in[i] == pre[startPre]) {// 当中序遍历等于根节点得值时3
				root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
				root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
				break;
			}
		}

		return root;
	}

	/**
	 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
	 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。 一句话功能简述
	 * 
	 * @param target
	 * @param array
	 * @return
	 * @date 2019年8月21日
	 * @author liudawei
	 */
	public static boolean Find(int target, int[][] array) {
		if (array.length == 1 && array[0].length < 1) {
			return false;
		}
		int size = array[0].length - 1;
		for (int i = 0; i < array.length; i++) {
			if (array[i][size] >= target) {
				for (int j = 0; j < size + 1; j++) {
					if (array[i][j] == target) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are
	 * Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 * 
	 * @param str
	 * @return
	 * @date 2019年8月22日
	 * @author liudawei
	 */
	public static String replaceSpace(StringBuffer str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char charAt = str.charAt(i);
			if (charAt == ' ') {
				sb.append("%20");
			} else {
				sb.append(charAt);
			}
		}

		return sb.toString();
	}

	/**
	 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。 一句话功能简述
	 * 
	 * @param listNode
	 * @return
	 * @date 2019年8月21日
	 * @author liudawei
	 */
	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> rt = new ArrayList<Integer>();
		while (listNode != null) {
			arr.add(listNode.val);
			listNode = listNode.next;
		}

		int size = arr.size();
		if (size > 0) {
			for (int i = size - 1; i >= 0; i--) {
				rt.add(arr.get(i));
			}
		}
		return rt;
	}
	/*
	 * public static ArrayList<Integer> printListFromTailToHead(ListNode
	 * listNode) { Stack<Integer> stack = new Stack<>(); ArrayList<Integer> a =
	 * new ArrayList<Integer>(); while(listNode != null){
	 * stack.add(listNode.val); listNode = listNode.next; }
	 * 
	 * while(!stack.isEmpty()){ a.add(stack.pop()); } return a; }
	 */}
