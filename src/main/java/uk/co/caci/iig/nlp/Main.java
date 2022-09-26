package uk.co.caci.iig.nlp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String DEFAULT = "source.txt";

    public static void main(String[] args) {
        String fileName = (args.length > 0) ? args[0] : DEFAULT;
        try {
            String fileContent = Files.readString(Paths.get(fileName));
            OpenNLP entityExtractor = new OpenNLP();

            new Analyser().analyse(fileContent, entityExtractor);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
