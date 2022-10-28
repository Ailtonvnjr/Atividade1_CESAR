package steps;

import base.BaseWeb;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook extends BaseWeb {

    @Before
    public void beforeScenario() {
        iniciarWebDriver();
    }

    @After
    public void afterScenario() {
        fecharWebDriver();
    }
}
