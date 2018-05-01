package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.util.TimeConverterConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeConverterImpl implements TimeConverter {


    @Override
    public String convertTime(String time) {

        if (!validateInputTime(time)) {
            return TimeConverterConstants.INVALID_TIME;
        }

        int timeUnitDivisibleByFive;
        int timeUnitModulusOfFive;
        int timeUnitValue;
        String[] splitTime = time.split(TimeConverterConstants.COLON);

        StringBuffer convertedTime = new StringBuffer();
        convertedTime.append(getLamps(Integer.parseInt(splitTime[2])));
        convertedTime.append(TimeConverterConstants.LINE_SEPARATOR);

        timeUnitValue = Integer.parseInt(splitTime[0]);
        timeUnitDivisibleByFive = timeUnitValue
                / TimeConverterConstants.MULTIPLE_OF_FIVE;
        timeUnitModulusOfFive = timeUnitValue
                % TimeConverterConstants.MULTIPLE_OF_FIVE;
        convertedTime.append(getLamps(timeUnitValue, timeUnitDivisibleByFive,
                TimeConverterConstants.RED_LAMP));
        convertedTime.append(TimeConverterConstants.LINE_SEPARATOR);
        convertedTime.append(getLamps(timeUnitValue, timeUnitModulusOfFive,
                TimeConverterConstants.RED_LAMP));
        convertedTime.append(TimeConverterConstants.LINE_SEPARATOR);

        timeUnitValue = Integer.parseInt(splitTime[1]);
        timeUnitDivisibleByFive = timeUnitValue
                / TimeConverterConstants.MULTIPLE_OF_FIVE;
        timeUnitModulusOfFive = timeUnitValue
                % TimeConverterConstants.MULTIPLE_OF_FIVE;

        convertedTime.append(getLamps(timeUnitValue, timeUnitDivisibleByFive,
                TimeConverterConstants.RED_LAMP,
                TimeConverterConstants.YELLOW_LAMP));
        convertedTime.append(TimeConverterConstants.LINE_SEPARATOR);
        convertedTime.append(getLamps(timeUnitValue, timeUnitModulusOfFive,
                TimeConverterConstants.YELLOW_LAMP));

        return convertedTime.toString();
    }

    private boolean validateInputTime(String inputTime) {

        try {
            if (inputTime != null && !inputTime.isEmpty()) {
                Pattern timeRegexPattern = Pattern
                        .compile(TimeConverterConstants.TIME_REGEX_PATTERN);
                Matcher timeMatcher = timeRegexPattern.matcher(inputTime);
                if (!timeMatcher.matches()) {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception  in validation method :-"
                    + e.getMessage());
        }
        return true;
    }
    public String getLamps(int timeUnitValue) {
        if (timeUnitValue % TimeConverterConstants.MULTIPLE_OF_TWO == 0) {
            return TimeConverterConstants.YELLOW_LAMP;
        } else {
            return TimeConverterConstants.OFF_LAMP;
        }
    }

    public String getLamps(int timeUnitValue, int numericValue,
                           String strRedLamp, String strYellowLamp) {

        StringBuilder lamps = new StringBuilder(
                TimeConverterConstants.ELEVEN_OFF_LAMPS);

        for (int i = 0; i < numericValue; i++) {
            if ((i + 1) % TimeConverterConstants.MULTIPLE_OF_THREE == 0) {
                lamps.replace(i, i + 1, strRedLamp);
            } else {
                lamps.replace(i, i + 1, strYellowLamp);
            }
        }
        return lamps.toString();
    }


    public String getLamps(int timeUnitValue, int numericValue, String strLamp) {
        StringBuilder lamps = new StringBuilder(
                TimeConverterConstants.FOUR_OFF_LAMPS);
        for (int i = 0; i < numericValue; i++) {
            lamps.replace(i, i + 1, strLamp);
        }

        return lamps.toString();

    }

}
