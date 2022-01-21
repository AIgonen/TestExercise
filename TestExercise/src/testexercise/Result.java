package testexercise;

public class Result {
    
    public double dayTimeTotal;
    public double nightTimeTotal;

    public Result(double dayTimeTotal, double nightTimeTotal) {
        this.dayTimeTotal = dayTimeTotal;
        this.nightTimeTotal = nightTimeTotal;
    }

    public double getDayTimeTotal() {
        return dayTimeTotal;
    }

    public void setDayTimeTotal(double dayTimeTotal) {
        this.dayTimeTotal = dayTimeTotal;
    }

    public double getNightTimeTotal() {
        return nightTimeTotal;
    }

    public void setNightTimeTotal(double nightTimeTotal) {
        this.nightTimeTotal = nightTimeTotal;
    }

    @Override
    public String toString() {
        return "Väljund: \nöö:\t" + nightTimeTotal + "\npäev:\t" + dayTimeTotal;
    }
    
}
