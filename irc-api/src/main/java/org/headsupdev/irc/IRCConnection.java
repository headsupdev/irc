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

package org.headsupdev.irc;

import java.io.IOException;

/**
 * The irc connection represents an actual connection and provides typical functionality. 
 *
 * @author Andrew Williams
 * @version $Id: IRCConnection.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public interface IRCConnection
{
    IRCServiceManager getManager();

    void disconnect()
        throws IOException;

    void disconnect( String reason )
        throws IOException;

    void sendMessage( String to, String message );

    void sendAction( String to, String action );

    void sendNotice( String to, String message );

    void join( String channel );

    void part( String channel );

    void part( String channel, String reason );

    void op( String channel, String nick );

    void deOp( String channel, String nick );

    void voice( String channel, String nick );

    void deVoice( String channel, String nick );

    void kick( String channel, String nick );

    void kick( String channel, String nick, String reason );

    void ban( String channel, String mask );

    void unBan( String channel, String mask );

    String getHost();

    String getNick();

    void setNick( String nick );

    String getUsername();

    String getRealname();

    String getTopic( String channel );

    void setTopic( String channel, String topic );

    String getTime( String nick );
}