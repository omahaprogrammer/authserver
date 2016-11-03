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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "uploaded_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UploadedContent.findAll", query = "SELECT u FROM UploadedContent u")
    , @NamedQuery(name = "UploadedContent.findById", query = "SELECT u FROM UploadedContent u WHERE u.id = :id")
    , @NamedQuery(name = "UploadedContent.findByPublicId", query = "SELECT u FROM UploadedContent u WHERE u.public_id = :id")
    , @NamedQuery(name = "UploadedContent.findByMimeType", query = "SELECT u FROM UploadedContent u WHERE u.mimeType = :mimeType")})
public class UploadedContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "public_id", nullable = false)
    @Type(type = "pg-uuid")
    private UUID publicId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "contents", nullable = false)
    private byte[] contents;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mime_type")
    private String mimeType;
    @OneToMany(mappedBy = "picture")
    private Set<Profile> profileSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logoImage")
    private Set<ClientLogo> clientLogoSet;

    public UploadedContent() {
    }

    public UploadedContent(Integer id) {
        this.id = id;
    }

    public UploadedContent(Integer id, UUID publicId, byte[] contents, String mimeType) {
        this.id = id;
        this.publicId = publicId;
        this.contents = contents;
        this.mimeType = mimeType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @XmlTransient
    public Set<Profile> getProfileSet() {
        return profileSet;
    }

    public void setProfileSet(Set<Profile> profileSet) {
        this.profileSet = profileSet;
    }

    @XmlTransient
    public Set<ClientLogo> getClientLogoSet() {
        return clientLogoSet;
    }

    public void setClientLogoSet(Set<ClientLogo> clientLogoSet) {
        this.clientLogoSet = clientLogoSet;
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
        if (!(object instanceof UploadedContent)) {
            return false;
        }
        UploadedContent other = (UploadedContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.UploadedContent[ id=" + id + " ]";
    }

}
