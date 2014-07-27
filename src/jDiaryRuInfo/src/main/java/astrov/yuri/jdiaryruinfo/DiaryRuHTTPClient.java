/*
 *The MIT License (MIT)
 *
 *Copyright (c) 2014 Yuri Astrov
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 */

package astrov.yuri.jdiaryruinfo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for work with diary.ru web service.
 * Created by Yuri Astrov on 26.07.14.
 */
public class DiaryRuHTTPClient {
    private HttpClient httpClient; /*? DefaultHttpClient*/
    public static final String LOGIN_URL = "http://pda.diary.ru/login.php";
    public static final String INFO_URL = "http://pay.diary.ru/yandex/online.php";
    public static final String MAIN_URL = "http://www.diary.ru/";

    public DiaryRuHTTPClient () {
        httpClient = new DefaultHttpClient();
    }

    /**
     * <p>Function for close InputStream of response.</p>
     * @param response
     * @throws IOException
     */
    private void closeInputStream (HttpResponse response) throws IOException {
        HttpEntity entity;
        entity = response.getEntity();
        InputStream is = entity.getContent();
        is.close();
    }

    /**
     * <p>Auth in web service.</p>
     * @param user user name (login)
     * @param pass user password
     * @throws IOException
     */
    public void auth (String user, String pass) throws IOException {
        //Cookies = HttpClient.getCookieStore();
        //HttpClient.setCookieStore(Cookies);
        HttpResponse response;
        HttpEntity entity;
        HttpGet httpget = new HttpGet(MAIN_URL);
        response = httpClient.execute(httpget);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
            throw new IOException();
        closeInputStream(response);

        HttpPost httpost = new HttpPost(LOGIN_URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user_login", user));
        nvps.add(new BasicNameValuePair("user_pass", pass));
        nvps.add(new BasicNameValuePair("save", "on"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, "cp1251"));
        //httpost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //httpClient.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);

        response = httpClient.execute(httpost);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
            throw new IOException();
        closeInputStream(response); // We must close InputStream from response
    }

    /***
     * <p>Load data as JSON format from service, and save for private object.</p>
     * @return response from server as InputStream
     * @throws IOException
     */
    public InputStream getAccountInfo() throws IOException {
        HttpGet httpget = new HttpGet(INFO_URL);
        HttpResponse response = httpClient.execute(httpget);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
            throw new IOException();

        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }
}
