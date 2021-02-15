import java.util.*;
import java.util.function.Function;

public class Collections {
    public static ArrayList<String> arrayList;
    public static LinkedList<String> linkedList;
    public static TreeSet<String> treeSet;
    public static HashMap<String, String> hashMap;
    public static TreeMap<String, String> treeMap;
    public static PriorityQueue<String> priorityQueue;
    public static ArrayDeque<String> arrayDeque;

    private static int randomisingIndex = 0;
    private static int randomisingIndex2 = 0;

    private final String[] testingArray = new String[]{"bcda", "cdab", "abcd"};


    private static int getRandomisingIndex() {
        return ++randomisingIndex;
    }

    private static int getRandomisingIndex2() {
        return ++randomisingIndex;
    }

    private static void resetRandomissingIndexes() {
        randomisingIndex = 0;
        randomisingIndex2 = 0;
    }

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

    private static void emptyAll() {
        arrayList.clear();
        linkedList.clear();
        treeSet.clear();
        hashMap.clear();
        treeMap.clear();
        priorityQueue.clear();
        arrayDeque.clear();
    }

    public void runAllTests() {
        testOrder();
        testSorted();
        testSameValues();
        testNull();
        testThreadSafe();
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
     * Lze pristupovat (getter) k jedne hodnote? Jakym zpusobem/metodou?
     */
    private void getElemets(){
        arrayList.get(0);
        linkedList.get(0);
        treeSet.iterator();
        hashMap.get(null);
        treeMap.get(null);
        priorityQueue.poll();
        priorityQueue.peek();
        arrayDeque.peek();
        arrayDeque.poll();
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

        ArrayList<String> saveOfPriorityQueue = new ArrayList<>();
        while (priorityQueue.size() > 0) {
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
        priorityQueue.addAll(saveOfPriorityQueue);
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

    /***
     * Muze vice vlaken bezpecne paralerne vkladat a mazat hodnoty? (thread-safe)
     */
    public static void testThreadSafe() {
        ConsoleColors.printBlue("Collections.testThreadSafe");
        emptyAll();
        int numberToAdd, size;

        numberToAdd = 10000;
        testThreadSafe(numberToAdd, () -> Collections.arrayList.add(Collections.getRandomisingIndex() + "TEST"), () -> Collections.arrayList.add(Collections.getRandomisingIndex2() + "TEST"));
        size = arrayList.size();
        compareThreadSafeResults(size, numberToAdd * 2, "arrayList");


        numberToAdd = 10000;
        testThreadSafe(numberToAdd, () -> Collections.linkedList.add(Collections.getRandomisingIndex() + "TEST"), () -> Collections.linkedList.add(Collections.getRandomisingIndex2() + "TEST"));
        size = linkedList.size();
        compareThreadSafeResults(size, numberToAdd * 2, "linkedList");

        numberToAdd = 500;
        testThreadSafe(numberToAdd, () -> Collections.treeSet.add(Collections.getRandomisingIndex() + "TEST"), () -> Collections.treeSet.add(Collections.getRandomisingIndex2() + "TEST"));
        size = treeSet.size();
        compareThreadSafeResults(size, numberToAdd * 2, "treeSet");

        numberToAdd = 10000;
        testThreadSafe(numberToAdd, () -> Collections.hashMap.put(Collections.getRandomisingIndex() + "TEST", Collections.getRandomisingIndex() + "TEST"), () -> Collections.hashMap.put(Collections.getRandomisingIndex2() + "TEST", Collections.getRandomisingIndex2() + "TEST"));
        size = hashMap.size();
        compareThreadSafeResults(size, numberToAdd * 2, "hashMap");

        numberToAdd = 500;
        testThreadSafe(numberToAdd, () -> Collections.treeMap.put(Collections.getRandomisingIndex() + "TEST", Collections.getRandomisingIndex() + "TEST"), () -> Collections.treeMap.put(Collections.getRandomisingIndex2() + "TEST", Collections.getRandomisingIndex2() + "TEST"));
        size = treeMap.size();
        compareThreadSafeResults(size, numberToAdd * 2, "treeMap");

        numberToAdd = 500;
        testThreadSafe(numberToAdd, () -> Collections.priorityQueue.add(Collections.getRandomisingIndex() + "TEST"), () -> Collections.priorityQueue.add(Collections.getRandomisingIndex2() + "TEST"));
        size = priorityQueue.size();
        compareThreadSafeResults(size, numberToAdd * 2, "priorityQueue");

        numberToAdd = 10000;
        testThreadSafe(numberToAdd, () -> Collections.arrayDeque.add(Collections.getRandomisingIndex() + "TEST"), () -> Collections.arrayDeque.add(Collections.getRandomisingIndex2() + "TEST"));
        size = arrayDeque.size();
        compareThreadSafeResults(size, numberToAdd * 2, "arrayDeque");
    }

    public static void compareThreadSafeResults(int x, int y, String msg) {
        if (x != y) {
            ConsoleColors.printRed("Thread safe fail " + msg + " (" + x + "," + y * 2 + ")");
        } else {
            ConsoleColors.printGreen("Thread safe success " + msg + " (" + x + "," + y * 2 + ")");
        }
    }


    public static void testThreadSafe(int numberOfTimes, Runnable runnable, Runnable runnable2) {
        Thread thread = new Thread(() -> repeatRunnable(numberOfTimes, runnable));
        Thread thread2 = new Thread(() -> repeatRunnable(numberOfTimes, runnable2));
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resetRandomissingIndexes();
    }

    public static void repeatRunnable(int numberOfTimes, Runnable runnable) {
        for (int i = 0; i < numberOfTimes; i++) {
            try {
                runnable.run();
            } catch (Exception ignored) {
            }
        }
    }
}
