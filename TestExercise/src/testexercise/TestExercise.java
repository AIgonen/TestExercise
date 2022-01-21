
package testexercise;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class TestExercise {
    public static LocalDateTime now = LocalDateTime.now();
        
    public static void main(String[] args) throws ParseException {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Sisestage kellaajad formaadis \"HH:mm\"");
        System.out.println("Sisestage alguskellaaeg");
        String beginningTime = scn.next();
        System.out.println("Sisestage lõppkellaaeg");
        String endingTime = scn.next();

        int startTimegHour = Integer.parseInt(beginningTime.substring(0, 2));
        int StarttimeMinute = Integer.parseInt(beginningTime.substring(3));
        int endTimeHour = Integer.parseInt(endingTime.substring(0, 2));
        int endTimeMinute = Integer.parseInt(endingTime.substring(3));
           
        LocalDateTime startTime = LocalDate.now().atTime(startTimegHour, StarttimeMinute);
        LocalDateTime endTime = LocalDate.now().atTime(endTimeHour, endTimeMinute);
        
        System.out.println("Sisend");
        System.out.println("Algus: " + beginningTime + ", lõpp: " + endingTime + "\n");
        Result total = CalculatePeriods(startTime, endTime);
        System.out.println(total.toString());
    }
    
    private static Result CalculatePeriods(LocalDateTime startTime, LocalDateTime endTime) throws ParseException{

    LocalDateTime MorningHoursStart = LocalDate.now().atTime(5, 59);
    LocalDateTime NightHoursStart = LocalDate.now().atTime(21, 59);

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