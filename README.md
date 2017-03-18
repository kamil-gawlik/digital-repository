# Team project - Pwr

Wskazówki co do prawidłowego skonfigurowania środowiska są dostępne na stronie wiki projektu.

Aby pokazać, że się wszystko gra z Twoim dostępem do repozytorium wpisz się na poniższą listę:
+ Kamil Gawlik
+ Szymon Matusiak
+ Adam Wudziński
+ Piotr Majcher
+ Alexander Stolar
+ Krzysztof Mikucki
+ Marcin Chajczyk

KONFIGURACJA PROJEKTU POD DEVELOPMENT:
1. Backend korzysta z bazy MongoDB, dlatego też trzeba ją mieć ją na komputerze,
    są dwie opcje:
    a) ściagacie i instalujecie mongoDB
    b) stawiacie bazę z wykorzystaniem dockera
    Gdy już to zrobicie, to musicie sprawdzić na jakim IP(w przypadku dockera) oraz na jakim
       porcie działa wasza baza danych, a następnie wpisać ten adres do pliku konfiguracyjnego
       <ścieżka_do_projektu>/back/src/main/resources/config/application-dev.yml
       Tam odnajdujecie spring -> data -> mongodb -> uri. I w tym miejscu podmieniacie na swoj
       adres na którym macie bazę.
2. Wchodzicie na stronę https://jhipster.github.io/installation/ znajdujecie dział
    "Local installation with NPM (alternative to Yarn)" i wykonujecie wszystkie punkty
     (chyba że coś już macie z tego zainstalowane)

URUCHOMIENIE PROJEKTU:
1. Uruchomienie jhipster-registry (trzeba to zrobić jako pierwsze!)
    a) otwieracie konsole/cmd
    b) przechodzicie do folderu <ścieżka_do_projektu>/jregister
    c) wywołujecie ./mvnw

2. Uruchomienie frontendu (dopiero jak uruchomi sie jhipster-registery!)
        a) otwieracie konsole/cmd
        b) przechodzicie do folderu <ścieżka_do_projektu>/gateway
        c) wywołujecie ./gradlew

3. Uruchomienie backendu (dopiero jak uruchomi sie jhipster-registery!)
        a) otwieracie konsole/cmd
        b) przechodzicie do folderu <ścieżka_do_projektu>/back
        c) wywołujecie ./gradlew

Aplikacje odpalają się na:
    -> localhost:8761  => jhipster-registery
    -> localhost:8080  => front
    -> localhost:8081  => back

Weryfikacja działania:
    Po wykonaniu punktów 1, 2 i 3 po wpisaniu w przeglądarkę localhost:8080
     powinniśmy dostać ładną, działąjąca stronkę.
