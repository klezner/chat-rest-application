**Projekt 1b**

Założenia ogólne

1. Projekt realizowany samodzielnie przez wszystkich uczestników kursu
2. Nieprzekraczalny termin oddania projektu to 8.05.2022
3. Stworzony kod powinien być opublikowany na repozytorium git np. GitHub
4. Realizując projekt używamy standardowego SDK Java 11 oraz technologii poznanych w dalszej części kursu tj. CDI,
   JPA/Hibernate, elementy Jakarta EE
6. Ze względu na kolejność realizowanych zajęć testy jednostkowe nie są wymagane, ale mile widziane

Opis aplikacji
Stwórz czat tekstowy/aplikację klient-server wykorzystując Java Sockets. Aplikacja powinna umożliwiać:

- rozmowę wielu osób na kanale grupowym
- rozmowę 2 lub więcej osób na kanale prywatnym
- przesyłanie plików między osobami na danym kanale
- zapamiętywanie historii rozmów po stronie serwera w bazie opartej o bazę danych
- możliwość przeglądania historii rozmów z poziomu klienta (jeśli uczestniczył on w rozmowie/był na kanale)

Obsługa aplikacji powinna odbywać się z terminala/linii komend (interfejs tekstowy) dla 1a oraz REST API w przypadku
1b.

Uwaga! Należy zwrócić szczególną uwagę na aspekty związane z wielowątkowością - zapewnić zarówno bezpieczeństwo jak
wydajność całego rozwiązania.

> **Server -> User**
>* online - connected
>* offline - disconnected
>* channel join - channel create and subscribe or switch channel
>* file send - send file to users on active channel
>* chat history - read from db
>
>**User -> Server**
>* login, logout - ok
>* me - ok
>* users, channel users - ok
>* channels - ok
>* history - ok
>
>**User -> User**
>* channel messages - ok
>* cache channel messages - ok
>* send file to users on active channel - x

> **Available commands**
>* ***clientMessage***
>* **/help** - help
>* **/me** - about me (username, subscribed channels, active channel)
>* **/users** - get all users connected
>* **/channels** - get all active channels
>* **/history** - get my channel history
>* **/channel /join *channelName*** - create and join new channels or join channel if exists (still subscribed)
>* **/channel /users** - get all channel users (subscribing channel)
>* **/send *fileName*** - send file to users on active channel
>* **/quit** - disconnect and close client
