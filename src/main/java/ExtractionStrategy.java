import com.itextpdf.text.pdf.parser.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by pancara on 6/2/14.
 */
public class ExtractionStrategy extends LocationTextExtractionStrategy {
    private int imgIndex = 0;

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        super.renderText(renderInfo);
        System.out.print("[" + renderInfo.getText() + "]");
        System.out.print("   " + renderInfo.getBaseline().getStartPoint().get(Vector.I1) + ", "
                + renderInfo.getBaseline().getStartPoint().get(Vector.I2));
        System.out.println(" -> " + renderInfo.getBaseline().getEndPoint().get(Vector.I1) + ", "
                + renderInfo.getBaseline().getEndPoint().get(Vector.I2));
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        super.renderImage(renderInfo);
        try {
            PdfImageObject image = renderInfo.getImage();
            if (image == null) {
                return;
            }
            saveImage(renderInfo.getImage().getFileType(), renderInfo.getImage().getImageAsBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImage(String type, byte[] bytes) {
        imgIndex++;
        File target = new File(System.getProperty("user.dir"), String.format("result/images/%d.%s", imgIndex, type));
        OutputStream os;
        try {
            os = new FileOutputStream(target);
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {

        }
    }
}
