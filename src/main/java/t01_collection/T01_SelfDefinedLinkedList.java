package t01_collection;

public class T01_SelfDefinedLinkedList<E> {

	private Node<E> first;
	private Node<E> last;
	private int size;

	public void add(E e) {
		// 如果是第一次添加，则此时 first/last均为null，需要初始化
		if (first == null) {
			Node<E> node = new Node<E>(null, null, e);
			first = last = node;
		} else {
			Node<E> node = new Node<E>(last, null, e);
			last.next = node;
			last = node;
		}
		size++;
	}

	// 在指定位置插入节点
	public void insert(int index, E e) {
		// 相比于正常的查询和删除，插入节点的时候可以让 index = size，表示在最后插入节点
		// 不过下述这个判断可以不需要，因为在 else 里面已经判断了，而特例在 if 里面已经处理了
		/*
		 * if (index < 0 || index > this.size) { throw new
		 * RuntimeException("索引不合法：" + index); }
		 */
		if (index == size) {
			// 相当于在最后添加节点，也就是正常的add 方法
			add(e);
		} else {
			Node<E> after = getNodeByIndex(index);
			Node<E> prev = after.prev;
			Node<E> insert = new Node<E>(prev, after, e);
			if (prev == null) {
				// 如果是在第0处插入一个节点
				first = insert;
			} else {
				prev.next = insert;
			}
			after.prev = insert;
			size++;
		}
	}

	private void checkRange(int index) {
		if (index < 0 || index >= this.size) {
			throw new RuntimeException("索引不合法：" + index);
		}
	}

	// 根据 get(index) 方法抽取其中遍历元素的部分，在多个函数中会使用到
	private Node<E> getNodeByIndex(int index) {
		checkRange(index);
		Node<E> tmp = null;
		if (index < (size >> 1)) {
			tmp = first;
			while (index-- > 0) {
				tmp = tmp.next;
			}
		} else {
			tmp = last;
			int numMoved = size - index - 1;
			while (numMoved-- > 0) {
				tmp = tmp.prev;
			}
		}
		return tmp;
	}

	/**
	 * 下面这个 get 方法是通过正向遍历整个链表来获取指定位置元素的， 可以优化：当index<size/2的时候，正向遍历；否则逆向遍历
	 * 
	 * @param index：索引位置
	 * @return：指定索引的对象
	 */
	public E get(int index) {
		/*
		 * checkRange(index); Node<E> tmp = null; if (index < (size >> 1)) { tmp
		 * = first;
		 * 
		 * for (int i = 0; i < index; i++) { tmp = tmp.next; }
		 * 
		 * while (index-- > 0) { tmp = tmp.next; } } else { tmp = last; int
		 * numMoved = size - index - 1; while (numMoved-- > 0) { tmp = tmp.prev;
		 * } } return tmp.value;
		 */
		return getNodeByIndex(index).value;
	}

	/**
	 * remove方法：修改指定位置元素的前后指向
	 */
	public void remove(int index) {
		Node<E> rm = getNodeByIndex(index);
		Node<E> prev = rm.prev;
		Node<E> next = rm.next;
		// 说明删除的是第一个节点
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
		}

		// 说明删除的是最后一个节点
		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
		}
		size--;
		rm = null;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("[");
		Node<E> tmp = first;
		if (first != null) {
			while (tmp != null) {
				s.append(tmp.value).append(",");
				tmp = tmp.next;
			}
			s.setCharAt(s.length() - 1, ']');
			s.append(", ").append("first=").append(first.value);
			s.append(", ").append("last=").append(last.value);
			s.append(", ").append("size=").append(size);
		} else {
			s.append(']');
			s.append(", ").append("first=").append("null");
			s.append(", ").append("last=").append("null");
			s.append(", ").append("size=").append(size);
		}
		return s.toString();
	}

	public static void main(String[] args) {
		T01_SelfDefinedLinkedList<String> linkedList = new T01_SelfDefinedLinkedList<>();
		System.out.println(linkedList);
		linkedList.add("A");
		System.out.println(linkedList);
		linkedList.add("B");
		System.out.println(linkedList);
		linkedList.add("C");
		System.out.println(linkedList);
		linkedList.add("D");
		linkedList.add("E");
		linkedList.add("F");

		System.out.println(linkedList.get(2));

		System.out.println(linkedList);
		linkedList.remove(3);
		System.out.println(linkedList);
		linkedList.remove(0);
		System.out.println(linkedList);
		linkedList.remove(linkedList.size - 1);
		System.out.println(linkedList);

		linkedList.insert(1, "insert1");
		System.out.println(linkedList);
		linkedList.insert(0, "insert0");
		System.out.println(linkedList);
		linkedList.insert(linkedList.size, "insert-last");
		System.out.println(linkedList);
	}

	static class Node<E> {
		Node<E> prev;
		Node<E> next;
		E value;

		public Node(Node<E> prev, Node<E> next, E value) {
			super();
			this.prev = prev;
			this.next = next;
			this.value = value;
		}

	}
}