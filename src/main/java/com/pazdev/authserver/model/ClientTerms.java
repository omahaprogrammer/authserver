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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "client_terms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientTerms.findAll", query = "SELECT c FROM ClientTerms c")
    , @NamedQuery(name = "ClientTerms.findById", query = "SELECT c FROM ClientTerms c WHERE c.id = :id")
    , @NamedQuery(name = "ClientTerms.findByTermsUri", query = "SELECT c FROM ClientTerms c WHERE c.termsUri = :termsUri")
    , @NamedQuery(name = "ClientTerms.findByTermsUriLang", query = "SELECT c FROM ClientTerms c WHERE c.termsUriLang = :termsUriLang")})
public class ClientTerms implements Serializable, MultiLanguageClaim {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "terms_uri")
    private String termsUri;
    @Size(max = 2147483647)
    @Column(name = "terms_uri_lang")
    private String termsUriLang;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    public ClientTerms() {
    }

    public ClientTerms(Integer id) {
        this.id = id;
    }

    public ClientTerms(Integer id, String termsUri) {
        this.id = id;
        this.termsUri = termsUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTermsUri() {
        return termsUri;
    }

    public void setTermsUri(String termsUri) {
        this.termsUri = termsUri;
    }

    public String getTermsUriLang() {
        return termsUriLang;
    }

    public void setTermsUriLang(String termsUriLang) {
        this.termsUriLang = termsUriLang;
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
        if (!(object instanceof ClientTerms)) {
            return false;
        }
        ClientTerms other = (ClientTerms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.ClientTerms[ id=" + id + " ]";
    }

    @Override
    public String getValue() {
        return termsUri;
    }

    @Override
    public Optional<LangTag> getLanguageTag() {
        try {
            return Optional.ofNullable(LangTag.parse(termsUriLang));
        } catch (LangTagException e) {
            throw new RuntimeException(e);
        }
    }
   
}
