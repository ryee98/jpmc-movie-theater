# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

## Change Log
* Made application into a Spring Boot Application
  * Enables use of Spring Framework to allow for IOC, dependency injection,
  and easier testing
  * refactored project structure and moved existing classes to new package structure.
  * moved operation code to service classes
  * updated to use Java 11
  * implemented all new requirements
      * added support for time-of-day based discounts
      * added support for day-of-month discount
      * added support for outputting movie schedule in json format.
      * added logic to handle edge case of negative ticket prices.
  * added unit tests for all classes
  * added Project Lombok dependency to enable the use of annotations to help 
  eliminate boilerplate code
  * added mockito library to enable mocking of dependencies
  * created config, model, service, and util packages
  * created service classes for performing business operations.
  
