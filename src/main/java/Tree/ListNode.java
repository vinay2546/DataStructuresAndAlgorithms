package Tree;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int _val) {
        this.val = _val;
    }

    ListNode(int _val, ListNode _next) {
        this.val = _val;
        this.next = _next;
    }
}
