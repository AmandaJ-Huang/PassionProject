## Daily Progress Log

### 2021-12-30
* Switching from BGG XML API to Board Game Atlas API due to API call limitation. BGG API only allows 1 request ~5 seconds (~12 requests per minute) vs. Board Game Atlas allowing 60 requests per minute per client.
* Was able to successfully use Jackson XML to parse BGG XML API, but will not be utilizing.

### 2021-12-29
* Fixed about button text
![image info](./screenshots/2021-12-29_AboutButton.png)
* Scrapping jhipster and trying to build app from ground up
    - trying to understand each piece from database to back-end to front-end (angular) and how everything connects

### 2021-12-28
* Researched Jackson XML package and was able to extract thumbnail url (see project link in 2021-12-27 > dev branch).
* Added about component, module, and route. Also tried adding button, but running into error page:
![image info](./screenshots/2021-12-28_AboutPageButtonClick.png)

### 2021-12-27
* Trying to scrape data from BGG XML API via [`Boardgames-Demo-w-REST`](https://github.com/AmandaJ-Huang/Boardgames-Demo-w-REST.git) and save data into MySQL database