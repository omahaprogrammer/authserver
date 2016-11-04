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
@Table(name = "token_extra_claim", catalog = "authserver", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TokenExtraClaim.findAll", query = "SELECT t FROM TokenExtraClaim t")
    , @NamedQuery(name = "TokenExtraClaim.findById", query = "SELECT t FROM TokenExtraClaim t WHERE t.id = :id")
    , @NamedQuery(name = "TokenExtraClaim.findByClaimName", query = "SELECT t FROM TokenExtraClaim t WHERE t.claimName = :claimName")
    , @NamedQuery(name = "TokenExtraClaim.findByClaimValue", query = "SELECT t FROM TokenExtraClaim t WHERE t.claimValue = :claimValue")})
public class TokenExtraClaim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "claim_name", nullable = false, length = 2147483647)
    private String claimName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "claim_value", nullable = false, length = 2147483647)
    private String claimValue;
    @JoinColumn(name = "token_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Token tokenId;

    public TokenExtraClaim() {
    }

    public TokenExtraClaim(Integer id) {
        this.id = id;
    }

    public TokenExtraClaim(Integer id, String claimName, String claimValue) {
        this.id = id;
        this.claimName = claimName;
        this.claimValue = claimValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    public String getClaimValue() {
        return claimValue;
    }

    public void setClaimValue(String claimValue) {
        this.claimValue = claimValue;
    }

    public Token getTokenId() {
        return tokenId;
    }

    public void setTokenId(Token tokenId) {
        this.tokenId = tokenId;
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
        if (!(object instanceof TokenExtraClaim)) {
            return false;
        }
        TokenExtraClaim other = (TokenExtraClaim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.TokenExtraClaim[ id=" + id + " ]";
    }
    
}
