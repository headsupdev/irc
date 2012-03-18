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

import org.headsupdev.irc.IRCCommand;
import org.headsupdev.irc.IRCConnection;
import org.headsupdev.irc.IRCUser;

/**
 * A Broken Command Stub
 *
 * @author Andrew Williams
 * @version $Id: BrokenCommandStub.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class BrokenCommandStub
    implements IRCCommand
{
    public String getId()
    {
        return "broken";
    }

    public void onCommand( String channel, IRCUser user, String message, IRCConnection conn )
    {
        System.out.println( "throwing exception" );
        throw new RuntimeException( "We broke on purpose" );
    }

    public void onPrivateCommand( IRCUser user, String message, IRCConnection conn )
    {
    }

    public String getHelp( String channel )
    {
        return "This is a broken stub command";
    }
}
