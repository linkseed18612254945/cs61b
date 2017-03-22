package homework.hw01.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void ArrayRingTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(20);
        arb.enqueue(10);
        arb.enqueue(1);
        assertEquals(20, (int) arb.peek());
        arb.dequeue();
        assertEquals(10, (int) arb.peek());
        arb.enqueue(3);
        assertEquals(10, (int) arb.peek());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
