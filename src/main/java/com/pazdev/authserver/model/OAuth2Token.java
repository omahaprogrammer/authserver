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
package com.pazdev.authserver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "o_auth2_token", catalog = "authserver", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OAuth2Token.findAll", query = "SELECT o FROM OAuth2Token o")
    , @NamedQuery(name = "OAuth2Token.findByScopes", query = "SELECT o FROM OAuth2Token o WHERE o.scopes = :scopes")
    , @NamedQuery(name = "OAuth2Token.findByClientId", query = "SELECT o FROM OAuth2Token o WHERE o.clientId = :clientId")
    , @NamedQuery(name = "OAuth2Token.findByUsername", query = "SELECT o FROM OAuth2Token o WHERE o.username = :username")
    , @NamedQuery(name = "OAuth2Token.findByTokenType", query = "SELECT o FROM OAuth2Token o WHERE o.tokenType = :tokenType")})
public class OAuth2Token extends Token implements Serializable {

    @Column(name = "scopes")
    private String scopes;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "username")
    private String username;
    @Column(name = "token_type")
    private String tokenType;

    public OAuth2Token() {
        super();
    }

    public OAuth2Token(Integer id) {
        super(id);
    }

    public String getScopes() {
        return scopes;
    }

    public void setScope(String scopes) {
        this.scopes = scopes;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.OAuth2Token[ id=" + getId() + " ]";
    }
    
}
