package Social; // Definizione del package

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileReader;
import java.io.IOException;

public class Social {
    // Variabili di istanza per memorizzare le informazioni sulla piattaforma
    private String name; // Nome della piattaforma
    private String description; // Descrizione della piattaforma
    private int foundedYear; // Anno di fondazione della piattaforma
    private int registeredAccount; // Numero di account registrati sulla piattaforma
    private ArrayList<Account> accounts; // Lista degli account registrati sulla piattaforma

    // Costruttore della classe Social per inizializzare le variabili di istanza
    public Social(String name, String description, int foundedYear, int registeredAccount) {
        this.name = name;
        this.description = description;
        this.foundedYear = foundedYear;
        this.registeredAccount = registeredAccount;
        this.accounts = new ArrayList<>(); // Inizializzazione della lista degli account
    }

    // Metodi set e get per accedere e modificare i valori delle variabili di istanza
    
    // Metodo per ottenere il nome della piattaforma
    public String getName() {
        return name;
    }

	// Metodo per impostare il nome della piattaforma
    public void setName(String name) {
        this.name = name;
    }
    
    // Metodo per ottenere la descrizione della piattaforma
    public String getDescription() {
        return description;
    }

    // Metodo per impostare la descrizione della piattaforma
    public void setDescription(String description) {
        this.description = description;
    }

    // Metodo per ottenere l'anno di fondazione della piattaforma
    public int getFoundedYear() {
        return foundedYear;
    }

    // Metodo per impostare l'anno di fondazione della piattaforma
    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    // Metodo per ottenere il numero di account registrati sulla piattaforma
    public int getRegisteredAccount() {
        return registeredAccount;
    }

    // Metodo per impostare il numero di account registrati sulla piattaforma
    public void setRegisteredAccount(int registeredAccount) {
        this.registeredAccount = registeredAccount;
    }

    // Metodo per impostare la lista degli account registrati sulla piattaforma
    //public void setAccounts(ArrayList<Account> accounts) {
    //    this.accounts = accounts;
    //}

    // Metodo per aggiungere un account alla lista degli account
    public void addAccountToArraylist(Account account) {
        accounts.add(account);
    }

    // Metodo per cancellare il contenuto di un file
    public void clearFileAndArray(String filename, ArrayList<Account> socialname) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(""); // Sovrascrive il file con una stringa vuota
            System.out.println("Account cancellati con successo.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Rimuove gli oggetti dall'ArrayList
        while (socialname.size() > 0) {
        	System.out.println("Size.\n" + socialname.size());
        	socialname.remove(socialname.size() - 1);
        }
    }

    // Metodo per contare il numero di influencer e creatori tra gli account
    public String countInfluencersAndCreators() {
        int influencer = 0;
        int creator = 0;
        // Controlla ogni account nella lista
        for (Account account : accounts) {
            // Conta gli influencer (account con più di 10000 follower) e i creatori (account con più di 1000 post)
            if (account.getNumFollowers() > 10000) {
                influencer++;
            }
            if (account.getNumPosts() > 1000) {
                creator++;
            }
        }
        // Restituisce il numero di influencer e creatori
        return "Numero di influencer: " + influencer + "\nNumero di creator: " + creator;
    }    

    // Metodo per calcolare la media dei post per utente
    public int averagePostsPerUser() {
        int totalPosts = 0;
        // Somma il numero di post di ogni account
        for (Account account : accounts) {
            totalPosts += account.getNumPosts();
        }
        if(accounts.size() == 0) {
        	return 0;
        }else {
        	// Calcola la media dei post per utente
            return totalPosts / accounts.size();
        }      
    }

    // Metodo per trovare l'account con il maggior numero di follower
    public String accountWithMostFollowers() {
    	if(accounts.size() == 0) {
    		return "Non ci sono account su questo social.";
    	}else {
    		// Utilizza il metodo Collections.max per trovare l'account con il maggior numero di follower
            Account maxFollowerAccount = Collections.max(accounts, Comparator.comparing(Account::getNumFollowers));
            return "Account con il maggior numero di follower: " + maxFollowerAccount.getNickname() + " con " + maxFollowerAccount.getNumFollowers() + " followers";
    	}
        
    }

    // Metodo per ordinare gli account per numero di follower in ordine decrescente
    public void sortByFollowersDescending() {
        // Utilizza Collections.sort per ordinare gli account in base al numero di follower in ordine decrescente
        Collections.sort(accounts, Comparator.comparing(Account::getNumFollowers).reversed());
    }
    
    // Metodo per ordinare le righe di un file in base al primo intero presente nella riga e sovrascrivere il file ordinato
    public void sortFileLinesByFirstIntAndRewrite(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Creazione di una lista per contenere le righe del file
            ArrayList<String> lines = new ArrayList<>();
            String line;
            // Legge ogni riga del file e la aggiunge alla lista
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Ordinamento delle righe della lista in base al primo intero presente nella riga
            Collections.sort(lines, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    // Ottiene il primo intero di ciascuna riga
                    int firstInt1 = Integer.parseInt(o1.split(", ")[1]);
                    int firstInt2 = Integer.parseInt(o2.split(", ")[1]);
                    return firstInt2 - firstInt1; // Ordinamento decrescente
                }
            });
            // Sovrascrittura del file ordinato
            try (FileWriter fw = new FileWriter(filename)) {
                for (String sortedLine : lines) {
                    fw.write(sortedLine + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per verificare se un account con un determinato nickname esiste in ArrayList
    //public boolean accountExists(String nickname) {
        // Controlla se esiste un account con il nickname specificato nella lista degli account
    //    for (Account account : accounts) {
    //        if (account.getNickname().equals(nickname)) {
    //            return true;
    //        }
    //   }
    //    return false;
    //}

    // Metodo per ottenere la lista degli account
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Metodo per ottenere il numero totale di account
    public int getNumAccounts() {
        return accounts.size();
    }

    // Metodo per leggere gli account da un file e aggiungerli alla lista degli account
    public void readAccountsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Legge ogni riga del file
            while ((line = br.readLine()) != null) {
                // Divisione di ogni riga del file in base al separatore ", "
                String[] parts = line.split(", ");
                // Controlla se la riga contiene esattamente 4 elementi
                if (parts.length == 4) {
                    String nickname = parts[0]; // Estrae il nickname dall'array
                    // Estrazione dei dati e cast
                    int followerCount = Integer.parseInt(parts[1]);
                    int followingCount = Integer.parseInt(parts[2]);
                    int postCount = Integer.parseInt(parts[3]);
                    // Crea un oggetto Account e lo aggiunge all'ArrayList
                    accounts.add(new Account(nickname, followerCount, followingCount, postCount));
                } else {
                    System.out.println("Linea non valida: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
	public String toString() {
		return "Info " + name + ": " + description + " " + foundedYear
				+ ", " + registeredAccount + " account(s).";
	}
}
