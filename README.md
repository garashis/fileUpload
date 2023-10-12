# Zip File Upload & unzip to view contents

### What is MultipartFile in spring boot?
A representation of an uploaded file received in a multipart request. The file contents are either stored in memory or temporarily on disk

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html

### ZipInputStream
ZipInputStream is a Java class that implements an input stream filter for reading files in the ZIP file format. 
It has support for both compressed and uncompressed entries.

https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipInputStream.html

### CURL Request for testing

````
curl --location 'localhost:8080/file' \
--form 'file=@"/Users/anilput1/Downloads/test.zip"'
````

![img.png](img.png)