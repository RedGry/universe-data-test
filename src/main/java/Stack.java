import java.util.Arrays;

/**
 * Класс для ревью кода - реализация стека на основе массива с Element
 */
public class Stack {
    public Integer size = 0;
    public Element[] elements;

    public Stack() {
        elements = new Element[2];
    }

    public void push(Element o) {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, size + 1);
        elements[size++] = o;
    }

    public Element pop() {
        if (size == 0)
            throw new RuntimeException();
        return elements[--size];
    }

    public class Element {
    }
}