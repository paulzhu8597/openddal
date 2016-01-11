/*
 * Copyright 2014-2016 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.openddal.server.processor;

import java.net.SocketAddress;

import com.openddal.engine.SessionInterface;
import com.openddal.server.ProtocolTransport;

import io.netty.util.AttributeKey;

/**
 * 
 * @author <a href="mailto:jorgie.mail@gmail.com">jorgie li</a>
 *
 */
public class RequestFactory {
    private static RequestFactory instance = new RequestFactory();

    private RequestFactory() {
    }

    public static RequestFactory getInstance() {
        return instance;
    }

    public Request createRequest(ProtocolTransport trans) {
        return new RequestImp(trans);
    }

    private static class RequestImp implements Request {
        
        private static final AttributeKey<SessionInterface> SESSION_ATTR_KEY =
                AttributeKey.valueOf("SESSION_ATTR_KEY");
        
        private ProtocolTransport trans;

        private RequestImp(ProtocolTransport trans) {
            this.trans = trans;
        }

        @Override
        public SocketAddress getRemoteSocketAddress() {
            return trans.getChannel().remoteAddress();
        }

        @Override
        public SessionInterface getSession() {
            return trans.getChannel().attr(SESSION_ATTR_KEY).get();
        }
    }
}
