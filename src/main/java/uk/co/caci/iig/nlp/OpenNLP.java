package uk.co.caci.iig.nlp;

import java.io.InputStream;

import org.apache.log4j.Logger;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class OpenNLP {
    private static final Logger LOGGER = Logger.getLogger(OpenNLP.class);

    private SentenceDetectorME sentenceDetector;
    private TokenizerME tokenizer;
    private NameFinderME nameFinder;
    private NameFinderME locationFinder;

    /**
     * Load models in constructor
     */
    public OpenNLP() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("models/en-sent.bin");
            SentenceModel model = new SentenceModel(inputStream);
            sentenceDetector = new SentenceDetectorME(model);
        } catch (Throwable e) {
            LOGGER.error("[ERROR] Could not find Model file for sentence detector", e);
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("models/en-token.bin");
            TokenizerModel model = new TokenizerModel(inputStream);
            tokenizer = new TokenizerME(model);
        } catch (Throwable e) {
            LOGGER.error("[ERROR] Could not find Model file for Tokenizer", e);
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("models/en-ner-person.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(inputStream);
            nameFinder = new NameFinderME(model);
        } catch (Throwable e) {
            LOGGER.error("[ERROR] Could not find Model file for name finder", e);
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("models/en-ner-location.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(inputStream);
            locationFinder = new NameFinderME(model);
        } catch (Throwable e) {
            LOGGER.error("[ERROR] Could not find Model file for name finder", e);
        }
    }

    public NameFinderME getNameFinder() {
        return nameFinder;
    }

    public NameFinderME getLocationFinder() {
        return locationFinder;
    }

    public SentenceDetectorME getSentenceDetector() {
        return sentenceDetector;
    }

    public TokenizerME getTokenizer() {
        return tokenizer;
    }
}
