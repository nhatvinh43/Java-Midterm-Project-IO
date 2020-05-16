import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static String encryptMsg(int type, String msg)
    { //Type: 1 for Caesar, 2 for ROT13
        String encryptedMsg = "";

        if(msg.isEmpty())
        {
            return encryptedMsg;
        }

        switch(type)
        {
            case 1: {
                encryptedMsg = CaesarCipher.encrypt(msg);
                break;
            }
            case 2: {
                encryptedMsg = ROT13Cipher.encrypt(msg);
                break;
            }
        }

        return encryptedMsg;
    }

    public static String decryptMsg(int type, String msg)
    {
        //Type: 1 for Caesar, 2 for ROT13

        String decryptedMsg = "";

        if(msg.isEmpty())
        {
            return decryptedMsg;
        }

        switch(type)
        {
            case 1: {
                decryptedMsg = CaesarCipher.decrypt(msg);
                break;
            }
            case 2: {
                decryptedMsg = ROT13Cipher.decrypt(msg);
                break;
            }
        }

        return decryptedMsg;
    }

    public static String readMsgFromConsole()
    {
        String msg = "";
        System.out.print("\nEnter your message: ");
        msg = sc.nextLine();
        return msg;
    }

    public static String readMsgFromFile()
    {

        String path, msg="";
        System.out.print("\nInput: Enter file path and name: ");
        path = sc.nextLine();

        if(path.isEmpty())
        {
            return msg;
        }

        try{

            File f = new File(path);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            BufferedReader br = new BufferedReader(new FileReader(f));

            String fileExtension = f.getName().substring(f.getName().indexOf('.'));
            if(!fileExtension.equals(".txt"))
            {
                System.out.println("Error, only text files are supported!");
                return "";
            }

            System.out.println("File successfully read!");
            System.out.println("File name: " + f.getName());
            System.out.println("Last modified: " + sdf.format(f.lastModified()));
            System.out.println("File size: "+ f.length() + " bytes\n");

            int i;
            while((i = br.read())!= -1)
            {
                msg+= (char) i;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Cannot find the file specified, please try again!\n");
        }


        return msg;


    }

    public static void writeMsgToConsole(String msg)
    {
        System.out.println("Processed message: " + msg);
    }

    public static void writeMsgToFile(String msg)
    {
        String path="";
        System.out.print("\nOutput: Enter file path and name: ");
        path = sc.nextLine();

        if(path.isEmpty())
        {
            System.out.println("Invalid file name and path!");
            return;
        }

        File file = new File(path);
        if(!file.exists())
        {
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                bw.write(msg);
                bw.close();
                System.out.println("Operation completed!");
            }
            catch (IOException e) {
                System.out.println("Cannot create file with the path specified, please try again!");
            }

        }
        else
        {
            System.out.println("File already exists! Options are: ");
            System.out.println("1. Override");
            System.out.println("2. Chose another name");
            System.out.println("3. Cancel");
            System.out.print("\nEnter your choice: ");

            int choice = Integer.valueOf(sc.nextLine());

            switch (choice)
            {
                case 1:{
                    file.delete();
                    break;
                }
                case 2:{
                    sc.nextLine();
                    String oldPath = path;
                    System.out.print("\nEnter your new path and name to write to: ");
                    path = sc.nextLine();

                    File tempFile = new File(path);
                    if(tempFile.exists())
                    {
                        System.out.println("Error, file already exists.");
                        System.out.println("Operation canceled!");
                        return;
                    }

                    break;

                }
                case 3:{
                    System.out.println("Operation canceled!");
                    return;
                }
            }

            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                bw.write(msg);
                bw.close();
                System.out.println("Operation complete!");
            }
            catch (IOException e) {
                System.out.println("Cannot create file with the path specified, please try again!");
            }

        }

    }

    public static void menu()
    {
        int actionChoice, inputChoice, outputChoice, cipherChoice;

        System.out.println("Step 1: Action");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");

        System.out.print("Select your action: ");
        actionChoice =Integer.valueOf(sc.nextLine());

        System.out.println("\nStep 2: Input source");
        System.out.println("1. Read message from console");
        System.out.println("2. Read message from file");

        System.out.print("Select your input source: ");
        inputChoice = Integer.valueOf(sc.nextLine());

        System.out.println("\nStep 3: Cipher selection");
        System.out.println("1. Caesar");
        System.out.println("2. ROT13");

        System.out.print("Select your cipher: ");
        cipherChoice = Integer.valueOf(sc.nextLine());

        System.out.println("\nStep 4: Output destination");
        System.out.println("1. Print message to console");
        System.out.println("2. Print message to file");

        System.out.print("Select your cipher: ");
        outputChoice = Integer.valueOf(sc.nextLine());

        menuController(actionChoice, inputChoice, cipherChoice, outputChoice);

    }

    public static void menuController(int actionChoice, int inputChoice, int cipherChoice, int outputChoice)
    {
        String msg ="", resultMsg = "";

        switch (inputChoice)
        {
            case 1: //Input from Console
            {
                msg = readMsgFromConsole();
                break;
            }
            case 2: //Input from File
            {
                msg = readMsgFromFile();
                break;
            }
        }

        switch (actionChoice)
        {
            case 1: //Encrypt
            {
                switch(cipherChoice)
                {
                    case 1: //Caesar
                    {
                        resultMsg = encryptMsg(1, msg);
                        break;
                    }
                    case 2: //ROT13
                    {
                        resultMsg = encryptMsg(2, msg);
                        break;
                    }
                }
                break;
            }
            case 2: //Decrypt
            {
                switch(cipherChoice)
                {
                    case 1: //Caesar
                    {
                        resultMsg = decryptMsg(1, msg);
                        break;
                    }
                    case 2: //ROT13
                    {
                        resultMsg = decryptMsg(2, msg);
                        break;
                    }
                }
                break;
            }
        }

        switch (outputChoice)
        {
            case 1: //Console
            {
                writeMsgToConsole(resultMsg);
                break;
            }
            case 2: //File
            {
                writeMsgToFile(resultMsg);
                break;
            }
        }

    }

    public static void main(String[] args){
        menu();
    }
}
