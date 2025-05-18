import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.IOException;

public class ValidadorDOM {
    public static void main(String[] args) {
        try {
            File archivo = new File("Ej01.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true); // Activar validación con DTD
            factory.setNamespaceAware(false);

            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(new org.xml.sax.helpers.DefaultHandler() {
                public void error(SAXParseException e) {
                    System.out.println("❌ Error: " + e.getMessage());
                }

                public void fatalError(SAXParseException e) {
                    System.out.println("❌ Error fatal: " + e.getMessage());
                }

                public void warning(SAXParseException e) {
                    System.out.println("⚠️ Advertencia: " + e.getMessage());
                }
            });

            builder.parse(archivo);
            System.out.println("✅ Documento válido según DTD (DOM)");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("❌ Excepción: " + e.getMessage());
        }
    }
}
