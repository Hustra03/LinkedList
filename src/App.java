
public class App {
    public static void main(String[] args) throws Exception {

        int lengthsToBeTested[] = { 100,250, 500, 1000, 10000, 100000 };

        for (int i : lengthsToBeTested) {

            linkedListBenchmarkFixedVsVariableAppending(i, true);
            linkedListBenchmarkFixedVsVariableAppending(i, false);

            System.out.println("Appedning array for size " + i);

            long t0 = System.nanoTime();
            AppendArray(new int[i / 2], new int[i / 2]);
            long t1 = System.nanoTime() - t0;

            System.out.println("Minimum Time : " + t1 + " ns");
            System.out.println("");

        }

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
        list.add(3);
        list.add(2);
        list.add(1);

        PrintLinkedList(list);

        LinkedList list2 = new LinkedList();
        list2.add(6);
        list2.add(5);
        list2.add(4);

        list.append(list2);
        PrintLinkedList(list);

        System.out.println(list.find(2));
        list.remove(2);
        PrintLinkedList(list);
        System.out.println(list.find(2));

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
}
