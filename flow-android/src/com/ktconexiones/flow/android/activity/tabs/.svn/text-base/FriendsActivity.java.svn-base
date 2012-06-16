package com.ktconexiones.flow.android.activity.tabs;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.activity.FriendFlowActivity;
import com.ktconexiones.flow.android.app.Keys;
import com.ktconexiones.flow.android.domain.Friend;
import com.ktconexiones.flow.android.domain.HarcodeFriends;

import java.util.List;

public class FriendsActivity extends ListActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView( R.layout.friends );
        
        FriendsAdapter adapter = new FriendsAdapter( this, R.layout.friends_row, HarcodeFriends.BANANA );

        super.setListAdapter( adapter );
        //super.getListView().setOnItemLongClickListener( this );
        super.getListView().setOnItemClickListener( this );
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        this.onClickHandler( adapterView, view, i, l );
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        this.onClickHandler( adapterView, view, i,l );    
    }

    private void onClickHandler(AdapterView<?> adapterView, View view, int i, long l)
    {
        Friend f = HarcodeFriends.BANANA.get( i );
        //Toast.makeText( this, getString( R.string.common_loading ), Toast.LENGTH_SHORT ).show();

        Intent intent = new Intent().setClass(this, FriendFlowActivity.class);
        intent.putExtra( Keys.Flow.PHONE, f.getPhone() );
        intent.putExtra( Keys.Flow.NAME, f.getFirstname() + " " + f.getLastname() );
        super.startActivity( intent );
    }

    
    public static class FriendsAdapter extends ArrayAdapter<Friend>
    {
        private List<Friend> items;
        private int textViewResourceId;

        public FriendsAdapter(Context context, int textViewResourceId, List<Friend> items)
        {
            super(context, textViewResourceId, items);
            this.items = items;
            this.textViewResourceId = textViewResourceId;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View v = convertView;
            if (v == null)
            {
                LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate( textViewResourceId, null);
            }

            Friend f = items.get(position);

            if (f != null)
            {
                    TextView tt = (TextView) v.findViewById(R.id.friend_toptext);
                    TextView bt = (TextView) v.findViewById(R.id.friend_bottomtext);

                    if (tt != null)
                          tt.setText( f.getLastname() + ", " + f.getFirstname() );

                    if(bt != null)
                          bt.setText( super.getContext().getString(R.string.common_phone) + ": " + f.getPhone() );
            }

            return v;
        }
    }

}
