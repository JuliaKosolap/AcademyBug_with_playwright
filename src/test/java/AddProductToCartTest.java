import io.qameta.allure.*;
import org.example.FindBugsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import setup.BaseTest;

public class AddProductToCartTest extends BaseTest {

    @Description("This test adds the first product in the list to the cart. If product does not have button 'Add to Cart'" +
            "then it throws exception")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Test started")
    @Test
    public void addFirstProductToCart() throws Exception {
        FindBugsPage findBugsPage = new FindBugsPage(page);

        Allure.step("Add product to the cart");

        findBugsPage.addProductToCart(1);

        Allure.step("Get the name of the button");
        String buttonName = findBugsPage.getButtonName(1);

        Allure.step("Verify that button name was changed from 'Add to Cart' to 'Checkout now'");
        Assertions.assertEquals("CHECKOUT NOW", buttonName);
    }
}
