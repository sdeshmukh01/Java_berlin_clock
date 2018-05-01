package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.util.TimeConverterConstants;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class BerlinClockFixture {

    private TimeConverter timeConverter = new TimeConverterImpl();
    private String theTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    @Test
    public void testSecondsLamps() {
        Assert.assertEquals("Y", timeConverter.getLamps(0));
        Assert.assertEquals("O", timeConverter.getLamps(3));
        Assert.assertEquals("O", timeConverter.getLamps(47));
        Assert.assertEquals("Y", timeConverter.getLamps(40));
    }

    @Test
    public void testSecondRowOfHourLampsLength() {
        Assert.assertEquals(4, timeConverter.getLamps(10,10/5,TimeConverterConstants.RED_LAMP).length());
    }

    @Test
    public void testThirdRowOfHourLampsLength() {
        Assert.assertEquals(4, timeConverter.getLamps(15,15/5,TimeConverterConstants.RED_LAMP).length());
    }

    @Test
    public void testSecondRowOfHourLamps() {
        Assert.assertEquals("OOOO", timeConverter.getLamps(0,0/5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RROO", timeConverter.getLamps(13,13/5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RRRO", timeConverter.getLamps(18,18/5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RRRR", timeConverter.getLamps(23,23/5,TimeConverterConstants.RED_LAMP));


    }

    @Test
    public void testThirdRowOfHourLamps() {
        Assert.assertEquals("OOOO", timeConverter.getLamps(0,0%5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RRRO", timeConverter.getLamps(13,13%5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("OOOO", timeConverter.getLamps(20,20%5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RRRO", timeConverter.getLamps(23,23%5,TimeConverterConstants.RED_LAMP));

    }

    @Test
    public void testFourthRowOfMinuteLampsLength() {
        Assert.assertEquals(11, timeConverter.getLamps(59,59/5,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP).length());
    }

    @Test
    public void testFifthhRowOfMinuteLampsLength() {
        Assert.assertEquals(4, timeConverter.getLamps(59,59%5,TimeConverterConstants.YELLOW_LAMP).length());
    }

    @Test
    public void testFourthRowOfMinuteLamps() {
        Assert.assertEquals("OOOOOOOOOOO", timeConverter.getLamps(0,0/55,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP));
        Assert.assertEquals("YYROOOOOOOO", timeConverter.getLamps(17,17/5,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP));
        Assert.assertEquals("YYRYYRYYRYY", timeConverter.getLamps(59,59/5,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP));

    }

    @Test
    public void testFifthRowOfMinuteLamps() {
        Assert.assertEquals("OOOO", timeConverter.getLamps(0,0%5,TimeConverterConstants.YELLOW_LAMP));
        Assert.assertEquals("YYOO", timeConverter.getLamps(17,17%5,TimeConverterConstants.YELLOW_LAMP));
        Assert.assertEquals("YYYY", timeConverter.getLamps(59,59%5,TimeConverterConstants.YELLOW_LAMP));
    }

    @Test
    public void testInvalidTimeFomat1() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("25:00:00"));

    }

    @Test
    public void testInvalidTimeFomat2() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("23:00"));

    }

    @Test
    public void testInvalidTimeFomat3() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("::"));

    }

    @Test
    public void testInvalidTimeFomat4() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("qwerty"));

    }

    @Test
    public void testInvalidTimeFomat5() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("aa:bb:cc"));

    }

    @Test
    public void testInvalidTimeFomat6() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("1:2:3"));

    }
    @Test
    public void testInvalidTimeFomat7() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime(""));

    }

    @Test
    public void testInvalidTimeFomat8() {
        Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime(null));

    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {

        assertThat(timeConverter.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);

    }

}
