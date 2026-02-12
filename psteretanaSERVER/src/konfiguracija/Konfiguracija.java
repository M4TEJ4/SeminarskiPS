package konfiguracija;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Konfiguracija {

    private static Konfiguracija instanca;
    private final Properties konfiguracija = new Properties();

    // root projekta  + /config/config.properties
    private static final Path PUTANJA =
            Paths.get(System.getProperty("user.dir"), "config", "config.properties");

    private Konfiguracija() {
        try {
            // napravi config folder i fajl ako ne postoje
            if (!Files.exists(PUTANJA.getParent())) {
                Files.createDirectories(PUTANJA.getParent());
            }
            if (!Files.exists(PUTANJA)) {
                Files.createFile(PUTANJA);
            }

            // ucitaj
            try (InputStream in = Files.newInputStream(PUTANJA)) {
                konfiguracija.load(in);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Konfiguracija getInstanca() {
        if (instanca == null) {
            instanca = new Konfiguracija();
        }
        return instanca;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try (OutputStream out = Files.newOutputStream(PUTANJA)) {
            konfiguracija.store(out, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //  za debug
    public String getPutanjaDoFajla() {
        return PUTANJA.toAbsolutePath().toString();
    }
}
