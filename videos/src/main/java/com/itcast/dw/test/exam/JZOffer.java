/**
 *
 * @date 2019年8月21日
 */
package com.itcast.dw.test.exam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;

import com.itcast.dw.test.model.ListNode;
import com.itcast.dw.test.model.RandomListNode;
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
	/*	TreeNode t = new TreeNode(1);
		t.addVal(2);
		t.addVal(2);
		t.addVal(3);
		t.addVal(5);
		t.addVal(5);
		t.addVal(3);
		for(int i = 2 ;i < 8;i++){
		    t.addVal(i);
		}

		TreeNode.cxSort(t);*/

	/*	ArrayList<ArrayList<Integer>> findPath = new JZOffer().FindPath(t,6);
		findPath.stream().forEach(e -> {
			e.stream().forEach(f -> {
				System.out.println(f);
			});
			System.out.println("===============");
		});*/

/*		RandomListNode node = new RandomListNode(1);
		node.add(2);
		node.random = new RandomListNode(10);
		node.add(3);
		node.add(4);
		node.printNodes(node);
		RandomListNode clone = Clone(node);
		System.out.println("============================clone======================");
		node.printNodes(clone);*/

	/*	TreeNode node = new TreeNode(4);
		node.addVal(node, 2);
		node.addVal(node, 6);
		node.addVal(node, 1);
		node.addVal(node, 3);
		node.addVal(node, 5);
		node.addVal(node, 7);
		node.qxSort(node);*/

/*		String str = "35142";
		ArrayList<String> permutation = Permutation(str);
		permutation.stream().forEach(e -> {System.out.println(e);});*/

		/*int [] arr = {1,2,3,2,2,2,5,4,2};
		System.out.println(MoreThanHalfNum_Solution(arr));*/


		/*int [] arr = {4,5,1,6,2,7,3,8};
		ArrayList<Integer> getLeastNumbers_Solution = GetLeastNumbers_Solution(arr,2);
		getLeastNumbers_Solution.stream().forEach(e -> {System.out.println(e);});*/

	/*	//int [] arr = {6,-3,-2,7,-15,1,2,2};
		int [] arr = {1,-2,3,10,-4,7,2,-5};
		//int [] arr = {2,8,1,5,9};
		System.out.println(FindGreatestSumOfSubArray(arr));*/

		//System.out.println(NumberOf1Between1AndN_Solution(13));

		//int[] arr = {3,32,321};
	/*	int[] arr = {3,5,1,4,2};
		System.out.println(PrintMinNumber(arr));*/

		//System.out.println(GetUglyNumber_Solution(8));

		//System.out.println(FirstNotRepeatingChar("aaabbbcccddeffffgggg"));


		/*int[] arr = {1,2,3,4,5,6,7,0};
		System.out.println(InversePairs(arr));*/

/*		ArrayList<Integer> q = new ArrayList<Integer>();
		q.add(1);
		q.add(2);

		System.out.println(q.get(0));
		q.remove(0);
		System.out.println(q.get(0));*/

/*		TreeNode node = new TreeNode(0);
		node.addVal(1);
		node.addVal(2);
		node.addVal(3);
		node.addVal(4);
		node.addVal(5);
		node.addVal(5);

		ArrayList<ArrayList<Integer>> print = Print(node);

		for(ArrayList<Integer> a:print){
			StringBuffer sb = new StringBuffer("");
			a.stream().forEach(e -> {
				sb.append(String.valueOf(e));
			});
			System.out.println(sb);
		}
		*/

	/*	TreeNode node = new TreeNode(0);
		node.addVal(1);
		node.addVal(2);
		node.addVal(3);
		node.addVal(4);?
		node.addVal(5);
		node.addVal(5);

		String str = node.Serialize(node);
		System.out.println(str);

		TreeNode deserialize = node.Deserialize(str);

		deserialize.cxSort(deserialize);*/

		/*TreeNode node = new TreeNode(5);
		node.addVal(3);
		node.addVal(7);
		node.addVal(2);
		node.addVal(4);
		node.addVal(6);
		node.addVal(8);

		System.out.println(KthNode(node,7).val);*/

	/*	JZOffer jzOffer = new JZOffer();
		jzOffer.Insert(2);
		jzOffer.Insert(5);
		jzOffer.Insert(3);
		jzOffer.Insert(4);
		jzOffer.Insert(1);
		jzOffer.Insert(6);
		jzOffer.Insert(8);
		jzOffer.Insert(7);
		jzOffer.minHeap.stream().forEach(e -> {
			System.out.println(e);
		});
		System.out.println(jzOffer.GetMedian());*/


	/*	node.addVal(2);
		TreeNode node = new TreeNode(1);
		node.addVal(3);
		node.addVal(4);
		node.addVal(5);
		node.addVal(6);
		node.addVal(7);
		node.addVal(8);*/
		/*node.left = new TreeNode(2);
		node.left.left = new TreeNode(2);
		node.left.left.left = new TreeNode(2);
		node.left.left.left.left = new TreeNode(2);
		node.left.left.left.left.left = new TreeNode(2);

		node.cxSort(node);

		System.out.println(TreeDepth(node));*/

