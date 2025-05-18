import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;

public class ValidadorSAX {
    public static void main(String[] args) {
        try {
            File archivo = new File("Ej01.xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true); // Activar validación con DTD
            factory.setNamespaceAware(false);

            SAXParser parser = factory.newSAXParser();

            DefaultHandler manejador = new DefaultHandler() {
                public void error(SAXParseException e) {
                    System.out.println("❌ Error: " + e.getMessage());
                }

                public void fatalError(SAXParseException e) {
                    System.out.println("❌ Error fatal: " + e.getMessage());
                }

                public void warning(SAXParseException e) {
                    System.out.println("⚠️ Advertencia: " + e.getMessage());
                }
            };

            parser.parse(archivo, manejador);
            System.out.println("✅ Documento válido según DTD (SAX)");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("❌ Excepción: " + e.getMessage());
        }
    }
}
