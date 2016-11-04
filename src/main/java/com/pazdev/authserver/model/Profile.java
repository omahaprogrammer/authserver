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
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "profile", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sub"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
    , @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id")
    , @NamedQuery(name = "Profile.findBySub", query = "SELECT p FROM Profile p WHERE p.sub = :sub")
    , @NamedQuery(name = "Profile.findByPreferredUsername", query = "SELECT p FROM Profile p WHERE p.preferredUsername = :preferredUsername")
    , @NamedQuery(name = "Profile.findByWebsite", query = "SELECT p FROM Profile p WHERE p.website = :website")
    , @NamedQuery(name = "Profile.findByEmail", query = "SELECT p FROM Profile p WHERE p.email = :email")
    , @NamedQuery(name = "Profile.findByEmailVerified", query = "SELECT p FROM Profile p WHERE p.emailVerified = :emailVerified")
    , @NamedQuery(name = "Profile.findByGender", query = "SELECT p FROM Profile p WHERE p.gender = :gender")
    , @NamedQuery(name = "Profile.findByBirthdate", query = "SELECT p FROM Profile p WHERE p.birthdate = :birthdate")
    , @NamedQuery(name = "Profile.findByZoneinfo", query = "SELECT p FROM Profile p WHERE p.zoneinfo = :zoneinfo")
    , @NamedQuery(name = "Profile.findByLocale", query = "SELECT p FROM Profile p WHERE p.locale = :locale")
    , @NamedQuery(name = "Profile.findByPhoneNumber", query = "SELECT p FROM Profile p WHERE p.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Profile.findByPhoneNumberVerified", query = "SELECT p FROM Profile p WHERE p.phoneNumberVerified = :phoneNumberVerified")
    , @NamedQuery(name = "Profile.findByAddressFormatted", query = "SELECT p FROM Profile p WHERE p.addressFormatted = :addressFormatted")
    , @NamedQuery(name = "Profile.findByAddressStreetAddress", query = "SELECT p FROM Profile p WHERE p.addressStreetAddress = :addressStreetAddress")
    , @NamedQuery(name = "Profile.findByAddressLocality", query = "SELECT p FROM Profile p WHERE p.addressLocality = :addressLocality")
    , @NamedQuery(name = "Profile.findByAddressRegion", query = "SELECT p FROM Profile p WHERE p.addressRegion = :addressRegion")
    , @NamedQuery(name = "Profile.findByAddressPostalCode", query = "SELECT p FROM Profile p WHERE p.addressPostalCode = :addressPostalCode")
    , @NamedQuery(name = "Profile.findByAddressCountry", query = "SELECT p FROM Profile p WHERE p.addressCountry = :addressCountry")
    , @NamedQuery(name = "Profile.findByUpdatedAt", query = "SELECT p FROM Profile p WHERE p.updatedAt = :updatedAt")})
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub", nullable = false)
    private String sub;
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preferred_username", nullable = false)
    private String preferredUsername;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "password_bytes", nullable = false)
    private byte[] passwordBytes;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "salt", nullable = false)
    private byte[] salt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rounds")
    private int rounds;
    @JoinColumn(name = "picture", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UploadedContent picture;
    @Column(name = "website")
    private String website;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "zoneinfo")
    private String zoneinfo;
    @Column(name = "locale")
    private String locale;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone_number_verified", nullable = false)
    private boolean phoneNumberVerified;
    @Column(name = "address_formatted")
    private String addressFormatted;
    @Column(name = "address_street_address")
    private String addressStreetAddress;
    @Column(name = "address_locality")
    private String addressLocality;
    @Column(name = "address_region")
    private String addressRegion;
    @Column(name = "address_postal_code")
    private String addressPostalCode;
    @Column(name = "address_country")
    private String addressCountry;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @OneToMany(mappedBy = "accountId")
    private Set<ProfileAttribute> profileAttributeSet;
    @OneToMany(mappedBy = "profileId")
    private Set<ProfileAddress> profileAddressSet;

    public Profile() {
    }

    public Profile(Integer id) {
        this.id = id;
    }

    public Profile(Integer id, String sub, String preferredUsername, byte[] passwordBytes, byte[] salt, Integer rounds, boolean emailVerified, boolean phoneNumberVerified) {
        this.id = id;
        this.sub = sub;
        this.preferredUsername = preferredUsername;
        this.passwordBytes = passwordBytes;
        this.salt = salt;
        this.rounds = rounds;
        this.emailVerified = emailVerified;
        this.phoneNumberVerified = phoneNumberVerified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public byte[] getPasswordBytes() {
        return passwordBytes;
    }

    public void setPasswordBytes(byte[] passwordBytes) {
        this.passwordBytes = passwordBytes;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public UploadedContent getPicture() {
        return picture;
    }

    public void setPicture(UploadedContent picture) {
        this.picture = picture;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getZoneinfo() {
        return zoneinfo;
    }

    public void setZoneinfo(String zoneinfo) {
        this.zoneinfo = zoneinfo;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
    }

    public String getAddressFormatted() {
        return addressFormatted;
    }

    public void setAddressFormatted(String addressFormatted) {
        this.addressFormatted = addressFormatted;
    }

    public String getAddressStreetAddress() {
        return addressStreetAddress;
    }

    public void setAddressStreetAddress(String addressStreetAddress) {
        this.addressStreetAddress = addressStreetAddress;
    }

    public String getAddressLocality() {
        return addressLocality;
    }

    public void setAddressLocality(String addressLocality) {
        this.addressLocality = addressLocality;
    }

    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Set<ProfileAttribute> getProfileAttributeSet() {
        return profileAttributeSet;
    }

    public void setProfileAttributeSet(Set<ProfileAttribute> profileAttributeSet) {
        this.profileAttributeSet = profileAttributeSet;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.Profile[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<ProfileAddress> getProfileAddressSet() {
        return profileAddressSet;
    }

    public void setProfileAddressSet(Set<ProfileAddress> profileAddressSet) {
        this.profileAddressSet = profileAddressSet;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
