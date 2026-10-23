
package genric_Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot_Utility {

    public static String takeScreenshot(WebDriver d, String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        File folder = new File("./screenshots/");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File source = ((TakesScreenshot) d)
                .getScreenshotAs(OutputType.FILE);

        File destination = new File("./screenshots/"
                + testName + "_" + timeStamp + ".png");

        try {
            Files.copy(source.toPath(), destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: "
                    + destination.getAbsolutePath());

            return destination.getAbsolutePath();

        } catch (IOException e) {
            System.out.println("Unable to save screenshot: "
                    + e.getMessage());
            return null;
        }
    }
}

