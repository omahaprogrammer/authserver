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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "id_token", catalog = "authserver", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdToken.findAll", query = "SELECT i FROM IdToken i")
    , @NamedQuery(name = "IdToken.findByIdTokenId", query = "SELECT i FROM IdToken i WHERE i.idTokenId = :idTokenId")
    , @NamedQuery(name = "IdToken.findByAuthTime", query = "SELECT i FROM IdToken i WHERE i.authTime = :authTime")
    , @NamedQuery(name = "IdToken.findByNonce", query = "SELECT i FROM IdToken i WHERE i.nonce = :nonce")
    , @NamedQuery(name = "IdToken.findByAcr", query = "SELECT i FROM IdToken i WHERE i.acr = :acr")
    , @NamedQuery(name = "IdToken.findByAzp", query = "SELECT i FROM IdToken i WHERE i.azp = :azp")
    , @NamedQuery(name = "IdToken.findByAthash", query = "SELECT i FROM IdToken i WHERE i.athash = :athash")
    , @NamedQuery(name = "IdToken.findByChash", query = "SELECT i FROM IdToken i WHERE i.chash = :chash")})
public class IdToken extends Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "auth_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authTime;
    @Column(name = "nonce")
    private String nonce;
    @Column(name = "acr")
    private String acr;
    @Column(name = "azp")
    private String azp;
    @Column(name = "athash")
    private String athash;
    @Column(name = "chash")
    private String chash;

    public IdToken() {
        super();
    }

    public IdToken(Integer id) {
        super(id);
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getAcr() {
        return acr;
    }

    public void setAcr(String acr) {
        this.acr = acr;
    }

    public String getAzp() {
        return azp;
    }

    public void setAzp(String azp) {
        this.azp = azp;
    }

    public String getAthash() {
        return athash;
    }

    public void setAthash(String athash) {
        this.athash = athash;
    }

    public String getChash() {
        return chash;
    }

    public void setChash(String chash) {
        this.chash = chash;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.IdToken[ id=" + getId() + " ]";
    }
    
}
