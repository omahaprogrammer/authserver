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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pazdev.jwt.JWTClaims;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@JsonDeserialize(builder = IdToken.Builder.class)
public class IdToken extends JWTClaims {

    @JsonProperty("auth_time")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s")
    private final Instant authTime;
    @JsonProperty("nonce")
    private final String nonce;
    @JsonProperty("acr")
    private final String acr;
    @JsonProperty("amr")
    private final List<String> amr;
    @JsonProperty("azp")
    private final String azp;
    @JsonProperty("at_hash")
    private final String atHash;
    @JsonProperty("c_hash")
    private final String cHash;

    public IdToken(Instant authTime, String nonce, String acr, List<String> amr, String azp, String atHash, String cHash, String issuer, String subject, String audience, Instant expirationTime, Instant notBefore, Instant issuedAt, String jwtId, Map<String, Object> claims) {
        super(issuer, subject, audience, expirationTime, notBefore, issuedAt, jwtId, claims);
        this.authTime = authTime;
        this.nonce = nonce;
        this.acr = acr;
        this.amr = amr;
        this.azp = azp;
        this.atHash = atHash;
        this.cHash = cHash;
    }

    public Instant getAuthTime() {
        return authTime;
    }

    public String getNonce() {
        return nonce;
    }

    public String getAcr() {
        return acr;
    }

    public List<String> getAmr() {
        return amr;
    }

    public String getAzp() {
        return azp;
    }

    public String getAtHash() {
        return atHash;
    }

    public String getcHash() {
        return cHash;
    }

    @JsonPOJOBuilder
    public static class Builder extends JWTClaims.Builder {

        private Instant authTime;
        private String nonce;
        private String acr;
        private List<String> amr;
        private String azp;
        private String atHash;
        private String cHash;

        @JsonProperty("auth_time")
        @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s")
        public Builder withAuthTime(Instant authTime) {
            this.authTime = authTime;
            return this;
        }

        @JsonProperty("nonce")
        public Builder withNonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        @JsonProperty("acr")
        public Builder withAcr(String acr) {
            this.acr = acr;
            return this;
        }

        @JsonProperty("amr")
        public Builder withAmr(List<String> amr) {
            this.amr = amr;
            return this;
        }

        @JsonProperty("azp")
        public Builder withAzp(String azp) {
            this.azp = azp;
            return this;
        }

        @JsonProperty("at_hash")
        public Builder withAtHash(String atHash) {
            this.atHash = atHash;
            return this;
        }

        @JsonProperty("c_hash")
        public Builder withcHash(String cHash) {
            this.cHash = cHash;
            return this;
        }

        @Override
        public IdToken build() {
            return new IdToken(authTime,
                    nonce,
                    acr,
                    amr,
                    azp,
                    atHash,
                    cHash,
                    issuer,
                    subject,
                    audience,
                    expirationTime,
                    notBefore,
                    issuedAt,
                    jwtId,
                    claims);
        }
    }
}
