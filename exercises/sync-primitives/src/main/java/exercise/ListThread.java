package exercise;

// BEGIN
public class ListThread implements Runnable {

    SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
                list.add(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
// END
