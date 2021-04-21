import java.util.Scanner;

public class vigenere {

    char[] plain;
    char[] crypt;
    char[] key;


    public char[] decrypt(char[] plain, char[] key) {
        char[] decrypted = new char[plain.length];
        for (int i = 0; i < plain.length; i++) {
            //-194 -> 2*97 -> 2x kleines a
            int currCharDecrypt = (plain[i] + key[i % key.length] - 194) % 26;
            decrypted[i] = (char) (currCharDecrypt + 97);
        }
        return decrypted;
    }

    public char[] encrypt(char[] crypt, char[] key) {
        char[] encrypted = new char[crypt.length];
        for (int i = 0; i < crypt.length; i++) {
            int currCharEncrypt;
            if (crypt[i] - key[i % key.length] < 0)
                //130 % 26 = 0
                currCharEncrypt = (crypt[i] - key[i % key.length] + 130) % 26;
            else
                currCharEncrypt = (crypt[i] - key[i % key.length]) % 26;
            encrypted[i] = (char) (currCharEncrypt + 97);
        }
        return encrypted;
    }

    public char[] formatInput(String unformatted) {
        char[] formatted;

        if (unformatted != null && !unformatted.isBlank()) {
            unformatted = unformatted.toLowerCase();
            unformatted = unformatted.replaceAll("\\s", "");
            unformatted = unformatted.replace("ä", "ae");
            unformatted = unformatted.replace("ö", "oe");
            unformatted = unformatted.replace("ü", "ue");
            formatted = unformatted.toCharArray();
            for (char current : formatted) {
                if (current < 97 || current > 122) {
                    System.out.println("Keine Sonderzeichen erlaubt");
                    return null;
                }
            }
            return formatted;
        }
        System.out.println("Eingabe kann nicht leer sein");
        return null;
    }

    public void cli() {

        System.out.println("0 verschlüsseln 1 entschlüsseln q oder exit zum beenden");
        Scanner scanner = new Scanner(System.in);
        String op = scanner.nextLine();
        switch (op) {
            case "0":
                while (true) {
                    System.out.println("Klartext eingeben:");
                    String plaintext = scanner.nextLine();
                    plain = formatInput(plaintext);
                    if (plain != null)
                        break;
                }
                while (true) {
                    System.out.println("Schluesselwort eingeben:");
                    String keyword = scanner.nextLine();
                    key = formatInput(keyword);
                    if (key != null)
                        break;
                }

                char[] decrypted = decrypt(plain, key);
                System.out.println("Verschluesselter Text:");
                System.out.println(decrypted);
                System.out.println();
                break;

            case "1":
                while (true) {
                    System.out.println("Geheimtext eingeben:");
                    String crypttext = scanner.nextLine();
                    crypt = formatInput(crypttext);
                    if (crypt != null)
                        break;
                }
                while (true) {
                    System.out.println("Schluesselwort eingeben:");
                    String keyword = scanner.nextLine();
                    key = formatInput(keyword);
                    if (key != null)
                        break;
                }

                char[] encrypted = encrypt(crypt, key);
                System.out.println("Entschlüsselter Text:");
                System.out.println(encrypted);
                System.out.println();
                break;
            case "q":
            case "exit":
                System.exit(0);
                break;
            default:
                System.exit(1);
                break;
        }
        cli();
    }

    public static void main(String[] args) {
        vigenere run = new vigenere();
        run.cli();
    }
}

