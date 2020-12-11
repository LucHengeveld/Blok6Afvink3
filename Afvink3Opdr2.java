import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Afvink3Opdr2 {

    public static ArrayList<String> regelsbestandArray = new ArrayList<>();

    public static ArrayList<String> geneIDArray = new ArrayList<>();
    public static ArrayList<String> symbolArray = new ArrayList<>();
    public static ArrayList<String> chromosomeArray = new ArrayList<>();
    public static ArrayList<String> tempArmMapLocArray = new ArrayList<>();

    public static ArrayList<String> armArray = new ArrayList<>();
    public static ArrayList<String> mapLocationArray = new ArrayList<>();

    public static ArrayList<ArrayList<String>> tempArray1 = new ArrayList<>();
    public static ArrayList<String> tempArray2 = new ArrayList<>();

    public static ArrayList<ArrayList<String>> sortedArray = new ArrayList<>();
    //----------------------------------------------
    public static ArrayList<String> regelsbestandLinked = new ArrayList<>();

    public static LinkedList<String> geneIDLinked = new LinkedList<>();
    public static LinkedList<String> symbolLinked = new LinkedList<>();
    public static LinkedList<String> chromosomeLinked = new LinkedList<>();
    public static LinkedList<String> tempArmMapLocLinked = new LinkedList<>();

    public static LinkedList<String> armLinked = new LinkedList<>();
    public static LinkedList<String> mapLocationLinked = new LinkedList<>();

    public static LinkedList<LinkedList<String>> tempLinked1 = new LinkedList<>();
    public static LinkedList<String> tempLinked2 = new LinkedList<>();

    public static LinkedList<LinkedList<String>> sortedLinked = new LinkedList<>();

    public static void main(String[] args) {

        Scanner askListType = new Scanner(System.in);
        System.out.println("ArrayList of LinkedList?");
        String listSoort = askListType.nextLine();

        if (listSoort.toLowerCase().equals("arraylist")) {

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
                    regelsbestandArray.add(regel);
                }
                regelsbestandArray.remove(0);

                long start1 = System.nanoTime();

                for (int i = 0; i < regelsbestandArray.size(); i++) {
                    //Waardes na splitten op tabs in array
                    //tax-id 0 - GeneID 1 - Symbol 2 - LocusTag	3 - Synonyms 4 - dbXrefs 5 - chromosome 6 - map_location 7 -
                    //description 8 - type_of_gene 9 - Symbol_from_nomenclature_authority 10 -
                    //Full_name_from_nomenclature_authority 11 - Nomenclature_status 12 - Other_designations 13 -
                    //Modification_date 14 - Feature_type 15
                    geneIDArray.add(Arrays.toString(new String[]{regelsbestandArray.get(i).split("\t")[1]}));
                    symbolArray.add(Arrays.toString(new String[]{regelsbestandArray.get(i).split("\t")[2]}));
                    chromosomeArray.add(Arrays.toString(new String[]{regelsbestandArray.get(i).split("\t")[6]}));
                    tempArmMapLocArray.add(Arrays.toString(new String[]{regelsbestandArray.get(i).split("\t")[7]}));

                    int pArm = tempArmMapLocArray.get(i).indexOf('p');
                    int qArm = tempArmMapLocArray.get(i).indexOf('q');

                    if (pArm != -1) {
                        armArray.add("p");
                        mapLocationArray.add(tempArmMapLocArray.get(i).replace("]", "").substring(pArm + 1));
                    } else if (qArm != -1) {
                        armArray.add("q");
                        mapLocationArray.add(tempArmMapLocArray.get(i).replace("]", "").substring(qArm + 1));
                    } else {
                        armArray.add("Onbekend");
                        mapLocationArray.add("-");
                    }
                }

                tempArray1.add(geneIDArray);
                tempArray1.add(symbolArray);
                tempArray1.add(chromosomeArray);
                tempArray1.add(armArray);
                tempArray1.add(mapLocationArray);

                for (int i = 0; i < geneIDArray.size(); i++) {
                    tempArray2.add(geneIDArray.get(i) + " " + symbolArray.get(i) + " " + chromosomeArray.get(i) + " " +
                            armArray.get(i) + " " + mapLocationArray.get(i));
                    sortedArray.add(new ArrayList<>());
                    sortedArray.get(i).add(Arrays.toString(tempArray2.get(i).split(" ")).replace("[", "").replace("]", ""));
                }

                long end1 = System.nanoTime();
                System.out.println("Opbouwen van ArrayList duurt " + (end1 - start1) + " nanoseconden");

                long start2 = System.nanoTime();
                sortedArray.get(10000);
                long end2 = System.nanoTime();
                System.out.println("Gene opzoeken in ArrayList op pos 10000 duurt " + (end2 - start2) + " nanoseconden");
            }

        } else if (listSoort.toLowerCase().equals("linkedlist")) {
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
                    regelsbestandLinked.add(regel);
                }
                regelsbestandLinked.remove(0);

                long start1 = System.nanoTime();

                for (int i = 0; i < regelsbestandLinked.size(); i++) {
                    //Waardes na splitten op tabs in array
                    //tax-id 0 - GeneID 1 - Symbol 2 - LocusTag	3 - Synonyms 4 - dbXrefs 5 - chromosome 6 - map_location 7 -
                    //description 8 - type_of_gene 9 - Symbol_from_nomenclature_authority 10 -
                    //Full_name_from_nomenclature_authority 11 - Nomenclature_status 12 - Other_designations 13 -
                    //Modification_date 14 - Feature_type 15
                    geneIDLinked.add(Arrays.toString(new String[]{regelsbestandLinked.get(i).split("\t")[1]}));
                    symbolLinked.add(Arrays.toString(new String[]{regelsbestandLinked.get(i).split("\t")[2]}));
                    chromosomeLinked.add(Arrays.toString(new String[]{regelsbestandLinked.get(i).split("\t")[6]}));
                    tempArmMapLocLinked.add(Arrays.toString(new String[]{regelsbestandLinked.get(i).split("\t")[7]}));

                    int pArm = tempArmMapLocLinked.get(i).indexOf('p');
                    int qArm = tempArmMapLocLinked.get(i).indexOf('q');

                    if (pArm != -1) {
                        armLinked.add("p");
                        mapLocationLinked.add(tempArmMapLocLinked.get(i).replace("]", "").substring(pArm + 1));
                    } else if (qArm != -1) {
                        armLinked.add("q");
                        mapLocationLinked.add(tempArmMapLocLinked.get(i).replace("]", "").substring(qArm + 1));
                    } else {
                        armLinked.add("Onbekend");
                        mapLocationLinked.add("-");
                    }
                }

                tempLinked1.add(geneIDLinked);
                tempLinked1.add(symbolLinked);
                tempLinked1.add(chromosomeLinked);
                tempLinked1.add(armLinked);
                tempLinked1.add(mapLocationLinked);

                for (int i = 0; i < geneIDLinked.size(); i++) {
                    tempLinked2.add(geneIDLinked.get(i) + " " + symbolLinked.get(i) + " " + chromosomeLinked.get(i) + " " +
                            armLinked.get(i) + " " + mapLocationLinked.get(i));
                    sortedLinked.add(new LinkedList<>());
                    sortedLinked.get(i).add(Arrays.toString(tempLinked2.get(i).split(" ")).replace("[", "").replace("]", ""));
                }

                long end1 = System.nanoTime();
                System.out.println("Opbouwen van LinkedList duurt " + (end1 - start1) + " nanoseconden");

                long start2 = System.nanoTime();
                sortedLinked.get(10000);
                long end2 = System.nanoTime();
                System.out.println("Gene opzoeken op pos 10000 in LinkedList duurt " + (end2 - start2) + " nanoseconden");
            }
        } else {
            System.out.println("Alleen mogelijk om te kiezen tussen een arraylist en linkedlist");
        }
    }
}