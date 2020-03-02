package test;

import org.junit.Test;

public class ListReverse {

    class ListNode {
        int value;
        ListNode next;
        public ListNode(int value) {
            this.value = value;
        }
    }

    ListNode createList(Integer[] values) {
        ListNode root = new ListNode(values[0]);
        ListNode iter = root;
        for(int i=1; i<values.length; i++) {
            iter.next = new ListNode(values[i]);
            iter = iter.next;
        }
        return root;
    }

    @Test
    public void test_() {
        Integer[] values = {1, 2, 3, 4, 5, 6};
        ListNode root = createList(values);
        int k = 3;

        ListNode dummyHead = new ListNode(-1);
        ListNode begin = dummyHead;
        ListNode curr = begin.next;
        int i = 0;
        while(curr != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, curr.next);
                curr = begin.next;
            } else {
                curr = curr.next;
            }
        }
        //return dummyHead.next;
    }

    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode prev = end;
        ListNode first = begin.next;
        ListNode curr = begin.next;

        while(curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return first;
    }
}
