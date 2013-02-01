-- EXAMPLE USE:
-- $ psql -h localhost -U postgres -f ./config/test.sql
--
-- REQUIRES:
-- PostgreSQL 9.+, PostGIS 2.0+

CREATE DATABASE testdb;

\c testdb;

CREATE EXTENSION postgis;

