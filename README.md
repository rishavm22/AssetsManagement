# AssetsManagement
This is a Assement Managment Project  for company to keep record of their assets 

(Spring Boot and H2CONSOLE)

## To get Asset Category List
http://localhost:8080/asset/categories/all

## To update Asset Category List
http://localhost:8080/asset/update/objectKey?id=3

### Body
{
        "name": "Type3",
        "description": "Updated"
    }



## To get Asset List
http://localhost:8080/asset/all

## To get AssetByName
http://localhost:8080/asset/name/?name='Item 1'

## To Update Asset
http://localhost:8080/asset/update/?id=1

### Body
{
        "name": "Item 1",
        "Status": "Available",
        "assignedTo": null,
        "category_id": 1L,
        "purchase_date": "2012-03-05",
        "condition_note": "note"
    }
    
## To Assign Asset
http://localhost:8080/asset/assign/?asset_id=1&emp_id=1

## To Recover Asset
http://localhost:8080/asset/recover/?asset_id=1

## To Delete Asset
http://localhost:8080/asset/delete/?id=1

It will delete Asset only if it is not with Assigned State

Junit Test Case is also added for all the operations
