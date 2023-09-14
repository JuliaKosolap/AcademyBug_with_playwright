import io.qameta.allure.*;
import org.example.FindBugsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import setup.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortProductsTest extends BaseTest {

    @Description("This test sorts the products by price from Low To High and checks if they were sorted correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test started")
    @Test
    public void sortProductsFromLowToHigh() {

        FindBugsPage findBugsPage = new FindBugsPage(page);

        Allure.step("Select sorting products from Low to High");
        findBugsPage.sortProductByPrice();

        Allure.step("Get list of sorted prices");
        List<Double> productPrices = findBugsPage.getListOfAllProductPrices();

        List<Double> sortedPrices = new ArrayList<>(productPrices);
        Collections.sort(sortedPrices);

        Allure.step("Verify that products are sorted correctly");
        Assertions.assertEquals(sortedPrices, productPrices);


    }
}