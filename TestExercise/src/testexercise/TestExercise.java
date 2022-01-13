
package testexercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;  
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;



public class TestExercise {
        
    public static void main(String[] args) throws ParseException {
        
        Scanner scn = new Scanner(System.in);
        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); 
        
        String p1 = "21:00"; 
        String p2 = "09:00"; 

        Date startTime = formatter.parse(p1);
        Date endTime = formatter.parse(p2);
            
        if (endTime.before(startTime)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(endTime);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            endTime = cal.getTime();
        }
            
        
        Result total = CalculatePeriods(startTime, endTime);
        System.out.println(total.toString());
        
    }
    
    private static Result CalculatePeriods(Date startTime, Date endTime) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); 
 //       DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm");
        
        Date MorningHoursStart = formatter.parse("05:59");
        Date NightHoursStart = formatter.parse("21:59");
            
        int dayTime = 0;
        int nightTime = 0;
        
        double dayHours = 0;
        double nightHours = 0;
        
        Date step = formatter.parse("00:15");

            while (startTime.before(endTime))
            {
                if (startTime.after(MorningHoursStart) && startTime.before(NightHoursStart)) {
                    dayTime += 15;
                } else{
                    nightTime += 15;
                }
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTime);
                cal.add(Calendar.MINUTE, 15);
                startTime = cal.getTime();
            }
            
            dayHours = dayTime / 60.0;
            nightHours = nightTime / 60.0;
        
            Result result = new Result(dayHours, nightHours);
            return result;
        }
}