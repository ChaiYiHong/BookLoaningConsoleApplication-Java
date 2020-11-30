import java.util.ArrayList;
import java.util.Scanner;

public class NovelRecordSystem {
    // Declare Variables and object globally
    static ArrayList<String> arrstrNovels = new ArrayList<String>();
    static Scanner Sc = new Scanner(System.in);

    public static void main(String[] args) {
        String strOptionMenu = "";
        String strStudentName;
        System.out.println("----------------------------------");
        System.out.println("|       Novels Record Program    |");
        System.out.println("|           Login Page           |");
        System.out.println("----------------------------------");
        System.out.println("Please enter your name");
        System.out.print("> ");
        strStudentName = Sc.nextLine();
        System.out.println("\nWelcome Back! " + strStudentName);

        while (!strOptionMenu.equalsIgnoreCase("E")) {
            fnMainMenu();
            strOptionMenu = Sc.next();
            Sc.nextLine();
            System.out.println();
            switch (strOptionMenu) {
                case "A":
                case "a":
                    fnInsert();
                    break;
                case "B":
                case "b":
                    fnView();
                    break;
                case "C":
                case "c":
                    fnUpdateMenu();
                    break;
                case "E":
                case "e":
                    break;
                default:
                    System.out.println("* Invalid Option!");
            }
        }
    }

    public static void fnMainMenu() {
        System.out.println("----------------------------------");
        System.out.println("|      Novels Record Program     |");
        System.out.println("----------------------------------");
        System.out.println("|      A. Insert New Book        |");
        System.out.println("|      B. View Book List         |");
        System.out.println("|      C. Update The Book List   |");
        System.out.println("|      E. Exit The Program       |");
        System.out.println("----------------------------------");
        System.out.println("Please input your choice");
        System.out.print("> ");
    }

    public static void fnInsert() {
        String strOptionInsert = "Yes";
        String strNovelInsert;
        int iTotalNovel = 0;
        boolean bNovelInput;
        boolean bDuplicate = false;
        do {
            if (strOptionInsert.equalsIgnoreCase("Yes")){
                do {
                    try {
                        System.out.println("How many novels do you want to input? (E.g. 5)");
                        System.out.print("> ");
                        iTotalNovel = Sc.nextInt();
                        bNovelInput = true;
                    } catch (Exception e) {
                        System.out.println();
                        System.out.print("* Invalid Input!");
                        bNovelInput = false;
                        Sc.nextLine();
                    }
                    System.out.println();
                } while (!bNovelInput);
                System.out.println("Please Input the book name");
                Sc.nextLine(); // To avoid strNovelInsert will accept the enter input
                for (int x = 0; x < iTotalNovel; x++) {
                    System.out.print("> ");
                    strNovelInsert = Sc.nextLine();
                    for (String arrstrNovel : arrstrNovels) {
                        bDuplicate = strNovelInsert.equalsIgnoreCase(arrstrNovel);
                        if (bDuplicate) {
                            x--;
                            break;
                        }
                    }
                    if (!bDuplicate) {
                        arrstrNovels.add(strNovelInsert);
                    } else {
                        System.out.println("* The novel \"");
                        System.out.print(strNovelInsert);
                        System.out.println("\" is already recorded in the system");
                    }
                }
            } else {
                System.out.print("* Invalid Option!");
            }
            System.out.println();
            System.out.println("Do you want to insert another book? (Yes or No)");
            System.out.print("> ");
            strOptionInsert = Sc.nextLine();
            System.out.println();
        } while (!strOptionInsert.equalsIgnoreCase("No"));
    }

    public static void fnView() {
        String strOptionView = "A";
        if (arrstrNovels.size() > 0) {
            do {
                if (strOptionView.equalsIgnoreCase("A") || strOptionView.equalsIgnoreCase("B")) {
                    if (strOptionView.equalsIgnoreCase("A")) {
                        fnSorting("BubbleSort");
                    } else {
                        fnSorting("SelectionSort");
                    }
                    // strIndent is a string contain of 33 spaces
                    String strIndent = "                                 ";
                    System.out.println("----------------------------------");
                    System.out.println("|          List of Books         |");
                    System.out.println("----------------------------------");
                    for (int i = 0; i < arrstrNovels.size(); i++) {
                        // strTemp and strTemp2 is used to adjust the indent of the box;
                        String strOutputTemp = "|      " + (i+1) + ". " + arrstrNovels.get(i);
                        String strTemp2 = strIndent.substring(0, (33 - strOutputTemp.length()));
                        System.out.println(strOutputTemp + strTemp2 + "|");
                    }
                } else {
                    System.out.println("* Invalid Option!");
                }
                System.out.println("----------------------------------");
                System.out.println("|      A. Ascending Order        |");
                System.out.println("|      B. Descending Order       |");
                System.out.println("|      E. Exit                   |");
                System.out.println("----------------------------------");
                System.out.println("Please input your choice");
                System.out.print("> ");
                strOptionView = Sc.nextLine();
                System.out.println();
            } while (!strOptionView.equalsIgnoreCase("E"));
        } else {
            System.out.println("* There are no records in the system");
        }
    }

