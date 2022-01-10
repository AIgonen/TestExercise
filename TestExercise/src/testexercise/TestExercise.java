
package testexercise;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;


public class TestExercise {


    public static void main(String[] args) {
        
        Scanner scn = new Scanner(System.in);
        
        LocalTime startTime;
        LocalTime endTime;
                
        System.out.println("siseskage kellaajad formaadis \"HH:mm\"");
        
        try{
            System.out.println("Sisestage alguskellaaeg");
            startTime = LocalTime.parse(scn.next());   
            
            System.out.println("Sisestage lõppkellaaeg");
            endTime = LocalTime.parse(scn.next());
            
            PrintTimes(GetTimes(startTime, endTime), startTime, endTime); //printing amount of hours to a console
        } catch(Exception e){
            System.out.println("Kellaeg on sisestatud valesti!");
        }
  
    }
    
    /**
    * Prints startTime, endTime and amount of hours to a console.
    */
    public static void PrintTimes(double[] times, LocalTime startTime, LocalTime endTime){
        System.out.println("Algus: " + startTime + ", lõpp: " + endTime);
        
        System.out.println("Öö\t " + times[1]);
        System.out.println("Päev\t " + times[0]);
    }
    
    /**
    * gets amount of night and day hours .
    */
    public static double[] GetTimes(LocalTime startTime,  LocalTime endTime){

        double firstHours, secondHours;         
        
        firstHours = (double)findDayTime(startTime,  endTime) / 3600;
        secondHours = (double)findNightTime(startTime,  endTime) / 3600;
        
        double[] times = {firstHours, secondHours};

        return times;
    }
    
    /**
    * finds amount of daytime hours.
    */
    public static long findDayTime(LocalTime startTime,  LocalTime endTime){
        long totalSeconds;        
        LocalTime MorningHoursStart = LocalTime.parse("06:00");
        LocalTime NightHoursStart = LocalTime.parse("22:00");
        
        // if starttime and endtime are both in daytime 
        if(startTime.getHour() < 6 && endTime.getHour() < 6 && startTime.getHour() < endTime.getHour()){
            totalSeconds = 0;
        } else if (startTime.getHour() >= 6 && endTime.getHour() < 6){ // stratTime after 6.00, endTime before 06.00
            Duration duration = Duration.between(startTime, NightHoursStart);
            totalSeconds = duration.getSeconds();                
        } else if (startTime.getHour() >= 6 && endTime.getHour()<= 22){ // 6.00 - 22.00
            Duration duration = Duration.between(startTime, endTime);
            totalSeconds = duration.getSeconds();
        } else if (startTime.getHour() >= 6 && endTime.getHour() > 22){ // stratTime after 6.00, endTime before 22.00
            Duration duration = Duration.between(startTime, NightHoursStart);
            totalSeconds = duration.getSeconds();
        } else if (startTime.getHour() < 6 && endTime.getHour() < 6 ){ // stratTime before 6.00, endTime after 00.00
            totalSeconds = 57600;
        } /*else if (startTime.getHour() < 6 && endTime.getHour() < 6){ // stratTime before 6.00, endTime before 06.00
        totalSeconds = 0;
        }*/ else if (startTime.getHour() < 6 && endTime.getHour() < 22 ){ // stratTime before 6.00, endTime before 22.00
            Duration duration = Duration.between(MorningHoursStart, endTime);
            totalSeconds = duration.getSeconds();
        } else if (startTime.getHour() < 6 && endTime.getHour() > 22){ // stratTime before 6.00, endTime after 22.00
            totalSeconds = 57600; // 16h
        } else 
            totalSeconds = 57600; // 16h
    return totalSeconds;    
    }
    
    /**
    * founds amount of nighttime hours .
    */
    public static long findNightTime(LocalTime startTime,  LocalTime endTime){
        long totalSeconds;      
        
        LocalTime MorningHoursStart = LocalTime.parse("06:00");
        LocalTime NightHoursStart = LocalTime.parse("22:00");
        LocalTime midnight = LocalTime.parse("00:00");
        
        if(startTime.getHour() < 6 && endTime.getHour() < 6 && startTime.getHour() < endTime.getHour()){
            Duration duration = Duration.between(startTime, endTime);
            totalSeconds = duration.getSeconds();
        } else if (startTime.getHour() < 6 && endTime.getHour() < 6 ){ // stratTime before 6.00, endTime after 00.00
            Duration duration = Duration.between(startTime, endTime);
            totalSeconds = 28800 + duration.getSeconds();
        } else if (startTime.getHour() >= 6 && endTime.getHour() < 6){ // stratTime after 6.00, endTime after 00.00
            Duration duration = Duration.between(midnight, endTime);
            totalSeconds = duration.getSeconds() + 7200;
        } else {
            Duration duration = Duration.between(startTime, endTime);
            totalSeconds = duration.getSeconds() - findDayTime(startTime, endTime);
        }
        return totalSeconds;
    }     
}