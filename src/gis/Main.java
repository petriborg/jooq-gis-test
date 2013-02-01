package gis;

import gis.generated.Routines;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jooq.SQLDialect;
import org.jooq.SelectSelectStep;
import org.jooq.impl.Factory;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

public class Main {

    public static Logger log = Logger.getLogger(Main.class);
    public static final String DRIVER =
            "org.postgis.DriverWrapperLW:"
            + "org.postgis.DriverWrapper:"
            + "org.postgis.DriverWrapperAutoprobe";

    static {
        System.setProperty("jdbc.drivers", DRIVER);
    }
    
    // NOTE URL will need to be modified for your DB HOSTNAME
    // NOTE also specified in test.sql and test.xml
    public static final String HOST = "localhost";
    public static final String DB = "testdb";
    public static final String URL =
            "jdbc:postgresql://" + HOST + ":5432/" + DB;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        log.info("Start");


        try {
            Connection c = DriverManager.getConnection(URL, "postgres", "");

            Point point = new Point(0, 0);
            point.setSrid(4326);
            PGgeometry pgpt = new PGgeometry(point);

            LineString line = new LineString("LINESTRING ( 2 0, 0 2 )");
            line.setSrid(4326);
            PGgeometry pgln = new PGgeometry(line);


            Factory f = new Factory(c, SQLDialect.POSTGRES);
            SelectSelectStep s = f.select(Routines.stIntersects2(pgpt, pgln).as("magic"));
            List<?> fetch = s.fetch("magic");
        } catch (SQLException ex) {
            log.error("FAIL", ex);
        }


        log.info("End");
    }
}
