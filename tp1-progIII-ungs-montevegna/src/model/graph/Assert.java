package model.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class Assert {

	public static void equalsSet(int[] expected, Set<Integer> result) {
		assertEquals(expected.length, result.size());

		for (int i = 0; i < expected.length; ++i)
			assertTrue(result.contains(expected[i]));
	}
}
