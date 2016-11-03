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
@Table(name = "profile_name")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileName.findAll", query = "SELECT p FROM ProfileName p")
    , @NamedQuery(name = "ProfileName.findById", query = "SELECT p FROM ProfileName p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileName.findByProfileName", query = "SELECT p FROM ProfileName p WHERE p.profileName = :profileName")
    , @NamedQuery(name = "ProfileName.findByProfileNameLang", query = "SELECT p FROM ProfileName p WHERE p.profileNameLang = :profileNameLang")})
public class ProfileName implements Serializable, MultiLanguageClaim<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "profile_name_lang")
    private String profileNameLang;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne
    private Profile profileId;

    public ProfileName() {
    }

    public ProfileName(Integer id) {
        this.id = id;
    }

    public ProfileName(Integer id, String profileName) {
        this.id = id;
        this.profileName = profileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileNameLang() {
        return profileNameLang;
    }

    public void setProfileNameLang(String profileNameLang) {
        this.profileNameLang = profileNameLang;
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
        if (!(object instanceof ProfileName)) {
            return false;
        }
        ProfileName other = (ProfileName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.ProfileName[ id=" + id + " ]";
    }
    
    @Override
    public String getValue() {
        return profileName;
    }

    @Override
    public Optional<Locale> getLanguageTag() {
        Optional<Locale> retval = Optional.empty();
        if (profileNameLang != null) {
            retval = Optional.of(Locale.forLanguageTag(profileNameLang));
        }
        return retval;
    }

}
