package Social; // Definizione del package

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main2 {
    // Costanti per i nomi dei file di testo per ogni social network
    private static final String FACEBOOK_FILE = "facebook.txt";
    private static final String INSTAGRAM_FILE = "instagram.txt";
    private static final String TWITTER_FILE = "twitter.txt";

    // Oggetti Random e Scanner per l'input utente
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    // Creazione di oggetti Social per Facebook, Instagram e Twitter
    static Social facebook = new Social("Facebook", "Facebook è una piattaforma di social networking che ti consente di connetterti e condividere con amici e familiari.", 2004, 0);
    static Social instagram = new Social("Instagram", "Instagram è un'applicazione di social media che consente agli utenti di condividere foto e video, e di interagire con altri utenti tramite like, commenti e messaggi.", 2010, 0);
    static Social twitter = new Social("Twitter", "Twitter è una piattaforma di social media che consente agli utenti di condividere brevi messaggi di testo, chiamati tweet, con i propri follower.", 2006, 0);
   
    // Metodo main
    public static void main(String[] args) {
        String socialNetwork = ""; // Variabile per memorizzare il social network selezionato dall'utente
        
        // Lettura degli account da ciascun file per ogni social network
        facebook.readAccountsFromFile(FACEBOOK_FILE);
        instagram.readAccountsFromFile(INSTAGRAM_FILE);
        twitter.readAccountsFromFile(TWITTER_FILE);
        
        // Stampa iniziale degli account (se presenti) per ogni social network
        System.out.println("--- FACEBOOK ---");
        System.out.println("Numero di account: " + facebook.getNumAccounts());
        ArrayList<Account> facebookAccounts = facebook.getAccounts();
        System.out.println("Nicknames:");
        for (Account account : facebookAccounts) {
        	System.out.println(account.toString());
        }
        System.out.println("\n--- INSTAGRAM ---");
        System.out.println("Numero di account: " +  instagram.getNumAccounts());
        ArrayList<Account> instagramAccounts = instagram.getAccounts();
        System.out.println("Nicknames:");
        for (Account account : instagramAccounts) {
            System.out.println(account.toString());
        }
        System.out.println("\n--- TWITTER ---");
        System.out.println("Numero di account: " + twitter.getNumAccounts());
        ArrayList<Account> twitterAccounts = twitter.getAccounts();
        System.out.println("Nicknames:");
        for (Account account : twitterAccounts) {
        	System.out.println(account.toString());
        }
        System.out.println("-------------------------\n");
        
        // Menu delle funzioni
        while (true) {
            System.out.println("---- MENU ----");
            System.out.println("1. Utilizza un social network");
            System.out.println("2. Aggiungere un account a un social network");
            System.out.println("3. Informazioni social");
            System.out.println("4. Visualizza numero influencer e creator del social.");
            System.out.println("5. Numero di post medi social.");
            System.out.println("6. Account con più follower.");
            System.out.println("7. Cancella tutti gli account dal social.");
            System.out.println("0. Uscire");

            int choice = scanner.nextInt(); // Lettura della scelta dell'utente
            scanner.nextLine(); // Consuma il newline

            switch (choice) {
                case 0:
                    System.out.println("Uscita..."); // Uscita dal programma
                    System.exit(0);
                    break;
                case 1:
                    socialNetwork = useSocialNetwork(); // Utilizza un social network
                    break;
                case 2:
                    if (socialNetwork == "") { // Aggiungi un account a un social network
                        System.out.println("Inserire prima il social network.\n");
                        break;
                    }
                    addAccount(socialNetwork);
                    break;
                case 3:
                    switch (socialNetwork) { // Informazioni social
                        case "facebook":
                            facebook.setRegisteredAccount(facebook.getNumAccounts());
                            System.out.println(facebook.toString());
                            break;
                        case "instagram":
                            instagram.setRegisteredAccount(instagram.getNumAccounts());
                            System.out.println(instagram.toString());
                            break;
                        case "twitter":
                            twitter.setRegisteredAccount(twitter.getNumAccounts());
                            System.out.println(twitter.toString());
                            break;
                        default:
                            System.out.println("Social network non supportato.");
                            break;
                    }
                    break;
                case 4:
                    switch (socialNetwork) { // Visualizza numero influencer e creator del social
                        case "facebook":
                            System.out.println(facebook.countInfluencersAndCreators());
                            break;
                        case "instagram":
                            System.out.println(instagram.countInfluencersAndCreators());
                            break;
                        case "twitter":
                            System.out.println(twitter.countInfluencersAndCreators());
                            break;
                        default:
                            System.out.println("Social network non supportato.");
                            break;
                    }
                    break;
                case 5:
                    switch (socialNetwork) { // Numero di post medi social
                        case "facebook":
                            System.out.println("Media dei post: " + facebook.averagePostsPerUser());
                            break;
                        case "instagram":
                            System.out.println("Media dei post: " + instagram.averagePostsPerUser());
                            break;
                        case "twitter":
                            System.out.println("Media dei post: " + twitter.averagePostsPerUser());
                            break;
                        default:
                            System.out.println("Social network non supportato.");
                            break;
                    }
                    break;
                case 6:
                    switch (socialNetwork) { // Account con più follower
                        case "facebook":
                            System.out.println(facebook.accountWithMostFollowers());
                            break;
                        case "instagram":
                            System.out.println(instagram.accountWithMostFollowers());
                            break;
                        case "twitter":
                            System.out.println(twitter.accountWithMostFollowers());
                            break;
                        default:
                            System.out.println("Social network non supportato.");
                            break;
                    }
                    break;
                case 7:
                    switch (socialNetwork) { // Cancella tutti gli account dal social
                        case "facebook":
                            facebook.clearFileAndArray(FACEBOOK_FILE, facebookAccounts);
                            break;
                        case "instagram":
                            instagram.clearFileAndArray(INSTAGRAM_FILE, instagramAccounts);
                            break;
                        case "twitter":
                            twitter.clearFileAndArray(TWITTER_FILE, twitterAccounts);
                            break;
                        default:
                            System.out.println("Social network non supportato.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    // Metodo per utilizzare un social network
    private static String useSocialNetwork() {
        String socialNetwork;
        do {
            System.out.println("Quale social network vuoi utilizzare? [Facebook, Instagram, Twitter]");
            socialNetwork = scanner.nextLine().toLowerCase();

            // Verifica se il social inserito è valido
            if (!socialNetwork.equals("facebook") && !socialNetwork.equals("instagram") && !socialNetwork.equals("twitter")) {
                System.out.println("Social network non valido. Riprova.");
            }
        } while (!socialNetwork.equals("facebook") && !socialNetwork.equals("instagram") && !socialNetwork.equals("twitter"));
        return socialNetwork;
    }

    // Metodo per aggiungere un account a un social network
    private static void addAccount(String socialNetwork) {
        System.out.print("Inserisci il nickname: ");
        String nickname = scanner.nextLine();
        
        // Controlla se il nickname esiste già nel file
        if (isNicknameExists(socialNetwork, nickname)) {
            System.out.println("Nickname già presente nel file.");
            return;
        }
        
        // Generazione casuale dei followers, seguiti e post
        int followerCount = random.nextInt(100000);
        int followingCount = random.nextInt(1000);
        int postCount = random.nextInt(3000);
        
        // Scrive le informazioni dell'account nel file e aggiunge l'account all'oggetto Social corrispondente
        switch (socialNetwork) {
            case "facebook":
                writeUserDataToFile(FACEBOOK_FILE, nickname, followerCount, followingCount, postCount);
                facebook.sortFileLinesByFirstIntAndRewrite(FACEBOOK_FILE);
                facebook.addAccountToArraylist(new Account(nickname, followerCount, followingCount, postCount));
                facebook.sortByFollowersDescending();
                break;
            case "instagram":
                writeUserDataToFile(INSTAGRAM_FILE, nickname, followerCount, followingCount, postCount);
                instagram.sortFileLinesByFirstIntAndRewrite(INSTAGRAM_FILE);
                instagram.addAccountToArraylist(new Account(nickname, followerCount, followingCount, postCount));
                instagram.sortByFollowersDescending();
                break;
            case "twitter":
                writeUserDataToFile(TWITTER_FILE, nickname, followerCount, followingCount, postCount);
                twitter.sortFileLinesByFirstIntAndRewrite(TWITTER_FILE);
                twitter.addAccountToArraylist(new Account(nickname, followerCount, followingCount, postCount));
                twitter.sortByFollowersDescending();
                break;
            default:
                System.out.println("Social network non supportato.");
        }
    }

    // Metodo per controllare se un nickname esiste già nel file per un dato social network
    private static boolean isNicknameExists(String socialNetwork, String nickname) {
        String filename = "";
        switch (socialNetwork) {
            case "facebook":
            	// Controlla se il nickname esiste già nel file
                filename = FACEBOOK_FILE;              
                //if (facebook.accountExists(nickname)) return true; // Controllo con ArrayList
                break;
            case "instagram":
                filename = INSTAGRAM_FILE;
                //if (instagram.accountExists(nickname)) return true;
                break;
            case "twitter":
                filename = TWITTER_FILE;
                //if (twitter.accountExists(nickname)) return true;
                break;
            default:
                System.out.println("Social network non supportato.");
                return true;
        }
        
        // Apre il file in modalità lettura e verifica se il nickname esiste
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(nickname)) {
                    return true;
                }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo per scrivere le informazioni dell'account nel file per un dato social network
    private static void writeUserDataToFile(String filename, String nickname, int followerCount, int followingCount, int postCount) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(nickname + ", " + followerCount + ", " + followingCount + ", " + postCount + "\n");
            System.out.println("Account aggiunto al file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
