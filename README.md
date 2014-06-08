ICurl
=====

Simple Java/Android Curl Request Maker



Sample
======

    ICurl curl=new ICurl("http://www.google.com");
    curl.setHeader("country", "jo");
    curl.setParameter("user", "moshx");
    curl.enableResponseLogging();
    curl.enableVerbose();
    String curlString = curl.toCurl();
    System.out.println(curlString);
    
    Result--->  curl -H "country: jo" -d "user=moshx" -i -v -X GET "http://www.google.com"
    
