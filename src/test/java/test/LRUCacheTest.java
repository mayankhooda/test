package test;

import org.junit.Test;

import java.util.HashMap;

public class LRUCacheTest {

	@Test
	public void testLRUCache() {
		LRUCache cache = new LRUCache(2);

		cache.getPage(0);
		cache.getPage(1);
		cache.getPage(2);
		cache.getPage(3);
	}

	class LRUCache {
		private int size;
		private final int capacity;

		HashMap<Integer, ListNode> map = new HashMap<>();
		ListNode head, tail;

		LRUCache(int capacity) {
			this.capacity = capacity;
			this.size = 0;
		}

		public int getPage(int i) {
			if (map.containsKey(i)) {
				System.out.println("found " + i + " page in cache.");
				moveToFront(map.get(i));
				return i;
			} else {
				System.out.println("fetching " + i + " page to cache.");
				addToFront(i);
				return i;
			}
		}

		private void addToFront(int i) {
			ListNode node = new ListNode(i);
			if (head == null || tail == null) {
				head = node;
				tail = node;
				size = 1;
			} else if (size == capacity) {
				System.out.println("removing " + tail.val + " page from cache.");
				tail = tail.next;
				tail.prev.next = null;
				tail.prev = null;

				head.next = node;
				node.prev = head;
				head = node;
			} else {
				head.next = node;
				node.prev = head;
				head = node;
				size++;
			}
			map.put(i, node);
		}

		private void moveToFront(ListNode node) {
			node.next.prev = node.prev;
			node.prev.next = node.next;

			node.next = null;
			node.prev = head;
			head.next = node;
			head = node;
		}

		class ListNode {
			ListNode next;
			ListNode prev;
			int val;

			ListNode(int val) {
				this.val = val;
			}
		}
	}
}
