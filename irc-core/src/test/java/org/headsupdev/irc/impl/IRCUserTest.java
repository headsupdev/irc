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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * IRCUser Tester.
 *
 * @author Andrew Williams
 * @version $Id: IRCUserTest.java 55 2009-12-08 15:46:03Z handyande $
 * @since 1.0
 */
public class IRCUserTest
    extends TestCase
{

    IRCUser user;

    public IRCUserTest( String name )
    {
        super( name );

        user = new DefaultIRCUser( "nick", "login", "host" );
    }

    public void testGetNick()
        throws Exception
    {
        assertEquals( user.getNick(), "nick" );
    }

    public void testGetLogin()
        throws Exception
    {
        assertEquals( user.getLogin(), "login" );
    }

    public void testGetHost()
        throws Exception
    {
        assertEquals( user.getHost(), "host" );
    }

    public static Test suite()
    {
        return new TestSuite( IRCUserTest.class );
    }
}
