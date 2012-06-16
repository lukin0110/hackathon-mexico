package com.ktconexiones.flow.android.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HarcodeFriends
{
    //555-0000
    /////19175550001
    public static final List<Friend> BANANA = new ArrayList<Friend>();

    static
    {
        BANANA.add( new Friend("John", "Doe", "19175550000") );
        BANANA.add( new Friend("Jane", "Doe", "19175550001") );
        BANANA.add( new Friend("Tarzan", "Weissmuller", "19175550002") );
        BANANA.add( new Friend("Walter", "Sobchak ", "19175550003") );
        BANANA.add( new Friend("Jack", "Johnson", "19175550004") );
        BANANA.add( new Friend("Eddie", "Vedder", "19175550005") );
        BANANA.add( new Friend("Israel", "Roldan", "19175550006") );
        BANANA.add( new Friend("Maarten", "Huijsmans", "19175550007") );
        BANANA.add( new Friend("Joost", "Van De Velde", "19175550008") );
        BANANA.add( new Friend("Django", "Reinhardt", "19175550009") );
        BANANA.add( new Friend("An", "Droid", "19175550010") );
        BANANA.add( new Friend("I", "Phone", "19175550011") );
        BANANA.add( new Friend("Forrest", "Gump", "19175550012") );
        BANANA.add( new Friend("Minded", "Absynthe", "19175550012") );
        BANANA.add( new Friend("Hoe", "Lang", "19175550014") );
        BANANA.add( new Friend("Marty", "McFly", "19175550015") );
        BANANA.add( new Friend("Eddy", "Merckx", "19175550016") );

        Collections.sort(BANANA, new FriendComparator() );
    }


    public static class FriendComparator implements Comparator<Friend>
    {
        public int compare(Friend o1, Friend o2)
        {
            int lastname = o1.getLastname().compareTo( o2.getLastname() );

            if( lastname != 0 )
                return lastname;

            return o1.getFirstname().compareTo( o2.getFirstname() );
        }
    }

    public static void main(String[] args)
    {
        for( Friend f : BANANA)
            System.out.println( f.getLastname() + ", " + f.getFirstname() + ", " + f.getPhone() );

    }

}

