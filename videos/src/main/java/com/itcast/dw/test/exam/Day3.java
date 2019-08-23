/**
 * 
 * @date 2019年8月22日
 */
package com.itcast.dw.test.exam;

import java.util.Stack;

/**
 * 剑指office4-8
 * @author liudawei
 */

public class Day3 {
	
	public static void main(String[] args) {
		//int[] array = {3,4,5,1,2};
		int[] array = {1,2,3,4,5};
		System.out.println(minNumberInRotateArray(array));
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
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array
     * @return 
     * @date 2019年8月23日
     * @author liudawei
     */
    public static int minNumberInRotateArray(int [] array) {
    	int low = 0;
    	int high = array.length - 1;
    	
    	while(low < high){
    		System.out.println("low == " + low +" high == " + high);
    		int mid = (low+high)/2;
    		if(array[mid] > array[high]){//中数大于最大数，最小数+1
    			low = mid + 1;
    		}else if(array[mid] == array[high]){
    			low = low - 1;
    			high = high - 1;
    		}else{
    			high = mid;
    		}
    		
    	}
    	
        return array[low];
    }
    
    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
     * @param n
     * @return 
     * @date 2019年8月23日
     * @author liudawei
     */
    public static int Fibonacci(int n) {
    	return 1;
    }

}
