// package utils;

// import java.io.File;
// import java.io.IOException;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.apache.commons.io.FileUtils;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.WebDriver;

// public class Screenshot {
//     public static String getScreenShot(WebDriver driver, String fileName) throws IOException {
//         DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
//         Date date = new Date();
        
//         String directoryPath = System.getProperty("user.dir") + "/screenshot/";
//         File directory = new File(directoryPath);
        
//         if (!directory.exists()) {
//             directory.mkdir();
//         }
        
//         File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//         String destination = directoryPath + fileName + "-" + dateFormat.format(date) + ".png";
//         File target = new File(destination);
//         FileUtils.copyFile(scrFile, target);
//         return destination;
//     }
// }

package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshot {
    public String captureScreenshotAsBase64(WebDriver driver, String screenshotName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        String screenshotsDirPath = System.getProperty("user.dir") + "/src/main/screenshots/";
        File screenshotsDir = new File(screenshotsDirPath);
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = screenshotsDirPath + screenshotName + "_" + timestamp + ".png";

        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64Screenshot = convertToBase64(destinationScreenshotPath);
        return base64Screenshot;
    }

    private String convertToBase64(String imagePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(imagePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileContent = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

