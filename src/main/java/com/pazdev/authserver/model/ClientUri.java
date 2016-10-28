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

import com.nimbusds.langtag.LangTag;
import com.nimbusds.langtag.LangTagException;
import java.io.Serializable;
import java.net.URI;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "client_uri", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "client_uri_lang"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientUri.findAll", query = "SELECT c FROM ClientUri c")
    , @NamedQuery(name = "ClientUri.findById", query = "SELECT c FROM ClientUri c WHERE c.id = :id")
    , @NamedQuery(name = "ClientUri.findByClientUri", query = "SELECT c FROM ClientUri c WHERE c.clientUri = :clientUri")
    , @NamedQuery(name = "ClientUri.findByClientUriLang", query = "SELECT c FROM ClientUri c WHERE c.clientUriLang = :clientUriLang")})
public class ClientUri implements Serializable, MultiLanguageClaim<URI> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_uri", nullable = false)
    private String clientUri;
    @Column(name = "client_uri_lang")
    private String clientUriLang;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    public ClientUri() {
    }

    public ClientUri(Integer id) {
        this.id = id;
    }

    public ClientUri(Integer id, String clientUri) {
        this.id = id;
        this.clientUri = clientUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientUri() {
        return clientUri;
    }

    public void setClientUri(String clientUri) {
        this.clientUri = clientUri;
    }

    public String getClientUriLang() {
        return clientUriLang;
    }

    public void setClientUriLang(String clientUriLang) {
        this.clientUriLang = clientUriLang;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientUri)) {
            return false;
        }
        ClientUri other = (ClientUri) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.ClientUri[ id=" + id + " ]";
    }

    @Override
    public URI getValue() {
        return URI.create(clientUri);
    }

    @Override
    public Optional<LangTag> getLanguageTag() {
        try {
            return Optional.ofNullable(LangTag.parse(clientUriLang));
        } catch (LangTagException e) {
            throw new RuntimeException(e);
        }
    }
    
}
