import de.intarsys.pdf.content.CSDeviceBasedInterpreter;
import de.intarsys.pdf.content.CSException;
import de.intarsys.pdf.content.text.CSTextExtractor;
import de.intarsys.pdf.parser.COSLoadException;
import de.intarsys.pdf.pd.PDDocument;
import de.intarsys.pdf.pd.PDPage;
import de.intarsys.pdf.pd.PDPageNode;
import de.intarsys.pdf.pd.PDPageTree;
import de.intarsys.pdf.tools.kernel.PDFGeometryTools;
import de.intarsys.tools.locator.FileLocator;
import org.junit.Test;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertTrue;

public class BlahTest {
    @Test
    public void should() throws IOException, COSLoadException {
        File pdfFile = new File(new File("/Users/dan/Downloads"), "Beginning Android Games.pdf");
        assertTrue(pdfFile.exists());

        process(pdfFile, new PageProcessor() {
            @Override
            public void process(PDPage page) {
                System.out.println("BlahTest.process");
                AffineTransform pageTx = new AffineTransform();
                CSTextExtractor extractor = new CSTextExtractor();
                extractor.setBounds(new java.awt.Rectangle(0, 0, 100, 100));

                PDFGeometryTools.adjustTransform(pageTx, page);
                extractor.setDeviceTransform(pageTx);
                CSDeviceBasedInterpreter interpreter = new CSDeviceBasedInterpreter(null, extractor);
                interpreter.process(page.getContentStream(), page.getResources());

                System.out.println(extractor.getContent());
            }
        });
    }


    void process(File pdfFile, PageProcessor processor) {
        PDDocument document = null;

        try {
            document = PDDocument.createFromLocator(new FileLocator(pdfFile));
            PDPageTree pageTree = document.getPageTree();
            process(pageTree, processor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void process(PDPageTree tree, PageProcessor processor) {
        for (Object o : tree.getKids()) {
            PDPageNode node = (PDPageNode) o;
            if (node.isPage()) {
                processor.process((PDPage) node);
            } else {
                process((PDPageTree) node, processor);
            }
        }
    }

    interface PageProcessor {
        void process(PDPage page);
    }
}
