public class ThreadGenerator {
    private final int a;
    private final int threadsNum;
    private final int operation;

    private CalculatorThread[] threads;

    public ThreadGenerator(int a, int threadsNum, int operation) {
        if (a <= 0 || a % threadsNum != 0) {
            throw new IllegalArgumentException("The a must be greater than zero and " +
                    "divisible by the number of threads without a remainder.");
        }
        if (operation < 0 || operation > 2) {
            throw new IllegalArgumentException("Operation must be from 1 to 3");
        }

        this.a = a;
        this.threadsNum = threadsNum;
        this.operation = operation;
    }

    void execute() {
        threads = new CalculatorThread[threadsNum];
        int capacity = a / threadsNum;
        int startValue;
        int endValue;
        for (int i = 0; i < threadsNum; i++) {
            startValue = i * capacity + 1;
            endValue = i * capacity + capacity;
            threads[i] = new CalculatorThread(startValue, endValue);
            threads[i].start();
        }
    }

    int getResult() {
        for (CalculatorThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Problems with joining threads");
            }
        }
        switch (operation) {
            case 0:     // SUM
                int sum = 0;
                for (CalculatorThread thread : threads) {
                    sum += thread.getResult();
                }
                return sum;
            case 1:     // SUB
                int sub = 0;
                for (CalculatorThread thread : threads) {
                    sub -= thread.getResult();
                }
                return sub;
            case 2:     //MUL
                int mul = 1;
                for (CalculatorThread thread : threads) {
                    mul *= thread.getResult();
                }
                return mul;
            default:
                return -1;
        }
    }
}
