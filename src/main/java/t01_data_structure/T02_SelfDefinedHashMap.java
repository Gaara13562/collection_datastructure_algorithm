package t01_data_structure;

import java.util.Objects;

public class T02_SelfDefinedHashMap<K, V> {

	// 位桶数组
	Node<K, V>[] table;
	int size;
	int LENGTH = 16;

	@SuppressWarnings("unchecked")
	public T02_SelfDefinedHashMap() {
		table = new Node[LENGTH];
	}

	private int hash(K key) {
		return Objects.hashCode(key);
		// return key.hashCode();
	}

	public void put(K key, V value) {
		int hashCode = hash(key);
		int index = hashCode & (LENGTH - 1);
		Node<K, V> newNode = new Node<>(hashCode, key, value, null);
		// 取出对应的链表
		Node<K, V> node = table[index];
		if (node == null) {
			// 如果链表为空，则将添加的节点赋值给该链表
			table[index] = newNode;
			size++;
		} else {
			/**
			 * 如果链表不为空，则循环遍历取出最后一个节点 需要说明的是：这里需要进行key重复判断
			 * 如果key已经存在了（即hashCode相同），那么更新该Node的value值即可；
			 * 如果key不存在，则一直遍历到最后节点，然后将该节点的next属性赋值为newNode即可
			 */
			while (node != null) {
				// 如果 hash 值和已经存在的某个Node 的hash值相同，说明key重复，更新数据，然后跳出循环
				if (node.hash == hashCode) {
					node.value = value;
					break;
				}
				// 如果 node.next 属性为 null，则说明已经找到最后一个节点了，但是此时仍然没有退出循环说明key没有重复
				if (node.next == null) {
					node.next = newNode;
					size++;
					break;
				}
				// 如果hash没有重复，也不是最后一个节点，则更新 node 继续遍历
				node = node.next;
			}
		}
	}

	public V get(K key) {
		int hashCode = hash(key);
		Node<K, V> node = table[hashCode & (LENGTH - 1)];
		while (node != null) {
			if (node.hash == hashCode) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}

	public void remove(K key) {
		int hashCode = hash(key);
		int index = hashCode & (LENGTH - 1);
		// 取出对应的链表
		Node<K, V> node = table[index];
		if (node != null) {
			// 如果是链表中的第一个元素匹配
			if (node.hash == hashCode) {
				table[index] = node.next;
				size--;
				node = null;
			} else {
				/**
				 *  只要不是链表中的第一个元素，则开始遍历	
				 *  此处遍历不同于之前的遍历，此处的遍历是针对该元素下一元素的key来进行判断，
				 *  从而便于更改此处元素的next属性
				 */
				Node<K, V> tmp = null;
				while ((tmp = node.next) != null) {
					if (tmp.hash == hashCode) {
						node.next = tmp.next;
						tmp.next = null;
						tmp = null;
						size--;
						break;
					}
					node = tmp;
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("size=" + size + "\t[");
		for (int i = 0; i < LENGTH; i++) {
			if (table[i] != null) {
				Node<K, V> node = table[i];
				while (node != null) {
					s.append(node.key).append('-').append(node.value).append(',');
					node = node.next;
				}
			}
		}
		s.setCharAt(s.length() - 1, ']');
		return s.toString();
	}

	public static void main(String[] args) {
		T02_SelfDefinedHashMap<Integer, String> map = new T02_SelfDefinedHashMap<>();
		map.put(1, "str1");
		map.put(1, "str11");
		map.put(2, "str2");
		map.put(3, "str3");
		map.put(17, "str17");
		map.put(33, "str33");
		map.put(null, "str-null");
		map.put(null, "null");
		System.out.println(map);
		System.out.println(map.get(1) + " = " + map.get(17) + " = " + map.get(117));

		map.remove(33);
		System.out.println(map);
	}

	private static class Node<K, V> {
		int hash;
		K key;
		V value;
		Node<K, V> next;

		public Node(int hash, K key, V value, Node<K, V> next) {
			super();
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

}