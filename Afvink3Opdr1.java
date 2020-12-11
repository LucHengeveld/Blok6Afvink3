import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Afvink3Opdr1 {

    public static String bestandstring;
    public static Stack<Character> charStackOpen = new Stack<>();
    public static Stack<Character> charStackClose = new Stack<>();

    public static void main(String[] args) {

        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = fc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            String bestandslocatie = selectedFile.getAbsolutePath();

            File bestand = new File(bestandslocatie);
            Scanner bestandlezer = null;
            try {
                bestandlezer = new Scanner(bestand);
            } catch (FileNotFoundException e) {
                System.out.println("Bestand is niet gevonden. Voer een nieuw bestand in.");
            }
            while (true) {
                assert bestandlezer != null;
                if (!bestandlezer.hasNextLine()) break;
                String regel = bestandlezer.nextLine();
                bestandstring += regel;
            }
        }

        bestandstring = bestandstring.replace("\t", "").replace("\n", "").
                replace(" ", "");

        String haakjes = bestandstring.replaceAll("[^(\\[\\]){}]", "").replace("\\", "");

        for (int i = 0; i < haakjes.length(); i++) {
            char letter = haakjes.charAt(i);
            if (letter == '(' || letter == '[' || letter == '{') {
                charStackOpen.push(letter);
            } else {
                charStackClose.push(letter);
            }
        }

        int count = 0;

        for (Character character : charStackOpen) {
            if (character == '{' & charStackClose.lastElement() == '}') {
                charStackClose.pop();
            } else if (character == '(' & charStackClose.lastElement() == ')') {
                charStackClose.pop();
            } else if (character == '[' & charStackClose.lastElement() == ']') {
                charStackClose.pop();
            } else {
                count++;
            }
        }

        if (count > 0) {
            System.out.println("Haakjes zijn onjuist geopend / afgesloten");
        } else {
            System.out.println("Alle haakjes zijn juist afgesloten");
        }
    }
}