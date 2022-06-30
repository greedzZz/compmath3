public class IntegrationFunction {
    private final int number;
    private final double accuracy;
    private final double leftBoundary;
    private final double rightBoundary;

    public IntegrationFunction(int number, double accuracy, double[] boundary) {
        this.number = number;
        this.accuracy = accuracy;
        this.leftBoundary = boundary[0];
        this.rightBoundary = boundary[1];
    }

    public int getNumber() {
        return number;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getLeftBoundary() {
        return leftBoundary;
    }

    public double getRightBoundary() {
        return rightBoundary;
    }
}
