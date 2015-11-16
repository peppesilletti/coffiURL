#coffiURL - URL shortener

A URL shortener is an online application that converts a regular URL (the web address that starts with http://) into its condensed format. The user only has to copy the full URL of a website and paste it into the URL shortening tool to come up with an abbreviated version that is around 10 to 20 characters long.

CoffiURL is a URL shortener implemented with a back-end in Java, a front-end in AngularJS and with Redis as persistence system.

#How to install

1. Clone the repository from Github:  
    `git clone http://github.com/peppesilletti/coffiURL.git`

2. Move inside the folder coffiURL and build the docker image with the Dockerfile with:  
    `docker build -t url-shortener .`

3. Run the container  
    `docker run -d -p 8080:8080 --name coffiURL url-shortener`

4. If you are a Linux user, skip this step. Otherwise, you need         to map the 8080 port in VirtualBox

#How to use

`Open your favourite browser and digit 'http://localhost:8080' for the home page. 
For the statistics page of a shortURL, write 'http://localhost:8080/shortURL$'. Without the $, you will be redirect to the original web site.`

#How to use api

* Create a new short URL  
**[POST]**: http://localhost:8080/api/url  
`curl --data "longURL=www.google.it" http://localhost:8080/api/url`  
`curl --data "longURL=www.google.it&shortURL=custom" http://localhost:8080/api/url`

* Get a long URL  
**[GET]**: http://localhost:8080/api/url?shortURL=xfGhti
`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/url?shortURL=xfGhti`  

* Get short URL statistics  
**[GET] All statistics**: http://localhost:8080/api/stats?shortURL=xfGhti  
`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/stats?shortURL=xfGhti`    
**[GET] Period statistics**: http://localhost:8080/api/stats?shortURL=xfGhti&fromTime=123456&toTime=1234589  
`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/stats?shortURL=xfGhti&fromTime=123456&toTime=1234589` 
>toTime e fromTime sono in formato timestamp 

#Developer

`Giuseppe Silletti - <sillettig@gmail.com>`


