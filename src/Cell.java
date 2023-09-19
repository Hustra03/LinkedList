    public class Cell {
        int head;
        Cell tail;
    
        Cell(int val, Cell tl) {
        head = val;
        tail = tl;
        }

        public int getHead()
        {return this.head;}

        public Cell getTail()
        {return this.tail;}

        public void setTail(Cell newTail)
        {
            this.tail=newTail;
        }
    }