/*		ArrayList<ArrayList<Integer>> findContinuousSequence = FindContinuousSequence(100);

		for(int i = 0;i < findContinuousSequence.size();i++){
			findContinuousSequence.get(i).stream().forEach(e -> {
				System.out.println(e);
			});
			System.out.println("========================");
		}*/

	/*	int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

		ArrayList<Integer> findNumbersWithSum = FindNumbersWithSum(arr,21);
		findNumbersWithSum.stream().forEach(e -> {
			System.out.println(e);
		});*/

		/*char[] matrix = {'a','b','c','e','s','f','c','s','a','d','e','e'};
		char[] str = {'a','b','c','c'};

		boolean hasPath = hasPath(matrix,3,4,str);
		System.out.println(hasPath);*/

		//System.out.println(movingCount(15,20,20));

		//System.out.println(cutRope(6));

		//System.out.println(Sum_Solution(1));

		//System.out.println(Add(5,3));

    String str = "abcXYZdef";

    System.out.println(LeftRotateString(str,3));

	}

	/**
	 * @description: 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
   * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
	 * @author: liudawei
	 * @date: 2020/4/2 15:57
	 * @param: str
	 * @param: n
	 * @return: java.lang.String
	 */
  public static String LeftRotateString(String str, int n) {
    int length = str.length();
    if(n > length || n <= 0){
      return "";
    }
    String substring = str.substring(n, length);
    String substring1 = str.substring(0, n);
    return substring + substring1;
  }

	/**
	 *
	 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
	 * 输入一个字符串,包括数字字母符号,可以为空
	 * 如果是合法的数值表达则返回该数字，否则返回0
	 * @param str
	 * @return
	 * @date 2019年10月15日
	 * @author liudawei
	 */
	public static int StrToInt(String str) {
		Integer.parseInt(str);
		return 1;
	}

	/**
	 * 发散性思维
	 *
	 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
	 *
	 * & 与运算 0&0=0 0&1=0 1&0=0 1&1=1 ，都为1，结果才能1
	 *
	 * | 或运算 0|0=0 0|1=1 1|0=1 1|1=1 ，一个为1，结果才能1
	 *
	 * ^ 异或运算 0^0=0 0^1=1 1^0=1 1^1=0 ，两个对应位数不同，结果为1
	 *
	 * ~ 取反运算 ~1=0 ~0=1 ,取反0变1，1变0
	 *
	 * << 左移运算符 a = a << 2 将a的二进制左移2两位，右补0
	 * >> 右移运算符 a = a >> 2 将a的二进制右移2两位，左补0或1，右丢弃
	 * >>> 无符号右移
	 *
	 * @param num1
	 * @param num2
	 * @return
	 * @date 2019年9月25日
	 * @author liudawei
	 */
	public static int Add(int num1, int num2) {
		if(num1 == 0) return num2;
		if(num2 == 0) return num1;

		int sum;
		while(num2 != 0){
			System.out.println(num1 + " ===== " + num2);
			sum = num1^num2;
			num2 = (num1 & num2) << 1;
			num1 = sum;
		}
		return num1;
	}

	/**
	 *
	 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
	 *
	 * 1+...+n = (1+n)n/2 = (n+n2)/2
	 * @param n
	 * @return
	 * @date 2019年10月15日
	 * @author liudawei
	 */
	public static int Sum_Solution(int n) {
		return ((int)Math.pow(n, 2) + n) >> 1;
	}

	/**
	 *
	 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），
	 * 每段绳子的长度记为k[0],k[1],...,k[m]。
	 * 请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
	 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	 *
	 * 输入一个数n，意义见题面。（2 <= n <= 60）
	 * @param target
	 * @return
	 * @date 2019年10月14日
	 * @author liudawei
	 */
	public static int cutRope(int target) {
		if(target < 2){
			return 0;
		}
		if(target == 2) return 1;
		if(target == 3) return 2;

		int[] f = new int[target+1];

		f[0] = 0;
		f[1] = 1;
		f[2] = 2;
		f[3] = 3;

		for(int i = 4;i <= target;i++){
			int max = 0;
			for(int j = 1;j<=i/2;j++){
				int num = f[j]*f[i-j];
				if(max < num){
					max = num;
				}
			}
			f[i] = max;
		}
		return f[target];
	}

	/**
	 *
	 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
	 * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
	 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
	 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
	 * 请问该机器人能够达到多少个格子？
	 * @param threshold
	 * @param rows
	 * @param cols
	 * @return
	 * @date 2019年10月10日
	 * @author liudawei
	 */
	public static int movingCount(int threshold, int rows, int cols) {
		ArrayList<ArrayList<Integer>> indexs = new ArrayList<ArrayList<Integer>>();
		for(int i = 0;i < rows;i++){
			ArrayList<Integer> rt = new ArrayList<Integer>();
			for(int j = 0;j < cols;j++){
				rt.add(checkNumbers(i,j,threshold));
			}
			indexs.add(rt);
		}
		indexs.stream().forEach(e -> {
			StringBuffer str = new StringBuffer("");
			e.stream().forEach(f -> {
				str.append(" " + f);
			});
			System.out.println("e.size() === " + e.size() + "str === " + str);
		});
		return movingCountHelper(0,rows,cols,0,0,indexs);
	}

	public static int checkNumbers(int i ,int j,int threshold){
		int sums = 0;
		while(i > 0){
			sums += i%10;
			i = i/10;
		}
		while(j > 0){
			sums += j%10;
			j = j/10;
		}
		return sums <= threshold ? 1:-1;
	}

	public static int movingCountHelper(int moveCounts,int rows, int cols, int i,int j,ArrayList<ArrayList<Integer>> indexs) {
		//走到哪步
		if(indexs.get(i).get(j) != -1){
			indexs.get(i).set(j, -1);
			moveCounts++;
		}

		if( i + 1 < rows && indexs.get(i+1).get(j) != -1){
			moveCounts = movingCountHelper(moveCounts,rows,cols,i + 1,j,indexs);
		}
		if( i - 1 >= 0 && indexs.get(i-1).get(j) != -1){
			moveCounts = movingCountHelper(moveCounts,rows,cols,i - 1,j,indexs);
		}
		if( j + 1 < cols && indexs.get(i).get(j+1) != -1){
			moveCounts = movingCountHelper(moveCounts,rows,cols,i,j + 1,indexs);
		}
		if( j - 1 >= 0 && indexs.get(i).get(j-1) != -1){
			moveCounts = movingCountHelper(moveCounts,rows,cols,i,j - 1,indexs);
		}

		return moveCounts;
	}



	/**
	 * 栈和队列
	 *
	 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
	 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
	 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
	 * 例如 a b c e
	 *     s f c s
	 *     a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
	 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
	 * @param matrix
	 * @param rows
	 * @param cols
	 * @param str
	 * @return
	 * @date 2019年9月27日
	 * @author liudawei
	 */
	public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		//路径标识，走过后标为true
		boolean[] flag = new boolean[matrix.length];

		for(int i = 0;i < rows;i++){
			for(int j = 0;j < cols;j++){
				//每个元素都可以为第一个
				if(hasPathHelper(matrix,i,j,rows,cols,str,flag,0)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean hasPathHelper(char[] matrix, int i,int j,int rows, int cols, char[] str,boolean[] flag,int k) {
		//当前走的数组位置
		int index = i*cols + j;
		//递归终止条件
		if(i < 0 || j < 0 || i >= rows || j >= cols || matrix[index] != str[k] || flag[index] == true){
			return false;
		}
		//所有字符已验证完
		if(k  == str.length - 1){
			return true;
		}

		flag[index] = true;

		if(hasPathHelper(matrix,i-1,j,rows,cols,str,flag,k + 1) ||
			hasPathHelper(matrix,i+1,j,rows,cols,str,flag,k + 1) ||
			hasPathHelper(matrix,i,j-1,rows,cols,str,flag,k + 1) ||
			hasPathHelper(matrix,i,j+1,rows,cols,str,flag,k + 1)){
			return true;
		}

		//走不通，改为false
		flag[index] = false;
		return false;
	}

	/**
	 *
	 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
	 * @param array
	 * @param sum
	 * @return
	 * @date 2019年10月9日
	 * @author liudawei
	 */
	public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
		ArrayList<Integer> rt = new ArrayList<Integer>();
		int i = 0,j = array.length - 1;
		int minA = 0;
		while(i < j){
			if(array[i] + array[j] == sum){
				if(minA == 0){
					minA = array[i]*array[j];
					rt.add(array[i]);
					rt.add(array[j]);
//					System.out.println("minA === " + minA + " i === " + array[i] + " j === " + array[j]);
				}else{
//					System.out.println("minA === " + minA + " i === " + array[i] + " j === " + array[j]);
					minA = minA < array[i]*array[j] ? minA:array[i]*array[j];
					if(minA > array[i]*array[j]){
						rt.clear();
						rt.add(array[i]);
						rt.add(array[j]);
					}
				}
				i++;
			}else if(array[i] + array[j] < sum){
				i++;
			}else{
				j--;
			}
		}

		return rt;
	}

	/**
	 *
	 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
	 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
	 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
	 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
	 *
	 *
	 * 输出:所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
	 * @param sum
	 * @return
	 * @date 2019年10月9日
	 * @author liudawei
	 */
	public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> rt = new ArrayList<ArrayList<Integer>>();
		//至少两位数
		int low = 1;
		int height = 2;
		while(low < height){
			int temp = (low+height)*(height - low + 1)/2;
			if(temp == sum){
				ArrayList<Integer> p = new ArrayList<Integer>();
				for(int i = low;i <= height;i++){
					p.add(i);
				}
				rt.add(p);
				low++;
			}else if(temp < sum){
				height++;
			}else{
				low++;
			}

		}

		return rt;
	}

	/**
	 *
	 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
	 * num1,num2分别为长度为1的数组。传出参数
	 * 将num1[0],num2[0]设置为返回结果
	 * @param array
	 * @param num1
	 * @param num2
	 * @date 2019年10月9日
	 * @author liudawei
	 */
	public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
         HashSet<Integer> set  = new HashSet<Integer>();

         for(int i = 0;i < array.length;i++){
        	 if(!set.contains(array[i])) set.add(array[i]);
        	 else set.remove(array[i]);
         }

         Iterator<Integer> iterator = set.iterator();
         if(iterator.hasNext()) num1[0] = iterator.next();
         if(iterator.hasNext()) num2[0] = iterator.next();
	}

	/**
	 *
	 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
	 * 平衡二叉树（Balanced Binary Tree）具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
	 * @param root
	 * @return
	 * @date 2019年10月9日
	 * @author liudawei
	 */
	public boolean IsBalanced_Solution(TreeNode root) {
		return depth(root) != -1;
	}

	public int depth(TreeNode node){
		if(node == null){
			return 0;
		}

		int left = depth(node.left);
		if(left == -1){
			return -1;
		}

		int right  = depth(node.right);
		if(right == -1){
			return -1;
		}

		if(left - right < -1 || left - right > 1){
			return -1;
		}else{
			return 1+ (left > right?left:right);
		}

	}

	/**
	 *
	 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
	 * @param root
	 * @return
	 * @date 2019年10月8日
	 * @author liudawei
	 */
	public static int TreeDepth(TreeNode root) {
		if(root == null) return 0;
		int leftDepth = TreeDepth(root.left);
		int rightDepth = TreeDepth(root.right);
		int number = 1+(leftDepth > rightDepth ? leftDepth:rightDepth);
		return number;
	}

	public static int TreeDepthHelper(TreeNode root,int depth) {
		if(root == null){
			return depth;
		}
		if(root.left != null){
			TreeDepthHelper(root.left,++depth);
		}
		if(root.right != null){
			TreeDepthHelper(root.right,++depth);
		}
		return ++depth;
	}

	/**
	 *
	 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
	 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
	 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
	 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
	 *   {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
	 *   {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
	 *   {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
	 * @param num
	 * @param size
	 * @return
	 * @date 2019年9月25日
	 * @author liudawei
	 */
	public ArrayList<Integer> maxInWindows(int[] num, int size) {
		ArrayList<Integer> rt = new ArrayList<Integer>();

		if(num == null || num.length == 0){
			return rt;
		}
		//滑动窗口的大小
		int flushNumber = num.length - size + 1;
		//第一个滑动窗口最大值
		int maxNumber = num[0];
		for(int i = 0; i < size;i++){
			if(num[i] > maxNumber){
				maxNumber = num[i];
			}
		}

		rt.add(maxNumber);

		for(int i = 1;i < flushNumber;i++){

		}
		return rt;
	}

	/**
	 *
	 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
	 * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
	 * 那么中位数就是所有数值排序之后中间两个数的平均值。
	 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
	 * @param num
	 * @date 2019年9月25日
	 * @author liudawei
	 */
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
	   new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	});

	int InsertCount = 0;
	public void Insert(Integer num) {
		if(InsertCount % 2 == 0){
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		}else{
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		}
		InsertCount++;
	}
	public Double GetMedian() {
		if(InsertCount % 2 == 0){
			return new Double(minHeap.peek() + maxHeap.peek()) / 2;
		}else{
			return new Double(minHeap.peek());
		}
	}



	/**
	 *
	 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，
	 * 按结点数值大小顺序第三小结点的值为4。
	 * @param pRoot
	 * @param k
	 * @return
	 * @date 2019年9月25日
	 * @author liudawei
	 */
	public static int KthNodeIndex = 0;
	public static TreeNode KthNode(TreeNode pRoot, int k) {
		//中序遍历
		if(pRoot != null){
			TreeNode node = KthNode(pRoot.left,k);
			if(node != null){
				System.out.println("left null");
				return node;
			}
			KthNodeIndex++;
			if(k == KthNodeIndex){
				System.out.println("the last end");
				return pRoot;
			}
			node = KthNode(pRoot.right,k);
			if(node != null){
				System.out.println("right null");
				return node;
			}
		}

		return null;
	}

	/**
	 *
	 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
	 * @param pRoot
	 * @return
	 * @date 2019年9月24日
	 * @author liudawei
	 */
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> rt = new ArrayList<ArrayList<Integer>>();
		PrintHelper(pRoot,1,rt);
		return rt;
	}
	public static void PrintHelper(TreeNode pRoot,int depth,ArrayList<ArrayList<Integer>> list) {
		if(pRoot == null) return;
		if(depth > list.size()){
			list.add(new ArrayList<Integer>());
		}

		list.get(depth - 1).add(pRoot.val);

		PrintHelper(pRoot.left,depth+1,list);
		PrintHelper(pRoot.right,depth+1,list);
	}


