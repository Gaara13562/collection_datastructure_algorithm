package t00_basic;

/**
 * 泛型测试
 */
public class T01_Generic {

	public static void main(String[] args) {
		MyCollection<String> mc = new MyCollection<>();
		mc.set("Str0", 0);
		mc.set("Str1", 1);
		String str1 = mc.get(1);
		System.out.println("str1===>>>" + str1);
	}
}

/**
 * 泛型的使用，在类的定义后面跟上<E>，E是一个形参，代表具体传入的类型
 * @param <E>
 */
class MyCollection<E> {
	Object[] objs = new Object[5];

	public void set(E e, int index) {
		objs[index] = e;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) objs[index];
	}
}