# esame_catering
versione completa del compito di esame sui casi d'uso 
di una società di catering

situazione al 14 giugno 2022

- L'applicazione è completa, effettuate diversi miglioramenti alle operazioni e agli elenchi.
- Effettuata revisione pagine di login con nuovi css

PROBLEMI NON RISOLTI:

1) Si riscontrano saltuarie perdite di connessione ai CSS, probabilmente a causa del browser. 
Non sono riuscito a determinarne l'origine. In particolare sulla pagina di logging.
2) Come pulizia del codice sarebbe stato meglio raggruppare le forms per ADMIN tutte
sotto la subdirectory templates/admin, ma non cambia nulla nelll'operatività corretta 
del codice. Sono state lasciate tutte insieme, tranne la home.
3) Nella classe AuthConfiguration, che estende la WebSecurityConfigurerAdapter, quest'ultima
è segnalata deprecata da Eclipse, senza conseguenze.
