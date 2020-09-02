package me.marcangeloh.Util.GeneralUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CooldownUtil {

    private final Date startTime;
    private Date endTime;
    private double multiplier;
    public CooldownUtil(String endTime, double multiplierAmount) {
        this.startTime = new Date();
        this.endTime = getFutureDate(endTime);
        multiplier = multiplierAmount;
    }
    public CooldownUtil(Date endTime, double multiplierAmount) {
        this.startTime = new Date();
        this.endTime = endTime;
        multiplier = multiplierAmount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public double getMultiplierAmount() {
        return multiplier;
    }

    /**
     * Checks if the multiplier is still valid
     * @return true if the multiplier is valid and false if not
     */
    public boolean isStillValid() {
        if (startTime == null) {
            return false;
        }
        long diffInMillies = startTime.getTime() - startTime.getTime();

        return (TimeUnit.MILLISECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS)>0);
    }

    /**
     * @param duration the duration in the future
     * @return the date
     */
    public Date getFutureDate(String duration) {
        final long SECOND_IN_MS = 1000;
        final long MINUTE_IN_MS = SECOND_IN_MS *60;
        final long HOUR_IN_MS = MINUTE_IN_MS *60;
        final long DAY_IN_MS = HOUR_IN_MS * 24;
        final long WEEK_IN_MS = DAY_IN_MS * 7;
        long totalToAdd = 0;

        char[] durationArray = duration.toCharArray();
        String number = "";
        for(char lastType: durationArray) {
            switch (lastType) {
                case 's':
                    totalToAdd = totalToAdd + (SECOND_IN_MS * Integer.parseInt(number));
                    number = "";
                    break;

                case 'm':
                    totalToAdd = totalToAdd + (MINUTE_IN_MS * Integer.parseInt(number));
                    number = "";
                    break;
                case 'h':
                    totalToAdd = totalToAdd + (HOUR_IN_MS * Integer.parseInt(number));
                    number = "";
                    break;
                case 'd':
                    totalToAdd = totalToAdd + (DAY_IN_MS * Integer.parseInt(number));
                    number = "";
                    break;
                case 'w':
                    totalToAdd = totalToAdd + (WEEK_IN_MS * Integer.parseInt(number));
                    number = "";
                default:
                    number =  lastType + number;
                    break;
            }

        }
        if(totalToAdd > 0)
            return new Date(System.currentTimeMillis() + totalToAdd);
        return null;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = getFutureDate(endTime);
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}
