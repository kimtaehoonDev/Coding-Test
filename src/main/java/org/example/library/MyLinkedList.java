package org.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyLinkedList<E> {

    public static void main(String[] args) {
        // case1
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addFirst("1");
        list.addFirst("0");
        List<String> results = list.changeToList();
        if (!results.get(0).equals("0") ||
            !results.get(1).equals("1") ||
            results.size() != 2 ||
            list.size != 2 ||
            !list.last.val.equals("1") ||
            !list.first.val.equals("0")
        ) {
            throw new IllegalArgumentException("Case 1 fail");
        } else {
            System.out.println("case 1 success");
        }

        // case2
        list = new MyLinkedList<>();
        list.addFirst("1");
        if (list.size != 1 ||
            !list.first.val.equals("1") ||
            !list.last.val.equals("1")
        ) {
            throw new IllegalArgumentException("Case 2 fail");
        } else {
            System.out.println("case 2 success");
        }

        // case3
        list = new MyLinkedList<>();
        list.addLast("1");
        list.addLast("0");
        results = list.changeToList();
        if (!results.get(0).equals("1") ||
            !results.get(1).equals("0") ||
            results.size() != 2 ||
            list.size != 2 ||
            !list.last.val.equals("0") ||
            !list.first.val.equals("1")
        ) {
            throw new IllegalArgumentException("Case 3 fail");
        } else {
            System.out.println("case 3 success");
        }

        // case4
        list = new MyLinkedList<>();
        list.addLast("1");
        if (list.size != 1 ||
            !list.first.val.equals("1") ||
            !list.last.val.equals("1")
        ) {
            throw new IllegalArgumentException("Case 4 fail");
        } else {
            System.out.println("case 4 success");
        }

        // case 5
        list = new MyLinkedList<>();
        list.addLast("1");
        try {
            list.get(-1);
            throw new IllegalArgumentException("Case 5 fail");
        } catch (RuntimeException e) {
            System.out.println("case 5 success");
        }

        // case 6
        list = new MyLinkedList<>();
        list.addLast("1");
        try {
            list.get(1);
            throw new IllegalArgumentException("Case 6 fail");
        } catch (RuntimeException e) {
            System.out.println("case 6 success");
        }

        // case 7
        list = new MyLinkedList<>();
        list.addLast("1");
        list.addLast("2");
        if (!Objects.equals(list.get(0), "1") ||
            !Objects.equals(list.get(1), "2")
        ) {
            throw new IllegalArgumentException("Case 7 fail");
        } else {
            System.out.println("case 7 success");
        }

        // case 8
        list = new MyLinkedList<>();
        try {
            list.get(0);
            throw new IllegalArgumentException("Case 8 fail");
        } catch (RuntimeException e) {
            System.out.println("case 8 success");
        }

        // case 9 // 원소 하나일 때 삭제
        list = new MyLinkedList<>();
        list.addLast("1");
        String removedValue = list.remove(0);
        if (removedValue.equals("1") &&
            list.size == 0 &&
            list.first == null &&
            list.last == null &&
            list.changeToList().size() == 0
        ) {
            System.out.println("Case 9 success");
        } else {
            throw new IllegalArgumentException("Case 9 Fail");
        }

        // case 10 원소 n개일때, first 삭제
        list = new MyLinkedList<>();
        list.addLast("1");
        list.addLast("2");
        removedValue = list.remove(0);
        if (removedValue.equals("1") &&
            list.size == 1 &&
            list.first.val.equals("2") &&
            list.last.val.equals("2") &&
            list.changeToList().size() == 1
        ) {
            System.out.println("Case 10 success");
        } else {
            throw new IllegalArgumentException("Case 10 Fail");
        }

        // case 11 원소 n개일때, last 삭제
        list = new MyLinkedList<>();
        list.addLast("1");
        list.addLast("2");
        removedValue = list.remove(1);
        if (removedValue.equals("2") &&
            list.size == 1 &&
            list.first.val.equals("1") &&
            list.last.val.equals("1") &&
            list.changeToList().size() == 1
        ) {
            System.out.println("Case 11 success");
        } else {
            throw new IllegalArgumentException("Case 11 Fail");
        }

        // case 12 원소 n개일때, 중간 node 삭제
        list = new MyLinkedList<>();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        removedValue = list.remove(1);
        if (removedValue.equals("2") &&
            list.size == 2 &&
            list.first.val.equals("1") &&
            list.last.val.equals("3") &&
            list.changeToList().size() == 2
        ) {
            System.out.println("Case 12 success");
        } else {
            throw new IllegalArgumentException("Case 12 Fail");
        }

        // case 13
        list = new MyLinkedList<>();
        list.addLast("1");
        list.addLast("2");

        if (list.contains("1") == true &&
            list.contains("not found") == false
        ) {
            System.out.println("Case 13 success");
        } else {
            throw new IllegalArgumentException("Case 13 Fail");
        }
    }

    private Node<E> first;

    private Node<E> last;

    private int size = 0;

    public void addFirst(E element) {
        if (first == null) {
            this.first = new Node<>(element);
            this.last = this.first;
            this.size = 1;
            return;
        }
        Node<E> created = new Node<>(element);
        this.first.prev = created;
        created.next = this.first;
        this.first = created;
        this.size++;
    }

    public void addLast(E element) {
        if (first == null) {
            addFirst(element);
            return;
        }
        Node<E> created = new Node<>(element);
        this.last.next = created;
        created.prev = this.last;
        this.last = created;
        this.size++;
    }

    public E get(int idx) {
        return getNode(idx).val;
    }

    // idx번째 원소를 삭제한다.
    public E remove(int idx) {
        Node<E> target = getNode(idx);

        if (size == 1) { // 원소가 한개짜리였다면?
            this.first = null;
            this.last = null;
        } else if (target == first) { // 삭제하는 원소가 first라면?
            this.first = target.next;
            this.first.prev = null;
        } else if (target == last) { // 삭제하는 원소가 last라면?
            this.last = target.prev;
            this.last.next = null;
        } else {
            Node<E> prev = target.prev;
            Node<E> next = target.next;

            prev.next = next;
            next.prev = prev;
        }
        target.prev = null;
        target.next = null;
        size--;
        return target.val;
    }

    // idx번째 노드를 찾는 헬퍼메서드
    private Node<E> getNode(int idx) {
        if (size < 0 || size <= idx) {
            throw new RuntimeException("out of range");
        }
        if (first == null) {
            throw new RuntimeException("out of range");
        }
        // idx번째 원소를 찾는다
        Node<E> temp = first;
        for (int i = 0; i < idx; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean contains(String val) {
        if (val == null) {
            throw new RuntimeException();
        }
        for (E each : changeToList()) {
            if (Objects.equals(each, val)) {
                return true;
            }
        }
        return false;
    }

    // first부터 last까지 String으로 바꾼다.
    public List<E> changeToList() {
        List<E> values = new ArrayList<>();
        Node temp = first;
        while (temp != null) {
            values.add((E) temp.val);
            temp = temp.next;
        }
        return values;
    }

    static class Node<E> {

        E val;
        Node<E> next;
        Node<E> prev;

        public Node(E val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}

//    public static void main(String[] args) {
//        // case1
//        MyLinkedList list = new MyLinkedList();
//        list.addFirst("1");
//        list.addFirst("0");
//        List<String> results = list.changeToList();
//        if (!results.get(0).equals("0") ||
//            !results.get(1).equals("1") ||
//            results.size() != 2 ||
//            list.size != 2 ||
//            !list.last.val.equals("1") ||
//            !list.first.val.equals("0")
//        ) {
//            throw new IllegalArgumentException("Case 1 fail");
//        } else {
//            System.out.println("case 1 success");
//        }
//
//        // case2
//        list = new MyLinkedList();
//        list.addFirst("1");
//        if (list.size != 1 ||
//            !list.first.val.equals("1") ||
//            !list.last.val.equals("1")
//        ) {
//            throw new IllegalArgumentException("Case 2 fail");
//        } else {
//            System.out.println("case 2 success");
//        }
//
//        // case3
//        list = new MyLinkedList();
//        list.addLast("1");
//        list.addLast("0");
//        results = list.changeToList();
//        if (!results.get(0).equals("1") ||
//            !results.get(1).equals("0") ||
//            results.size() != 2 ||
//            list.size != 2 ||
//            !list.last.val.equals("0") ||
//            !list.first.val.equals("1")
//        ) {
//            throw new IllegalArgumentException("Case 3 fail");
//        } else {
//            System.out.println("case 3 success");
//        }
//
//        // case4
//        list = new MyLinkedList();
//        list.addLast("1");
//        if (list.size != 1 ||
//            !list.first.val.equals("1") ||
//            !list.last.val.equals("1")
//        ) {
//            throw new IllegalArgumentException("Case 4 fail");
//        } else {
//            System.out.println("case 4 success");
//        }
//
//        // case 5
//        list = new MyLinkedList();
//        list.addLast("1");
//        try {
//            list.get(-1);
//            throw new IllegalArgumentException("Case 5 fail");
//        } catch(RuntimeException e) {
//            System.out.println("case 5 success");
//        }
//
//        // case 6
//        list = new MyLinkedList();
//        list.addLast("1");
//        try {
//            list.get(1);
//            throw new IllegalArgumentException("Case 6 fail");
//        } catch(RuntimeException e) {
//            System.out.println("case 6 success");
//        }
//
//        // case 7
//        list = new MyLinkedList();
//        list.addLast("1");
//        list.addLast("2");
//        if (!Objects.equals(list.get(0), "1") ||
//            !Objects.equals(list.get(1), "2")
//        ) {
//            throw new IllegalArgumentException("Case 7 fail");
//        } else {
//            System.out.println("case 7 success");
//        }
//
//        // case 8
//        list = new MyLinkedList();
//        try {
//            list.get(0);
//            throw new IllegalArgumentException("Case 8 fail");
//        } catch(RuntimeException e) {
//            System.out.println("case 8 success");
//        }
//
//        // case 9 // 원소 하나일 때 삭제
//        list = new MyLinkedList();
//        list.addLast("1");
//        String removedValue = list.remove(0);
//        if (removedValue.equals("1") &&
//            list.size == 0 &&
//            list.first == null &&
//            list.last == null &&
//            list.changeToList().size() == 0
//        ) {
//            System.out.println("Case 9 success");
//        } else {
//            throw new IllegalArgumentException("Case 9 Fail");
//        }
//
//        // case 10 원소 n개일때, first 삭제
//        list = new MyLinkedList();
//        list.addLast("1");
//        list.addLast("2");
//        removedValue = list.remove(0);
//        if (removedValue.equals("1") &&
//            list.size == 1 &&
//            list.first.val.equals("2") &&
//            list.last.val.equals("2") &&
//            list.changeToList().size() == 1
//        ) {
//            System.out.println("Case 10 success");
//        } else {
//            throw new IllegalArgumentException("Case 10 Fail");
//        }
//
//        // case 11 원소 n개일때, last 삭제
//        list = new MyLinkedList();
//        list.addLast("1");
//        list.addLast("2");
//        removedValue = list.remove(1);
//        if (removedValue.equals("2") &&
//            list.size == 1 &&
//            list.first.val.equals("1") &&
//            list.last.val.equals("1") &&
//            list.changeToList().size() == 1
//        ) {
//            System.out.println("Case 11 success");
//        } else {
//            throw new IllegalArgumentException("Case 11 Fail");
//        }
//
//        // case 12 원소 n개일때, 중간 node 삭제
//        list = new MyLinkedList();
//        list.addLast("1");
//        list.addLast("2");
//        list.addLast("3");
//        removedValue = list.remove(1);
//        if (removedValue.equals("2") &&
//            list.size == 2 &&
//            list.first.val.equals("1") &&
//            list.last.val.equals("3") &&
//            list.changeToList().size() == 2
//        ) {
//            System.out.println("Case 12 success");
//        } else {
//            throw new IllegalArgumentException("Case 12 Fail");
//        }
//
//        // case 13
//        list = new MyLinkedList();
//        list.addLast("1");
//        list.addLast("2");
//
//        if (list.contains("1") == true &&
//            list.contains("not found") == false
//        ) {
//            System.out.println("Case 13 success");
//        } else {
//            throw new IllegalArgumentException("Case 13 Fail");
//        }
//    }