import static java.lang.Math.*;

public class RectangleMethod {
    private final int MAX_ITERATION = 20;
    private final int number;
    private final double e;
    private final double a;
    private final double b;
    private int partitionCount;
    private double error;
    private Double result;

    public RectangleMethod(IntegrationFunction function) {
        this.number = function.getNumber();
        this.e = function.getAccuracy();
        this.a = function.getLeftBoundary();
        this.b = function.getRightBoundary();
    }

    public void solve(double coefficient) {
        partitionCount = 1;
        double sum = 0, previousSum;
        int iterationCount = 0;
        do {
            iterationCount++;
            partitionCount *= 2;
            previousSum = sum;
            double step = (b - a) / partitionCount;
            sum = 0;
            for (double i = a; i < b; i += step) {
                double x = getValueOfFunction(i + coefficient * step);
                if (Double.isNaN(x)) {
                    x = (getValueOfFunction(i + coefficient * step + 0.00001) +
                            getValueOfFunction(i + coefficient * step - 0.00001)) / 2;
                }
                sum += x * step;
            }
            if (Double.isNaN(sum) || Double.isInfinite(sum) || iterationCount > MAX_ITERATION) {
                result = null;
                return;
            }
        } while (Math.abs(sum - previousSum) > e);
        result = sum;
        error = Math.abs(sum - previousSum);
    }

    private double getValueOfFunction(double x) {
        return switch (number) {
            case (1) -> 2 * pow(x, 3) + pow(x, 2) - 5 * x - 1;
            case (2) -> sin(x) / x;
            case (3) -> 1 / x;
            case (4) -> log(x);
            default -> 0;
        };

    }

    public Double getResult() {
        return result;
    }

    public double getError() {
        return error;
    }

    public int getPartitionCount() {
        return partitionCount;
    }
}
