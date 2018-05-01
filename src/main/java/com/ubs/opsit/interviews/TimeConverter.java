package com.ubs.opsit.interviews;

public interface TimeConverter {

    String convertTime(String aTime);
    String getLamps(int timeUnitValue);

    String getLamps(int timeUnitValue, int numericValue,String strLamp);

    String getLamps(int timeUnitValue, int numericValue,String strRedLamp, String strYellowLamp);


}
