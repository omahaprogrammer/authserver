/*
 * Copyright 2016 Jonathan Paz <jonathan@pazdev.com>.
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
package com.pazdev.authserver.services;

import com.pazdev.authserver.model.Client;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
public class ClientService {
    private final EntityManager em;
    
    @Inject
    public ClientService(EntityManager em) {
        this.em = em;
    }

    public Client getClient(String clientId) {
        Client retval = null;
        TypedQuery<Client> q = em.createNamedQuery("Client.findByClientId", Client.class);
        q.setParameter(1, clientId);
        List<Client> results = q.getResultList();
        if (!results.isEmpty()) {
            retval = results.get(0);
        }
        return retval;
    }
}
