# Instructions

## Code review
As you can imagine this code (particularly the `BookingService`) is not very domain driven.

Are you already familiar with some DDD concepts as in the glossary? If not, discuss the meaning of the items  
the terms in the glossary below. If yes, go ahead with the following questions

* How would you describe the style of this code?
* Can you identify some of the lackings and shortcomings with regard to the domain, 
in what ways does it "violate" the principles of DDD? List as many points as you can
* Do you see any good points? Do you think refactoring will be easy? Why?

## Refactoring
* Refactor it to be as DDD as you can.
* Prefer small steps
* Run the tests continuously

## Bonus
If you have time
* Enforce no logic in the domain-service layer. That is no ifs, no for...
* Organise the code into the DDD constituents (domain, infra)
* Are there any other options with regard to where you draw the line between domain and infra?


### DDD Glossary
* DTO
* Value Object
* Entity
* Domain Service
* Usecase
