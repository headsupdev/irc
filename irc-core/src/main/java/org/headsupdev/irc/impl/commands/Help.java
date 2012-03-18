/*
 * Copyright 2010-2011 Heads Up Development Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.headsupdev.irc.impl.commands;

import org.headsupdev.irc.AbstractIRCCommand;
import org.headsupdev.irc.IRCCommand;
import org.headsupdev.irc.IRCConnection;
import org.headsupdev.irc.IRCServiceManager;
import org.headsupdev.irc.IRCUser;

import java.util.Iterator;
import java.util.Map;

/**
 * The help command provides information on the available commands.
 *
 * @author Andrew Williams
 * @version $Id: Help.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class Help
    extends AbstractIRCCommand
{
    public String getId()
    {
        return "help";
    }

    public void onCommand( String channel, IRCUser user, String message, IRCConnection conn )
    {
        Map commands = IRCServiceManager.getInstance().getCommands();

        if ( message == null || message.equals( "" ) )
        {
            conn.sendMessage( channel, IRCServiceManager.getInstance().getServiceName() +
                " - the following commands are available" );
            Iterator commandIter = commands.keySet().iterator();
            StringBuffer list = new StringBuffer( "  " );
            while ( commandIter.hasNext() )
            {
                String command = (String) commandIter.next();
                list.append( command );
                list.append( " " );
            }

            conn.sendMessage( channel, list.toString() );
            conn.sendMessage( channel, "I can be addressed as " + conn.getNick() + ": or by using the prefixes !,~ or '" );
            conn.sendMessage( channel, "For help on any command send me the message help <command>" );
        }
        else
        {
            String command, parameters;
            int space;
            if ( ( space = message.indexOf( ' ' ) ) == -1 )
            {
                command = message;
                parameters = "";
            }
            else
            {
                command = message.substring( 0, space );
                parameters = message.substring( space + 1 ).trim();
            }

            IRCCommand ircCommand = (IRCCommand) commands.get( command );
            if ( ircCommand == null )
            {
                conn.sendMessage( channel, "Command \"" + command + "\" not found" );
            }
            else
            {
                conn.sendMessage( channel, ircCommand.getHelp( parameters ) );
            }
        }

    }
}
