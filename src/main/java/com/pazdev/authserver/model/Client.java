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
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;

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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne
    private Profile profileId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_type", nullable = false)
    private String clientType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_id", nullable = false)
    private String clientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_secret", nullable = false)
    private String clientSecret;
    @Column(name = "client_id_issued_at")
    private Instant clientIdIssuedAt;
    @Column(name = "client_secret_expires_at")
    private Instant clientSecretExpiresAt;
    @Column(name = "token_endpoint_auth_method")
    private String tokenEndpointAuthMethod;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_uri")
    private String clientUri;
    @Column(name = "logo_uri")
    private String logoUri;
    @Column(name = "scopes")
    private String scopes;
    @Column(name = "tos_uri")
    private String tosUri;
    @Column(name = "policy_uri")
    private String policyUri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issue_date", nullable = false)
    private Instant issueDate;
    @Column(name = "software_id")
    @Type(type = "pg-uuid")
    private UUID softwareId;
    @Column(name = "software_version")
    private String softwareVersion;
    @Column(name = "application_type")
    private String applicationType;
    @Column(name = "sector_identifier_uri")
    private String sectorIdentifierUri;
    @Column(name = "subject_type")
    private String subjectType;
    @Column(name = "id_token_signed_response_alg")
    private String idTokenSignedResponseAlg;
    @Column(name = "id_token_encrypted_response_alg")
    private String idTokenEncryptedResponseAlg;
    @Column(name = "userinfo_signed_response_alg")
    private String userinfoSignedResponseAlg;
    @Column(name = "userinfo_encrypted_response_alg")
    private String userinfoEncryptedResponseAlg;
    @Column(name = "request_object_signing_alg")
    private String requestObjectSigningAlg;
    @Column(name = "request_object_encryption_alg")
    private String requestObjectEncryptionAlg;
    @Column(name = "token_endpoint_auth_signing_method")
    private String tokenEndpointAuthSigningMethod;
    @Column(name = "default_max_age")
    private Integer defaultMaxAge;
    @Column(name = "require_auth_time")
    private Boolean requireAuthTime;
    @Column(name = "initiate_login_uri")
    private String initiateLoginUri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Set<ClientRedirectUri> clientRedirectUriSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Set<ClientContact> clientContactSet;


    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(Integer id, String clientId, String clientSecret, String clientType, Instant issueDate) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientType = clientType;
        this.issueDate = issueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
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

    public Instant getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
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
    public Set<ClientRedirectUri> getClientRedirectUriSet() {
        return clientRedirectUriSet;
    }

    public void setClientRedirectUriSet(Set<ClientRedirectUri> clientRedirectUriSet) {
        this.clientRedirectUriSet = clientRedirectUriSet;
    }

    @XmlTransient
    public Set<ClientContact> getClientContactSet() {
        return clientContactSet;
    }

    public void setClientContactSet(Set<ClientContact> clientContactSet) {
        this.clientContactSet = clientContactSet;
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

    public Instant getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(Instant clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    public Instant getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public String getTokenEndpointAuthMethod() {
        return tokenEndpointAuthMethod;
    }

    public void setTokenEndpointAuthMethod(String tokenEndpointAuthMethod) {
        this.tokenEndpointAuthMethod = tokenEndpointAuthMethod;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientUri() {
        return clientUri;
    }

    public void setClientUri(String clientUri) {
        this.clientUri = clientUri;
    }

    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    public String getTosUri() {
        return tosUri;
    }

    public void setTosUri(String tosUri) {
        this.tosUri = tosUri;
    }

    public String getPolicyUri() {
        return policyUri;
    }

    public void setPolicyUri(String policyUri) {
        this.policyUri = policyUri;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getSectorIdentifierUri() {
        return sectorIdentifierUri;
    }

    public void setSectorIdentifierUri(String sectorIdentifierUri) {
        this.sectorIdentifierUri = sectorIdentifierUri;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getIdTokenSignedResponseAlg() {
        return idTokenSignedResponseAlg;
    }

    public void setIdTokenSignedResponseAlg(String idTokenSignedResponseAlg) {
        this.idTokenSignedResponseAlg = idTokenSignedResponseAlg;
    }

    public String getIdTokenEncryptedResponseAlg() {
        return idTokenEncryptedResponseAlg;
    }

    public void setIdTokenEncryptedResponseAlg(String idTokenEncryptedResponseAlg) {
        this.idTokenEncryptedResponseAlg = idTokenEncryptedResponseAlg;
    }

    public String getUserinfoSignedResponseAlg() {
        return userinfoSignedResponseAlg;
    }

    public void setUserinfoSignedResponseAlg(String userinfoSignedResponseAlg) {
        this.userinfoSignedResponseAlg = userinfoSignedResponseAlg;
    }

    public String getUserinfoEncryptedResponseAlg() {
        return userinfoEncryptedResponseAlg;
    }

    public void setUserinfoEncryptedResponseAlg(String userinfoEncryptedResponseAlg) {
        this.userinfoEncryptedResponseAlg = userinfoEncryptedResponseAlg;
    }

    public String getRequestObjectSigningAlg() {
        return requestObjectSigningAlg;
    }

    public void setRequestObjectSigningAlg(String requestObjectSigningAlg) {
        this.requestObjectSigningAlg = requestObjectSigningAlg;
    }

    public String getRequestObjectEncryptionAlg() {
        return requestObjectEncryptionAlg;
    }

    public void setRequestObjectEncryptionAlg(String requestObjectEncryptionAlg) {
        this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
    }

    public String getTokenEndpointAuthSigningMethod() {
        return tokenEndpointAuthSigningMethod;
    }

    public void setTokenEndpointAuthSigningMethod(String tokenEndpointAuthSigningMethod) {
        this.tokenEndpointAuthSigningMethod = tokenEndpointAuthSigningMethod;
    }

    public Integer getDefaultMaxAge() {
        return defaultMaxAge;
    }

    public void setDefaultMaxAge(Integer defaultMaxAge) {
        this.defaultMaxAge = defaultMaxAge;
    }

    public Boolean getRequireAuthTime() {
        return requireAuthTime;
    }

    public void setRequireAuthTime(Boolean requireAuthTime) {
        this.requireAuthTime = requireAuthTime;
    }

    public String getInitiateLoginUri() {
        return initiateLoginUri;
    }

    public void setInitiateLoginUri(String initiateLoginUri) {
        this.initiateLoginUri = initiateLoginUri;
    }

}
