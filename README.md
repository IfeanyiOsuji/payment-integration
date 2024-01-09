# payment-integration
This project aims to integrate payment APIs of popular organizations like paystack, interswitch, flutterwave, etc.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Examples](examples)

## Installation
This is a server side project so in order to set up the project, you need to JDK installed. You also need to have maven installed and a host of dependencies such as spring data jpa, spring data web, h2 database, jackson databind, lombok 

## Usage

To run this project, make sure you have JDK 17 or higher installed

1. Clone the repository:
git clone https://github.com/IfeanyiOsuji/payment-integration.git

2. Navigate to the project directory
cd payment-integration

Compile the Java files
javac IntegrationApplication.java 
    or
Click on the run button at the top right if you are using vs code or intellij idea

4. Run the program
java IntegrationApplication.java 

If the code runs successfully, you can use postman to call the endpoints


## Example
To create a customer on paystack:
http://localhost:8080/paystack/createCustomer

To initializa a transaction on flutterwave:
http://localhost:8080/flutterwave/pay

To initialize a transaction on paystack:
http://localhost:8080/paystack/transaction/initailize

