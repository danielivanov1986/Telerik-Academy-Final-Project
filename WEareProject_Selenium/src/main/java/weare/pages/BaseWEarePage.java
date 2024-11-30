package weare.pages;

import org.openqa.selenium.By;
import testframework.PropertiesManager;
import testframework.core.BaseWebPage;

public class BaseWEarePage extends BaseWebPage {
    public BaseWEarePage(String pageSpecificUrl) {
        super(pageSpecificUrl);
    }

    @Override
    public String getBasePageUrl() { return PropertiesManager.getConfigProperties().getProperty("weareBaseUrl"); }
}
