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

import org.headsupdev.irc.IRCUser;

/**
 * The default simple user object.
 *
 * @author Andrew Williams
 * @version $Id: DefaultIRCUser.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class DefaultIRCUser
    implements IRCUser
{

    private String nick, login, host;

    public DefaultIRCUser( String nick, String login, String host )
    {
        this.nick = nick;
        this.login = login;
        this.host = host;
    }

    public DefaultIRCUser( org.schwering.irc.lib.IRCUser user )
    {
        this.nick = user.getNick();
        this.login = user.getUsername();
        this.host = user.getHost();
    }

    public String getNick()
    {
        return nick;
    }

    public String getLogin()
    {
        return login;
    }

    public String getHost()
    {
        return host;
    }
}
