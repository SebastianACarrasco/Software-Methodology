package src;

public class Profile {
    /**
     * The Profile class creates an object Profile using a String for first name, last name,
     * and a Date object for the date of birth. It also can return the information of the attributes
     * in a toString method, and can check if two patients are the same person or not.
     * @RachaelChin
     * @JundyLacuata
     */
        private String fName;
        private String lName;
        private Date dob;

        /**
         * This constructor takes the input parameters and sets it to
         * our instance variables
         *
         * @param fName is the String representation taken to get the first name
         * @param lName is the String representation taken to get the last name
         * @param dob   is the String representation taken to get the date of birth
         */
        public Profile(String fName, String lName, Date dob) {
            this.fName = fName;
            this.lName = lName;
            this.dob = dob;
        }

        /**
         * toString method to return the attributes in the formatted way for profile
         * @return a String of the first name, last name, and date of birth
         */
        public String toString() {
            return this.fName + " " + this.lName + ", DOB: " + this.dob;
        }

        /**
         * The equals method checks to see if the given patient is the same as the
         * current profile
         * @param profile is an object of type patient
         * @return an integer where is the profile are the same, we return 0, and
         * if not we return 1
         */

        public int equals(Profile profile) {
            if (profile.fName.equals(this.fName) && profile.lName.equals(this.lName)
                    && profile.dob == (this.dob)) {
                return 0; //the profiles are the same
            }
            return 1; //the profiles are different. One or more attributes are not the same
        }

        public String getlName() {
            return this.lName;
        }

        public String getfName() {
            return this.fName;
        }

        public Date getDob() {
            return this.dob;
        }

}

