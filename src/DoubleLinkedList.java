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
        DoubleLinkedCell newFirstCell=new DoubleLinkedCell(item, this.firstCell,null);
        if (this.firstCell!=null) {
            
        this.firstCell.setPreviousCell(newFirstCell);
        }
        this.firstCell =newFirstCell;
    }

    void insert(DoubleLinkedCell newFirstCell) {
        if (this.firstCell!=null) {
                this.firstCell.setPreviousCell(newFirstCell);
        }
        newFirstCell.setPreviousCell(null);
        newFirstCell.setTail(this.firstCell);
        this.firstCell =newFirstCell;
    }

    int length() {
        int length = 0;
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt.tail != null) {
            nxt = nxt.tail;
            length++;
            System.out.println("Current Element "+length+" value"+nxt.getHead());
            if (length>10) {
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
        if (item.getPreviousCell()!=null) {
        item.getPreviousCell().setTail(item.getTail());
        }
        item.getTail().setPreviousCell(item.getPreviousCell());
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
