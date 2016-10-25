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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@Entity
@Table(name = "client_logo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientLogo.findAll", query = "SELECT c FROM ClientLogo c")
    , @NamedQuery(name = "ClientLogo.findById", query = "SELECT c FROM ClientLogo c WHERE c.id = :id")
    , @NamedQuery(name = "ClientLogo.findByLogoLang", query = "SELECT c FROM ClientLogo c WHERE c.logoLang = :logoLang")})
public class ClientLogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "logo_image")
    private byte[] logoImage;
    @Column(name = "logo_lang")
    private String logoLang;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    public ClientLogo() {
    }

    public ClientLogo(Integer id) {
        this.id = id;
    }

    public ClientLogo(Integer id, byte[] logoImage) {
        this.id = id;
        this.logoImage = logoImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(byte[] logoImage) {
        this.logoImage = logoImage;
    }

    public String getLogoLang() {
        return logoLang;
    }

    public void setLogoLang(String logoLang) {
        this.logoLang = logoLang;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof ClientLogo)) {
            return false;
        }
        ClientLogo other = (ClientLogo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pazdev.authserver.model.ClientLogo[ id=" + id + " ]";
    }
    
}
