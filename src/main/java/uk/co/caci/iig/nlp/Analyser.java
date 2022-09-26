package uk.co.caci.iig.nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Analyser {
    private static final Logger LOGGER = Logger.getLogger(Analyser.class);

    /**
     * Performs analysis of the source text using the models available under the
     * OpenNLP parameter
     * 
     * @param source  The source text
     * @param openNlp Container for each OpenNLP model
     */
    public void analyse(String source, OpenNLP openNlp) throws IOException {
        LOGGER.info("Analysing source of size " + source.length());

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] arr = tokenizer.tokenize(source);

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        LOGGER.info("Extracting persons...");
        findInfo(arr, "src/main/resources/models/en-ner-person.bin");
        LOGGER.info("Extracting locations...");
        findInfo(arr, "src/main/resources/models/en-ner-location.bin");
        LOGGER.info("Extracting money...");
        findInfo(arr, "src/main/resources/models/en-ner-money.bin");
        LOGGER.info("Extracting organizations...");
        findInfo(arr, "src/main/resources/models/en-ner-organization.bin");

    }

    public void findInfo(String[] arr, String modelSrc) throws IOException {

        InputStream is = new FileInputStream(modelSrc);

        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);

        Span nameSpans[] = nameFinder.find(arr);

        // nameSpans contain all the possible entities detected
        for(Span s: nameSpans){
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for(int index=s.getStart();index<s.getEnd();index++){
                System.out.print(arr[index]+" ");
            }
            System.out.println();
        }
    }
}
