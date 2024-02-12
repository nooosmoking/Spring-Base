package edu.school21.preProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String translate(String source) {
        return source.toLowerCase();
    }
}
