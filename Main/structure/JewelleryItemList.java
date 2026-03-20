package structure;

import model.JewelleryItem;

public class JewelleryItemList {
    private Node<JewelleryItem> head;
    private int size;

    public void add(JewelleryItem jewelleryItem) {
        Node<JewelleryItem> newNode = new Node<>(jewelleryItem);
        if (head == null) {
            head = newNode;
        } else {
            Node<JewelleryItem> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean remove(JewelleryItem target) {
        if (head == null) return false;
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }
        Node<JewelleryItem> current = head;
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

    public Node<JewelleryItem> getHead() {
        return head;
    }

    public Node<JewelleryItem> getHead() { return head; }
    public int size() { return size; }

}