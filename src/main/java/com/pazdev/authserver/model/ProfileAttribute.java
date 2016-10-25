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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "profile_attribute", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"account_id", "attribute_name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileAttribute.findAll", query = "SELECT p FROM ProfileAttribute p")
    , @NamedQuery(name = "ProfileAttribute.findById", query = "SELECT p FROM ProfileAttribute p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileAttribute.findByAttributeName", query = "SELECT p FROM ProfileAttribute p WHERE p.attributeName = :attributeName")
    , @NamedQuery(name = "ProfileAttribute.findByAttributeValue", query = "SELECT p FROM ProfileAttribute p WHERE p.attributeValue = :attributeValue")})
public class ProfileAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "attribute_name", nullable = false, length = 2147483647)
    private String attributeName;
    @Size(max = 2147483647)
    @Column(name = "attribute_value", length = 2147483647)
    private String attributeValue;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne
    private Profile accountId;

    public ProfileAttribute() {
    }

    public ProfileAttribute(Integer id) {
        this.id = id;
    }

    public ProfileAttribute(Integer id, String attributeName) {
        this.id = id;
        this.attributeName = attributeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Profile getAccountId() {
        return accountId;
    }

    public void setAccountId(Profile accountId) {
        this.accountId = accountId;
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
        if (!(object instanceof ProfileAttribute)) {
            return false;
        }
        ProfileAttribute other = (ProfileAttribute) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.ProfileAttribute[ id=" + id + " ]";
    }
    
}
