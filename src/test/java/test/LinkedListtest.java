package test;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.Test;

import java.util.Map;

public class LinkedListtest {

	//Key Finder


	Map<String, Tree> map;
	String KEY = "hello";
	String newKEY = "new Hello";

	/**Central Service
	 * 		calls keyfinder
	 * 				searchOccurences("key", "ERROR:[0-9]+")
	 *
	 * 				ERROR:1		1
	 * 				ERROR:3		3
	 * 				ERROR:5		2
	 *
	 * 										"TRANSAXTION:[0-9]
	 * 				addNewKey("key', "newHello123")
	 *
	 */


	@Test
	public void testsdlk() {


	}

	class Node {
		Node next;
		int val;

		Node(int val) {
			this.val = val;
		}
	}

	@Test
	public void testReverseLinkedList() {
		Node head = new Node(0);
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);

		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;

		print(head);
		Node newHead = reverse(head);
		System.out.println();
		print(newHead);
	}

	private void print(Node head) {
		while(head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

	private Node reverse(Node head) {
		Node prev = null;
		Node curr = head;

		while(curr != null) {
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}

		return prev;
	}
}
