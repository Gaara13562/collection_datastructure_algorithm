package t02_algorithm;

import java.util.Arrays;

/**
 * 排序分类：
 * 1. 比较排序（8大排序）
 * 	1.1 交换排序，包括【冒泡排序】、【快速排序】等
 * 	1.2 选择排序，包括【基本选择排序】、【堆排序】等
 * 	1.3 插入排序，包括【直接插入排序】、【折半插入排序】、【希尔排序】等
 * 	1.4 归并排序，包括【归并排序】
 * 
 * 2. 非比较排序
 * 	2.1 主要包括【计数排序】和【基数排序】
 */
public class T01_Sorting {

	public static void main(String[] args) {
		Integer[] a = { 12, 20, 5, 16, 15, 1, 30, 45, 23, 9, 9 };
		System.out.println(Arrays.toString(a));
		Integer[] quickSort = quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(quickSort));
	}

	/**
	 * 快速排序算法
	 * 
	 * @param arr
	 *            待排序的数组
	 * @param left
	 *            排序起始元素下标
	 * @param right
	 *            排序结束元素下标
	 */
	public static <E extends Comparable<? super E>> E[] quickSort(E[] arr, int left, int right) {
		if (left >= right) {
			return arr;
		}
		
		int start = left;
		int end = right;
		E key = arr[start];
		while (end > start) {
			// 从后往前比较
			// 如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
			while (end > start && arr[end].compareTo(key) >= 0) {
				end--;
			}
			if (start < end) {
				arr[start] = arr[end];
				start++;
			}

			// 从前往后比较
			// 如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
			while (end > start && arr[start].compareTo(key) < 0) {
				start++;
			}
			if (start < end) {
				arr[end] = arr[start];
				end--;
			}
			// 此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
		}
		arr[start] = key;
		
		// 递归
		quickSort(arr, left, start - 1);// 左边序列。第一个索引位置到关键值索引-1
		quickSort(arr, end + 1, right);// 右边序列。从关键值索引+1到最后一个
		return arr;
	}
}
