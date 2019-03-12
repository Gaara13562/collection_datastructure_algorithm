package t00_basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通过 Collections 工具类来实现对集合的一些操作
 */
public class T02_CollectionUtils {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("Str" + i);
		}
		System.out.println("初始数据\t" + list);

		// 随机排列 list 中的元素
		Collections.shuffle(list);
		System.out.println("随机排列之后的数据\t" + list);

		// 对list中的元素进行逆序排列
		Collections.reverse(list);
		System.out.println("逆序排列之后的数据\t" + list);
		
		// 对list中的元素进行递增排列
		Collections.sort(list);
		System.out.println("递增排列之后的数据\t" + list);
	}
}
