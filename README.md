# thread-simulation

slow-service: run this server to simulate a slow-responding server called by the other two.

deferredresult-service: run this server to simulate a server which will process a request asynchronously (detach from Tomcat thread) and additionally process each sub-process on separate executors.

reactive-service: run this server to simulate a server which will utilize reactor-netty and webclient to perform non-blocking IO operation to other service. 