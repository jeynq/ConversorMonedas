import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class Generador {

    public void guardarJson(Monedas monedas, String rutaArchivo) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter escritura = new FileWriter(rutaArchivo)) {
            escritura.write(gson.toJson(monedas));
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
            throw e;
        }
    }

    public void guardarJson(Monedas monedas) throws IOException {
        guardarJson(monedas, "monedas.json");
    }
}
