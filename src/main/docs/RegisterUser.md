
## Register User



    Client make a request to java backend. 
    Java backend handle stuffs after that
    Make a request to Ejabberd Api
    Then Java Backend handle the ejabberd response.
    Return that to the client.  
#### Request to Java Backend
##### Request
    POST /register
    {
          "user": "aniltaskir",
          "email": "anil@taskiran.com",
          "password": "1234"
    }
##### Response

    {
        "status": true,
        "code": 200,
        "message": "\"User aniltaskir@174.138.12.182 
        successfully registered\""
    }



#### Request For Ejabberd Api
##### Request

        POST /api/register
        { 
            "user": "peter",
            "host": "myserver.com",
            "password": "secret"
        }
                HTTP/1.1 200 OK
                
##### Response
res :: string : Raw result string if it is success




        
