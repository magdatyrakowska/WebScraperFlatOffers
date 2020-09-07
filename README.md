# Web scraper of flat offers - project
Project created for easy searching and comparing of flat rent offers published in popular portal [olx.pl](https://www.olx.pl). Main purpose of creating project was to learnJava Spring Boot framework in practice.

## Table of contents
* [Technologies](#technologies)
* [Details](#details)

## Technologies
In project were used:
* Java Spring Boot - main framework
* Thymeleaf - views engine
* Bootstrap - framework of CSS styles
* JSoup - scraping informations directly from web pages

## Details
Application is nearly completed. Solutions chosen at the beginning of work now are not enough and hard to develop. The most probable is that conclusions will be used to make a new application.

* **comparing flat offers**

Searching options are limited just to flat rent offers, only to one portal. Basing on current application it would be hard to widen search options to all types of offers on olx portal. It would require to double whole search options segment of all categories, that are alredy provided in olx portal.

To widen application functionality and allow to search through all categories it would be easier to build a new application, and from the beginning do not try to replicate choosing search options. It would be much easier just to copy adress of the olx result page, where all settings are written in the url address, and paste it to app.

* **searching options**

Project allows to use different search options (i.e. city, number of rooms, flat size in square meters) and generate simple one-page summary of all offers. Searching options matches those ones available in olx platform, however they do not allow to use multiple checks (like both two- and three-room flats). Solution might to change radio buttons to checboxes (html elements), however it would require to change also Form class to accept multichooses.

* **scrapping informations directly from web page**

To get data from web pages there is used JSoup library. Thanks to that solution is independent from any limits of the portal, and easy to use. However, to make application more useful and universal it would be a good idea to use olx developer API.
