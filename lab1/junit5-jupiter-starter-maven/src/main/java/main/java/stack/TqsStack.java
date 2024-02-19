package main.java.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private List<T> stack;
    private int max_size;
    

    public TqsStack() {
        this.stack = new ArrayList<>();
        this.max_size = 100;
    }


    public TqsStack(int max_size) {
        this.stack = new ArrayList<>();
        this.max_size = max_size;
    }


    public boolean isEmpty() {
        return stack.size() == 0;
    }


    public void push(T stack_item) throws IllegalStateException{
        if (stack.size() == max_size ) {
            throw new IllegalStateException();
        }

        stack.add(stack_item);
    }

    public T pop() throws NoSuchElementException {

        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }

        return stack.remove(stack.size()-1);

    }

    public T peek() throws NoSuchElementException {

        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }

        return stack.get(0);
        
    }

    public int size() {
        return stack.size();
        
    }

}
