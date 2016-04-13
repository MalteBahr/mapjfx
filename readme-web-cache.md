# branch notice

in this development branch I will try to implement a caching solution for the loaded map data, so that after having 
loaded data into the cache, offline usage will be possible. 

## April 12th, 2016

got the caching mechanism working. When connected to the internet the downlaoded files are used for all the 
implemented map providers.

When running with no connection to the internet, OSM and MapQuest maps are retrieved from the cache without problems.
With Bing maps there is still a rest call trying to go out, which is not captured by the cache and so this i 
blocking the map display. Must investigate further. BUt for the first attempt, with the standard map providers it is
working.
  
Still need to cleanup the code, define and document a clean api before publishing it.  
 
 
## state of progress on April 11th, 2016

I managed to intercept the data being transferred from a webserver to the webview and to store it in local files 
together with relevant meta informations.

I still have a problem in reading this data when offline and the browser wants to load from the original https 
connection. I still have to disable the ssl handshake in this case because for that I would need internet access.

I have an idea which might be working, first test looks good; I'll work on it again tomoorow, it's too late for today
 now.
 
## first recherche results

it seems that WebView has no native cache support; one solution is to replace the URLStreamHandlerFactory and to 
implement a custom URLStreamHandler for http and https which will intercept the calls to the web and will do the 
caching.
 
**Hint:** This means that **all** call to URL resources in the JVM are intercepted. 
