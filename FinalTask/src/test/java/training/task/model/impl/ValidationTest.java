package training.task.model.impl;

import org.junit.Assert;
import org.junit.Test;
import training.task.model.api.exception.ValidationServiceException;

public class ValidationTest {

    @Test
    public void testMatchDate() throws Exception {
        boolean test = false;
        try {
            String date = "2017:05:20";
            Validation.matchDate(date);
        } catch (ValidationServiceException e) {
            test = true;
        } finally {
            Assert.assertTrue(test);
        }
    }

    @Test
    public void test2MatchDate() throws Exception {
        boolean test = false;
        try {
            String date = "2017-05-20";
            Validation.matchDate(date);
        } catch (ValidationServiceException e) {
            test = true;
        } finally {
            Assert.assertFalse(test);
        }
    }
}