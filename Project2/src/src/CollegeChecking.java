package src;

public class CollegeChecking extends Account{

    private static final double INTEREST = 100.25;
    private static final double COLLEGEFEE = 0.0;

    public CollegeChecking (Profile holder, double balance, boolean closed){
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
        return COLLEGEFEE;
    }

    @Override
    public String getType() {
        return "College Checkings";
    }
}
