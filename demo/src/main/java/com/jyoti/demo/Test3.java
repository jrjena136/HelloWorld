package com.jyoti.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) throws MalformedURLException {
        String s = "oauth_token=kingjyoti11-2543.17629A86A74.6874747073253341253246253246617069312E73796E61636F722E746B253341333134343325324673657276696365253246657874656E73696F6E2532466F617574683225324661757468656E746963617465253246657665726E6F7465.7B85B4D1C46E0173BF7C8A6390FE52EA&oauth_token_secret=1041E4C447F4675054758B8B8DB4A423&oauth_callback_confirmed=true";
        Map<String, String>  map = Arrays.stream(s.split("&"))
        .filter(s1 -> s.contains("="))
        .map(s1 -> s1.split("="))
        .collect(Collectors.toMap(a -> a[0], a -> a[1]));
        System.out.println(map);
        System.out.println(map.get("oauth_token"));
        URL url = new URL("https://api1.synacor.tk:31443/service/extension/oauth2/authenticate/evernote:evernote");
        String   baseUrl = url.getProtocol() + "://" + url.getHost() + (url.getPort() != -1 ? (":" + url.getPort()) : "");
        System.out.println( baseUrl);
        System.out.println(url.getAuthority());
        String path = url.getPath();
        System.out.println(path);
    }

}
