package pages.v8.multifilepageobjectsectionssingleton.CartPage;

import core.BrowserService;
import pages.v8.multifilepageobjectsectionssingleton.LoggingSingletonDriver;
import pages.v8.multifilepageobjectsectionssingleton.NavigatableEShopPage;
import pages.v8.multifilepageobjectsectionssingleton.Sections.BreadcrumbSection;
import pages.v8.multifilepageobjectsectionssingleton.SingletonFactory;

import java.lang.reflect.InvocationTargetException;

public class CartPage extends NavigatableEShopPage {
    private static CartPage _instance;
    private final BrowserService _browserService = LoggingSingletonDriver.getInstance();

    public static CartPage getInstance()
    {
        if (_instance == null)
        {
            _instance = new CartPage();
        }

        return _instance;
    }

    public static CartPage getInstanceFactory() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return SingletonFactory.getInstance(CartPage.class);
    }

    @Override
    protected String getUrl() {
        return "http://demos.bellatrix.solutions/cart/";
    }

    public CartPageElements elements() {
        return new CartPageElements(elementFindService);
    }

    public BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(elementFindService);
    }

    public void applyCoupon(String coupon) throws InterruptedException {
        elements().couponCodeTextField().typeText(coupon);
        elements().applyCouponButton().click();
        _browserService.waitForAjax();
    }

    public void increaseProductQuantity(int newQuantity) throws InterruptedException {
        elements().quantityBox().typeText(String.valueOf(newQuantity));
        elements().updateCart().click();
        _browserService.waitForAjax();
    }

    public void clickProceedToCheckout()
    {
        elements().proceedToCheckout().click();
        _browserService.waitUntilPageLoadsCompletely();
    }

    public String getTotal()
    {
        return elements().totalSpan().getText();
    }

    public String getMessageNotification()
    {
        return elements().messageAlert().getText();
    }
}