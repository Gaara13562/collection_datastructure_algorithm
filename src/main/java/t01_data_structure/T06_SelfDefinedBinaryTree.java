package t01_data_structure;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 自定义二叉树的实现 以二叉链表为基础，每一个节点包括左子节点、右子节点以及元素本身三个属性； 所有的实现方法均涉及大量的递归调用。
 */
public class T06_SelfDefinedBinaryTree<E> implements T06_BinaryTreeInterface<E> {

	private Node<E> root;

	public static void main(String[] args) {
		Node<Integer> node5 = new Node<>(5);
		Node<Integer> node4 = new Node<>(4, null, node5);
		Node<Integer> node7 = new Node<>(7);
		Node<Integer> node6 = new Node<>(6, null, node7);
		Node<Integer> node3 = new Node<>(3);
		Node<Integer> node2 = new Node<>(2, node3, node6);
		Node<Integer> node1 = new Node<>(1, node4, node2);

		T06_SelfDefinedBinaryTree<Integer> tree = new T06_SelfDefinedBinaryTree<>(node1);
		tree.preOrderTraverse();
		tree.inOrderTraverse();
		tree.postOrderTraverse();

		System.out.println("元素个数：" + tree.size());
		System.out.println("树的高度：" + tree.height());
		System.out.println(tree.findKey(6));

		tree.levelOrderByQueue();
		tree.inOrderByStack();
	}

	public T06_SelfDefinedBinaryTree() {
		super();
	}

	public T06_SelfDefinedBinaryTree(Node<E> root) {
		super();
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return getSize(root);
	}

	private int getSize(Node<E> node) {
		if (node != null) {
			return 1 + getSize(node.leftChild) + getSize(node.rightChild);
		} else {
			return 0;
		}
	}

	@Override
	public int height() {
		return getHeight(root);
	}

	private int getHeight(Node<E> node) {
		if (node != null) {
			return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
		} else {
			return 0;
		}
	}

	@Override
	public Node<E> findKey(E value) {
		return findKey(root, value);
	}

	private Node<E> findKey(Node<E> node, E value) {
		if (node == null) {
			return null;
		} else {
			if (value.equals(node.value)) {
				return node;
			} else {
				Node<E> key = findKey(node.leftChild, value);
				if (key != null) {
					return key;
				} else {
					return findKey(node.rightChild, value);
				}
			}
		}

	}

	@Override
	public void preOrderTraverse() {
		List<E> list = new ArrayList<>();
		preOrderTraverseInternal(root, list);
		System.out.println("先序遍历：" + list);
	}

	private void preOrderTraverseInternal(Node<E> node, List<E> list) {
		if (node != null) {
			list.add(node.value);
			preOrderTraverseInternal(node.leftChild, list);
			preOrderTraverseInternal(node.rightChild, list);
		}
	}

	@Override
	public void inOrderTraverse() {
		List<E> list = new ArrayList<>();
		inOrderTraverseInternal(root, list);
		System.out.println("中序遍历：" + list);
	}

	private void inOrderTraverseInternal(Node<E> node, List<E> list) {
		if (node != null) {
			inOrderTraverseInternal(node.leftChild, list);
			list.add(node.value);
			inOrderTraverseInternal(node.rightChild, list);
		}
	}

	@Override
	public void postOrderTraverse() {
		List<E> list = new ArrayList<>();
		postOrderTraverse(root, list);
		System.out.println("后序遍历：" + list);
	}

	private void postOrderTraverse(Node<E> node, List<E> list) {
		if (node != null) {
			postOrderTraverse(node.leftChild, list);
			postOrderTraverse(node.rightChild, list);
			list.add(node.value);
		}
	}

	/**
	 * 中心思想：借助栈stack
	 */
	@Override
	public void inOrderByStack() {
		System.out.print("中序非递归遍历：[");
		// 创建栈
		Deque<Node<E>> stack = new LinkedList<>();
		Node<E> current = root;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.leftChild;
			}

			if (!stack.isEmpty()) {
				current = stack.pop();
				System.out.print(current.value + " ");
				current = current.rightChild;
			}
		}
		System.out.println("]");
	}

	@Override
	public void preOrderByStack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postOrderByStack() {
		// TODO Auto-generated method stub

	}

	/**
	 * 中心思想： 借助队列Queue，队列中保存每一层的节点， 每次该层节点弹出队列，
	 * 然后针对弹出的每一个节点，分别获取其左右子节点，然后将子节点加入队列，
	 * 如此循环，直至队列中没有节点（也即没有子节点了）
	 */
	@Override
	public void levelOrderByQueue() {
		System.out.print("按照层次遍历二叉树：[");
		if (root == null)
			return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.add(root);
		while (queue.size() != 0) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				Node<E> temp = queue.poll();
				System.out.print(temp.value + " ");
				if (temp.leftChild != null)
					queue.add(temp.leftChild);
				if (temp.rightChild != null)
					queue.add(temp.rightChild);
			}
		}
		System.out.println("]");
	}

	static class Node<E> {
		E value;
		Node<E> leftChild;
		Node<E> rightChild;

		public Node(E data) {
			super();
			this.value = data;
		}

		public Node(E data, Node<E> leftChild, Node<E> rightChild) {
			super();
			this.value = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		@Override
		public String toString() {
			return "Node [data=" + value + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
		}

	}

}
