import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Folder path: ");

        String folderPath = scanner.next();

        String[] files = new File(folderPath).list();

        assert files != null;
        for (String filename: files) {

            Path photo = Paths.get(folderPath + "\\" + filename);

            System.out.println(filename);

            int photoYear = Integer.parseInt(filename.substring(4,8));
            int photoMonth = Integer.parseInt(filename.substring(8,10));
            int photoDay = Integer.parseInt(filename.substring(10,12));


            Calendar newPhotoDate = Calendar.getInstance();
            newPhotoDate.set(Calendar.DAY_OF_MONTH, photoDay);
            newPhotoDate.set(Calendar.YEAR, photoYear);
            newPhotoDate.set(Calendar. MONTH, photoMonth-1);

            Files.setAttribute(photo, "creationTime", FileTime.fromMillis(newPhotoDate.getTimeInMillis()));
        }
    }
}
