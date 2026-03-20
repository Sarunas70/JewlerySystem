package structure;

import model.DisplayTray;

public class DisplayTrayList {
    private Node<DisplayTray> head;
    private int size;

    public void add(DisplayTray displayTray) {
        Node<DisplayTray> newNode = new Node<>(displayTray);
        if (head == null) {
            head = newNode;
        } else {
            Node<DisplayTray> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean remove(DisplayTray target) {
        if (head == null) return false;
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }
        Node<DisplayTray> current = head;
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

    public Node<DisplayTray> getHead() {
        return head;
    }
}