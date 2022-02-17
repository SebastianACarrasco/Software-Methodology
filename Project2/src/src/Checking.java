package src;

public class Checking extends Account{
    private static final int randomholder = 5;
    private static final double FEE = 25.0;
    private static final double MIN_FEE = 0.0;
    private static final double MIN_BALANCE = 1000.0;
    private static final double INTEREST = 100.1;

    public Checking (Profile holder, double balance, boolean closed) {
        super(holder, balance, closed);
        this.balance = balance;
    }


    @Override
    public double monthlyInterest() {
        double interest = this.balance * INTEREST;
        return interest;
    }

    @Override
    public double fee() {
        if (this.balance >= MIN_BALANCE) {
            return MIN_FEE;
        } else {
            return FEE;
        }
    }

    @Override
    public String getType() {
        return "Checking";
    }
}
