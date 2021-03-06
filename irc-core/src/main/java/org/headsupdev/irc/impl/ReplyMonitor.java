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

package org.headsupdev.irc.impl;

import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import EDU.oswego.cs.dl.util.concurrent.TimeoutException;
import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.IRCEventAdapter;
import org.schwering.irc.lib.IRCUser;
import org.schwering.irc.lib.IRCUtil;

/**
 * Register an interest in a property and wait for the reply to come in.
 * Currently it only supports <code>getTopic</code> and <code>getTime</code>.
 *
 * @author Andrew Williams
 * @version $Id: ReplyMonitor.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class ReplyMonitor
    extends IRCEventAdapter
{

    private IRCConnection conn;

    private int type;

    private String param;

    private FutureResult reply;

    /**
     * Make an asynchronous request in a synchronous manner.
     *
     * @param conn The connection to request on
     * @param type The type of request to make
     */
    public ReplyMonitor( IRCConnection conn, int type )
    {
        this( conn, type, null );
    }

    /**
     * Make an asynchronous request in a synchronous manner.
     *
     * @param conn  The connection to request on
     * @param type  The type of request to make
     * @param param An optional parameter
     */
    public ReplyMonitor( IRCConnection conn, int type, String param )
    {
        this.conn = conn;
        this.type = type;
        this.param = param;
        this.reply = new FutureResult();
    }

    /* This compensates for a bug in IRCLib - hopefully soon to be fixed */
    public void onNotice( String target, IRCUser ircUser, String message )
    {
        if ( type == IRCUtil.RPL_TIME && message.startsWith( "TIME " ) )
        {
            reply.set( message.substring( 5 ) );
        }
    }

    /**
     * The server informs us of any replies.
     * If they match the one we are watching for then we need to let folk know
     * it has arrived.
     *
     * @param num     The reply type
     * @param value   The username and other info related to the request
     * @param message The reply
     */
    public void onReply( int num, String value, String message )
    {
        if ( num == type || ( type == IRCUtil.RPL_TOPIC && num == IRCUtil.RPL_NOTOPIC ) )
        {
            reply.set( message );
        }
    }

    /**
     * If an error is recieved we should probably pass this on...
     *
     * @param message The error
     */
    public void onError( String message )
    {
        reply.set( "error - " + message );
    }

    /**
     * If an error is recieved we should probably pass this on...
     *
     * @param num     The error number
     * @param message The error
     */
    public void onError( int num, String message )
    {
        reply.set( "error (" + num + ") - " + message );
    }

    /**
     * get the reply to our request. This blocks and waits for the reply to
     * arrive.
     *
     * @return The reply sent back from the server
     */
    public String getReply()
    {
        /* we only handle the topic and time replies at the moment */
        if ( type != IRCUtil.RPL_TOPIC && type != IRCUtil.RPL_TIME )
        {
            return null;
        }

        String ret = null;
        conn.addIRCEventListener( this );
        if ( type == IRCUtil.RPL_TOPIC )
        {
            conn.doTopic( param );
        }
        if ( type == IRCUtil.RPL_TIME )
        {
            conn.doPrivmsg( param, IRCUtil.actionIndicator + "TIME" + IRCUtil.actionIndicator );
        }

        try
        {
            ret = (String) reply.timedGet( 60000 ); // wait only a minute for replies
        }
        catch ( TimeoutException e )
        {
            System.err.println( "Timed out waiting for type " + type );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            conn.removeIRCEventListener( this );
        }
        return ret;
    }
}
