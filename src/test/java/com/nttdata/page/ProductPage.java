package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductPage {
    public static By session = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span");
    public static By categoryClothes = By.xpath("//*[@id=\"category-3\"]/a");
    public static By subcategoryMen = By.xpath("//*[@id=\"left-column\"]/div[1]/ul/li[2]/ul/li[1]/a");
    public static By firstProduct = By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article");
    public static By quantityInput = By.xpath("//*[@id=\"quantity_wanted\"]");
    public static By addToCartButton = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button");
}
