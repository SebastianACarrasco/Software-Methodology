package src;

public class BankTeller {
    private static final int NEWBRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int CAMDEN = 2;

    private void run() {

    }

    private boolean isValidLocation(int location){
        if(location == NEWBRUNSWICK || location == NEWARK || location == CAMDEN){
            return true;
        }

        return false;
    }
}
