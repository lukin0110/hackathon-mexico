package com.ktconexiones.flow.android.io;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpMultipartRequest
{
	public static final String BOUNDARY = "----------V2ymHFg03ehbqgZCaKO6jy";
    public static final String TAG = "HttpMultipartRequest";

	private byte[] postBytes = null;
	private String url = null;

	public HttpMultipartRequest(String url, Map<String, String> params, String fileField, String fileName, String fileType, InputStream fileInputStream)
            throws IOException
	{
		this.url = url;

		String boundaryMessage = getBoundaryMessage( BOUNDARY, params, fileField, fileName, fileType);
		String endBoundary = "\r\n--" + BOUNDARY + "--\r\n";

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(boundaryMessage.getBytes());


        int bytesAvailable = fileInputStream.available();
        int maxBufferSize = 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0)
        {
            bos.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        fileInputStream.close();
		bos.write(endBoundary.getBytes());
        
        this.postBytes = bos.toByteArray();

        bos.flush();
		bos.close();
	}

	private String getBoundaryMessage(String boundary, Map<String, String> params, String fileField, String fileName, String fileType)
	{
		StringBuffer res = new StringBuffer("--").append(boundary).append("\r\n");

        for( Map.Entry<String, String> me : params.entrySet() )
		{
			res.append("Content-Disposition: form-data; name=\"").append( me.getKey() ).append("\"\r\n")
				.append("\r\n").append( me.getValue() ).append("\r\n")
				.append("--").append(boundary).append("\r\n");
		}

		res.append("Content-Disposition: form-data; name=\"").append(fileField).append("\"; filename=\"").append(fileName).append("\"\r\n")
			.append("Content-Type: ").append(fileType).append("\r\n\r\n");

		return res.toString();
	}


	public String send() throws IOException
	{
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try
		{
			conn = (HttpURLConnection) new URL( url ).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Connection", "Keep-Alive" );
            conn.setRequestProperty( "Content-Type", "multipart/form-data;boundary=" + BOUNDARY );

            OutputStream dout = conn.getOutputStream();
			dout.write(postBytes);
			dout.close();

			int ch;
			is = conn.getInputStream();

			while ((ch = is.read()) != -1)
				bos.write(ch);

            return new String( bos.toByteArray() );
		}
		finally
		{
            // keep the house and memory clean
            try{ bos.close(); } catch(Exception e){ Log.e( TAG, "Upsie, but it didn't break anything", e ); }
            try{ is.close(); } catch(Exception e){ Log.e( TAG, "Upsie, but it didn't break anything", e ); }
            try{ conn.disconnect(); } catch(Exception e){ Log.e( TAG, "Upsie, but it didn't break anything", e ); }
		}
	}

}