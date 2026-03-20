package structure;

import model.DisplayCase;

public class DisplayCaseList {
    public Node<DisplayCase> head;
    public int size;

    public void add(DisplayCase displayCase) {
        Node<DisplayCase> newNode = new Node<>(displayCase);
        if (head == null) {
            head = newNode;
        } else {
            Node<DisplayCase> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean remove(DisplayCase target) {
        if (head == null) return false;
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }
        Node<DisplayCase> current = head;
        while (current.next != null && current.next.data != target) {
            current = current.next;
        }
        if (current.next == null) return false;
        current.next = current.next.next;
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public Node<DisplayCase> getHead() {
        return head;
    }
}