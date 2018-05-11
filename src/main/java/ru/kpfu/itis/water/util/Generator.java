package ru.kpfu.itis.water.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * https://stackoverflow.com/questions/19743124/java-password-generator
 * Answer by George Siggouroglou
 */

public final class Generator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean usePunctuation;

    private Generator() {
        throw new UnsupportedOperationException("Empty constructor is not supported.");
    }

    private Generator(GeneratorBuilder builder) {
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.usePunctuation = builder.usePunctuation;
    }

    public static class GeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean usePunctuation;

        public GeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.usePunctuation = false;
        }

        public GeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        public GeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public GeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public GeneratorBuilder usePunctuation(boolean usePunctuation) {
            this.usePunctuation = usePunctuation;
            return this;
        }

        public Generator build() {
            return new Generator(this);
        }
    }

    public String generate(int length) {
        if (length <= 0) {
            return "";
        }

        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<>(4);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }
        if (usePunctuation) {
            charCategories.add(PUNCTUATION);
        }

        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }
}
