import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        int lengthsToBeTested[] = { 10,20,30,50};
        int percentageToUnlink[] = { 3, 6};

        for (int i : lengthsToBeTested) {

            for (int j : percentageToUnlink) {
                singleVsDoubleLinkedList(i, j);
            }
            System.out.println("");

        }
         //DoubleLinkedListTest();
        //LinkedListTest();
    }

    public static void singleVsDoubleLinkedList(int lengthOfList, int percentageToUnlink) {
        int numberOfAttempts = 1000;
        long minimumSingle = Long.MAX_VALUE;
        long minimumDouble = Long.MAX_VALUE;

        int array[] = new int[lengthOfList];
        int kArray[] = new int[lengthOfList];

        for (int i = 0; i < lengthOfList; i++) {
            array[i] = i+1;
        }

        kArray = keyArray(lengthOfList-(lengthOfList*percentageToUnlink/10));
        Cell singleArray[] = new Cell[lengthOfList];
        DoubleLinkedCell doubleArray[] = new DoubleLinkedCell[lengthOfList];

        for (int i = 0; i < lengthOfList; i++) {
            singleArray[i] = new Cell(array[i], null);
            doubleArray[i] = new DoubleLinkedCell(array[i], null, null);
        }

        for (int i = 0; i < numberOfAttempts; i++) {
            LinkedList singleList = new LinkedList();
            DoubleLinkedList doubleList = new DoubleLinkedList();
            for (int j = 0; j < lengthOfList; j++) {
                singleList.insert(singleArray[j]);
                //PrintLinkedList(singleList);
                doubleList.insert(doubleArray[j]);
            }

            long t0 = System.nanoTime();
            for (int j = 0; j < kArray.length; j++) {
                //PrintLinkedList(singleList);
                singleList.unlink(singleArray[kArray[j]]);
                //PrintLinkedList(singleList);
                singleList.insert(singleArray[kArray[j]]);

            }
            long t1 = System.nanoTime() - t0;
            if (t1 < minimumSingle) {
                minimumSingle = t1;
            }

            t0 = System.nanoTime();
            for (int j = 0; j < kArray.length; j++) {
                doubleList.unlink(doubleArray[kArray[j]]);
                doubleList.insert(doubleArray[kArray[j]]);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimumDouble) {
                minimumDouble = t1;
            }
        }
        System.out.println("Single List Minimum Time for size: " + lengthOfList + " for random elements: "
                + kArray.length + " = " + minimumSingle + " ns");
        System.out.println("Double List Minimum Time for size :" + lengthOfList + " for random elements :"
                +  kArray.length + " = " + minimumDouble + " ns");

    }

    public static int[] keyArray(int arraySize) {
        Random rnd = new Random();

        int[] key = new int[arraySize];

        for (int j = 0; j < key.length - 1; j++) {
            key[j] = rnd.nextInt(1 * arraySize);
        }

        return key;

    }

    public static void linkedListBenchmarkFixedVsVariableAppending(int lengthOfAppendingList,
            boolean fixedListToBeAppended) {
        LinkedList list = new LinkedList();
        Cell last = null;
        long t0 = 0;
        long t1 = 0;
        for (int i = lengthOfAppendingList; i > 0; i--) {
            last = new Cell(i, last);
        }
        list.firstCell = last;

        LinkedList list2 = new LinkedList();
        last = null;
        for (int i = 5; i > 0; i--) {
            last = new Cell(i, last);
        }
        list2.firstCell = last;

        if (fixedListToBeAppended) {
            System.out.println("Appedning fixed length with variable length list for size " + lengthOfAppendingList);

            t0 = System.nanoTime();
            list2.append(list);
            t1 = System.nanoTime() - t0;

            System.out.println("Minimum Time : " + t1 + " ns");
        } else {
            System.out.println("Appending variable length with fixed length list for size " + lengthOfAppendingList);

            t0 = System.nanoTime();
            list.append(list2);
            t1 = System.nanoTime() - t0;

            System.out.println("Minimum Time : " + t1 + "ns");

        }
    }

    public static int[] AppendArray(int ArrayToBeAppended[], int AppendingArray[]) {

        int length = ArrayToBeAppended.length + AppendingArray.length;
        int newArray[] = new int[length];

        for (int i = 0; i < newArray.length; i++) {
            if (i < ArrayToBeAppended.length) {
                newArray[i] = ArrayToBeAppended[i];
            } else {
                newArray[i] = AppendingArray[i - ArrayToBeAppended.length];
            }
        }

        return newArray;
    }

    public static void LinkedListTest() {
        LinkedList list = new LinkedList();
        Cell cell1 = new Cell(1, null);

        Cell cell2 = new Cell(2, null);

        Cell cell3 = new Cell(3, null);

        list.insert(cell3);
        list.insert(cell2);
        list.insert(cell1);

        PrintLinkedList(list);

        list.unlink(cell2);

        PrintLinkedList(list);
        list.insert(cell2);
        PrintLinkedList(list);
        list.unlink(cell3);
        list.insert(cell3);

        PrintLinkedList(list);
        /*list.insert(cell3);
        PrintLinkedList(list);*/



        /*
         * LinkedList list2 = new LinkedList();
         * list2.add(6);
         * list2.add(5);
         * list2.add(4);
         * 
         * list.append(list2);
         * PrintLinkedList(list);
         * 
         * System.out.println(list.find(2));
         * list.remove(2);
         * PrintLinkedList(list);
         * System.out.println(list.find(2));
         */

    }

    public static void DoubleLinkedListTest() {
        DoubleLinkedList list = new DoubleLinkedList();
        
        
        DoubleLinkedCell cell1 = new DoubleLinkedCell(1, null,null);

        DoubleLinkedCell cell2 = new DoubleLinkedCell(2, null,null);

        DoubleLinkedCell cell3 = new DoubleLinkedCell(3, null,null);
        
        list.insert(cell3);
        list.insert(cell2);
        list.insert(cell1);

        PrintDoubleLinkedList(list);

        list.unlink(cell2);

        PrintDoubleLinkedList(list);
        list.insert(cell2);
        PrintDoubleLinkedList(list);
        list.unlink(cell3);
        list.insert(cell2);
        PrintDoubleLinkedList(list);
        list.insert(cell3);
        PrintDoubleLinkedList(list);



        /*list.add(3);
        list.add(2);
        list.add(1);

        PrintDoubleLinkedList(list);

        DoubleLinkedList list2 = new DoubleLinkedList();
        list2.add(6);
        list2.add(5);
        list2.add(4);

        list.append(list2);
        PrintDoubleLinkedList(list);

        System.out.println(list.find(2));
        list.remove(2);
        PrintDoubleLinkedList(list);
        System.out.println(list.find(2));*/

    }

    public static void PrintLinkedList(LinkedList list) {
        Cell nextCell = list.getFirstCell();
        int index = 1;
        while (nextCell != null) {
            System.out.println("Element " + index + " : " + nextCell.getHead());
            nextCell = nextCell.tail;
            index++;
        }
        System.out.println("");
    }

    public static void PrintDoubleLinkedList(DoubleLinkedList list) {
        DoubleLinkedCell nextCell = list.getFirstCell();
        int index = 1;
        while (nextCell != null) {
            System.out.println("Element " + index + " : " + nextCell.getHead());
            nextCell = nextCell.tail;
            index++;
        }
        System.out.println("");
    }
}
