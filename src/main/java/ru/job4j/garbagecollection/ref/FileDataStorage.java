package ru.job4j.garbagecollection.ref;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 19.04.2021
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDataStorage implements DataStorage<String, String> {

    private static final Logger LOG = LoggerFactory.getLogger(FileDataStorage.class.getName());

    @Override
    public String getValue(String path) {
        String rsl = null;
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            rsl = read.readLine();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }
}
