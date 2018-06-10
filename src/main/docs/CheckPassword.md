
## CHECK PASSWORD



    Client make a request to java backend. 
    Java backend handle stuffs after that
    Make a request to Ejabberd Api
    Then Java Backend handle the ejabberd response.
    Return that to the client.  
#### Request to Java Backend
##### Request
    POST /checkUser
    {
         "user": "anil",
         "host": "174.138.12.182",
         "password": "1234"
    }
##### Response

    {
        "status": true,
        "code": 200,
        "message": "success"
    }



#### Request For Ejabberd Api
##### Request

        POST /api/check_password
        { 
            "user": "peter",
            "host": "myserver.com",
            "password": "secret"
        }
                HTTP/1.1 200 OK
                
##### Response
 integer: Status code (0 on success, 1 otherwise)




        
