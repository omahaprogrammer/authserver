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
/**
 * Author:  Jonathan Paz <jonathan@pazdev.com>
 * Created: Oct 24, 2016
 */

drop table if exists public.uploaded_content cascade;
create table public.uploaded_content (
    id serial primary key,
    public_id uuid not null,
    contents bytea not null,
    mime_type text not null
);
drop table if exists public.profile cascade;
CREATE TABLE public.profile
(
    id serial PRIMARY KEY, 
    sub text UNIQUE NOT NULL, 
    password_bytes bytea NOT NULL, 
    salt bytea NOT NULL, 
    rounds integer NOT NULL,
    profile_name text,
    given_name text,
    family_name text,
    middle_name text,
    nickname text,
    preferred_username text NOT NULL, 
    picture integer references uploaded_content (id) on delete set null, 
    website text, 
    email text, 
    email_verified boolean NOT NULL DEFAULT false, 
    gender text, 
    birthdate date, 
    zoneinfo text, 
    locale text, 
    phone_number text, 
    phone_number_verified boolean NOT NULL DEFAULT false, 
    updated_at timestamp
)
;
drop table if exists public.profile_address cascade;
create table public.profile_address (
    id serial primary key,
    profile_id integer references public.profile (id) on delete cascade,
    formatted text, 
    street_address text,
    locality text,
    region text,
    postal_code text,
    country text
);
drop table if exists public.profile_attribute cascade;
create table public.profile_attribute (
    id serial primary key,
    account_id integer references public.profile (id),
    attribute_name text not null,
    attribute_value text,
    unique (account_id, attribute_name)
);
drop table if exists public.client cascade;
create table public.client (
    id serial primary key,
    client_type text not null,
    client_id text not null unique,
    client_secret text not null unique,
    client_id_issued_at timestamp,
    client_secret_expires_at timestamp,
    token_endpoint_auth_method text,
    client_name text,
    client_uri text,
    logo_uri text,
    scopes text,
    tos_uri text,
    policy_uri text,
    issue_date timestamp not null,
    software_id uuid,
    software_version text,
    application_type text,
    sector_identifier_uri text,
    subject_type text,
    id_token_signed_response_alg text,
    id_token_encrypted_response_alg text,
    userinfo_signed_response_alg text,
    userinfo_encrypted_response_alg text,
    request_object_signing_alg text,
    request_object_encryption_alg text,
    token_endpoint_auth_signing_method text,
    default_max_age integer,
    require_auth_time boolean,
    initiate_login_uri text
);
drop table if exists public.client_redirect_uri cascade;
create table public.client_redirect_uri (
    id serial primary key,
    client_id integer not null references public.client (id) on delete cascade,
    redirect_uri text not null,
    unique(client_id, redirect_uri)
);
drop table if exists public.client_grant_type cascade;
create table public.client_grant_type (
    id serial primary key,
    client_id integer references public.client(id) on delete cascade,
    grant_type text not null,
    unique (client_id, grant_type)
);
drop table if exists public.client_response_type cascade;
create table public.client_response_type (
    id serial primary key,
    client_id integer references public.client(id) on delete cascade,
    response_type text not null,
    unique (client_id, response_type)
);
drop table if exists public.client_contact cascade;
create table public.client_contact (
    id serial primary key,
    client_id integer not null references public.client (id) on delete cascade,
    personal_name text,
    email_address text not null
);
drop table if exists public.client_default_acr_value cascade;
create table public.client_default_acr_value (
    id serial primary key,
    client_id integer not null references public.client (id) on delete cascade,
    default_acr_value text not null,
    unique (client_id, default_acr_value)
);
drop table if exists public.token cascade;
create table public.token (
    id serial primary key,
    issuer text,
    subject text,
    audience text,
    expiration_time timestamp,
    not_before timestamp,
    issued_at timestamp not null,
    jwt_id text unique not null
);
drop table if exists public.token_extra_claim cascade;
create table public.token_extra_claim (
    id serial primary key,
    token_id integer not null references public.token(id) on delete cascade,
    claim_name text not null,
    claim_value text not null,
    unique(token_id,claim_name)
);
drop table if exists public.o_auth2_token cascade;
create table public.o_auth2_token (
    id integer references public.token (id),
    scopes text,
    client_id text,
    username text,
    token_type text
);
drop table if exists public.id_token cascade;
create table public.id_token (
    id integer references public.token (id),
    auth_time timestamp,
    nonce text,
    acr text,
    azp text,
    atHash text,
    cHash text
);
drop table if exists public.id_token_amr cascade;
create table public.id_token_amr (
    id serial primary key,
    token_id integer references public.token (id) on delete cascade,
    amr text not null,
    unique (token_id, amr)
);