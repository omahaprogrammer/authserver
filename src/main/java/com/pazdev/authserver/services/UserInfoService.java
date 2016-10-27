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
package com.pazdev.authserver.services;

import com.google.inject.Provider;
import com.pazdev.authserver.guice.annotation.Password;
import com.pazdev.authserver.model.Profile;
import com.pazdev.authserver.model.Profile_;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
public class UserInfoService {
    private final EntityManager em;
    private final @Password SecretKeyFactory fac;

    @Inject
    public UserInfoService(EntityManager em, @Password SecretKeyFactory fac) {
        this.em = em;
        this.fac = fac;
    }

    public Optional<Profile> getProfile(String sub) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> root = cq.from(Profile.class);
        cq.where(cb.equal(root.get(Profile_.sub), sub));
        TypedQuery<Profile> q = em.createQuery(cq);
        List<Profile> profiles = q.getResultList();
        Optional<Profile> profile = Optional.empty();
        if (profiles.size() == 1) {
            profile = Optional.of(profiles.get(0));
        }
        return profile;
    }

    public Optional<Profile> authenticateProfile(String sub, char[] password) {
        password = Arrays.copyOf(password, password.length);
        Optional<Profile> profile = getProfile(sub);
        byte[] passwordBytes = profile.map(Profile::getPasswordBytes).orElse(new byte[32]);
        byte[] salt = profile.map(Profile::getSalt).orElse(new byte[32]);
        int rounds = profile.map(Profile::getRounds).orElse(1048576);
        PBEKeySpec spec = new PBEKeySpec(password, salt, rounds, passwordBytes.length);
        Arrays.fill(password, '\0');
        byte[] testbytes;
        try {
            SecretKey key = fac.generateSecret(spec);
            spec.clearPassword();
            testbytes = key.getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        if (!testEquals(passwordBytes, testbytes)) {
            profile = Optional.empty();
        }
        return profile;
    }

    private static boolean testEquals(byte[] a, byte[] b) {
        boolean retval = true;
        int lena = a.length;
        int lenb = b.length;
        int maxlen = Math.max(lena, lenb);
        for (int i = 0; i < maxlen; ++i) {
            int ia = Math.min(i,lena);
            int ib = Math.min(i,lenb);
            if (ia != ib | a[ia] != b[ib]) {
                retval = false;
            }
        }
        return retval;
    }
}
