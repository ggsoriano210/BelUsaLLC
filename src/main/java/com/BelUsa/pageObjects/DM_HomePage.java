package com.BelUsa.pageObjects;

/*
ifr      -  iframe
btn      -  Button
chkbx    -  CheckBox
chklst   -  CheckBoxList
drpdwn   -  DropDownList
grdvew   -  GridView
hyrlnk   -  Hyperlink
img      -  Image
imgbtn   -  ImageButton
lbl      -  Label
lnkbtn   -  LinkButton
lnk      -  Link
lstbx    -  ListBox
lit      -  Literal
pnl      -  Panel
ph       -  PlaceHolder
rdbtn    -  RadioButton
rdbtnlst -  RadioButtonList
txtbx       Textbox
txt      -  Text
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DM_HomePage {
    public DM_HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //********************** Global Banner ****************************
    public final String xpath_GlobalBannerText = "//div[contains(@class,'promo_bar')]";
    @FindBy(xpath= xpath_GlobalBannerText)
    private WebElement txt_GlobalBanner;

    public final String xpath_GlobalBannerPromoCodeText = "//div[contains(@class,'promo_bar')]//following-sibling::span";
    @FindBy(xpath= xpath_GlobalBannerPromoCodeText)
    private WebElement txt_GlobalBannerPromoCode;

    public final String xpath_GlobalBannerSeeDetailsLink = "//div[contains(@class,'promo_bar')]//following-sibling::a";
    @FindBy(xpath= xpath_GlobalBannerSeeDetailsLink)
    private WebElement lnk_GlobalBannerSeeDetails;

    //********************** Header Section ****************************
    public final String xpath_DiscountMugLogoLink = "//div[@class='header_section']//a[@aria-label='DiscountMugs']";
    @FindBy(xpath= xpath_DiscountMugLogoLink)
    private WebElement lnk_DiscountMugLogo;

    public final String xpath_SearchTextBox = "//form[@name='frmSearch']//input[@id='keyword_search']";
    @FindBy(xpath= xpath_SearchTextBox)
    private WebElement txtbx_Search;

    public final String xpath_SearchItemsText = "//div[contains(@class,'products-container')]//div[contains(@class,'nxt-ac-item')]//span";
    @FindBy(xpath= xpath_SearchItemsText)
    private List<WebElement> txt_SearchItems;

    public final String xpath_SearchButton = "//form[@name='frmSearch']//button";
    @FindBy(xpath= xpath_SearchButton)
    private WebElement btn_Search;

    public final String xpath_ChatNowLink = "//div[contains(@class,'top_links')]//a[@id='bc-chat-online']";
    @FindBy(xpath= xpath_ChatNowLink)
    private WebElement lnk_ChatNow;

    public final String xpath_AccountLink = "//div[contains(@class,'top_links')]//a[@title='Account']";
    @FindBy(xpath= xpath_AccountLink)
    private WebElement lnk_Account;

    public final String xpath_TrackOrderLink = "//div[contains(@class,'account_wrap')]//div[contains(@class,'ac_dropdown')]//a[contains(@href,'track_order')]";
    @FindBy(xpath= xpath_TrackOrderLink)
    private WebElement lnk_TrackOrder;

    public final String xpath_CartLink = "//div[contains(@class,'top_links')]//a[@title='Cart']";
    @FindBy(xpath= xpath_CartLink)
    private WebElement lnk_Cart;

    public final String xpath_DrinkWareAndCanCoolersLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[1]";
    @FindBy(xpath= xpath_DrinkWareAndCanCoolersLink)
    private WebElement lnk_DrinkWareAndCanCoolers;

    public final String xpath_GlassWareAndBarWareLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[2]";
    @FindBy(xpath= xpath_GlassWareAndBarWareLink)
    private WebElement lnk_GlassWareAndBarWare;

    public final String xpath_BagsAndBackpacksLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[3]";
    @FindBy(xpath= xpath_BagsAndBackpacksLink)
    private WebElement lnk_BagsAndBackpacks;

    public final String xpath_ClothingAndAccessoriesLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[4]";
    @FindBy(xpath= xpath_ClothingAndAccessoriesLink)
    private WebElement lnk_ClothingAndAccessories;

    public final String xpath_MaskAndSanitizersLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[5]";
    @FindBy(xpath= xpath_MaskAndSanitizersLink)
    private WebElement lnk_MaskAndSanitizers;

    public final String xpath_PensPencilsAndHighlightersLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[6]";
    @FindBy(xpath= xpath_PensPencilsAndHighlightersLink)
    private WebElement lnk_PensPencilsAndHighlighters;

    public final String xpath_SportsAndOutdoorsLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[7]";
    @FindBy(xpath= xpath_SportsAndOutdoorsLink)
    private WebElement lnk_SportsAndOutdoors;

    public final String xpath_OfficeLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[8]";
    @FindBy(xpath= xpath_OfficeLink)
    private WebElement lnk_Office;

    public final String xpath_HomeAutoAndWellnessLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[9]";
    @FindBy(xpath= xpath_HomeAutoAndWellnessLink)
    private WebElement lnk_HomeAutoAndWellness;

    public final String xpath_TechAndMobileLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[10]";
    @FindBy(xpath= xpath_TechAndMobileLink)
    private WebElement lnk_TechAndMobile;

    public final String xpath_EventsAndOccasionsLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[11]";
    @FindBy(xpath= xpath_EventsAndOccasionsLink)
    private WebElement lnk_EventsAndOccasions;

    public final String xpath_NewArrivalsLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[12]";
    @FindBy(xpath= xpath_NewArrivalsLink)
    private WebElement lnk_NewArrivals;

    public final String xpath_ClearanceAndCloseoutItemsLink = "(//section[contains(@class,'new-header')]//li[contains(@class,'link-box')]//a[@class='header-menu-top-link'])[13]";
    @FindBy(xpath= xpath_ClearanceAndCloseoutItemsLink)
    private WebElement lnk_ClearanceAndCloseoutItems;

    public final String xpath_FastAccessLink = "//li[contains(@class,'link-box')]//a[@class='header-menu-top-link']/..";
    @FindBy(xpath= xpath_FastAccessLink)
    private List<WebElement> lnk_FastAccess;



    //****************** Chat Module Container *************************
    public final String xpath_ChatCloseButton = "icon icon-ic_close mild";
    @FindBy(xpath= xpath_ChatCloseButton)
    private WebElement btn_ChatClose;

    public final String xpath_ChatBackButton = "//div[@class='hotline-launcher  h-open   ']//div[@class='h-header   ']//i";
    @FindBy(xpath= xpath_ChatBackButton)
    private WebElement btn_ChatBack;

    public final String xpath_ChatSalesButton = "//ul[@class='channel-list']//li[1]//a";
    @FindBy(xpath= xpath_ChatSalesButton)
    private WebElement btn_ChatSales;

    public final String xpath_ChatCustomerServiceButton = "//ul[@class='channel-list']//li[2]//a";
    @FindBy(xpath= xpath_ChatCustomerServiceButton)
    private WebElement btn_ChatCustomerService;


    //****************** Banner Block Container *************************
    public final String xpath_HeroBannerLink = "(//div[contains(@class,'banner_block')]//a)[1]";
    @FindBy(xpath= xpath_HeroBannerLink)
    private WebElement lnk_HeroBanner;

    //****************** Custom Promotional Products *************************
    public final String xpath_PromoProductsLink = "//div[contains(@class,'shop_categories')]//div[contains(@class,'shop_category')]//a[2]";
    @FindBy(xpath= xpath_PromoProductsLink)
    private List<WebElement> lnk_PromoProducts;

    public final String xpath_ViewAllCategoriesButton = "//div[contains(@class,'shop_categories')]//div[@class='text-center mt-5']//a";
    @FindBy(xpath= xpath_ViewAllCategoriesButton)
    private WebElement btn_ViewAllCategories;

    //*****************************************************************************************************************
    //*************************************Start of Methods of Home Page***********************************************
    //*****************************************************************************************************************

    //********************** Global Banner ****************************
    public WebElement getGlobalBannerText() {return txt_GlobalBanner;}

    public WebElement getGlobalBannerPromoCodeText() {return txt_GlobalBannerPromoCode;}

    public WebElement getGlobalBannerSeeDetailsLink() {return lnk_GlobalBannerSeeDetails;}

    //********************** Header Section ****************************
    public WebElement getDiscountMugLogoLink() {return lnk_DiscountMugLogo;}

    public WebElement getSearchTextBox() {return txtbx_Search;}

    public List<WebElement> getSearchItemsText() {return txt_SearchItems;}

    public WebElement getSearchButton() {return btn_Search;}

    public WebElement getChatNowLink() {return lnk_ChatNow;}

    public WebElement getAccountLink() {return lnk_Account;}

    public WebElement getTrackOrderLink() {return lnk_TrackOrder;}

    public WebElement getCartLink() {return lnk_Cart;}

    public WebElement getDrinkWareAndCanCoolersLink() {return lnk_DrinkWareAndCanCoolers;}

    public WebElement getGlassWareAndBarWareLink() {return lnk_GlassWareAndBarWare;}

    public WebElement getBagsAndBackpacksLink() {return lnk_BagsAndBackpacks;}

    public WebElement getClothingAndAccessoriesLink() {return lnk_ClothingAndAccessories;}

    public WebElement getMaskAndSanitizersLink() {return lnk_MaskAndSanitizers;}

    public WebElement getPensPencilsAndHighlightersLink() {return lnk_PensPencilsAndHighlighters;}

    public WebElement getSportsAndOutdoorsLink() {return lnk_SportsAndOutdoors;}

    public WebElement getOfficeLink() {return lnk_Office;}

    public WebElement getHomeAutoAndWellnessLink() {return lnk_HomeAutoAndWellness;}

    public WebElement getTechAndMobileLink() {return lnk_TechAndMobile;}

    public WebElement getEventsAndOccasionsLink() {return lnk_EventsAndOccasions;}

    public WebElement getNewArrivalsLink() {return lnk_NewArrivals;}

    public WebElement getClearanceAndCloseoutItemsLink() {return lnk_ClearanceAndCloseoutItems;}

    public List<WebElement> getFastAccessLink() {return lnk_FastAccess;}

    //****************** Chat Module Container *************************
    public WebElement getChatCloseButton() {return btn_ChatClose;}

    public WebElement getChatBackButton() {return btn_ChatBack;}

    public WebElement getChatSalesButton() {return btn_ChatSales;}

    public WebElement getChatCustomerServiceButton() {return btn_ChatCustomerService;}


    //****************** Banner Block Container *************************
    public WebElement getHeroBannerLink() {return lnk_HeroBanner;}

    //****************** Custom Promotional Products *************************
    public List<WebElement> getPromoProductsLink() {return lnk_PromoProducts;}

    public WebElement getViewAllCategoriesButton() {return btn_ViewAllCategories;}

}
