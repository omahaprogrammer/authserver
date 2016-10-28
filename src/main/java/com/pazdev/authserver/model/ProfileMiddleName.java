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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "profile_middle_name")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileMiddleName.findAll", query = "SELECT p FROM ProfileMiddleName p")
    , @NamedQuery(name = "ProfileMiddleName.findById", query = "SELECT p FROM ProfileMiddleName p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileMiddleName.findByProfileMiddleName", query = "SELECT p FROM ProfileMiddleName p WHERE p.profileMiddleName = :profileMiddleName")
    , @NamedQuery(name = "ProfileMiddleName.findByProfileMiddleNameLang", query = "SELECT p FROM ProfileMiddleName p WHERE p.profileMiddleNameLang = :profileMiddleNameLang")})
public class ProfileMiddleName implements Serializable, MultiLanguageClaim<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profile_middle_name")
    private String profileMiddleName;
    @Column(name = "profile_middle_name_lang")
    private String profileMiddleNameLang;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne
    private Profile profileId;

    public ProfileMiddleName() {
    }

    public ProfileMiddleName(Integer id) {
        this.id = id;
    }

    public ProfileMiddleName(Integer id, String profileMiddleName) {
        this.id = id;
        this.profileMiddleName = profileMiddleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileMiddleName() {
        return profileMiddleName;
    }

    public void setProfileMiddleName(String profileMiddleName) {
        this.profileMiddleName = profileMiddleName;
    }

    public String getProfileMiddleNameLang() {
        return profileMiddleNameLang;
    }

    public void setProfileMiddleNameLang(String profileMiddleNameLang) {
        this.profileMiddleNameLang = profileMiddleNameLang;
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
        if (!(object instanceof ProfileMiddleName)) {
            return false;
        }
        ProfileMiddleName other = (ProfileMiddleName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.ProfileMiddleName[ id=" + id + " ]";
    }
    
    @Override
    public String getValue() {
        return profileMiddleName;
    }

    @Override
    public Optional<LangTag> getLanguageTag() {
        try {
            return Optional.ofNullable(LangTag.parse(profileMiddleNameLang));
        } catch (LangTagException e) {
            throw new RuntimeException(e);
        }
    }

}
