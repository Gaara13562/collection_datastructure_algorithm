package t01_collection;

/**
 * 自定义单向链表
 * 与前述双向节点不同之处：引入了头节点（同样也可以引入尾节点），让编程更加简单
 */
public class T04_SingleLinkedList<E> {

	// 定义一个头节点，不存储数据，为了编程方便
	private Node<E> head;
	private int size;

	public T04_SingleLinkedList() {
		super();
		head = new Node<>();
	}

	public void add(E e) {
		add(size, e);
	}

	public Node<E> getNodeByIndex(int index) {
		// 通过设置头节点的方式，避免了双向节点中对于头结点这一特殊情况的判断，使得编程简单了很多
		Node<E> p = head;
		while (index-- >= 0) {
			p = p.next;
		}
		return p;
	}

	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new RuntimeException("索引不合法：" + index);
		}
		// 首先获取 index-1 位置处的节点
		Node<E> p = getNodeByIndex(index - 1);
		
		// 然后获取 index 位置处的节点
		// Node<E> next = p.next;

		// 创建要添加的节点
		Node<E> node = new Node<>(e, p.next);
		p.next = node;
		size++;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("size=" + size + "[");
		Node<E> tmp = head;
		while ((tmp = tmp.next) != null) {
			s.append(tmp.e).append(",");
		}
		s.setCharAt(s.length() - 1, ']');
		return s.toString();
	}

	public static void main(String[] args) {
		T04_SingleLinkedList<Integer> singleLinkedList = new T04_SingleLinkedList<>();
		singleLinkedList.add(1);
		singleLinkedList.add(3);
		singleLinkedList.add(5);
		singleLinkedList.add(6);
		System.out.println(singleLinkedList);
		singleLinkedList.add(2, 4);
		System.out.println(singleLinkedList);
		singleLinkedList.add(0, 2);
		System.out.println(singleLinkedList);
	}

	static class Node<E> {
		E e;
		Node<E> next;

		public Node() {
			super();
		}

		public Node(E e) {
			super();
			this.e = e;
		}

		public Node(E e, Node<E> next) {
			super();
			this.e = e;
			this.next = next;
		}
	}
}
