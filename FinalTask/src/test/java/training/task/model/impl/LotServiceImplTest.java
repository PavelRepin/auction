package training.task.model.impl;

import org.junit.Assert;
import org.junit.Test;
import training.task.bean.Lot;
import training.task.model.api.ServiceFactory;

import java.util.List;

public class LotServiceImplTest {

    @Test
    public void testGetLotsPageByCategory() throws Exception {
        String category = "Painting";
        int page = 1;
        List<Lot> lots = ServiceFactory.getInstance().getLotService().getLotsPageByCategory(category, page);
        boolean test = true;
        for (Lot lot : lots) {
            if (!lot.getCategory().equals(category)) {
                test = false;
            }
        }
        Assert.assertTrue(test);
    }

    @Test
    public void test2GetLotsPageByCategory() throws Exception {
        String category = "Painting";
        int page = 1;
        List<Lot> lots = ServiceFactory.getInstance().getLotService().getLotsPageByCategory(category, page);
        boolean test = true;
        for (Lot lot : lots) {
            if (!lot.getCategory().equals(category)) {
                test = false;
            }
        }
        Assert.assertTrue(lots.size() <= 8);
    }
}