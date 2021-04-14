import Utilities.Constants;
import com.github.opendevl.JFlat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class JsonTest {
    WebDriver driver;

    @Test
    public void ReadJson() throws FileNotFoundException, UnsupportedEncodingException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://192.168.7.22:9200/customer_alerts/_search");
        String Body =  driver.findElement(By.cssSelector("body pre")).getText();
        System.out.println(Body);
        JFlat flatMe = new JFlat(Body);
        File file = new File(Constants.CSVFilePath);
        flatMe.json2Sheet().write2csv(String.valueOf(file));
        driver.quit();
    }
}
