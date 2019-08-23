/**
 * 
 * @date 2019年8月19日
 */
package com.itcast.dw.test.exam;

import java.util.Arrays;

/**
 * 牛客网
 * @author liudawei
 */

public class Day1 {
	
	/**
	 * 
	 * 1、数据的物理结构是指数据在计算机内的实际存储形式----对
	 * 
	 * 2、设一组初始记录关键字序列为(45，80，55，40，42，85)，则以第一个记录关键字45为基准而得到一趟快速排序的结果是（）
	 *   42，40，45，55，80，85
	 *   
	 * 3、在所有排序方法中，关键字比较的次数与记录的初始排列次序无关的是（选择排序）
	 *  希尔排序、冒牌排序、插入排序、选择排序
	 * 
	 * 4、设初始记录关键字基本有序，则快速排序算法的时间复杂度为 O(nlog2n) 。（ error）
	 * 
	 * 5、在存储数据时，通常不仅要存储各数据元素的值，而且还要存储（数据元素间得关系）。
	 * 
	 * 6、对一个具有n个元素的线性表，建立其有序单链表的时间复杂度为（）
	 * 
	 * 7、对N个数进行排序,在各自最优条件下以下算法复杂度最低的是(冒泡排序、插入排序)
	 * 快速排序、堆排序、冒泡排序、插入排序、选择排序、归并排序
	 * 
	 * 
	 */
	public static void main(String[] args) {
		 int[] arr = {6,11,8,5,22,3,55,44,333};
		 //quickSort(arr,0,arr.length - 1);
		 
		 //bubbleSort(arr, arr.length);
		 
		 //insertSort(arr);
		 
		 selectionSort(arr);
		 
		 Arrays.stream(arr).forEach(e -> System.out.println(e));
	}
	
	//归并排序|分治法
	
	
	//选择排序
	public static void selectionSort(int[] arr){
		
		for(int i = 0; i < arr.length; i++){
			
			//最小数得索引
			int minIndex = i;
			for(int j = i;j < arr.length;j++){
				//找到最小数，并记录最小数索引
				if(arr[j] < arr[minIndex]){
					minIndex = j;
				}
			}
			
			//交换符合条件得数
			int tmp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = tmp;
			
		}
		
	}
	
	
	//插入排序
	public static void insertSort(int[] arr){
		int n = arr.length;
		int i,j;
		
		for(i = 1;i < n;i++){
			int temp = arr[i];
			
			for(j = i - 1;j >= 0 && arr[j] > temp;j --){
				arr[j + 1]  = arr[j];
			}
			
			arr[j + 1] = temp;
		}
		
	}
	
	//堆排序
	
	//冒泡排序
	public static void bubbleSort(int[] arr,int n){
		if(n <= 1) return;
		
		for(int i = 0;i < n;i++){
			boolean flag = false;
			
			for(int j = 0;j < n -i -1; j++){
				if(arr[j] > arr [j + 1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					flag = true;
				}
			}
			
			if(!flag) break;
			
		}
		
	}
     
     /**
      * 
      * 快速排序(二分)
      * @param sort
      * @return 
      * @date 2019年8月19日
      * @author liudawei
      */
     public static void quickSort(int[] arr,int low,int high){
    	 int i,j,temp,t;
    	 if(low > high){
    		 return;
    	 }
    	 
    	 i = low;
    	 j = high;
    	 temp = arr[low];//基准数
    	 
    	 while(i < j){
    		 
    		 while(temp <= arr[j] && i<j){
    			 j--;
    		 }
    		 
    		 while(temp >= arr[i] && i<j){
    			 i++;
    		 }
    		 
    		 if(i<j){
    			 t = arr[j];
    			 arr[j] = arr[i];
    			 arr[i] = t;
    		 }
    	 }
    	 
    	 //最后将基准为与i和j相等位置得数字交换
    	 arr[low] = arr[i];
    	 arr[i] = temp;
    	 
    	 //递归调用左半数组
    	 quickSort(arr,low,j-1);
    	 //递归调用右半数组
    	 quickSort(arr,j+1,high);
    	 
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

}
