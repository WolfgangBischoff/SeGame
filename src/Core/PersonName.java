package Core;

import Util.Util;

import static Util.Util.DEFAULT_FIRSTNAME;
import static Util.Util.DEFAULT_LASTNAME;

public class PersonName {
    private String firstname;
    private String lastname;
    //private Title title;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public PersonName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public PersonName(String firstAndLastname)
    {
        String[] nameParts = firstAndLastname.split(" ");
        if (nameParts.length < 2)
            throw new IllegalArgumentException("Name must consist of at least two parts");

        firstname = nameParts[0];
        StringBuilder sblastname = new StringBuilder();
        for (int i = 1; i < nameParts.length; i++)
        {
            if (i == nameParts.length - 1)
                sblastname.append(nameParts[i]);
            else
                sblastname.append(nameParts[i] + " ");
        }
        lastname = sblastname.toString();
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public PersonName()
    {
        firstname = Util.DEFAULT_FIRSTNAME;
        lastname = Util.DEFAULT_LASTNAME;
    }
}
