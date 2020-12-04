import com.victor.tank.ResourceMgr;
import com.victor.tank.TankFrame;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    public void test() {

        try {
            BufferedImage image =
                    ImageIO.read(new File("C:/Users/victor/IdeaProjects/victor/tank/src/main/resources/images/0.gif"));
            assertNotNull(image);

            String path = URLDecoder.decode(TankFrame.class.getProtectionDomain()
                    .getCodeSource().getLocation().getFile(), "UTF-8");
            System.out.println(path);

            BufferedImage image1 =
                    ImageIO.read(TankFrame.class.getClassLoader().getResourceAsStream("images/0.gif"));
            assertNotNull(image1);
            //Random random = new Random();
            /*for (int i = 0; i < 100; i++) {
                System.out.println(random.nextInt(10)+" ");
            }*/
            System.out.println(ResourceMgr.explodes.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}