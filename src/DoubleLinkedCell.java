public class DoubleLinkedCell {
        int head;
        DoubleLinkedCell tail;
        DoubleLinkedCell previousCell;
    
        DoubleLinkedCell(int val, DoubleLinkedCell tl,DoubleLinkedCell prv) {
        head = val;
        tail = tl;
        }

        public int getHead()
        {return this.head;}

        public DoubleLinkedCell getTail()
        {return this.tail;}

        public DoubleLinkedCell getPreviousCell()
        {return this.previousCell;}

        public void setTail(DoubleLinkedCell newTail)
        {
            this.tail=newTail;
        }

         public void setPreviousCell(DoubleLinkedCell newPrevious)
        {
            this.previousCell=newPrevious;
        }
    
}
