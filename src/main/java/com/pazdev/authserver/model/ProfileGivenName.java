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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "profile_given_name")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileGivenName.findAll", query = "SELECT p FROM ProfileGivenName p")
    , @NamedQuery(name = "ProfileGivenName.findById", query = "SELECT p FROM ProfileGivenName p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileGivenName.findByProfileGivenName", query = "SELECT p FROM ProfileGivenName p WHERE p.profileGivenName = :profileGivenName")
    , @NamedQuery(name = "ProfileGivenName.findByProfileGivenNameLang", query = "SELECT p FROM ProfileGivenName p WHERE p.profileGivenNameLang = :profileGivenNameLang")})
public class ProfileGivenName implements Serializable, MultiLanguageClaim<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profile_given_name")
    private String profileGivenName;
    @Column(name = "profile_given_name_lang")
    private String profileGivenNameLang;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne
    private Profile profileId;

    public ProfileGivenName() {
    }

    public ProfileGivenName(Integer id) {
        this.id = id;
    }

    public ProfileGivenName(Integer id, String profileGivenName) {
        this.id = id;
        this.profileGivenName = profileGivenName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileGivenName() {
        return profileGivenName;
    }

    public void setProfileGivenName(String profileGivenName) {
        this.profileGivenName = profileGivenName;
    }

    public String getProfileGivenNameLang() {
        return profileGivenNameLang;
    }

    public void setProfileGivenNameLang(String profileGivenNameLang) {
        this.profileGivenNameLang = profileGivenNameLang;
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
        if (!(object instanceof ProfileGivenName)) {
            return false;
        }
        ProfileGivenName other = (ProfileGivenName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.ProfileGivenName[ id=" + id + " ]";
    }
    
    @Override
    public String getValue() {
        return profileGivenName;
    }

    @Override
    public Optional<Locale> getLanguageTag() {
        Optional<Locale> retval = Optional.empty();
        if (profileGivenNameLang != null) {
            retval = Optional.of(Locale.forLanguageTag(profileGivenNameLang));
        }
        return retval;
    }

}
