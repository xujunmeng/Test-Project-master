package example;
/**
@author junmeng.xu
@date  2016年4月20日上午11:24:02
@see  练习  题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在第10次落地时，共经过多少米？第10次反弹多高？
 */
public class Main2 {

	public static void main(String[] args) {
		
		double m = 100;
		double t = 0 ;
		for(int i = 0 ; i <= 10 ; i++){
			t += m;
			m = m/2;
		}
		System.out.println(t);
		System.out.println(m);
		
	}
	
}
