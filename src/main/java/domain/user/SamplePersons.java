/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.user;

import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class SamplePersons {
    public static final Person Administrator = 
            new Person("administrator",
                    "administrator",
                    "Administrator",
                    null,
                    null,
                    null);
    public static final Person User1 = 
            new Person("ajadriano",
                    "ajadriano",
                    "Adrian Joseph Adriano",
                    "London SW19 6PG",
                    "aadria01@mail.bbk.ac.uk",
                    new CreditCard("1234567812345678", new Date()));
    public static final Person User2 = 
            new Person("dtargaryen",
                    "dtargaryen",
                    "Daenerys Targaryen",
                    "Valyria",
                    "dany@wherearemydragons.com",
                    new CreditCard("9876543212345678", new Date()));
}
