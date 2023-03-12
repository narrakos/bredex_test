# BREDEX Backend Test
## How to run
- Maven  
```mvn spring-boot:run```
- Docker  
```docker build -t narrakos/bredex_test ```  
```docker run -p 8080:8080 narrakos/bredex_test```


## Recommended data to be used for testing
>**user**: Teszt Tamás  
**email**: teszt.tamas@gmail.com  
Ads:
> - Id: 1   
> Brand: Suzuki  
> Type: Swift
>- Id: 2  
> Brand: BMW  
> Type: M5
> - Id: 3  
> Brand: Mercedes-Benz  
> Type: SLK-Class
> - Id: 4  
> Brand: Toyota  
> Type: Corolla

## Options for improvement
- Password
- Confirmation link on registration
- Forgot password option
- Roles and admin features
- Store JWTs in external store like redis
- Delete old and expired JWTs
- Pictures for the ads
- Search ads by price
- Ad ids are exposed
- Hardcoded URL in AdService
- Hardcoded and exposed SECRET_KEY in JwtService