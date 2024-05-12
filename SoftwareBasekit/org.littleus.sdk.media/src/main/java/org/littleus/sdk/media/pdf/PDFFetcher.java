package org.littleus.sdk.media.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFFetcher {
    private static final String PDF_FILE_NAME = "pdf_%s.png";
    private final String pdfFile;
    private final String outputPath;
    private final String imgFormat;

    public PDFFetcher(String pdfFile, String outputPath) {
        this(pdfFile, outputPath, "png");
    }

    public PDFFetcher(String pdfFile, String outputPath, String imgFormat) {
        this.pdfFile = pdfFile;
        this.outputPath = outputPath;
        this.imgFormat = imgFormat;
    }

    public void fetch() throws IOException {
        try (PDDocument document = Loader.loadPDF(new File(pdfFile))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();
            for (int i = 0; i < numberOfPages; i++) {
                BufferedImage bim = pdfRenderer.renderImage(i);
                String imageFileName = String.format(PDF_FILE_NAME, i + 1);
                File imageFile = new File(outputPath, imageFileName);
                ImageIO.write(bim, imgFormat, imageFile);
            }
        }
    }

}
