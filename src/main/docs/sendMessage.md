## Send Message
Send a message to a local or remote bare of full JID

###Arguments:

type :: string : Message type: normal, chat, headline

from :: string : Sender JID

to :: string : Receiver JID

subject :: string : Subject, or empty string

body :: string : Body

####Result:
res :: integer : Status code (0 on success, 1 otherwise)

Examples:
    
    POST /api/send_message
    
    {
      "type": "headline",
      "from": "admin@localhost",
      "to": "user1@localhost",
      "subject": "Restart",
      "body": "In 5 minutes"
    }
    
    HTTP/1.1 200 OK
    ""
