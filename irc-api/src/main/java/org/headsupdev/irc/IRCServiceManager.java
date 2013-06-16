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
import java.util.List;
import java.util.Map;

/**
 * The irc manager is the central class to control access to connections.
 *
 * @author Andrew Williams
 * @version $Id: IRCServiceManager.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public interface IRCServiceManager
{
    public String getServiceName();

    public void setServiceName( String name );

    public String getDescription();

    public void setDescription( String description );

    public IRCConnection connect( String host, String nick, String username, String realname )
        throws IOException;

    public IRCConnection connect( String host, String nick, String pass, String username, String realname )
        throws IOException;

    public Map<String,IRCCommand> getCommands();

    public List<IRCListener> getListeners();
}
