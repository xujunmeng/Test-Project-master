package 单链表;

/**
 * @author junmeng.xu
 * @date 2016年7月13日下午6:43:07
 */
public class Main {

	public static void main(String[] args) {
		
		LinkList linkList = new LinkList();
		linkList.insertFirst(11, 1.99);
		linkList.insertFirst(22, 2.99);
		linkList.insertFirst(33, 3.99);
		linkList.insertFirst(44, 4.99);
		linkList.insertFirst(55, 5.99);

		linkList.displayList();

		while(linkList.first != null){
			Link link = linkList.deleteFirst();
			System.out.println("Deleted : ");
			link.displayLink();
		}
		
	}
}

