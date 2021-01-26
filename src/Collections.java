import java.util.*;
import java.util.function.Function;

public class Collections {
    public ArrayList<String> arrayList;
    public LinkedList<String> linkedList;
    public TreeSet<String> treeSet;
    public HashMap<String, String> hashMap;
    public TreeMap<String, String> treeMap;
    public PriorityQueue<String> priorityQueue;
    public ArrayDeque<String> arrayDeque;
    private final String[] testingArray = new String[]{"bcda", "cdab", "abcd"};

    public Collections() {
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>();
        treeMap = new TreeMap<>();
        priorityQueue = new PriorityQueue<>();
        arrayDeque = new ArrayDeque<>();

        addItems();

    }

    private void addItems() {
        for (String x : testingArray) {
            arrayList.add(x);
            linkedList.add(x);
            treeSet.add(x);
            hashMap.put(x, x);
            treeMap.put(x, x);
            priorityQueue.add(x);
            arrayDeque.add(x);
        }
    }

    public void runAllTests() {
        testOrder();
        testSorted();
        testSameValues();
        testNull();
    }

    /**
     * Jak se jmenuje metoda pro odebrani jednoho prvku?
     * Jake ma vstupy?
     * <p>
     * Nebudu spoustet jen ukazuji ze IDE nevyhazuje zadne syntaxicke chyby
     */
    private void removeElement() {
        Object x = new Object();
        arrayList.remove(0);
        arrayList.remove(x);
        linkedList.remove(0);
        linkedList.remove(x);
        linkedList.removeFirst();
        linkedList.removeLast();
        treeSet.remove(x);
        hashMap.remove(x);
        hashMap.remove(x, x);
        treeMap.remove(x, x);
        treeMap.remove(x, x);
        priorityQueue.remove();
        priorityQueue.poll();
        priorityQueue.remove(x);
        arrayDeque.remove();
        arrayDeque.poll();
        arrayDeque.remove(x);
        arrayDeque.pop();
    }

    /***
     * Jsou hodnoty ulozene v serazenem poradi od nejmensiho po nejvetsi?
     */
    private void testSorted() {
        String[] copy = Arrays.copyOf(testingArray, testingArray.length);
        Arrays.sort(copy);
        ConsoleColors.printBlue("Collections.testSorted");
        compareCollectionToArray(copy);
    }

    private void compareCollectionToArray(String[] copy) {

        ArrayList<String> saveOfPriorityQueue=new ArrayList<>();
        while(priorityQueue.size()>0){
            saveOfPriorityQueue.add(priorityQueue.poll());
        }

        for (int i = 0; i < copy.length; i++) {
            String x = copy[i];
            ConsoleColors.printYellow("Testing " + x);
            Object y;
            y = arrayList.get(i);
            if (!x.equals(y)) {
                ConsoleColors.printRed("arrayList - fail:" + i + ", " + y.toString());
            }
            y = linkedList.get(i);
            if (!x.equals(y)) {
                ConsoleColors.printRed("linkedList - fail:" + i + ", " + y.toString());
            }
            y = treeSet.toArray()[i];
            if (!x.equals(y)) {
                ConsoleColors.printRed("treeSet - fail:" + i + ", " + y.toString());
            }
            y = hashMap.keySet().toArray()[i];
            if (!x.equals(y)) {
                ConsoleColors.printRed("hashMap - fail:" + i + ", " + y.toString());
            }
            y = treeMap.keySet().toArray()[i];
            if (!x.equals(y)) {
                ConsoleColors.printRed("treeMap - fail:" + i + ", " + y.toString());
            }

            y = saveOfPriorityQueue.get(i);
            if (!x.equals(y)) {
                ConsoleColors.printRed("priorityQueue - fail:" + i + ", " + y.toString());
            }

            y = arrayDeque.toArray()[i];
            if (!x.equals(y)) {
                ConsoleColors.printRed("arrayDeque - fail:" + i + ", " + y.toString());
            }
        }
        for (String s : saveOfPriorityQueue) {
            priorityQueue.add(s);
        }
    }

    /***
     * Je zachovano poradi, ve kterem jsou hodnoty vkladany?
     */
    private void testOrder() {
        ConsoleColors.printBlue("Collections.testOrder");
        compareCollectionToArray(testingArray);
    }

