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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "profile_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileAddress.findAll", query = "SELECT p FROM ProfileAddress p")
    , @NamedQuery(name = "ProfileAddress.findById", query = "SELECT p FROM ProfileAddress p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileAddress.findByFormatted", query = "SELECT p FROM ProfileAddress p WHERE p.formatted = :formatted")
    , @NamedQuery(name = "ProfileAddress.findByStreetAddress", query = "SELECT p FROM ProfileAddress p WHERE p.streetAddress = :streetAddress")
    , @NamedQuery(name = "ProfileAddress.findByLocality", query = "SELECT p FROM ProfileAddress p WHERE p.locality = :locality")
    , @NamedQuery(name = "ProfileAddress.findByRegion", query = "SELECT p FROM ProfileAddress p WHERE p.region = :region")
    , @NamedQuery(name = "ProfileAddress.findByPostalCode", query = "SELECT p FROM ProfileAddress p WHERE p.postalCode = :postalCode")
    , @NamedQuery(name = "ProfileAddress.findByCountry", query = "SELECT p FROM ProfileAddress p WHERE p.country = :country")
    , @NamedQuery(name = "ProfileAddress.findByAddressLang", query = "SELECT p FROM ProfileAddress p WHERE p.addressLang = :addressLang")})
public class ProfileAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "formatted")
    private String formatted;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "locality")
    private String locality;
    @Column(name = "region")
    private String region;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "address_lang")
    private String addressLang;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne
    private Profile profileId;

    public ProfileAddress() {
    }

    public ProfileAddress(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLang() {
        return addressLang;
    }

    public void setAddressLang(String addressLang) {
        this.addressLang = addressLang;
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
        if (!(object instanceof ProfileAddress)) {
            return false;
        }
        ProfileAddress other = (ProfileAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.ProfileAddress[ id=" + id + " ]";
    }
    
}
