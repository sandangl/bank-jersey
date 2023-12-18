# Banca REST - Jersey

È  necessario  scrivere  un’applicazione web per gestire le operazione di home banking. Ogni **utente** della banca può avere uno o più **conti** presso lo stesso istituto bancario, identificati univocamente da un *IBAN* di esattamente 16 simboli (*es.: IT15A0045894503*), *nome*, *cognome*, *numero di telefono* del correntista. Inoltre il conto tiene una lista dei movimenti effettuati, nei quali sono specificati *data*, *importo*, dettagli del bonifico. A ciascun conto si può collegare una carta. Una carta ha un *PIN* di 5 cifre. L'applicazione deve consentire i seguenti casi d'uso:

### Utente effettua il login (OK)

L'utente inserisce nome utente (email) e password.

### Utente ottiene dettagli sui suoi conti bancari (OK)

### Utente ottiene dettagli sui bonifici effettuati (OK)

### Utente crea conto bancario (OK)

L'utente richiede di aprire un nuovo conto bancario. Il sistema crea un nuovo conto bancario assegnandovi un nuovo IBAN e le generalità fornite dall'utente.

*Scenario alternativo:* se il conto possiede generalità diverse da quelle dell'utente, allora il sistema segnala l'errore.

### Utente aggiunge una carta al conto (OK)

L'utente richiede una nuova carta specificandone il PIN e il sistema ne crea una da associare al conto scelto.

### Utente imposta un nuovo pin per la carta (OK)

L'utente fornisce il codice della carta, il vecchio PIN e quello nuovo.

### Utente effettua bonifico

L'utente specifica nome, cognome, IBAN del conto destinatario e un importo, insieme ad una causale ed una data. Il sistema trasferisce il denaro dal conto mittente a quello destinatario.

*Scenario alternativo:* se nome e cognome non coincidono con quelle specificate nel conto bancario, il sistema segnala l'errore.

### Utente annulla bonifico

L'utente inserisce l'id del bonifico, e il sistema lo annulla. Un bonifico può essere annullato solo se si agisce tempestivamente, ossia prima delle 20:00 del giorno in cui è stato effettuato.

### Utente effettua versamento/prelievo

L'utente specifica la cifra da accreditare/decurtare dal conto, previa specifica dell'id della carta e del PIN.

### Utente disdice conto bancario

L'utente richiede la chiusura del conto bancario.

*Scenario alternativo:* se il conto possiede ancora una liquidità residua, il sistema prega l'utente di effettuare un giroconto verso un altro conto bancario in suo possesso, o un bonifico verso terzi.
