package IntegracionBackFront.backfront.Services.Cloudinary;

import com.cloudinary.Cloudinary;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class CloudinaryService {
    //1. Definir el tamaño de las imagenes
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    //2. Definir las extensiones permitidas
    private static final String[] ALLOWED_EXTENNSIONS = {".jpg", ".jpeg", ".png"};
    //3. Atributo Claudinary
    private final Cloudinary cloudinary;
    //Constructor para inyeccion de dependencias de Cloudinary
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file)throws IOException {
        validateImage(file);
    }

    private void validateImage(MultipartFile file) {
        //1 Verificar si el archivo esta vacio
        if (file.isEmpty()){
            throw new IllegalArgumentException ("El archivo esta vacio");
        }

        //2 verificar si el tamaño excede el limite permitido
    if (file.getSize() > MAX_FILE_SIZE{

        throw new IllegalReceiveException("El tamaño del archivo no debe ser mayor a 5MB");
        }

        //3 verificar el bnombre original del archivo
        String  originalFileName = file.getOriginalFilename();
        if (originalFileName == null){
            throw new IllegalArgumentException("Nombre de archivo invalido");
        }


        //4 Extraer y validar la extension del archivo
        String extension = originalFileName .substring(originalFileName.lastIndexOf(".")).toLowerCase();
        if(!Arrays.asList(ALLOWED_EXTENNSIONS).contains(extension)){
            throw  new IllegalArgumentException("Solo Se permite archivos JPG, JPEG, PNG");
        }

        //5 Verificar que el tipo MIME sea una imagen
        if (!file.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("El archivo debe ser una imagen valida");
        }
    }
}
