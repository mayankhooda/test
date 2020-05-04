package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PermutationTest {

	@Test
	public void testPermutation() {
		String str = "hooda";

		Map<Character, Integer> map = new HashMap<>();
		map.put('a', null);

		assertEquals(null, map.get('a'));

		// get counts of all characters
		for (Character c : str.toCharArray()) {
			if (!map.containsKey(c))
				map.put(c, 1);
			else
				map.put(c, map.get(c)+1);
		}

		permute(map, str.length(), new StringBuffer());
	}

	private void permute(Map<Character, Integer> map, int n, StringBuffer buffer) {
		// base case, all characters considered
		if (n == 0)
			System.out.println(buffer.toString());

		// add every 'available' character
		for (Character c : map.keySet()) {

			// character unavailable
			if (map.get(c) == 0)
				continue;

			// add to buffer and decrement count
			buffer.append(c);
			map.put(c, map.get(c)-1);

			// recursively do this for n length
			permute(map, n-1, buffer);

			// undo or 'backtrack' selection of this character
			map.put(c, map.get(c)+1);
			buffer.deleteCharAt(buffer.length()-1);
		}
	}
}
