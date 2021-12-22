# V3B-Food
For all your tasty needs.

# Sonarcloud
## Customer

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Customer&metric=alert_status)](https://sonarcloud.io/summary/overall?id=V3B-Food-Customer)[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Customer&metric=bugs)](https://sonarcloud.io/summary/overall?id=V3B-Food-Customer)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Customer&metric=code_smells)](https://sonarcloud.io/summary/overall?id=V3B-Food-Customer)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Customer&metric=coverage)](https://sonarcloud.io/summary/overall?id=V3B-Food-Customer)

## Dishes

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Dishes&metric=alert_status)](https://sonarcloud.io/summary/overall?id=V3B-Food-Dishes)[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Dishes&metric=bugs)](https://sonarcloud.io/summary/overall?id=V3B-Food-Dishes)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Dishes&metric=code_smells)](https://sonarcloud.io/summary/overall?id=V3B-Food-Dishes)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=V3B-Food-Dishes&metric=coverage)](https://sonarcloud.io/summary/overall?id=V3B-Food-Dishes)

## Ingredients

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Ingredients&metric=alert_status)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Ingredients)[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Ingredients&metric=bugs)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Ingredients)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Ingredients&metric=code_smells)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Ingredients)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Ingredients&metric=coverage)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Ingredients)

## Orders

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Orders&metric=alert_status)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Orders)[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Orders&metric=bugs)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Orders)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Orders&metric=code_smells)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Orders)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Orders&metric=coverage)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Orders)

## Reviews

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Reviews&metric=alert_status)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Reviews)[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Reviews&metric=bugs)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Reviews)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Reviews&metric=code_smells)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Reviews)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=dlankheet_V3B-Food_Reviews&metric=coverage)](https://sonarcloud.io/summary/overall?id=dlankheet_V3B-Food_Reviews)

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
