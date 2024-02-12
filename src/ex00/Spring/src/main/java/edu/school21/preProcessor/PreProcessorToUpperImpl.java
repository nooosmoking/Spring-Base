package edu.school21.preProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String translate(String source) {
        return source.toUpperCase();
    }
}
