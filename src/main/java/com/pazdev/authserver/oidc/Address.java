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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
public class Address implements Serializable {
    @JsonProperty
    private final String formatted;
    @JsonProperty("street_address")
    private final String streetAddress;
    @JsonProperty
    private final String locality;
    @JsonProperty
    private final String region;
    @JsonProperty("postal_code")
    private final String postalCode;
    @JsonProperty
    private final String country;

    public Address(String formatted, String streetAddress, String locality, String region, String postalCode, String country) {
        this.formatted = formatted;
        this.streetAddress = streetAddress;
        this.locality = locality;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getFormatted() {
        return formatted;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public String getRegion() {
        return region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

}
