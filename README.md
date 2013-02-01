jooq-gis-test
=============

jooq-gis-test


In this test project I'm trying to figure out why I can't build the
following SQL example using jOOQ:

SELECT ST_Intersects('POINT(0 0)'::geometry, 'LINESTRING ( 2 0, 0 2)'::geometry);


