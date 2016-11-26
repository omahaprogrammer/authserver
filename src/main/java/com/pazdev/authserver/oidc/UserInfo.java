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
package com.pazdev.authserver.oidc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
public class UserInfo {
    @JsonProperty("sub")
    private String subject;
    @JsonProperty
    private String name;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty
    private String nickname;
    @JsonProperty("preferred_username")
    private String preferredUsername;
    @JsonProperty
    private URI profile;
    @JsonProperty
    private URI picture;
    @JsonProperty
    private URI website;
    @JsonProperty
    private String email;
    @JsonProperty("email_verified")
    private Boolean emailVerified;
    @JsonProperty
    private String gender;
    @JsonProperty
    private LocalDate birthdate;
    @JsonProperty
    private String zoneinfo;
    @JsonProperty
    private String locale;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("phone_number_verified")
    private Boolean phoneNumberVerified;
    @JsonProperty
    private Address address;
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s")
    private Instant updatedAt;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public URI getProfile() {
        return profile;
    }

    public void setProfile(URI profile) {
        this.profile = profile;
    }

    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    public URI getWebsite() {
        return website;
    }

    public void setWebsite(URI website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
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

    public Boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(Boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