    /***
     * Je mozne ulozit dve stejne hodnoty?
     */
    private void testSameValues() {
        int sizeBefore;

        ConsoleColors.printBlue("Collections.testSameValues");
        String x = testingArray[0];
        String y = "m";
        String z = "n";

        sizeBefore = arrayList.size();
        arrayList.add(x);
        if (arrayList.size() == sizeBefore) {
            ConsoleColors.printRed("ArrayList - fail");
        }

        sizeBefore = linkedList.size();
        linkedList.add(x);
        if (linkedList.size() == sizeBefore) {
            ConsoleColors.printRed("linkedList - fail");
        }

        sizeBefore = treeSet.size();
        treeSet.add(x);
        if (treeSet.size() == sizeBefore) {
            ConsoleColors.printRed("treeSet - fail");
        }

        sizeBefore = hashMap.size();
        hashMap.put(x, x);
        if (hashMap.size() == sizeBefore) {
            ConsoleColors.printRed("hashMap key+value - fail");
        }

        sizeBefore = treeMap.size();
        treeMap.put(x, x);
        if (treeMap.size() == sizeBefore) {
            ConsoleColors.printRed("treeMap key+value - fail");
        }

        sizeBefore = hashMap.size();
        hashMap.put(z, x);
        if (hashMap.size() == sizeBefore) {
            ConsoleColors.printRed("hashMap value - fail");
        }

        sizeBefore = treeMap.size();
        treeMap.put(z, x);
        if (treeMap.size() == sizeBefore) {
            ConsoleColors.printRed("treeMap value - fail");
        }

        sizeBefore = hashMap.size();
        hashMap.put(x, y);
        if (hashMap.size() == sizeBefore) {
            ConsoleColors.printRed("hashMap key - fail");
        }

        sizeBefore = treeMap.size();
        treeMap.put(x, y);
        if (treeMap.size() == sizeBefore) {
            ConsoleColors.printRed("treeMap key - fail");
        }

        sizeBefore = priorityQueue.size();
        priorityQueue.add(x);
        if (priorityQueue.size() == sizeBefore) {
            ConsoleColors.printRed("priorityQueue - fail");
        }

        sizeBefore = arrayDeque.size();
        arrayDeque.add(x);
        if (arrayDeque.size() == sizeBefore) {
            ConsoleColors.printRed("arrayDeque - fail");
        }
    }

    /***
     * Je mozne vlozit hodnotu null?
     */
    private void testNull() {
        int sizeBefore;

        ConsoleColors.printBlue("Collections.testNull");
        String x = null;
        String y = "testNull";

        sizeBefore = arrayList.size();
        arrayList.add(x);
        if (arrayList.size() == sizeBefore) {
            ConsoleColors.printRed("ArrayList - fail");
        }

        sizeBefore = linkedList.size();
        linkedList.add(x);
        if (linkedList.size() == sizeBefore) {
            ConsoleColors.printRed("linkedList - fail");
        }

        sizeBefore = treeSet.size();
        try {
            treeSet.add(x);
        } catch (Exception e) {
            ConsoleColors.printRed("treeSet - exception:" + e.getMessage());
        }
        if (treeSet.size() == sizeBefore) {
            ConsoleColors.printRed("treeSet - fail");
        }

        sizeBefore = hashMap.size();
        hashMap.put(x, x);
        if (hashMap.size() == sizeBefore) {
            ConsoleColors.printRed("hashMap key+value - fail");
        } else {
            hashMap.remove(x);
        }

        sizeBefore = hashMap.size();
        hashMap.put(x, y);
        if (hashMap.size() == sizeBefore) {
            ConsoleColors.printRed("hashMap key - fail");
        } else {
            hashMap.remove(x);
        }

        sizeBefore = hashMap.size();
        hashMap.put(y, x);
        if (hashMap.size() == sizeBefore) {
            ConsoleColors.printRed("hashMap value - fail");
        } else {
            hashMap.remove(y);
        }

        sizeBefore = treeMap.size();
        try {
            treeMap.put(x, x);
        } catch (Exception e) {
            ConsoleColors.printRed("treeMap - exception:" + e.getMessage());
        }
        if (treeMap.size() == sizeBefore) {
            ConsoleColors.printRed("treeMap key+value - fail");
        } else {
            treeMap.remove(x);
        }

        sizeBefore = treeMap.size();
        try {
            treeMap.put(x, y);
        } catch (Exception e) {
            ConsoleColors.printRed("treeMap - exception:" + e.getMessage());
        }
        if (treeMap.size() == sizeBefore) {
            ConsoleColors.printRed("treeMap key - fail");
        } else {
            treeMap.remove(x);
        }

        sizeBefore = treeMap.size();
        try {
            treeMap.put(y, x);
        } catch (Exception e) {
            ConsoleColors.printRed("treeMap - exception:" + e.getMessage());
        }
        if (treeMap.size() == sizeBefore) {
            ConsoleColors.printRed("treeMap value - fail");
        } else {
            treeMap.remove(y);
        }

        sizeBefore = priorityQueue.size();
        try {
            priorityQueue.add(x);
        } catch (Exception e) {
            ConsoleColors.printRed("priorityQueue - exception:" + e.getMessage());
        }
        if (priorityQueue.size() == sizeBefore) {
            ConsoleColors.printRed("priorityQueue - fail");
        }

        sizeBefore = arrayDeque.size();
        try {
            arrayDeque.add(x);
        } catch (Exception e) {
            ConsoleColors.printRed("arrayDeque - exception:" + e.getMessage());
        }
        if (arrayDeque.size() == sizeBefore) {
            ConsoleColors.printRed("arrayDeque - fail");
        }
    }
}
