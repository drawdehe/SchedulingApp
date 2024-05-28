public class TimeSlot {

    private int year;
    private int month;
    private int day;
    private int startHour;
    private int endHour;

    public TimeSlot(int year, int month, int day, int startHour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.endHour = startHour + 1;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day + " " + startHour + ":00-" + endHour + ":00";
    }
}