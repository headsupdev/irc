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

import org.headsupdev.irc.IRCServiceManager;
import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.IRCUtil;

import java.io.IOException;

/**
 * the default connection provider which handles common irc functionality.
 *
 * @author Andrew Williams
 * @version $Id: DefaultIRCConnection.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class DefaultIRCConnection
    implements org.headsupdev.irc.IRCConnection
{
    private IRCConnection conn;
    private IRCServiceManager manager;

    public DefaultIRCConnection( IRCConnection conn, IRCServiceManager manager )
    {
        this.conn = conn;
        this.manager = manager;
    }

    public IRCServiceManager getManager()
    {
        return manager;
    }

    public void disconnect()
        throws IOException
    {
        disconnect( "Application shutting down" );
    }

    public void disconnect( String reason )
        throws IOException
    {
        checkConnected();

        conn.doQuit( reason );
        conn = null;
    }

    public void sendMessage( String to, String message )
    {
        if ( to == null || message == null )
        {
            throw new IllegalArgumentException();
        }

        checkConnected();

        int linesSent = 0;
        String[] lines = message.split( "\n" );
        for ( int i = 0; i < lines.length; i++ )
        {
            linesSent++;
            if ( linesSent > 5 )
            {
                try
                {
                    Thread.sleep( 100 * ( ( linesSent <= 10 ) ? 1 : 5 ) );
                }
                catch ( InterruptedException e )
                {
                    // risk posting anyway
                }
            }
            conn.doPrivmsg( to, lines[i] );
        }
    }

    public void sendAction( String to, String action )
    {
        checkConnected();

        conn.doPrivmsg( to, IRCUtil.actionIndicator + "ACTION " + action + IRCUtil.actionIndicator );
    }

    public void sendNotice( String to, String notice )
    {
        checkConnected();

        conn.doNotice( to, notice );
    }

    public void join( String channel )
    {
        checkConnected();

        conn.doJoin( channel );
    }

    public void part( String channel )
    {
        checkConnected();

        conn.doPart( channel );
    }

    public void part( String channel, String reason )
    {
        checkConnected();

        conn.doPart( channel, reason );
    }

    public void op( String channel, String nick )
    {
        checkConnected();

        conn.doMode( channel, "+o " + nick );
    }

    public void deOp( String channel, String nick )
    {
        checkConnected();

        conn.doMode( channel, "-o " + nick );
    }

    public void voice( String channel, String nick )
    {
        checkConnected();

        conn.doMode( channel, "+v " + nick );
    }

    public void deVoice( String channel, String nick )
    {
        checkConnected();

        conn.doMode( channel, "-v " + nick );
    }

    public void kick( String channel, String nick )
    {
        checkConnected();

        conn.doKick( channel, nick );
    }

    public void kick( String channel, String nick, String reason )
    {
        checkConnected();

        conn.doKick( channel, nick, reason );
    }

    public void ban( String channel, String mask )
    {
        checkConnected();

        conn.doMode( channel, "+b " + mask );
    }

    public void unBan( String channel, String mask )
    {
        checkConnected();

        conn.doMode( channel, "-b " + mask );
    }

    public String getHost()
    {
        checkConnected();

        return conn.getHost();
    }

    public String getNick()
    {
        checkConnected();

        return conn.getNick();
    }

    public void setNick( String nick )
    {
        checkConnected();

        conn.doNick( nick );
    }

    public String getUsername()
    {
        checkConnected();

        return conn.getUsername();
    }

    public String getRealname()
    {
        checkConnected();

        return conn.getRealname();
    }

    public String getTopic( String channel )
    {
        checkConnected();

        return ( new ReplyMonitor( conn, IRCUtil.RPL_TOPIC, channel ) ).getReply();
    }

    public void setTopic( String channel, String topic )
    {
        checkConnected();

        conn.doTopic( channel, topic );
    }

    public String getTime( String nick )
    {
        return ( new ReplyMonitor( conn, IRCUtil.RPL_TIME, nick ) ).getReply();
    }

    public void checkConnected()
    {
        if ( conn == null )
        {
            throw new IllegalStateException( "Must be connected to a server" );
        }
    }
}