import com.itextpdf.text.pdf.parser.*;

import java.io.IOException;

/**
 * Created by pancara on 6/2/14.
 */
public class ExtractionStrategy extends LocationTextExtractionStrategy {

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        super.renderText(renderInfo);
        System.out.print("[" + renderInfo.getText() + "]");
        System.out.print("   " + renderInfo.getBaseline().getStartPoint().get(Vector.I1) + ", " + +renderInfo.getBaseline().getStartPoint().get(Vector.I2));
        System.out.println(" -> " + renderInfo.getBaseline().getEndPoint().get(Vector.I1) + ", " + +renderInfo.getBaseline().getEndPoint().get(Vector.I2));
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        super.renderImage(renderInfo);

        try {
            PdfImageObject image = renderInfo.getImage();
            if (image == null) {
                return;
            }
            System.out.println(renderInfo.getImage().getFileType());
            System.out.println(renderInfo.getImage().getImageAsBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
