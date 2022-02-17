package src;

public class MoneyMarket extends Account{

    private boolean isLoyal;
    private static final double FEE = 10.0;
    private static final double MIN_FEE = 0.0;
    private static final double MIN_BALANCE = 2500.0;
    private static final double REGULARINTEREST = 100.8;
    private static final double LOYALINTEREST = 100.95;
    private static final int MAXWITHDRAWALS = 3;

    public MoneyMarket(Profile holder, double balance, boolean closed) {
        super(holder, balance, closed);
        this.balance = balance;
        this.isLoyal = true; //loyal customer by default
    }

    @Override
    public double monthlyInterest() {
        double interest = 0;
        if (isLoyal) {
            interest = this.balance * REGULARINTEREST;
        } else {
            interest = this.balance * LOYALINTEREST;
        }
        return interest;
    }

    @Override
    public double fee() {
        if (this.balance >= MIN_BALANCE && numberOfWithdrawals > MAXWITHDRAWALS) {
            return MIN_FEE;
        } else {
            //case if the balance <2500 or if the number of withdrawals > 3
            this.isLoyal = false; //balance is < 2500 so they are no longer a loyal customer
            return FEE;
        }
    }

    @Override
    public String getType() {
        return "Money Market";
    }
}
