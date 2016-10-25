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
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "client", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_secret"})
    , @UniqueConstraint(columnNames = {"client_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id")
    , @NamedQuery(name = "Client.findByClientId", query = "SELECT c FROM Client c WHERE c.clientId = :clientId")
    , @NamedQuery(name = "Client.findByClientSecret", query = "SELECT c FROM Client c WHERE c.clientSecret = :clientSecret")
    , @NamedQuery(name = "Client.findByClientType", query = "SELECT c FROM Client c WHERE c.clientType = :clientType")
    , @NamedQuery(name = "Client.findByIssueDate", query = "SELECT c FROM Client c WHERE c.issueDate = :issueDate")
    , @NamedQuery(name = "Client.findByGrantTypes", query = "SELECT c FROM Client c WHERE c.grantTypes = :grantTypes")
    , @NamedQuery(name = "Client.findByResponseTypes", query = "SELECT c FROM Client c WHERE c.responseTypes = :responseTypes")
    , @NamedQuery(name = "Client.findByScopes", query = "SELECT c FROM Client c WHERE c.scopes = :scopes")
    , @NamedQuery(name = "Client.findBySoftwareVersion", query = "SELECT c FROM Client c WHERE c.softwareVersion = :softwareVersion")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_id", nullable = false)
    private String clientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_secret", nullable = false)
    private String clientSecret;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_type", nullable = false)
    private String clientType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issue_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime issueDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grant_types", nullable = false)
    private String grantTypes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "response_types", nullable = false)
    private String responseTypes;
    @Column(name = "scopes")
    private String scopes;
    @Lob
    @Column(name = "software_id")
    private UUID softwareId;
    @Column(name = "software_version")
    private String softwareVersion;
    @OneToMany(mappedBy = "clientId")
    private Set<ClientUri> clientUriSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Set<ClientContact> clientContactSet;
    @OneToMany(mappedBy = "clientId")
    private Set<ClientPolicy> clientLogoSet;
    @OneToMany(mappedBy = "clientId")
    private Set<ClientName> clientNameSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Set<ClientRedirectUri> clientRedirectUriSet;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(Integer id, String clientId, String clientSecret, String clientType, ZonedDateTime issueDate, String grantTypes, String responseTypes) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientType = clientType;
        this.issueDate = issueDate;
        this.grantTypes = grantTypes;
        this.responseTypes = responseTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public ZonedDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(ZonedDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getResponseTypes() {
        return responseTypes;
    }

    public void setResponseTypes(String responseTypes) {
        this.responseTypes = responseTypes;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public UUID getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(UUID softwareId) {
        this.softwareId = softwareId;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    @XmlTransient
    public Set<ClientUri> getClientUriSet() {
        return clientUriSet;
    }

    public void setClientUriSet(Set<ClientUri> clientUriSet) {
        this.clientUriSet = clientUriSet;
    }

    @XmlTransient
    public Set<ClientContact> getClientContactSet() {
        return clientContactSet;
    }

    public void setClientContactSet(Set<ClientContact> clientContactSet) {
        this.clientContactSet = clientContactSet;
    }

    @XmlTransient
    public Set<ClientPolicy> getClientLogoSet() {
        return clientLogoSet;
    }

    public void setClientLogoSet(Set<ClientPolicy> clientLogoSet) {
        this.clientLogoSet = clientLogoSet;
    }

    @XmlTransient
    public Set<ClientName> getClientNameSet() {
        return clientNameSet;
    }

    public void setClientNameSet(Set<ClientName> clientNameSet) {
        this.clientNameSet = clientNameSet;
    }

    @XmlTransient
    public Set<ClientRedirectUri> getClientRedirectUriSet() {
        return clientRedirectUriSet;
    }

    public void setClientRedirectUriSet(Set<ClientRedirectUri> clientRedirectUriSet) {
        this.clientRedirectUriSet = clientRedirectUriSet;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.Client[ id=" + id + " ]";
    }
    
}
