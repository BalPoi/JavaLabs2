public class CalculatorThread extends Thread {
    private final int startValue;
    private final int endValue;
    private int result = 1;

    public CalculatorThread(int startValue, int endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        System.out.println(CalculatorThread.currentThread());
        for (int i = startValue; i <= endValue; i++) {
            result *= i;
        }
    }
}