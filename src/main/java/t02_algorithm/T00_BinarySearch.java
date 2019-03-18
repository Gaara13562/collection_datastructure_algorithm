package t02_algorithm;

public class T00_BinarySearch {

	public static void main(String[] args) {
		Integer[] arr = { 1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(
				binarySearchByLoop(arr, 4) + "   " + binarySearchByLoop(arr, 5) + "   " + binarySearchByLoop(arr, 14));
		System.out.println(binarySearchByRecursion(arr, 4, 0, arr.length - 1) + "   "
				+ binarySearchByRecursion(arr, 5, 0, arr.length - 1) + "   "
				+ binarySearchByRecursion(arr, 14, 0, arr.length - 1));
	}

	/**
	 * 使用循环进行二分查找 时间复杂度：T(n)=O(logN) 空间复杂度：S(n)=O(1)
	 * 
	 * @param arr
	 *            输入的数组
	 * @param value
	 *            需要查找的值
	 * @return 返回查找的索引，-1代表没有找到
	 */
	public static <E extends Comparable<? super E>> int binarySearchByLoop(E[] arr, E value) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			int compare = arr[mid].compareTo(value);
			if (compare < 0) {
				left = mid + 1;
			} else if (compare > 0) {
				right = mid - 1;
			} else {
				int index = mid;
				while (true) {
					if (arr[index - 1].compareTo(value) != 0) {
						return index;
					} else {
						index--;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * 使用递归进行二分查找 时间复杂度：T(n)=O(logN) 空间复杂度：S(n)=O(logN)
	 * 
	 * @param arr
	 *            输入的数组
	 * @param value
	 *            需要查找的值
	 * @return 返回查找的索引，-1代表没有找到
	 */
	public static <E extends Comparable<? super E>> int binarySearchByRecursion(E[] arr, E value, int left, int right) {
		if (left <= right) {
			int mid = (left + right) / 2;
			int compare = arr[mid].compareTo(value);
			if (compare < 0) {
				// left = mid + 1;
				return binarySearchByRecursion(arr, value, mid + 1, right);
			} else if (compare > 0) {
				// right = mid - 1;
				return binarySearchByRecursion(arr, value, left, mid - 1);
			} else {
				int index = mid;
				while (true) {
					if (arr[index - 1].compareTo(value) != 0) {
						return index;
					} else {
						index--;
					}
				}
			}
		}
		return -1;
	}

}
