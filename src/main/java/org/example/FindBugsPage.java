package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class FindBugsPage {
    Page page;
    public FindBugsPage(Page page) {
        this.page = page;
    }

    public void sortProductByPrice() {
        page.locator("#sortfield").selectOption("1");
        page.waitForLoadState();
    }

    public List<Double> getListOfAllProductPrices() {
        List<Locator> locators =  page.locator("css=span[class='ec_price_type1']").all();
        List<Double> productPrices = new ArrayList<>();
        for (Locator locator: locators
        ) {
            String s = locator.innerText().replaceAll("[^0-9.]", "");
            double price = Double.parseDouble(s);
            productPrices.add(price);
        } return productPrices;
    }

    public void addProductToCart(int productNumberInList) throws Exception {
        List<Locator> productCards = getProductCards();
        Locator productCard = productCards.get(productNumberInList - 1);
        Locator button = productCard.locator("css=span[class='ec_product_addtocart']");

        String buttonName = button.innerText();
        if(buttonName.equals("ADD TO CART")) {
            button.click();
            page.waitForSelector("css=div[class='ec_product_added_to_cart']", new Page.WaitForSelectorOptions().
                    setState(WaitForSelectorState.VISIBLE));
        }
        else throw new Exception("Add To Cart Button is not available for this product");
    }
    public List<Locator> getProductCards() {
        List<Locator> elements = page.locator("css=div[class='academy-product-description-wrapper']").all();
        return elements;
    }

    public String getButtonName(int productNumberInList) {
        List<Locator> productCards = getProductCards();
        Locator productCard = productCards.get(productNumberInList - 1);
        String buttonName = productCard.locator("css=span[class='ec_product_addtocart']").innerText();
        return buttonName;
    }
}
