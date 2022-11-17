import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        int a, threadsNum, operation;

        Scanner stdin = new Scanner(System.in);

        System.out.print("Enter A: ");
        a = stdin.nextInt();
        System.out.print("Enter threads number: ");
        threadsNum = stdin.nextInt();
        System.out.print("Enter operation (1 - sum, 2 - sub, 3 - mul): ");
        operation = stdin.nextShort() - 1;

        ThreadGenerator tg = new ThreadGenerator(a, threadsNum, operation);
        tg.execute();

        System.out.printf("Result: %d", tg.getResult());
    }
}
