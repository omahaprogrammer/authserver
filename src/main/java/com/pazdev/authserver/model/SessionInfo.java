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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "session_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SessionInfo.findAll", query = "SELECT s FROM SessionInfo s")
    , @NamedQuery(name = "SessionInfo.findById", query = "SELECT s FROM SessionInfo s WHERE s.id = :id")
    , @NamedQuery(name = "SessionInfo.findByCookieValue", query = "SELECT s FROM SessionInfo s WHERE s.cookieValue = :cookieValue")
    , @NamedQuery(name = "SessionInfo.findByExpirationTime", query = "SELECT s FROM SessionInfo s WHERE s.expirationTime = :expirationTime")})
public class SessionInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cookie_value")
    private String cookieValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationTime;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne
    private Profile profileId;

    public SessionInfo() {
    }

    public SessionInfo(Integer id) {
        this.id = id;
    }

    public SessionInfo(Integer id, String cookieValue, Date expirationTime) {
        this.id = id;
        this.cookieValue = cookieValue;
        this.expirationTime = expirationTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
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
        if (!(object instanceof SessionInfo)) {
            return false;
        }
        SessionInfo other = (SessionInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.SessionInfo[ id=" + id + " ]";
    }
    
}
