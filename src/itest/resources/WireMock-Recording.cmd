java -Dhttp.proxyHost=192.168.1.254 -Dhttp.proxyPort=8080 ^
     -Dhttps.proxyHost=192.168.1.254 -Dhttps.proxyPort=8080 ^
     -Dhttp.nonProxyHosts="localhost|127.0.0.1" ^
    -jar ../../../bin/wiremock-standalone-2.5.1.jar ^
    --proxy-all="https://api.airtable.com" --port=8080 ^
    --record-mappings --verbose --preserve-host-header ^
    --enable-browser-proxying