HeadsUp Development IRC Service
===============================

An IRC service and API for running an IRC bot with custom commands

An example usage:

    IRCServiceManager manager = IRCServiceManager.getInstance();

    ( (DefaultIRCServiceManager) manager ).addCommand( new AbstractIRCCommand() {
        public String getId() {
            return "hello";
        }

        public void onCommand( String channel, IRCUser ircUser, message, IRCConnection connection ) {
            connection.sendMessage( channel, "Hello World" );
        }
    } );

    IRCConnection conn = manager.connect( "hostname", "nick", "password", "username", "FullName" );
    conn.join( "#channel" );

