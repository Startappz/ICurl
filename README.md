ICurl
=====

Simple Java/Android Curl Request Maker

Blog Post: http://moshx.com/?p=14

Download
========
Please check Releases page to download the jar file
https://github.com/MoshDev/ICurl/releases

or clone the repository and build it by your self


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
    
