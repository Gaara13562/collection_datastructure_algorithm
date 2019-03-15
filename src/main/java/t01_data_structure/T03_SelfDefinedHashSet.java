package t01_data_structure;

import java.util.HashMap;
/**
 * 自定义 HashSet 实现
 * HashSet的实现底层是基于 HashMap的，将HashSet的内容作为HashMap的key，value为一常量 
 * @param <E>
 */
public class T03_SelfDefinedHashSet<E> {
	HashMap<E, Object> map;
	private static final Object VALUE = new Object();

	public T03_SelfDefinedHashSet() {
		map = new HashMap<E, Object>();
	}

	public void add(E obj) {
		map.put(obj, VALUE);
	}
	
	public int size() {
		return map.size();
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");
		for (E key : map.keySet()){
			s.append(key).append(",");
		}
		s.setCharAt(s.length()-1, '}');
		return s.toString();
	}
	
	public static void main(String[] args) {
		T03_SelfDefinedHashSet<String> set = new T03_SelfDefinedHashSet<String>();
		set.add("Str0");
		System.out.println(set);
		set.add("Str1");
		System.out.println(set);
	}
}
