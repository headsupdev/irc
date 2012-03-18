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

package org.headsupdev.irc;

/**
 * An abstract listener allowing the developer to implement what they are interested in.
 *
 * @author Andrew Williams
 * @version $Id: AbstractIRCListener.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public abstract class AbstractIRCListener
    implements IRCListener
{
    public void onRegistered( IRCConnection connection )
    {
    }

    public void onDisconnected( IRCConnection connection )
    {
    }

    public void onError( String message, IRCConnection connection )
    {
    }

    public void onError( int num, String message, IRCConnection connection )
    {
    }

    public void onInvite( String channel, IRCUser user, String invitee, IRCConnection connection )
    {
    }

    public void onJoin( String channel, IRCUser ircUser, IRCConnection connection )
    {
    }

    public void onKick( String channel, IRCUser user, String kickee, String message, IRCConnection connection )
    {
    }

    public void onMode( IRCUser user, String string, String mode, IRCConnection connection )
    {
    }

    public void onNick( IRCUser user, String newNick, IRCConnection connection )
    {
    }

    public void onNotice( String target, IRCUser user, String message, IRCConnection connection )
    {
    }

    public void onPart( String channel, IRCUser user, String message, IRCConnection connection )
    {
    }

    public void onPing( String ping, IRCConnection connection )
    {
        /* The correct response is handled internally by IRCLib */
    }

    public void onMessage( String channel, IRCUser user, String message, IRCConnection connection )
    {
    }

    public void onPrivateMessage( IRCUser user, String message, IRCConnection connection )
    {
        onMessage( user.getNick(), user, message, connection );
    }

    public void onAction( String target, IRCUser user, String action, IRCConnection connection )
    {
    }

    public void onQuit( IRCUser user, String message, IRCConnection connection )
    {
    }

    public void onReply( int num, String value, String message, IRCConnection connection )
    {
    }

    public void onTopic( String channel, IRCUser user, String topic, IRCConnection connection )
    {
    }

    public void onVersion( String target, IRCUser user, IRCConnection connection )
    {
        /* The correct response is now handled internally */
    }

    public void onTime( String target, IRCUser user, IRCConnection connection )
    {
        /* The correct response is now handled internally */
    }
}
