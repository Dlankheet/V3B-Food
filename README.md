# V3B-Food
For all your tasty needs.

# Mogelijke functionaliteiten
* Registreren / inloggen verschillende rollen 
* Bestellen van één of meer gerechten 
* Aannemen of afwijzen van bestelling door keuken 
* Gereedmelding of afmelding van bestelling door keuken 
* Afrekenen van bestellingen 
* Bezorging van bestellingen 
* Near real-time statusupdate over bestelling 
* Beoordeling van bestelling door klant 
* Bestellingshistorie voor een klant 
* Overzicht voorraad (bijvoorbeeld ingrediënten van gerechten) 
* Automatisch verbergen van gerechten waarvan ingrediënten niet op voorraad zijn 
* Automatisch afwijzen van bestellingen waarvan ingrediënten niet meer op voorraad zijn 
* Overzicht verkochte gerechten voor restauranthouder 
* Overzicht financiële gegevens voor restauranthouder

# Eisen/Verplichtingen
* Gebruik van GitHub of Gitlab volgens een geschikte workflow
* Maak 4 of meer verschillende (micro)services
* Minstens 4 (micro)services moeten in Java zijn geschreven
* Het platform is extern bereikbaar via gangbare webprotocollen
* Voor communicatie tussen services wordt ergens gebruik gemaakt van messaging, bij voorkeur met RabbitMQ
* Voor communicatie tussen services wordt ergens gebruik gemaakt van remote procedure invocation, bij voorkeur een REST API
* Voor de opslag wordt ergens gebruik maakt van een NoSQL-database, bij voorkeur MongoDB
* Maak gebruik van standaardoplossingen, zoals frameworks en libraries
* De code is netjes, leesbaar en niet nodeloos gecompliceerd


# Aanbevolen:
* Maak gebruik van tactical patterns uit Domain Driven Design (DDD)
* Structureer elke (micro)service volgens een dependency inversion architecture zoals Hexagonal Architecture, Onion Architecture of Clean Architecture
* De code is voldoende geautomatiseerd getest, bijvoorbeeld met unit en/of integration tests
