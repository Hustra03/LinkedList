public class LinkedList {

    Cell firstCell;

    public LinkedList() {
        this.firstCell = null;
    }

    public Cell getFirstCell() {
        return this.firstCell;
    }

    void add(int item) {
        this.firstCell = new Cell(item, this.firstCell);
    }

    void insert(Cell newFirstCell) {
        newFirstCell.setTail(this.firstCell);
        this.firstCell = newFirstCell;
    }

    int length() {
        int length = 0;
        Cell nxt = this.firstCell;
        while (nxt != null) {
            nxt = nxt.tail;
            length++;
        }
        return length;
    }

    boolean find(int item) {
        Cell nxt = this.firstCell;
        while (nxt != null) {
            if (nxt.getHead() == item) {
                return true;
            }
            nxt = nxt.tail;
        }
        return false;
    }

    void remove(int item) {

        if (firstCell.getHead() != item) {

            Cell nxt = this.firstCell;
            Cell prv = null;
            while (nxt.tail != null) {
                prv = nxt;
                nxt = nxt.tail;
                if (nxt.getHead() == item) {
                    prv.setTail(nxt.getTail());
                    break;
                }
            }
        } else {
            this.firstCell = this.firstCell.getTail();
        }
    }

    void unlink(Cell item) {
            Cell nxt = this.firstCell;
            Cell prv = null;
            System.out.println("Unlinking"+item.getHead());
            while (nxt.tail != null) {
                System.out.println("Current "+nxt.getHead());
                prv = nxt;
                nxt = nxt.tail;
                if (nxt == item) {
                    prv.setTail(nxt.getTail());
                    break;
                }
                
            }
    }

    void append(LinkedList b) {
        Cell nxt = this.firstCell;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.setTail(b.getFirstCell());
    }

}
