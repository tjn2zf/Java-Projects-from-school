/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfhelloworld;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thomasnewman
 */
public class Tjn2zfHelloWorld {

    public static void invokeMe(String pawprint){
        System.out.println("My PawPrint is " + pawprint);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy hh:mm");
        System.out.println("Today's date is " + ft.format(dNow));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         System.out.println("Hello World");
         String myPawPrint = "tjn2zf";
         invokeMe(myPawPrint);
    }
    
}
