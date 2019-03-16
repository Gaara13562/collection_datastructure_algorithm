package t01_data_structure;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 4. 栈和队列
 * 		4.1 栈是通过双端队列 Deque来实现的，包括 push（入栈）/pop（出栈）等方法
 * 		4.2 队列可以通过 Queue来实现，当然也可以通过Deque来实现，主要包括 offer（入队）/poll（出队）等方法
 * 通过栈（双端队列）来实现十进制和二进制的转换
 */
public class T05_ConversionByDeque {

	public static int conversion(int input) {
		Deque<Integer> res = new LinkedList<>();
		while (input > 0) {
			res.push(input % 2);
			input >>= 1;
		}
		System.out.println(res);
		return 0;
	}

	public static void main(String[] args) {
		conversion(13);
	}
}
