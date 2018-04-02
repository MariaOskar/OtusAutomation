import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import static org.junit.Assert.assertEquals;

public class Main {

        public WebDriver createFirefoxDriver (){
            WebDriver driver = new FirefoxDriver();
            return driver;
        }

        public WebDriver createCromeDriver(){
            WebDriver driver = new ChromeDriver();
            return driver;
        }

        public WebDriver createOperaDriver(){
           OperaOptions oo = new OperaOptions();
            oo.setBinary("C:\\Program Files\\Opera\\52.0.2871.40\\opera.exe");
            WebDriver driver = new OperaDriver(oo);
            return driver;
        }

        public WebDriver createEdgeDriver(){
            WebDriver driver = new EdgeDriver();
            return driver;
        }

        public WebDriver getDriver(){
            String value =  System.getProperty("webdriver");
            if (value == null) value = "chrome";
            switch (value){
                case "chrome": return createCromeDriver();
                case "firefox": return createFirefoxDriver();
                case "opera": return createOperaDriver();
                case "edge": return createEdgeDriver();
                default: return createCromeDriver();
            }
        }
    @Test
    public void test() {
        WebDriver driver = getDriver();
        driver.get("http://blazedemo.com/");
        WebElement submit = driver.findElement(By.cssSelector("input[type=submit]"));
        WebElement from = driver.findElement(By.cssSelector("select[name=fromPort] option"));
        String fromValue = from.getAttribute("value");
        WebElement to = driver.findElement(By.cssSelector("select[name=toPort] option"));
        String toValue = to.getAttribute("value");
        submit.click();

        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("reserve.php"));

        assertEquals("http://blazedemo.com/reserve.php", driver.getCurrentUrl());
        WebElement fromPortInput = driver.findElement(By.cssSelector("input[name=fromPort]"));
        WebElement toPortInput = driver.findElement(By.cssSelector("input[name=toPort]"));
        assertEquals(fromValue, fromPortInput.getAttribute("value"));
        assertEquals(toValue, toPortInput.getAttribute("value"));
        driver.quit();


    }



}
