package 单链表;

/**
 * Created by jgsoft on 2019/2/25.
 */
class Link {

	public int iData;

	public double dData;

	public Link next;

	public Link(int iData, double dData) {
		this.iData = iData;
		this.dData = dData;
	}

	public void displayLink() {
		System.out.println("Link [iData=" + iData + ", dData=" + dData + "]");
	}

}
