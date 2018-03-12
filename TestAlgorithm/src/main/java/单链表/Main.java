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
		
		displayLinkList(linkList);
		
		deleteLinkList(linkList);
		
	}

	public static void displayLinkList(LinkList linkList) {
		linkList.displayList();
	}
	
	public static void deleteLinkList(LinkList linkList){
		while(linkList.first != null){
			Link link = linkList.deleteFirst();
			System.out.println("Deleted : ");
			link.displayLink();
		}
	}

}

class LinkList {

	public Link first;

	public void insertFirst(int id, double dd) {
		Link newLink = new Link(id, dd);
		newLink.next = first;
		first = newLink;
	}

	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}

	public void displayList() {
		System.out.println("List (first --> last): ");
		Link current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}

	@Override
	public String toString() {
		return "LinkList [first=" + first + "]";
	}

}

class Link {

	public int iData;

	public double dData;

	public Link next;

	public Link(int iData, double dData) {
		this.iData = iData;
		this.dData = dData;
	}

	@Override
	public String toString() {
		return "Link [iData=" + iData + ", dData=" + dData + ", next=" + next
				+ "]";
	}

	public void displayLink() {
		System.out.println("Link [iData=" + iData + ", dData=" + dData + "]");
	}

}