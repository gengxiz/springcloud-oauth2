package com.test;

public class Node {
    private int data;
    private Node next;

    public void append(int val){
        Node current = this;
        while (current.next != null){
            current = current.next;
        }

        current.next = new Node(val);
    }

    public void print(){
        System.out.println(data);
        Node node = this.next;
        while (node != null){
            System.out.println(node.getData());
            node = node.next;
        }
    }

    public void remove(int index){
        Node slow = this;
        Node fast = this;
        while (index > 0){
            fast = fast.next;
            index--;
        }
        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        System.out.println("===");
        System.out.println(slow.getData());
        System.out.println(fast.getData());
    }

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
