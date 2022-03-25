package com.BelUsa.pageMethods;

import com.BelUsa.baseClass.ScenarioContext;
import com.BelUsa.managers.PageMethodManager;
import com.BelUsa.managers.PageObjectManager;
import com.BelUsa.pageObjects.*;
import com.BelUsa.utility.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonPageMethods {

    private WebDriver driver;
    private PageObjectManager pageObjectManager;
    private PageMethodManager pageMethodManager;
    private ScenarioContext scenarioContext;
    private Common commonPage;

    public CommonPageMethods(WebDriver driver, PageObjectManager pageObjectManager, PageMethodManager pageMethodManager, ScenarioContext scenarioContext) {
        this.driver				= driver;
        this.pageObjectManager 	= pageObjectManager;
        this.pageMethodManager  = pageMethodManager;
        this.scenarioContext 	= scenarioContext;
        commonPage              = pageObjectManager.getCommon();
    }






}
