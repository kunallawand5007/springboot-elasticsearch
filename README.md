Sample poc for spring-data-elastic search with srpingBoot

SpringBoot version    : 2.2.6.RELEASE
Elasticsearch version : 7.3.1
Java                  : 1.8.0_91   

Index name -products
type       - _doc

Elasticsearch has two ports available:
1-http port: 9200 (rest json api)
2-transport port: 9300 (binary api)
Official java client connects to port 9300

API-1 :
Method :GET
GET :http://localhost:8090/grahakbazar/product/list
Response :
[
    {
        "productName": "Orange",
        "descripation": "Good for health to increase immunity system",
        "category": "agriculture",
        "tag": "fruits",
        "price": 25,
        "coupon": 0,
        "vendorUserId": 2,
        "rate": "KG"
    },
    {
        "productName": "Watermelon",
        "descripation": "Good in summer for health",
        "category": "agriculture",
        "tag": "fruits",
        "price": 50,
        "coupon": 2.5,
        "vendorUserId": 2,
        "rate": "KG"
    },
    {
        "productName": "Banana",
        "descripation": "Good for increase health",
        "category": "agriculture",
        "tag": "fruits",
        "price": 40,
        "coupon": 0,
        "vendorUserId": 3,
        "rate": "Dozen"
    },
    {
        "productName": "Muskmelon",
        "descripation": "Good source of vitamin-A",
        "category": "agriculture",
        "tag": "fruits",
        "price": 25,
        "coupon": 0,
        "vendorUserId": 4,
        "rate": "KG"
    },
    {
        "productName": "Tomato",
        "descripation": "Essential item for daily cooking as most of curry we use tomatto",
        "category": "agriculture",
        "tag": "vegetable",
        "price": 10,
        "coupon": 0,
        "vendorUserId": 5,
        "rate": "KG"
    }
]

API-2 :
Method: POST
URL:http://localhost:8090/grahakbazar/product/add
Request:

{
	
	"productName":"Tomato",
	"category":"agriculture",
	"tag":"vegetable",
	"price":10,
	"rate":"KG",
	"descripation":"Essential item for daily cooking as most of curry we use tomatto",
	"vendorUserId":5,
	"coupon":0
}
Response:
 return unique id


Method : GET
URL    : http://localhost:8090/grahakbazar/product/tag
Description :
we need pass the field name to this api, it will calculate "tag" field aggregations and its count


Response :

{
    "fruits": 4,
    "vegetable": 1
}


API-3 :

Method :GET
URL:http://localhost:8090/grahakbazar/product/search/tomato
Response :

[
    {
        "productName": "Tomato",
        "descripation": "Essential item for daily cooking as most of curry we use tomatto",
        "category": "agriculture",
        "tag": "vegetable",
        "price": 10,
        "coupon": 0,
        "vendorUserId": 5,
        "rate": "KG"
    }
]
