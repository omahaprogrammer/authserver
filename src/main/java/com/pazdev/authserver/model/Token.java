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
import java.time.Instant;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "token", catalog = "authserver", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
    , @NamedQuery(name = "Token.findById", query = "SELECT t FROM Token t WHERE t.id = :id")
    , @NamedQuery(name = "Token.findByIssuer", query = "SELECT t FROM Token t WHERE t.issuer = :issuer")
    , @NamedQuery(name = "Token.findBySubject", query = "SELECT t FROM Token t WHERE t.subject = :subject")
    , @NamedQuery(name = "Token.findByAudience", query = "SELECT t FROM Token t WHERE t.audience = :audience")
    , @NamedQuery(name = "Token.findByExpirationTime", query = "SELECT t FROM Token t WHERE t.expirationTime = :expirationTime")
    , @NamedQuery(name = "Token.findByNotBefore", query = "SELECT t FROM Token t WHERE t.notBefore = :notBefore")
    , @NamedQuery(name = "Token.findByIssuedAt", query = "SELECT t FROM Token t WHERE t.issuedAt = :issuedAt")
    , @NamedQuery(name = "Token.findByJwtId", query = "SELECT t FROM Token t WHERE t.jwtId = :jwtId")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Token implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "issuer")
    private String issuer;
    @Column(name = "subject")
    private String subject;
    @Column(name = "audience")
    private String audience;
    @Column(name = "expiration_time")
    private Instant expirationTime;
    @Column(name = "not_before")
    private Instant notBefore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issued_at", nullable = false)
    private Instant issuedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jwt_id", nullable = false)
    private String jwtId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tokenId")
    private Set<TokenExtraClaim> tokenExtraClaimSet;

    public Token() {
    }

    public Token(Integer id) {
        this.id = id;
    }

    public Token(Integer id, Instant issuedAt, String jwtId) {
        this.id = id;
        this.issuedAt = issuedAt;
        this.jwtId = jwtId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    @XmlTransient
    public Set<TokenExtraClaim> getTokenExtraClaimSet() {
        return tokenExtraClaimSet;
    }

    public void setTokenExtraClaimSet(Set<TokenExtraClaim> tokenExtraClaimSet) {
        this.tokenExtraClaimSet = tokenExtraClaimSet;
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
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.Token[ id=" + id + " ]";
    }
    
}
