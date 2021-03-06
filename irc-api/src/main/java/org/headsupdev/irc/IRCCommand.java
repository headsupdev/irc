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
 * Basic listeners for an IRC command
 *
 * @author Andrew Williams
 * @version $Id: IRCCommand.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public interface IRCCommand
{
    public String getId();

    public void onCommand( String channel, IRCUser user, String message, IRCConnection connection );

    public void onPrivateCommand( IRCUser user, String message, IRCConnection connection );

    public String getHelp( String parameters );
}
