package nl.vkb.ingredients;

import nl.vkb.ingredients.core.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Those are some very very simple tests. Will be expanded later with assertTrows checks.
 */
class ActionTest {
	@Test
	void addTest() {
		assertEquals(25, Action.ADD.action(10,15));
	}
	@Test
	void setTest() {
		assertEquals(15,Action.SET.action(10,15));
	}
}
