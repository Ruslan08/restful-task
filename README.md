# restful-task
Standalone RESTful service for money transfer between accounts.

**Transfer between accounts**
----
  Transfer money from first account to second one.

* **URL**

  /transfer

* **Method:**

  `GET`
  
*  **Query Params**

   **Required:**
 
   `firstAccountId=[long]` - id of account which money will be transferred from
   `secondAccountId=[long]` - id of account which money will be transferred to
   `amount=[double]` - amount of money for transfer

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `Transfer success`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `Account does not have enough money`


**Get account balance**
----
  Get currently account balance.
  
* **URL**

  /balance/{accountId}

* **Method:**

  `GET`
  
*  **Path Params**

   **Required:**
   
   `accountId=[long]` - id of account which balance want to check

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `Amount`
    
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `No such account found`
