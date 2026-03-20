package structure;

import model.Material;

public class MaterialList {
    public Node<Material> head;
    public int size;

    public void add(Material material) {
        Node<Material> newNode = new Node<>(material);
        if (head == null) {
            head = newNode;
        } else {
            Node<Material> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean remove(Material target) {
        if (head == null) return false;
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }
        Node<Material> current = head;
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

    public Node<Material> getHead() {
        return head;
    }
}