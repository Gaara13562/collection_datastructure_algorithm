package t00_basic;

/**
 * 拆箱和装箱操作 参考链接 1)
 * 深入理解JVM之JVM内存区域与内存分配：https://www.cnblogs.com/wangjzh/p/5258254.html 2)
 * 深入剖析Java中的装箱和拆箱：https://www.cnblogs.com/dolphin0520/p/3780005.html
 * 
 * @author 李瑞东
 *
 */
public class T00_Boxing_Unboxing {

	public static void main(String[] args) {

		System.out.println("=============== Basic =================");

		Integer i1 = 100;
		Integer i2 = 100;
		Integer i3 = 200;
		Integer i4 = 200;

		System.out.println(i1 == i2);
		System.out.println(i3 == i4);

		System.out.println("=============== Advanced =================");

		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		Long h = 2L;

		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
		System.out.println(g.equals(a + h));
	}
}
