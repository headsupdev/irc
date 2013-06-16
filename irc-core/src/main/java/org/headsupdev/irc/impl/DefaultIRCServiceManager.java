/*
 * Copyright 2010-2013 Heads Up Development Ltd.
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

package org.headsupdev.irc.impl;

import org.headsupdev.irc.IRCCommand;
import org.headsupdev.irc.IRCListener;
import org.headsupdev.irc.IRCServiceManager;
import org.headsupdev.irc.impl.commands.Help;
import org.schwering.irc.lib.IRCConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The default manager handles metadata and creating connections.
 *
 * @author Andrew Williams
 * @version $Id: DefaultIRCServiceManager.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class DefaultIRCServiceManager
    implements IRCServiceManager
{
    private Map<String, IRCCommand> commands = new HashMap<String,IRCCommand>();
    private List<IRCListener> listeners = new LinkedList<IRCListener>();

    private String name = "HeadsUp IRC Framework";
    private String description = "http://headsupdev.org";

    public DefaultIRCServiceManager()
    {
        addCommand( new Help() );
    }

    public String getServiceName()
    {
        return name;
    }

    public void setServiceName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public org.headsupdev.irc.IRCConnection connect( String host, String nick, String username, String realname )
        throws IOException
    {
        return connect( host, nick, null, username, realname );
    }

    public org.headsupdev.irc.IRCConnection connect( String host, String nick, String pass, String username, String realname )
        throws IOException
    {
        int portMin = 6667;
        int portMax = 6669;

        IRCConnection conn = new IRCConnection( host, portMin, portMax, pass, nick, username, realname );
        conn.setPong( true );

        org.headsupdev.irc.IRCConnection ret = new DefaultIRCConnection( conn, this );
        conn.addIRCEventListener( new DefaultIRCServiceListener( ret ) );

        conn.connect();
        return ret;
    }

    public Map<String,IRCCommand> getCommands()
    {
        return commands;
    }

    public void addCommand( IRCCommand command )
    {
        commands.put( command.getId(), command );
    }

    public void removeCommand( IRCCommand command )
    {
        commands.remove( command.getId() );
    }

    public List<IRCListener> getListeners()
    {
        return listeners;
    }

    public void addListener( IRCListener listener )
    {
        listeners.add( listener );
    }

    public void removeListener( IRCListener listener )
    {
        listeners.remove( listener );
    }
}

