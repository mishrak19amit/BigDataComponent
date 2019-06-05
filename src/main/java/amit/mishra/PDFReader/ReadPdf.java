package amit.mishra.PDFReader;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
public class ReadPdf {

	public static void main(String[] args) throws IOException {

		try (PDDocument document = PDDocument.load(new File("C:\\Users\\mishr\\Downloads\\IE_09_Sep_2018.pdf"))) {

			document.getClass();

		/*	PDFReader reader = new PDFReader(new File("C:\\\\Users\\\\mishr\\\\Downloads\\\\IE_09_Sep_2018.pdf"));
			reader.open(); // open the file.
			int pages = reader.getNumberOfPages();

			for (int i = 0; i < pages; i++) {
				String text = reader.extractTextFromPage(i);
				System.out.println("Page " + i + ": " + text);
			}

			reader.close();*/

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				// System.out.println("Text:" + st);

				// split by whitespace
				String lines[] = pdfFileInText.split("\\r?\\n");
				for (String line : lines) {
					System.out.println(line);
				}

			}

		}

	}
}
