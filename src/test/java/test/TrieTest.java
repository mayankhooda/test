package test;

import org.junit.Test;

import java.util.*;

public class TrieTest {

	class Event {
		String badgeId;
		boolean isEntry;
		int timestamp;
	}

	@Test
	public void test1() {
		int[] arr = {0, 0, 1, 0, 0, 0};

		List<Integer> sizesOfZeroArrays = new ArrayList<>();
		int currSum = 0;
		for (int i=0; i<arr.length; i++) {
			if (arr[i] == 0) {
				currSum++;
			} else if (currSum != 0){
				sizesOfZeroArrays.add(currSum);
				currSum = 0;
			}
		}
		if (currSum != 0)
			sizesOfZeroArrays.add(currSum);

		int valid = 0;
		for (int size : sizesOfZeroArrays) {
			valid += (size * (size-1)) / 2 + size;
		}

		System.out.println(valid);
	}

	@Test
	public void test() {
		String[] arr = {"hello", "hell", "abc"};
		Trie trie = new Trie();

		TreeSet<Integer> set = new TreeSet<>();
		SortedSet<Integer> sortedSet = new TreeSet<>();

		for (String str : arr) {
			if (trie.insert(str)) {
				System.out.println("true");
			}
		}
	}

	class TrieNode {
		int val;
		boolean isTerminal;
		TrieNode[] children;

		TrieNode(int val) {
			this.val = val;
			this.isTerminal = false;
			this.children = new TrieNode[26];
		}
	}

	class Trie {
		private final TrieNode root;

		Trie() {
			this.root = new TrieNode(0);
		}

		boolean insert(String data) {
			TrieNode curr = root;
			boolean isPathExists = true;

			for (Character c : data.toCharArray()) {
				int idx = c - 'a';

				if (curr.children[idx] == null) {
					curr.children[idx] = new TrieNode(0);
					isPathExists = false;
				}

				curr = curr.children[idx];
				if (curr.isTerminal) {
					return true;
				}
			}

			curr.isTerminal = true;
			curr.val++;

			return isPathExists;
		}


	}
}
