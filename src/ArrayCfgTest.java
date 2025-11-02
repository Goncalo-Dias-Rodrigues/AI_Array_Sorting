import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArrayCfgTest {

    @Test
    void testStringConstructor() {
        String input = "10 5 8 3";
        ArrayCfg cfg = new ArrayCfg(input);

        assertNotNull(cfg.data);
        assertArrayEquals(new int[]{10, 5, 8, 3}, cfg.data);
        assertEquals(0, cfg.cost);
    }

    @Test
    void testArrayAndCostConstructor() {
        int[] inputArray = {1, 2, 3, 4};
        int inputCost = 50;
        ArrayCfg cfg = new ArrayCfg(inputArray, inputCost);

        assertArrayEquals(inputArray, cfg.data);
        assertEquals(inputCost, cfg.cost);
    }

    @Test
    void testIsGoal() {
        ArrayCfg cfg1 = new ArrayCfg("1 2 3");
        ArrayCfg cfg2 = new ArrayCfg("1 2 3");
        ArrayCfg cfg3 = new ArrayCfg("3 2 1");

        assertTrue(cfg1.isGoal(cfg2));
        assertFalse(cfg1.isGoal(cfg3));
    }

    @Test
    void testEqualsAndHashCode() {
        ArrayCfg cfg1 = new ArrayCfg("8 4 2");
        ArrayCfg cfg2 = new ArrayCfg("8 4 2");
        ArrayCfg cfg3 = new ArrayCfg("1 2 3");

        // Teste de igualdade (equals)
        assertEquals(cfg1, cfg2);
        assertNotEquals(cfg1, cfg3);
        assertNotEquals(null, cfg1);

        // Teste de hashCode
        assertEquals(cfg1.hashCode(), cfg2.hashCode());
        assertNotEquals(cfg1.hashCode(), cfg3.hashCode());
    }

    @Test
    void testGetK() {
        ArrayCfg cfg = new ArrayCfg(new int[]{5, 6}, 15);
        assertEquals(15, cfg.getK());
    }

    @Test
    void testToString() {
        ArrayCfg cfg = new ArrayCfg("10 20 30");
        String expected = "10 20 30 ";
        assertEquals(expected, cfg.toString());
    }
}