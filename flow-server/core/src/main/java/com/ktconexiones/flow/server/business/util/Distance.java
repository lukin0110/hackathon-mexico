package com.ktconexiones.flow.server.business.util;

public class Distance
{
    // Haversine formula used: http://en.wikipedia.org/wiki/Haversine_formula
    // Latitude = Horizontal = x: http://en.wikipedia.org/wiki/Latitude
    // Longitude = Vertical = y: http://en.wikipedia.org/wiki/Longitude
    public static final double EARTH_RADIUS = 6371; // it's in km

    /**
     * Calculates the distance between 2 points. Will return the result in 'meters'. Each point consists of a latitude and longitude.
     *
     * @param point1 start point
     * @param point2 end point
     * @return int with the distance between 2 points in 'meters'
     * */
    public static int getDistance( Point point1, Point point2 )
    {
        return getDistance( point1.getLongitude(), point1.getLatitude(), point2.getLongitude(), point2.getLatitude() );
    }

    
    public static int getDistance( double longitude1, double latitude1, double longitude2, double latitude2 )
    {
        longitude1 = Math.toRadians( longitude1 );
        latitude1 = Math.toRadians( latitude1 );
        longitude2 = Math.toRadians( longitude2 );
        latitude2 = Math.toRadians( latitude2 );

        double diff_longitude = longitude2 - longitude1;
        double diff_latitude = latitude2 - latitude1;

        double sin_longitude = Math.sin( diff_longitude / 2);
        double sin_latitude = Math.sin( diff_latitude / 2 );

        double a = (sin_latitude * sin_latitude) + Math.cos( latitude1 )*Math.cos( latitude2 )*(sin_longitude*sin_longitude);
        double c = 2 * Math.asin( Math.min(1.0, Math.sqrt(a)));
        double distance = EARTH_RADIUS * c * 1000;

        return (int) distance;
    }


    public static class Point
    {
        private double latitude, longitude;

        public Point(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }


    public static void main(String[] args)
    {
        // latitude, longitude
        // use 'drop latlong marker' in google maps to obtain latitude-longitude coordinates
        Point brussel = new Point( 50.853339, 4.346352 );
        Point lommel = new Point( 51.229377, 5.310402 );
        Point gent = new Point( 51.064, 3.735 );

        int dist = getDistance( 4.346352, 50.853339, 5.310402 , 51.229377 );
        int dist2 = getDistance( brussel, lommel );
        System.out.println( "Brussel - Lommel = " + dist + "m, " + dist2 + "m" );

        dist = getDistance( 3.735, 51.064, 5.310402 , 51.229377 );
        dist2 = getDistance(gent, lommel );
        System.out.println( "Lommel - Gent = " + dist + "m, " + dist2 + "m" );

        dist = getDistance( 3.735, 51.064, 4.346352, 50.853339 );
        dist2 = getDistance( brussel, gent );
        System.out.println( "Brussel - Gent = " + dist + "m, " + dist2 + "m" );
    }

}




