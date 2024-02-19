package test.java.stack;

import main.java.stack.TqsStack;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TqsStackTest {
    
    TqsStack<String> w_stack;

    @BeforeEach
    void setUp() {
        w_stack = new TqsStack<>();
    }

    @DisplayName("A stack is empty on construction.")
    @Test
    void testA() {
        assertTrue(w_stack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction")
    @Test
    void testB() {
        assertTrue(w_stack.size() == 0);
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void testC() {
        Assertions.assertAll(
            () -> {
                w_stack.push("1");
                assertFalse(w_stack.isEmpty());
            },
            () -> {
                w_stack.push("2");
                assertEquals(2, w_stack.size());
            }
        );
    }

    @DisplayName("If one pushes x then pops, the value popped is x")
    @Test
    void testD() {
        Assertions.assertAll(
            () -> {
                w_stack.push("x");
            },
            () -> {
                assertEquals("x",w_stack.pop());
            }
        );
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void testE() {
        Assertions.assertAll(
            () -> {
                w_stack.push("x");
            },
            () -> {
                assertEquals("x",w_stack.peek());
            },
            () -> {
                assertEquals(1, w_stack.size());
            }
        );
    }
    
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void testF() {
        Assertions.assertAll(
            () -> {
                w_stack.push("1");
                w_stack.push("2");
                w_stack.push("3");
                assertEquals(3 , w_stack.size());
                w_stack.pop();
                w_stack.pop();
                w_stack.pop();
            },
            () -> {
                assertEquals(0,w_stack.size());
                assertTrue(w_stack.isEmpty());
            }
        );
    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void testG() {
        assertTrue(w_stack.isEmpty());
        assertThrows(java.util.NoSuchElementException.class, () -> {
            w_stack.pop();
        });
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void testH() {
        assertTrue(w_stack.isEmpty());
        assertThrows(java.util.NoSuchElementException.class, () -> {
            w_stack.peek();
        });
    }

    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    void testI() {
        TqsStack<String> maxsize_stack = new TqsStack<>(3);
        maxsize_stack.push("1");
        maxsize_stack.push("2");
        maxsize_stack.push("3");
        assertThrows(java.lang.IllegalStateException.class, () -> {
            maxsize_stack.push("4");
        });

    
    }
}


    


