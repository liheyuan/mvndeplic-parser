package com.coder4.mvndeplic.parser;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class LicParserTest {

    private static enum TestFile {
        FILE1("1.txt"),
        FILE2("2.txt"),
        FILE3("3.txt"),
        FILE4("4.txt"),
        FILE5("5.txt");

        private String file;

        TestFile(String file) {
            this.file = file;
        }

        public String getFile() {
            return file;
        }
    }

    private File getTestFile(TestFile testFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(testFile.getFile()).getFile());
    }

    private String loadFile(TestFile testFile) throws FileNotFoundException {
        File file = getTestFile(testFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    // Test mvn lic single project
    @Test
    public void testSingle() throws FileNotFoundException {
        LicParseResult res = LicParser.parse(loadFile(TestFile.FILE1));
        Assert.assertEquals(46, res.getItems().size());
        Assert.assertEquals(2, res.getItems().get(0).getLicenses().size());
        Assert.assertEquals(1, res.getItems().get(2).getLicenses().size());
        Assert.assertEquals("Apache License 2.0", res.getItems().get(3).getLicenses().get(0));
        Assert.assertEquals("org.yaml", res.getItems().get(45).getDep().getGroupId());
        Assert.assertEquals("snakeyaml", res.getItems().get(45).getDep().getArtifactId());
        Assert.assertEquals("1.29", res.getItems().get(45).getDep().getVersion());
    }

    // Test mvn lic mutiple project
    @Test
    public void testEmpty() throws FileNotFoundException {
        LicParseResult res = LicParser.parse(loadFile(TestFile.FILE2));
        Assert.assertEquals(0, res.getItems().size());
    }

    // Test mvn lic invalid project
    @Test
    public void testInvalid() throws FileNotFoundException {
        LicParseResult res = LicParser.parse(loadFile(TestFile.FILE5));
        Assert.assertEquals(0, res.getItems().size());
    }

    // Test mvn lic in File Mode
    @Test
    public void testFile() throws FileNotFoundException {
        LicParseResult res = LicParser.parse(getTestFile(TestFile.FILE3));
        Assert.assertEquals(2, res.getItems().size());
    }
}
