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

drop table if exists public.profile cascade;
CREATE TABLE public.profile
(
   id serial PRIMARY KEY, 
   sub text UNIQUE NOT NULL, 
   preferred_username text NOT NULL, 
   password_bytes bytea NOT NULL, 
   salt bytea NOT NULL, 
   rounds integer NOT NULL,
   picture bytea, 
   website text, 
   email text, 
   email_verified boolean NOT NULL DEFAULT false, 
   gender text, 
   birthdate date, 
   zoneinfo text, 
   locale text, 
   phone_number text, 
   phone_number_verified boolean NOT NULL DEFAULT false, 
   address_formatted text, 
   address_street_address text,
   address_locality text,
   address_region text,
   address_postal_code text,
   address_country text, 
   updated_at timestamp with time zone
)
;
drop table if exists public.profile_family_name cascade;
create table public.profile_family_name (
   id serial primary key,
   profile_id integer references public.profile (id) on delete cascade,
   profile_family_name text not null,
   profile_family_name_lang text,
   unique (profile_id, profile_family_name_lang)
);
drop table if exists public.profile_given_name cascade;
create table public.profile_given_name (
   id serial primary key,
   profile_id integer references public.profile (id) on delete cascade,
   profile_given_name text not null,
   profile_given_name_lang text,
   unique (profile_id, profile_given_name_lang)
);
drop table if exists public.profile_middle_name cascade;
create table public.profile_middle_name (
   id serial primary key,
   profile_id integer references public.profile (id) on delete cascade,
   profile_middle_name text not null,
   profile_middle_name_lang text,
   unique (profile_id, profile_middle_name_lang)
);
drop table if exists public.profile_nickname cascade;
create table public.profile_nickname (
   id serial primary key,
   profile_id integer references public.profile (id) on delete cascade,
   profile_nickname text not null,
   profile_nickname_lang text,
   unique (profile_id, profile_nickname_lang)
);
drop table if exists public.profile_name cascade;
create table public.profile_name (
   id serial primary key,
   profile_id integer references public.profile (id) on delete cascade,
   profile_name text not null,
   profile_name_lang text,
   unique (profile_id, profile_name_lang)
);
drop table if exists public.profile_attribute cascade;
create table public.profile_attribute (
   id serial primary key,
   account_id integer references public.profile (id),
   attribute_name text not null,
   attribute_value text,
   unique (account_id, attribute_name)
)
;
drop table if exists public.client cascade;
create table public.client (
   id serial primary key,
   client_id text not null unique,
   client_secret text not null unique,
   client_type text not null,
   issue_date timestamp with time zone not null,
   grant_types text not null,
   response_types text not null,
   scopes text,
   software_id uuid,
   software_version text
)
;
drop table if exists public.client_name cascade;
create table public.client_name (
   id serial primary key,
   client_id integer references public.client(id) on delete cascade,
   client_name text not null,
   client_lang text,
   unique (client_id, client_lang)
);
drop table if exists public.client_policy cascade;
create table public.client_policy (
   id serial primary key,
   client_id integer references public.client(id) on delete cascade,
   policy_uri text not null,
   policy_uri_lang text,
   unique (client_id, policy_uri_lang)
);
drop table if exists public.client_logo cascade;
create table public.client_logo (
   id serial primary key,
   client_id integer references public.client(id) on delete cascade,
   logo_image bytea not null,
   logo_lang text,
   unique (client_id, logo_lang)
);
drop table if exists public.client_terms cascade;
create table public.client_terms (
   id serial primary key,
   client_id integer references public.client(id) on delete cascade,
   terms_uri text not null,
   terms_uri_lang text,
   unique (client_id, terms_uri_lang)
);
drop table if exists public.client_uri cascade;
create table public.client_uri (
   id serial primary key,
   client_id integer references public.client(id) on delete cascade,
   client_uri text not null,
   client_uri_lang text,
   unique (client_id, client_uri_lang)
);
drop table if exists public.client_redirect_uri cascade;
create table public.client_redirect_uri (
   id serial primary key,
   client_id integer not null references public.client (id) on delete cascade,
   redirect_uri text not null,
   unique(client_id, redirect_uri)
);
drop table if exists public.client_contact cascade;
create table public.client_contact (
   id serial primary key,
   client_id integer not null references public.client (id) on delete cascade,
   personal_name text,
   email_address text not null
);