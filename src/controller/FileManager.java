package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	private static final String DIRECTORY = "Games/";
	private static final int MAX_ARCH = 3;
	
    public static void Serializar(Serializable objeto, String nameArch) {
        String pathComplete = DIRECTORY + nameArch;
        
        if (GetArchs() >= MAX_ARCH) {
            System.out.println("Error: Se alcanzó el límite máximo de archivos.");
            return;
        }
        
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(pathComplete))) {
            outputStream.writeObject(objeto);
            System.out.println("Objeto serializado exitosamente en " + pathComplete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int GetArchs() {
        File directorio = new File(DIRECTORY);
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            return archivos.length;
        }

        return 0;
    }
    public static Object ChargeArch(File archivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivo))) {
            // Lee el objeto desde el archivo y devuélvelo
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Manejo de errores, puedes ajustar según tus necesidades
        }
    }
    public static List<File> getListArchs() {
        System.out.println("Archivos disponibles:");

        File directory = new File(DIRECTORY);
        File[] archs= directory.listFiles();
        
        List<File> ListArchs = new ArrayList<>();

        if (archs != null && archs.length > 0) {
            for (File archivo : archs) {
                ListArchs.add(archivo);
            }
        }

        return ListArchs;
    }
}
