package t00_basic;

/**
 * 拆箱和装箱操作 参考链接 1)
 * 深入理解JVM之JVM内存区域与内存分配：https://www.cnblogs.com/wangjzh/p/5258254.html 2)
 * 深入剖析Java中的装箱和拆箱：https://www.cnblogs.com/dolphin0520/p/3780005.html
 */
public class T00_Boxing_Unboxing {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {

		System.out.println("=============== Basic =================");

		Integer i1 = 100;
		Integer i2 = 100;
		Integer i3 = 200;
		Integer i4 = 200;

		System.out.println(i1 == i2); // true
		System.out.println(i3 == i4); //  false，-128~127范围内的数值会采用常量池技术

		System.out.println("=============== Advanced =================");

		Integer a = 1;
		Integer b = 2; 
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		Long h = 2L;

		System.out.println(c == d); // true，常量池技术
		System.out.println(e == f); // false，超出范围，不适用常量池技术
		System.out.println(c == (a + b)); // true，==会自动进行拆箱操作
		System.out.println(c.equals(a + b)); // true，equal需要类型和数值同时匹配
		System.out.println(g == (a + b)); // true，拆箱操作之后数值相等，类型此时不重要
		System.out.println(g.equals(a + b)); // false，类型不匹配
		System.out.println(g.equals(a + h)); // true，自动将Integer拓展为Long并且进行类型和数值的比较，都相等

		System.out.println("=============== String 也实现了常量池技术 =================");
		String aStr = "aa";
		String bStr = "aa";
		System.out.println((aStr == bStr)); // true，常量池技术
		System.out.println(aStr.equals(bStr)); // true，类型且值都相等
	}
}
