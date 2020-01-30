/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfjavalanguagebasics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 *
 * @author thomasnewman
 */
public class Tjn2zfJavaLanguageBasics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        byte test1 = 0x7C;
        byte test2 = 124;
        short currentSpeed = 85;
        long balance = 751532445;
        float acceleration = 9.8f;
        float weight = (float) 195.7;
        double length = 9.763001;
        boolean lost = false;
        boolean smart = true;
        //Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        //calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //System.out.println(hour +" "+ minute);
        char integral = '\u222B';
        char character1 = 'a';
        char character2 = 97;
        String greeting = "Ni Hao";
        String myPawPrint = "tjn2zf";
        
        //compare test1 and test2
        if (test1==test2){
            System.out.println("The examples are equal"); 
        }
        else{
            System.out.println("The examples are not equal");
        }
        
        if (currentSpeed >= 55 && currentSpeed <= 80){
            System.out.println("Current Speed is legal");
        }
        else{
            System.out.println("Current Speed may get a ticket");
        }
        
        if (balance >= 100000000){
            System.out.println("I'm rich!");
        }
        else{
            System.out.println("I'm poor!");
        }
        
        float force = weight * acceleration;
        System.out.println("force = " + force);
        
        System.out.println(length + " is the length");
        
        if(lost==true && smart==true){
            System.out.println
        ("I am really sorry! I will get the teaching"
                + " assistant.");
        }
        else if(lost==false && smart==true){
            System.out.println("Congratulations for understanding"
                    + " and working hard.");
        }
        else{
            System.out.println("No message available");
        }
        
        switch(hour){
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("The current time is " + hour + ":" + minute + " and it is the morning");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                System.out.println("The current time is " + hour + ":" + minute + " and it is the afternoon");
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                System.out.println("The current time is " + hour + ":" + minute + " and it is the evening");
                break;
            case 23:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("The current time is " + hour + ":" + minute + " and it is the night");
                break;
            default: 
                System.out.println("You have the wrong time");
                break;
        }
        
        System.out.println(integral + " is an integral");
        
        if(character1==character2){
            System.out.println(character1 + " and " + character2 + " are the same");
        }
        else{
            System.out.println(character1 + " and " + character2 + " are NOT the same");
        }
        
        for(int i=1; i<16; i++){
            if(i % 3 == 0){
                System.out.println("i = " + i);
            }
        }
        
        int age = 0;
        while(age<13){
            System.out.println("age = " + age);
            age++;
        }
        
        invokeMe(myPawPrint, greeting);
    }
    
    public static void invokeMe(String pawprint, String greeting){
        //System.out.println("My PawPrint is " + pawprint);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm");
        System.out.println(greeting + ", my pawprint is " + pawprint + " and today's date is " + ft.format(dNow));
    }
    
}
