# API Reference

POST /product/upload Body

```
Headers:
Content-Type: multipart/form-data

Body:
file Backend_Assignment_Data.xlsx

Response Body:
{
    "message": "File saved"
}
```

GET /product/2/details/2022-03-03

```
Headers: none
Body: none
Response Body:
[
    {
        "price": 11688.9,
        "name": "GrowFix Gold Dec",
        "interest_rate": 11.0
    }
]
```

GET /product/2/price

```
Headers: none
Body: none
Response Body:
[
    {
        "price": 11688.9
    },
    {
        "price": 11692.2
    },
    {
        "price": 11695.6
    }
]
```

# DEPENDENCIES

Spring Web WEB

PostgreSQL Driver SQL

JDBC API SQL

Apache POI https://mvnrepository.com/artifact/org.apache.poi/poi/5.2.0

# References

https://youtu.be/19lZkM7Dr-c

https://youtu.be/p_xrBVxOmq8

https://youtu.be/qflq2xxweUw

https://www.baeldung.com/java-apache-poi-cell-string-value#:~:text=Apache%20POI%20uses%20the%20Workbook,to%20get%20the%20cell%20type