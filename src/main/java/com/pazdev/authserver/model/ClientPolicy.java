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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Table(name = "client_policy", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "policy_uri_lang"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientPolicy.findAll", query = "SELECT c FROM ClientPolicy c")
    , @NamedQuery(name = "ClientPolicy.findById", query = "SELECT c FROM ClientPolicy c WHERE c.id = :id")
    , @NamedQuery(name = "ClientPolicy.findByPolicyUri", query = "SELECT c FROM ClientPolicy c WHERE c.policyUri = :policyUri")
    , @NamedQuery(name = "ClientPolicy.findByPolicyUriLang", query = "SELECT c FROM ClientPolicy c WHERE c.policyUriLang = :policyUriLang")})
public class ClientPolicy implements Serializable, MultiLanguageClaim {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "policy_uri", nullable = false)
    private String policyUri;
    @Column(name = "policy_uri_lang")
    private String policyUriLang;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    public ClientPolicy() {
    }

    public ClientPolicy(Integer id) {
        this.id = id;
    }

    public ClientPolicy(Integer id, String policyUri) {
        this.id = id;
        this.policyUri = policyUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyUri() {
        return policyUri;
    }

    public void setPolicyUri(String policyUri) {
        this.policyUri = policyUri;
    }

    public String getPolicyUriLang() {
        return policyUriLang;
    }

    public void setPolicyUriLang(String policyUriLang) {
        this.policyUriLang = policyUriLang;
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
        if (!(object instanceof ClientPolicy)) {
            return false;
        }
        ClientPolicy other = (ClientPolicy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.ClientPolicy[ id=" + id + " ]";
    }

    @Override
    public String getValue() {
        return policyUri;
    }

    @Override
    public Optional<LangTag> getLanguageTag() {
        try {
            return Optional.ofNullable(LangTag.parse(policyUriLang));
        } catch (LangTagException ex) {
            throw new RuntimeException(ex);
        }
    }
   
}
