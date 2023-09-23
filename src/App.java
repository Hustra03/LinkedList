import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        int lengthsToBeTested[] = { 100,250,500,1000,10000,100000};
        int percentageToUnlink[] = { 4, 5 };

        for (int i : lengthsToBeTested) {

            linkedListBenchmarkFixedVsVariableAppending(i);
            /*
             * for (int j : percentageToUnlink) {
             * singleVsDoubleLinkedList(i, j);
             * }
             */
            System.out.println("");

        }
        // DoubleLinkedListTest();
        // LinkedListTest();
    }

    public static void singleVsDoubleLinkedList(int lengthOfList, int percentageToUnlink) {
        int numberOfAttempts = 1000;
        long minimumSingle = Long.MAX_VALUE;
        long minimumDouble = Long.MAX_VALUE;

        int array[] = new int[lengthOfList];
        int kArray[] = new int[lengthOfList];

        for (int i = 0; i < lengthOfList; i++) {
            array[i] = i + 1;
        }

        kArray = keyArray(lengthOfList, percentageToUnlink);
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
                // PrintLinkedList(singleList);
                doubleList.insert(doubleArray[j]);
            }

            long t0 = System.nanoTime();
            for (int j = 0; j < kArray.length; j++) {
                // PrintLinkedList(singleList);
                singleList.unlink(singleArray[kArray[j]]);
                // PrintLinkedList(singleList);
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
                + kArray.length + " = " + minimumDouble + " ns");

    }

    public static int[] keyArray(int arraySize, int percentageToUnlink) {
        Random rnd = new Random();

        int[] key = new int[arraySize - (arraySize * percentageToUnlink / 10)];

        for (int j = 0; j < key.length - 1; j++) {
            key[j] = rnd.nextInt(arraySize);
        }

        return key;

    }

    public static void linkedListBenchmarkFixedVsVariableAppending(int lengthOfAppendingList) {

        int numberOfAttempts = 10;
        long minimum1 = Long.MAX_VALUE;
        long minimum2 = Long.MAX_VALUE;
        long minimum3 = Long.MAX_VALUE;

        for (int j = 0; j < (numberOfAttempts * 3); j++) {

            LinkedList list = new LinkedList();
            Cell last = null;
            long t0 = 0;
            long t1 = 0;
            for (int i = lengthOfAppendingList-5; i > 0; i--) {
                last = new Cell(i, last);
            }
            list.firstCell = last;

            LinkedList list2 = new LinkedList();
            last = null;
            for (int i = 5; i > 0; i--) {
                last = new Cell(i, last);
            }
            list2.firstCell = last;

            if (j % 3 == 0) {

                t0 = System.nanoTime();
                list2.append(list);
                t1 = System.nanoTime() - t0;
                if (t1 < minimum1) {
                    minimum1 = t1;
                }
            }

            if (j % 3 == 1) {

                t0 = System.nanoTime();
                list.append(list2);
                t1 = System.nanoTime() - t0;
                if (t1 < minimum2) {
                    minimum2 = t1;
                }
            }
            if (j % 3 == 2) {

                int array1[]=keyArray(lengthOfAppendingList/2, 0);
                int array2[]=keyArray(lengthOfAppendingList/2, 0);
                t0 = System.nanoTime();
                int arrayAppended[]=AppendArray(array1, array2);
                t1 = System.nanoTime() - t0;
                if (t1 < minimum3) {
                    minimum3 = t1;
                }
            }

        }

        System.out.println("Appedning fixed length with variable length list for size " + lengthOfAppendingList
                + " Minimum Time : " + minimum1 + " ns");

        System.out.println("Appedning variable length with fixed length list for size " + lengthOfAppendingList
                + " Minimum Time : " + minimum2 + " ns");

        System.out.println("Appedning array length with array  for size " + lengthOfAppendingList + " Minimum Time : "
                + minimum3 + " ns");

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
        long t0 = System.nanoTime();
        for (int i = 0; i < 14; i++) {

            list.unlink(cell3);
            list.insert(cell3);
        }
        System.out.println("Time:" + (System.nanoTime() - t0));

        /*
         * list.insert(cell3);
         * list.insert(cell2);
         * list.insert(cell1);
         * 
         * PrintLinkedList(list);
         * 
         * list.unlink(cell2);
         * 
         * PrintLinkedList(list);
         * list.insert(cell2);
         * PrintLinkedList(list);
         * list.unlink(cell3);
         * list.insert(cell3);
         * 
         */// PrintLinkedList(list);
        /*
         * list.insert(cell3);
         * PrintLinkedList(list);
         */

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

        DoubleLinkedCell cell1 = new DoubleLinkedCell(1, null, null);

        DoubleLinkedCell cell2 = new DoubleLinkedCell(2, null, null);

        DoubleLinkedCell cell3 = new DoubleLinkedCell(3, null, null);

        /*
         * long t0=System.nanoTime();
         * for (int i = 0; i < 14; i++) {
         * 
         * list.unlink(cell3);
         * list.insert(cell3);
         * }
         * System.out.println("Time:"+(System.nanoTime()-t0));
         */

        list.insert(cell3);
        list.insert(cell2);
        list.insert(cell1);

        System.out.print(list.length());
        PrintDoubleLinkedList(list);

        list.unlink(cell2);

        PrintDoubleLinkedList(list);
        list.insert(cell2);
        PrintDoubleLinkedList(list);
        list.unlink(cell3);
        PrintDoubleLinkedList(list);
        list.insert(cell3);
        PrintDoubleLinkedList(list);

        /*
         * list.add(3);
         * list.add(2);
         * list.add(1);
         * 
         * PrintDoubleLinkedList(list);
         * 
         * DoubleLinkedList list2 = new DoubleLinkedList();
         * list2.add(6);
         * list2.add(5);
         * list2.add(4);
         * 
         * list.append(list2);
         * PrintDoubleLinkedList(list);
         * 
         * System.out.println(list.find(2));
         * list.remove(2);
         * PrintDoubleLinkedList(list);
         * System.out.println(list.find(2));
         */

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
