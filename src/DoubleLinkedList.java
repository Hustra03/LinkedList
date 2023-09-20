public class DoubleLinkedList {

    DoubleLinkedCell firstCell;

    public DoubleLinkedList() {
        this.firstCell = null;
    }

    public DoubleLinkedCell getFirstCell() {
        return this.firstCell;
    }

    public void setFirstCell(DoubleLinkedCell NewFirstCell) {
        this.firstCell = NewFirstCell;
    }

    void add(int item) {
        DoubleLinkedCell newFirstCell = new DoubleLinkedCell(item, this.firstCell, null);
        if (this.firstCell != null) {

            this.firstCell.setPreviousCell(newFirstCell);
        }
        this.firstCell = newFirstCell;
    }

    void insert(DoubleLinkedCell newFirstCell) {
        if (this.firstCell != null && this.firstCell != newFirstCell) {
            this.firstCell.setPreviousCell(newFirstCell);
        }
        newFirstCell.setPreviousCell(null);

        if (this.firstCell != newFirstCell) {

            newFirstCell.setTail(this.firstCell);
        } else {
            newFirstCell.setTail(this.firstCell.getTail());
        }
        this.firstCell = newFirstCell;
    }

    int length() {
        int length = 0;
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt.tail != null) {
            nxt = nxt.tail;
            length++;

            if (length > 10) {
                break;
            }
        }
        return length;
    }

    boolean find(int item) {
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt.tail != null) {
            nxt = nxt.tail;
            if (nxt.getHead() == item) {
                return true;
            }
        }
        return false;
    }

    void remove(int item) {

        if (firstCell.getHead() != item) {

            DoubleLinkedCell nxt = this.firstCell;
            while (nxt.tail != null) {
                nxt = nxt.tail;
                if (nxt.getHead() == item) {
                    nxt.previousCell.setTail(nxt.getTail());
                    nxt.getTail().setPreviousCell(nxt.previousCell);
                    break;
                }
            }
        } else {
            this.firstCell = this.firstCell.getTail();
        }
    }

    void unlink(DoubleLinkedCell item) {
        DoubleLinkedCell nxt = this.firstCell;
        DoubleLinkedCell prv = null;

        if (firstCell != item) {

            while (nxt.tail != null) {
                prv = nxt;
                nxt = nxt.tail;
                if (nxt == item) {
                    DoubleLinkedCell realTail = nxt;
                    while (realTail == item) {
                        realTail = realTail.getTail();
                    }
                    prv.setTail(realTail);
                    if (realTail != null) {
                        realTail.setPreviousCell(prv);
                    }
                    break;
                }

            }
        } else {
            if (firstCell == null) {
                this.firstCell = null;
            } else {
                this.firstCell = this.firstCell.getTail();
            }
        }
    }

    public void append(DoubleLinkedList b) {
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.setTail(b.getFirstCell());
        nxt.getTail().setPreviousCell(nxt);
    }
}
