
package testexercise;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;


public class TestExercise {
    public static LocalDateTime now = LocalDateTime.now();
        
    public static void main(String[] args) throws ParseException {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("siseskage kellaajad formaadis \"HH:mm\"");
        System.out.println("Sisestage alguskellaaeg");
        String s1 = scn.next();
        System.out.println("Sisestage lõppkellaaeg");
        String s2 = scn.next();

        int startTimegHour = Integer.parseInt(s1.substring(0, 2));
        int StarttimeMinute = Integer.parseInt(s1.substring(3));
        int endTimeHour = Integer.parseInt(s2.substring(0, 2));
        int endTimeMinute = Integer.parseInt(s2.substring(3));
   
        
        LocalDateTime startTime = now.withHour(startTimegHour).withMinute(StarttimeMinute).withSecond(0);
        LocalDateTime endTime = now.withHour(endTimeHour).withMinute(endTimeMinute).withSecond(0);

        
            
        Result total = CalculatePeriods(startTime, endTime);
        System.out.println(total.toString());
    }
    
    private static Result CalculatePeriods(LocalDateTime startTime, LocalDateTime endTime) throws ParseException{

    LocalDateTime MorningHoursStart = now.withHour(5).withMinute(59).withSecond(0);
    LocalDateTime NightHoursStart = now.withHour(21).withMinute(59).withSecond(0);

    if (endTime.isBefore(startTime) && startTime.isAfter(NightHoursStart)) {
            endTime = endTime.plusDays(1);
            MorningHoursStart = MorningHoursStart.plusDays(1);
            NightHoursStart = NightHoursStart.plusDays(1);
    } else if (endTime.isBefore(startTime)) {
            endTime = endTime.plusDays(1);
    }
    
    
    int dayTime = 0;
    int nightTime = 0;

    double dayHours = 0;
    double nightHours = 0;

        while (startTime.isBefore(endTime))
        {
            if (startTime.isAfter(MorningHoursStart) && startTime.isBefore(NightHoursStart)) {
                dayTime += 15;
            } else{
                nightTime += 15;
            }

            startTime = startTime.plusMinutes(15);
        }

        dayHours = dayTime / 60.0;
        nightHours = nightTime / 60.0;
        
        Result result = new Result(dayHours, nightHours);
        return result;
    }
}