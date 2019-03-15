package t01_collection;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 通过栈（双端队列）来实现十进制和二进制的转换
 */
public class T05_VonversionByDeque {

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
