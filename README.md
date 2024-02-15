# transfer-money
This project is designed to handle financial transactions between bank accounts. 
It provides a RESTful API for making transactions and ensures the integrity of account balances 
during transfers.

## Features
Transfer money between two bank accounts
Handle scenarios like insufficient balance, transfers between the same account, and non-existent accounts
Ensure atomicity of operations through transaction management

## Technologies Used
Java
Spring Boot
Spring Data JPA
MariaDB
Maven
Getting Started
Prerequisites
JDK 8 or higher
Maven
MariaDB
Installation

Clone the repository:

```bash
git clone https://github.com/Maknikolas/transfer-money.git
```
Navigate to the project directory:
```bash
cd transfer-money
```
### Running the Application

Start MariaDB server.

Update the database configuration in application.properties.

Run the application

### API Endpoints

POST /transaction: Make a financial transaction between two accounts.

Example Request:
Here's an example of a POST request to make a transaction:

Content-Type: application/json
```json
{
"sourceAccountId": 1,
"targetAccountId": 2,
"amount": 100.0,
"currency": "USD"
}
```

License
This project is licensed under the MIT License.


