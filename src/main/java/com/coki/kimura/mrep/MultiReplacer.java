package com.coki.kimura.mrep;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * MultiReplacer
 */
public class MultiReplacer {

    public static void main(String[] args) {
        try {
            List<String> linesKey = Files.readAllLines(Path.of("keyword.txt"), StandardCharsets.UTF_8);
            Map<String, String> keywords = new HashMap<>();
            for (String line : linesKey) {
                keywords.put(line.split(",")[0], line.split(",")[1]);
            }

            List<String> lines = Files.readAllLines(Path.of("sample.txt"), StandardCharsets.UTF_8);

            List<String> linesReplaced = new ArrayList<>();
            for (String line : lines) {
                for (Entry<String, String> keyword :keywords.entrySet()) {
                   line = line.replace(keyword.getKey(), keyword.getValue());
                }
                linesReplaced.add(line);
            }

            Files.createFile(Path.of("sample_replaced.txt"));
            Files.write(Path.of("sample_replaced.txt"), linesReplaced, Charset.forName("UTF-8"),
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}