/*	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> rt = new ArrayList<ArrayList<Integer>>();
		if(pRoot == null){
			return rt;
		}
		ArrayList<TreeNode> q = new ArrayList<TreeNode>();
		q.add(pRoot);

		int floorNumber = 1;
		ArrayList<Integer> at = new ArrayList<Integer>();
		while(!q.isEmpty()){
			int number = floorNumber;
			while(number % 2 == 0){
				number = number / 2;
			}
			if(number == 1){
				at = new ArrayList<Integer>();
			}
			TreeNode treeNode = q.get(0);
			at.add(treeNode.val);

			if(treeNode.left != null){
				q.add(treeNode.left);
			}
			if(treeNode.right != null){
				q.add(treeNode.right);
			}

			if(number == 1){
				rt.add(at);
			}
			q.remove(0);
			floorNumber++;
		}
		return rt;
	}*/



	/**
	 *
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
	 *  即输出P%1000000007
		输入描述:
		题目保证输入的数组中没有的相同的数字
		数据范围：
			对于%50的数据,size<=10^4
			对于%75的数据,size<=10^5
			对于%100的数据,size<=2*10^5
		示例1输入
		1,2,3,4,5,6,7,0
		输出 7
	 * @param array
	 * @return
	 * @date 2019年9月24日
	 * @author liudawei
	 */
	public static int InversePairs(int[] array) {
		for(int i = array.length - 1;i > 0;i--){
			System.out.println(array[i]);
		}
		return 1;
	}

	/**
	 *
	 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
	 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
	 * @param str
	 * @return
	 * @date 2019年9月24日
	 * @author liudawei
	 */
	public static int FirstNotRepeatingChar(String str) {
		if(str == null || str.length() == 0 || str.length() > 10000){
			return -1;
		}

		char[] arr = str.toCharArray();
		for(int i = 0;i < arr.length;i++){
			int number = 0;
			for(char a:arr){
				if(arr[i] == a){
					number++;
				}
			}

			if(number == 1){
				return i;
			}
		}

		return -1;
	}


	/**
	 *
	 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
	 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
	 * @param index
	 * @return
	 * @date 2019年9月24日
	 * @author liudawei
	 */
	public static int GetUglyNumber_Solution(int index) {
		if(index < 7) return index;
		Vector<Integer> arr = new Vector<Integer>();

		int p1 = 0,p2 = 0,p3 = 0,newNum = 1;
		arr.add(newNum);

		while(arr.size() < index){
			newNum = Math.min(arr.get(p1) * 2, Math.min(arr.get(p2) * 3, arr.get(p3) * 5));
			if(arr.get(p1) * 2 == newNum) p1++;
			if(arr.get(p2) * 3 == newNum) p2++;
			if(arr.get(p3) * 5 == newNum) p3++;
			arr.add(newNum);
		}

		return newNum;
	}

	/**
	 *
	 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
	 * @param numbers
	 * @return
	 * @date 2019年9月23日
	 * @author liudawei
	 */
	public static String PrintMinNumber(int[] numbers) {
		if(numbers == null || numbers.length == 0){
			return "";
		}

		ArrayList<String> numberList = new ArrayList<String>();
		ArrayList<String> printMinNumberHelper = PrintMinNumberHelper(numbers,0,numberList);
		printMinNumberHelper.sort(null);

		return printMinNumberHelper.get(0);
	}

	public static ArrayList<String> PrintMinNumberHelper(int[] numbers,int index,ArrayList<String> numberList) {
		if(index == numbers.length - 1){
			StringBuffer sb = new StringBuffer("");
			for(int s:numbers){
				sb.append(String.valueOf(s));
			}
			numberList.add(sb.toString());
			return numberList;
		}else{
			for(int i = index;i < numbers.length;i++){
				swap(numbers,index,i);
				PrintMinNumberHelper(numbers,index+1,numberList);
				swap(numbers,index,i);
			}
		}
		return numberList;
	}

	public static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}



	/**
	 *
	 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
	 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
	 * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
	 * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
	 * @param n
	 * @return
	 * @date 2019年9月23日
	 * @author liudawei
	 */
	public static int NumberOf1Between1AndN_Solution(int n) {
		if(n < 1){
			return 0;
		}

		int number = 0;
		for(int i = 1;i <= n ;i++){
			char[] charArray = (i + "").toCharArray();
			for(char c:charArray){
				if("1".equals(String.valueOf(c))){
					number++;
				}
			}

		}
		return number;
	}

	/**
	 *
	 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
	 * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
	 * 当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
	 * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
	 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
	 * @param array
	 * @return
	 * @date 2019年9月23日
	 * @author liudawei
	 */
	public static int FindGreatestSumOfSubArray(int[] array) {
		if(array == null || array.length == 0){
			return 0;
		}

		int addNumber = array[0];
		int maxNumber = array[0];

		for(int i = 1;i < array.length;i++){
			if(i > 1){
				addNumber = 0;
			}
			for(int j = i;j < array.length;j++){
				addNumber += array[j];
				if(addNumber > maxNumber){
					maxNumber = addNumber;
				}
			}
		}
		return maxNumber;
	}

	/**
	 *
	 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
	 * @param input
	 * @param k
	 * @return
	 * @date 2019年9月23日
	 * @author liudawei
	 */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    	ArrayList<Integer> rt = new ArrayList<Integer>();
    	if(input == null || input.length == 0 || k <= 0 || k > input.length){
    		return rt;
    	}
    	int maxNumberIndex = 0;
    	int maxNumber = input[0];
    	for(int i = 0;i < k;i++){
    		rt.add(i,input[i]);
    		if(input[i] > maxNumber){
    			maxNumber = input[i];
    			maxNumberIndex = i;
    		}
    	}

    	for(int i = k; i < input.length;i++){
    		if(input[i] < maxNumber){
    			rt.set(maxNumberIndex, input[i]);
    			//重新选择最大数
    			maxNumberIndex = 0;
    	    	maxNumber = rt.get(0);
    			for(int j = 0;j < rt.size();j++){
    				if(rt.get(j) > maxNumber){
    					maxNumberIndex = j;
    					maxNumber = rt.get(j);
    				}
    			}
    		}
    	}
        return rt;
    }

	/**
	 *
	 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
	 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
	 * @param array
	 * @return
	 * @date 2019年9月23日
	 * @author liudawei
	 */
    public static int MoreThanHalfNum_Solution(int [] array) {
    	if(array == null || array.length == 0){
    		return 0;
    	}
    	int numberSize = array.length/2;
    	for(int i = 0;i < array.length;i++){
    		int size = 0;
    		for(int j = 0;j < array.length;j++){
    			if(array[i] == array[j]){
    				size++;
    			}
    		}
    		if(size > numberSize){
    			return array[i];
    		}
    	}

        return 0;
    }

	/**
	 *
	 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
	 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
	 *
	 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
	 * @param str
	 * @return
	 * @date 2019年9月19日
	 * @author liudawei
	 */
	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> rt = new ArrayList<String>();
		if(str != null && str.length() > 0){
			permutationHelper(str.toCharArray(),0,rt);
			rt.sort(null);
			//Collections.sort(rt);
		}
		return (ArrayList<String>) rt;
	}

	public static void permutationHelper(char[] cs,int i,ArrayList<String> list){
		if(i == cs.length - 1){//最后一个字母，不用交换
			String val = String.valueOf(cs);
			if(!list.contains(val)){
				list.add(val);
			}
		}else{
			for(int j = i;j < cs.length;j++){//交换i和i之后的位置
				swp(cs,i,j);
				permutationHelper(cs,i+1,list);
				swp(cs,i,j);
			}
		}
	}

	public static void swp(char[] cs,int i,int j){
		char temp = cs[i];
		cs[i] = cs[j];
		cs[j] = temp;
	}

	/**
	 *
	 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
	 * @param pRootOfTree
	 * @return
	 * @date 2019年9月19日
	 * @author liudawei
	 */
	private static TreeNode pLast = null;
	public static TreeNode Convert(TreeNode pRootOfTree) {
		if(pRootOfTree == null){
			return null;
		}

		/**
		 * 中序遍历，对结点左右子树进行处理
		 */
		TreeNode head = Convert(pRootOfTree.left);

		//左子树为空
		if(head == null){
			head = pRootOfTree;
		}

		pRootOfTree.left = pLast;
		if(pLast != null){
			pLast.right = pRootOfTree;
		}
		pLast = pRootOfTree;

		Convert(pRootOfTree.right);
		return head;
	}

	/**
	 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
	 * 返回结果为复制后复杂链表的head。
	 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
	 * @param pHead
	 * @return
	 * @date 2019年9月17日
	 * @author liudawei
	 */
	public static RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null){
        	return null;
        }

        RandomListNode currentNode = pHead;
        //1、复制每个结点、如复制结点A得到A1，将结点A1插到A后面
        while(currentNode != null){
        	RandomListNode cloneNode = new RandomListNode(currentNode.label);
        	cloneNode.next = currentNode.next;
        	currentNode.next = cloneNode;
        	currentNode = cloneNode.next;
        }
        currentNode = pHead;
        //2、重新遍历链表、复制老结点得随机指针给新结点，如A1.random = A.random
        while(currentNode != null){
        	currentNode.next.random = currentNode.random == null ? null:currentNode.random.next;
        	currentNode = currentNode.next.next;
        }
        //3、拆分链表、将链表拆分为原链表和复制后得链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while(currentNode != null){
        	RandomListNode cloneNode = currentNode.next;
        	currentNode.next = cloneNode.next;
        	cloneNode.next = cloneNode.next == null ? null:cloneNode.next.next;
        	currentNode = currentNode.next;
        }

        return pCloneHead;
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
