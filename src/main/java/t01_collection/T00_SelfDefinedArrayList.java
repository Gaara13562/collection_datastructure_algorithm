package t01_collection;

import java.util.Arrays;

/**
 * 自定义实现 ArrayList
 */
public class T00_SelfDefinedArrayList<E> {
	// 存储所有的元素
	private E[] elementData;
	// size代表元素的个数，实际上 elementData的长度要大于size，但是用size表征元素个数，用elementData代表容器本身
	private int size;

	private static final int DEFAULT_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	public T00_SelfDefinedArrayList() {
		this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public T00_SelfDefinedArrayList(int size) {
		this.elementData = (E[]) new Object[size];
		this.size = 0;
	}

	public void add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
	}

	private void ensureCapacity(int capacity) {
		if (this.elementData.length < capacity) {
			int newCapacity = elementData.length + (elementData.length >> 1) + 1;
			System.out.println("=== 进入扩容函数 ===，新容量：" + newCapacity);
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	public E get(int index) {
		checkRange(index);
		return elementData[index];
	}

	private void checkRange(int index) {
		if (index < 0 || index >= this.size) {
			throw new RuntimeException("索引不合法：" + index);
		}
	}

	public void set(int index, E e) {
		checkRange(index);
		elementData[index] = e;
	}

	public void remove(int index) {
		checkRange(index);
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		}
		elementData[--size] = null;
	}

	public void remove(E e) {
		for (int i = 0; i < size; i++) {
			if (elementData[i].equals(e)) {
				remove(i);
				break;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for (int i = 0; i < size; i++) {
			s.append(elementData[i]).append(",");
		}
		s.setCharAt(s.length() - 1, ']');
		return s.toString();
	}

	public static void main(String[] args) {
		T00_SelfDefinedArrayList<String> t = new T00_SelfDefinedArrayList<>(1);
		t.add("A");
		t.add("B");
		t.add("C");
		t.add("D");
		t.add("E");
		System.out.println(t);

		// System.out.println(t.get(-1));

		t.remove(t.size - 1);
		t.remove(0);
		System.out.println(t);
	}
}
