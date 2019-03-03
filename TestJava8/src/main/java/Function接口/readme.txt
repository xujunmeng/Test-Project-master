14年，Oracle公司如期发布了Java 8正式版。
现如今4年过去了，终于鼓起勇气认真对待它，就好似虽然认识了好几年的伙伴，突然感觉要成为情侣的感觉……

JDK 1.8 API包含了很多内建的函数式接口，在老Java中常用到的比如Comparator或者Runnable接口，
这些接口都增加了@FunctionalInterface注解以便能用在lambda上。现如今，我们则从Function常用函数入口，真正了解一下。

name	type	description
Consumer	Consumer< T >	接收T对象，不返回值
Predicate	Predicate< T >	接收T对象并返回boolean
Function	Function< T, R >	接收T对象，返回R对象
Supplier	Supplier< T >	提供T对象（例如工厂），不接收值
UnaryOperator	UnaryOperator	接收T对象，返回T对象
BinaryOperator	BinaryOperator	接收两个T对象，返回T对象
标注为FunctionalInterface的接口被称为函数式接口，该接口只能有一个自定义方法，但是可以包括从object类继承而来的方法。
如果一个接口只有一个方法，则编译器会认为这就是一个函数式接口。是否是一个函数式接口，需要注意的有以下几点：

该注解只能标记在”有且仅有一个抽象方法”的接口上。
JDK8接口中的静态方法和默认方法，都不算是抽象方法。
接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
该注解不是必须的，如果一个接口符合”函数式接口”定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。
如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。
在一个接口中定义两个自定义的方法，就会产生Invalid ‘@FunctionalInterface’ annotation; FunctionalInterfaceTest is not a functional interface错误.