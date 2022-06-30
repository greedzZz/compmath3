import java.util.Scanner;

public class IOManager {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Интегрирование методом прямоугольников.");
        System.out.println("Разрывы первого рода устраняются при помощи использования " +
                "алгоритмического среднего значения двух соседних от точки разрыва точек функции.");
        IntegrationFunction function = new IntegrationFunction(readFunctionNumber(), readAccuracy(), readBoundary());
        RectangleMethod method = new RectangleMethod(function);
        method.solve(0);
        System.out.println("\nМетод левых прямоугольников:\n");
        if (null == method.getResult()) {
            System.out.println("Интеграл вычислить невозможно.");
        } else {
            System.out.println("Результат: " + method.getResult());
            System.out.println("Количество разбиений: " + method.getPartitionCount());
            System.out.println("Погрешность: " + method.getError());
        }
        method.solve(0.5);
        System.out.println("\nМетод средних прямоугольников:\n");
        if (null == method.getResult()) {
            System.out.println("Интеграл вычислить невозможно.");
        } else {
            System.out.println("Результат: " + method.getResult());
            System.out.println("Количество разбиений: " + method.getPartitionCount());
            System.out.println("Погрешность: " + method.getError());
        }
        method.solve(1);
        System.out.println("\nМетод правых прямоугольников:\n");
        if (null == method.getResult()) {
            System.out.println("Интеграл вычислить невозможно.");
        } else {
            System.out.println("Результат: " + method.getResult());
            System.out.println("Количество разбиений: " + method.getPartitionCount());
            System.out.println("Погрешность: " + method.getError());
        }
    }

    private int readFunctionNumber() {
        int number = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Выберите функцицю, которую необходимо проинтегрировать:");
            System.out.println("[1]: " + Data.FUNCTIONS[0]);
            System.out.println("[2]: " + Data.FUNCTIONS[1]);
            System.out.println("[3]: " + Data.FUNCTIONS[2]);
            System.out.println("[4]: " + Data.FUNCTIONS[3]);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= 1 && number <= 4) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return number;
    }

    private double readAccuracy() {
        double accuracy = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите точность от 0 до 1:");
            try {
                accuracy = Double.parseDouble(scanner.nextLine());
                if (accuracy > 0 && accuracy < 1) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return accuracy;
    }

    private double[] readBoundary() {
        double left = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите левую границу интервала: ");
            try {
                left = Double.parseDouble(scanner.nextLine());
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        double right = 0;
        correct = false;
        while (!correct) {
            System.out.println("Введите правую границу интервала: ");
            try {
                right = Double.parseDouble(scanner.nextLine());
                if (right > left) correct = true;
                else throw new IllegalArgumentException();
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            } catch (IllegalArgumentException e) {
                System.out.println("Правая граница должна быть больше левой.");
            }
        }
        return new double[]{left, right};
    }
}