    public static void fnSorting(String strOptionSorting) {
        String strTemp;
        switch (strOptionSorting) {
            case "BubbleSort":
                for (int i = 0; i < arrstrNovels.size(); i++) {
                    for (int j = 1; j < arrstrNovels.size(); j++) {
                        if (arrstrNovels.get(j).compareToIgnoreCase(arrstrNovels.get(j - 1)) < 0) {
                            strTemp = arrstrNovels.get(j - 1);
                            arrstrNovels.set((j - 1), arrstrNovels.get(j));
                            arrstrNovels.set(j, strTemp);
                        }
                    }
                }
                break;
            case "SelectionSort":
                for (int y = 0; y < arrstrNovels.size(); y++) {
                    int iMax = y;
                    for (int z = y + 1; z < arrstrNovels.size(); z++) {
                        if (arrstrNovels.get(z).compareToIgnoreCase(arrstrNovels.get(iMax)) > 0) {
                            iMax = z;
                        }
                    }
                    strTemp = arrstrNovels.get(y);
                    arrstrNovels.set(y, arrstrNovels.get(iMax));
                    arrstrNovels.set(iMax, strTemp);
                }
                break;
        }
    }

    public static void fnUpdateMenu() {
        if (arrstrNovels.size() > 0) {
            String strUpdateOption;
            do {
                System.out.println("----------------------------------");
                System.out.println("|       A. Update/Remove Novel   |");
                System.out.println("|       B. Remove all Novel      |");
                System.out.println("|       E. Exit                  |");
                System.out.println("----------------------------------");
                System.out.println("Please input your choice");
                System.out.print("> ");
                strUpdateOption = Sc.nextLine();
                System.out.println();
                switch (strUpdateOption) {
                    case "A":
                    case "a":
                        fnUpdateRemove();
                        break;
                    case "B":
                    case "b":
                        arrstrNovels.clear();
                        if (arrstrNovels.size() > 0) {
                            System.out.println("* All the Novel records are removed from the system");
                        } else {
                            System.out.println("* There are no records in the system");
                        }
                        break;
                    case "E":
                    case "e":
                        break;
                    default:
                        System.out.println("* Invalid Option!");
                }
            } while (!strUpdateOption.equalsIgnoreCase("E"));
        } else {
            System.out.println("* There are no records in the system");
        }
    }

    public static void fnUpdateRemove() {
        String strUpdateRemoveOption;
        String strNovelName;
        String strUpdateName;

        System.out.println("Please Input the novel name that you want to update ");
        System.out.print("> ");
        strNovelName = Sc.nextLine();
        System.out.println();
        String[] arrstrResult = fnCheck(strNovelName);
        boolean bChecked = Boolean.parseBoolean(arrstrResult[0]);
        boolean bUpdateDuplicate = false;
        int iUpdateIndex = Integer.parseInt(arrstrResult[1]);

        if (bChecked) {
            do {
                System.out.println("----------------------------------");
                System.out.println("|       A. Update the Novel      |");
                System.out.println("|       B. Remove the Novel      |");
                System.out.println("|       E: Exit                  |");
                System.out.println("----------------------------------");
                System.out.println("Please input your choice");
                System.out.print("> ");
                strUpdateRemoveOption = Sc.nextLine();
                System.out.println();
                switch (strUpdateRemoveOption) {
                    case "A":
                    case "a":
                        System.out.println("Please enter the new Novel Name.");
                        System.out.print("> ");
                        strUpdateName = Sc.nextLine();

                        System.out.println();
                        for (String strName : arrstrNovels) {
                            if (strUpdateName.equalsIgnoreCase(strName)) {
                                bUpdateDuplicate = true;
                                break;
                            }
                        }
                        if (!bUpdateDuplicate) {
                            arrstrNovels.set(iUpdateIndex, strUpdateName);
                            System.out.print("* The novel \"" + strNovelName);
                            System.out.println("\" is successful updated to \"" + strUpdateName + "\"");
                        } else {
                            System.out.println("* The novel name is duplicated with another novel in the records");
                        }
                        break;
                    case "B":
                    case "b":
                        arrstrNovels.remove(iUpdateIndex);
                        System.out.print("* The Novel \" " + strNovelName);
                        System.out.println("\" is successful removed from the record");
                        break;
                    case "E":
                    case "e":
                        break;
                    default:
                        System.out.println("* Invalid Option!");
                        break;
                }
            } while (!strUpdateRemoveOption.equalsIgnoreCase("A") &&
                    !strUpdateRemoveOption.equalsIgnoreCase("B") &&
                    !strUpdateRemoveOption.equalsIgnoreCase("E"));
        }
    }

    public static String[] fnCheck(String strCheckName) {
        String[] arrstrCheckResult = new String[2];
        arrstrCheckResult[0] = "false";
        arrstrCheckResult[1] = "0";

        // Loop to get all the novels with the same name from the record.
        for (int i = 0; i < arrstrNovels.size(); i++) {
            if (arrstrNovels.get(i).equalsIgnoreCase(strCheckName)) {
                System.out.println("* The \"" + strCheckName + "\" is found in the records");
                arrstrCheckResult[0] = "true";
                arrstrCheckResult[1] = Integer.toString(i);
                break;
            } else if (i == (arrstrNovels.size() - 1)) {
                System.out.println("* There are no record found on \"" + strCheckName + "\"");
                break;
            }
        }
        return arrstrCheckResult;
    }
}