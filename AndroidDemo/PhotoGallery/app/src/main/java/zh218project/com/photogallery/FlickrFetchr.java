package zh218project.com.photogallery;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hui on 2016/9/1.
 */
public class FlickrFetchr {

    private static final String TAG = "FlickrFetch";

    private static final String API_KEY = "e516e51b2f4487f53b3b7b0416b76459";

    public byte[] getUrlBytes(String urlSpec) throws IOException {  //getUrlByte方法
        URL url = new URL(urlSpec); //创建一个URL对象
        HttpURLConnection connection = (HttpURLConnection)url.openConnection(); //调用openConnection()方法默认返回URL
        //connection对象,由于需要连接的是Http URL，类型强制转换为HttpURLConnection对象

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();   //调用getInputStream()方法才会真正连接指定的Url地址(post请则调用getOutputStream()方法)

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ":with" + urlSpec);
            }

            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) > 0) {      //循环调用read()方法读取网络数据
                out.write(buffer, 0, byteRead);
            }
            out.close();        //读取完关闭网络连接
            return out.toByteArray();       //将读取的数据写入ByteArrayOutputStream字节数组中
        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws  IOException {        //负责将获取的字节数组转换为String
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems() {

        List<GalleryItem> items = new ArrayList<>();
        try {
            String url = Uri.parse("https://www.flickr.com/services/api/").buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", "API_KEy")
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();

            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON:" + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (JSONException je) {
            Log.e(TAG, "Faild to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody)
            throws IOException, JSONException {

        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            GalleryItem item = new GalleryItem();
            item.setId(photoJsonObject.getString("id"));
            item.setCaption(photoJsonObject.getString("title"));

            if (!photoJsonObject.has("url_s")) {
                continue;
            }

            item.setUrl(photoJsonObject.getString("url_s"));
            items.add(item);
        }
    }



}
