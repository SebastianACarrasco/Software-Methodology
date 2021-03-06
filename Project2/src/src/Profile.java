package src;
/**
 * The Profile class creates an object Profile using a String for first name, last name,
 * and a Date object for the date of birth. It also can return the information of the attributes
 * in a toString method, and can check if two patients are the same person or not.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public class Profile {
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
    @Override
    public String toString() {
        return this.fName + " " + this.lName + ", DOB: " + this.dob;
    }

    /**
     * The equals method checks to see if the given patient is the same as the
     * current profile
     * @param profile is an object of type patient
     * @return a boolean where is the profile are the same, we return true, and
     * if not we return false
     */
    public boolean equals(Profile profile) {
        //if (profile.fName.equals(this.fName) && profile.lName.equals(this.lName)
        if(profile.fName.toUpperCase().equals(fName.toUpperCase())
                && profile.lName.toUpperCase().equals(lName.toUpperCase())
                && profile.dob.compareTo(this.dob) == 0 ){
            return true; //the profiles are the same
        }
        return false;//the profiles are different.One or more attributes are not the same
    }

    /**
     * public getter method to return the last name
     * @return a String of last Name
     */
    public String getlName() {
            return this.lName;
        }

    /**
     * public getter method to return the  first name
     * @return a String of last Name
     */
    public String getfName() {
        return this.fName;
    }

    /**
     * public getter method to return the ate of birth
     * @return a Date object for the date of birth
     */
    public Date getDob() {
        return this.dob;
    }

}

