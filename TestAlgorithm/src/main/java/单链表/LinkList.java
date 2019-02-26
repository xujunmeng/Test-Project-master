package 单链表;

/**
 * Created by jgsoft on 2019/2/25.
 */
class LinkList {

	public Link first;

	public LinkList() {
		first = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * 在表头插入一个新链结点
	 * @param id
	 * @param dd
     */
	public void insertFirst(int id, double dd) {

		//新链结点引用
		Link newLink = new Link(id, dd);

		//新链结点的引用赋值first，first的值是为null的
		newLink.next = first;

		//first的值赋值为新链结点的引用
		first = newLink;
	}

	public Link deleteFirst() {

		if (isEmpty()) {
			throw new RuntimeException("链表为空的");
		}

		Link temp = first;

		//first的值赋值为first的引用，即first的下一个链结点，
		first = first.next;

		return temp;
	}

	/**
	 * 查找指定链结点
	 */
	public Link find(int key) {
		if (isEmpty()) {
			throw new RuntimeException("链表为空");
		}

		Link current = first;
		while (current.iData != key) {
			if (current.next == null) {
				return null;
			} else {
				current = current.next;
			}
		}
		return current;
	}

	/**
	 * 删除指定链结点
	 */
	public Link delete(int key) {
		Link current = first;
		Link previous = first;
		while (current.iData != key) {
			if (current.next == null) {
				return null;
			} else {
				previous = current;
				current = current.next;
			}
		}
		if (current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;
	}


	public void displayList() {
		Link current = first;

		while (current != null) {

			current.displayLink();

			current = current.next;
		}
	}

}
