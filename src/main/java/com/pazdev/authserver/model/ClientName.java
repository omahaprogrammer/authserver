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
import java.util.Locale;
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
@Table(name = "client_name", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "client_lang"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientName.findAll", query = "SELECT c FROM ClientName c")
    , @NamedQuery(name = "ClientName.findById", query = "SELECT c FROM ClientName c WHERE c.id = :id")
    , @NamedQuery(name = "ClientName.findByClientName", query = "SELECT c FROM ClientName c WHERE c.clientName = :clientName")
    , @NamedQuery(name = "ClientName.findByClientLang", query = "SELECT c FROM ClientName c WHERE c.clientLang = :clientLang")})
public class ClientName implements Serializable, MultiLanguageClaim<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_name", nullable = false)
    private String clientName;
    @Column(name = "client_lang")
    private String clientLang;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    public ClientName() {
    }

    public ClientName(Integer id) {
        this.id = id;
    }

    public ClientName(Integer id, String clientName) {
        this.id = id;
        this.clientName = clientName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLang() {
        return clientLang;
    }

    public void setClientLang(String clientLang) {
        this.clientLang = clientLang;
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
        if (!(object instanceof ClientName)) {
            return false;
        }
        ClientName other = (ClientName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.ClientName[ id=" + id + " ]";
    }

    @Override
    public String getValue() {
        return clientName;
    }

    @Override
    public Optional<Locale> getLanguageTag() {
        Optional<Locale> retval = Optional.empty();
        if (clientLang != null) {
            retval = Optional.of(Locale.forLanguageTag(clientLang));
        }
        return retval;
    }
   
}
