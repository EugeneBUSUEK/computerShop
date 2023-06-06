# Computer shop

## Contents

* [Run in Docker](#run-in-docker)
* [API overview](#api-overview)

## Run in Docker 

You do need a Docker daemon.
If you do not have Docker follow this [link](https://www.docker.com/) to download and install it.

* Run terminal window in directory which you want project located in.
* Clone project using Git:

```text
git clone https://github.com/EugeneBUSUEK/computerShop.git
```
* move to `computerShop` directory(project root directory)

```text
cd computerShop
```

* Compile the code and package it into a jar file

First command

```text
.\gradlew clean
```

Second command

```text
.\gradlew build
```

* Start the image creation process

```text
docker build -t computershop:first .
```

* Run a new container

```text
docker run -p 8081:8081 -e H2_DB_NAME=compshop -e H2_USERNAME=root -e H2_PASSWORD=root --name=computershop computershop:first
```

Optionally you can set your own h2 database credentials.

### Credentials

---

`H2_DB_NAME`={your database name}

`H2_USERNAME`={your username}

`H2_PASSWORD`={your password}

### If you want to view the database.

---

While application is running follow link [http://localhost:8081/h2-console/](http://localhost:8081/h2-console/)
where log in with your [credentials](#credentials).

### If you have any difficulties with launching the application in this way.

You can also execute commands below to create container image of the application using the Spring Boot build plugins for Gradle.
But the build might take a long time because it has to download some container images and the JDK.

* Run terminal window in project root directory
```text
./gradlew bootBuildImage --imageName=myorg/myapp
```

* Then you can run the image in terminal window
```text
docker run -p 8081:8081-e H2_DB_NAME=compshop -e H2_USERNAME=root -e H2_PASSWORD=root --name=computershop -t myorg/myapp
```



## API overview

### Enums

### `ProductType`

| Value      | Description |
|:-----------|:------------|
| `DESKTOP`  | PC          |
| `NOTEBOOK` | Notebook    |
| `MONITOR`  | Monitor     |
| `HDD`      | HDD         |

### `PropertyType`

| Value      | Description           |
|:-----------|:----------------------|
| `FORM_FACTOR`  | PC form factor        |
| `NOTEBOOK_SIZE` | Notebook size         |
| `DIAGONAL_SIZE`  | Monitor diagonal size |
| `CAPACITY`      | HDD capacity          |

### Value enums

---

### `FormFactor`

Value of `FORM_FACTOR` for [PropertyType](#propertytype) enum.

| Value      | Description  |
|:-----------|:-------------|
| `DESKTOP`  | Desktop PC   |
| `NETTOP` | Nettop PC    |
| `MONOBLOCK`  | Monoblock PC |

### `SizeType`

Value of `NOTEBOOK_SIZE` for [PropertyType](#propertytype) enum.

| Value      | Description       |
|:-----------|:------------------|
| `SIZE_TYPE_13`  | 13' notebook size |
| `SIZE_TYPE_14` | 14' notebook size |
| `SIZE_TYPE_15`  | 15' notebook size |
| `SIZE_TYPE_17`      | 17' notebook size |

### Request objet

### `ProductRequest`

----

ProductRequest is an object that abstracts single product and it's properties.

Example:
```json5
{
  "id": 1,
  "serialNumber": "4dge54tcye4t",
  "producer": "Apple",
  "price" : 100000,
  "quantity": 250000,
  "productType": "NOTEBOOK",
  "details": [
    {
      "propertyType": "NOTEBOOK_SIZE",
      "propertyValue": "SIZE_TYPE_15"
    }
  ]
}
```

Where:

* `id` is unique identifier.
* `serialNumber` is a serial number of product.
* `producer` is a producer of product.
* `price` is price per copy.
* `quantity` is quantity of products in stock.
* `productType` is [ProductType](#producttype) `enum` type of product.
* `details` is an array of `Object`
  where:
    * `propertyType` is an [PropertyType](#propertytype) `enum` type of property.
    * `propertyValue` is a value of related property(could be `enum` according to [PropertyType](#propertytype) or other type as string).

### Response objet

### `ProductResponse`

----

ProductResponse is an object that describes performed [ProductRequest](#productrequest) response.


Example:
```json5
{
  "id": 1,
  "serialNumber": "4dge54tcye4t",
  "producer": "Apple",
  "price" : 100000,
  "quantity": 250000,
  "productType": "NOTEBOOK",
  "details": [
    {
      "propertyType": "NOTEBOOK_SIZE",
      "propertyValue": "SIZE_TYPE_15"
    }
  ]
}
```
Where:

* `id` is unique identifier.
* `serialNumber` is a serial number of product.
* `producer` is a producer of product.
* `price` is price per copy.
* `quantity` is quantity of products in stock.
* `productType` is [ProductType](#producttype) `enum` type of product.
* `details` is an array of `Object`
  where:
  * `propertyType` is an [PropertyType](#propertytype) `enum` type of property.
  * `propertyValue` is a value of related property(could be `enum` according to [PropertyType](#propertytype) or other type as string).


### Add product

```http
  POST /api/v1/products
```

Request body

[ProductRequest](#productrequest) object.

Where:

* `id` field **must** be `null`.

Returns

[ProductResponse](#productresponse) object.

### Update product

```http
  PUT /api/v1/products
```

Request body

[ProductRequest](#productrequest) object.

Where:

* `id` is **required**.
* Fields not for update **must** absent.

Returns

[ProductResponse](#productresponse) object.

### Get product by id

```http
  GET /api/v1/products/${id}
```

#### URI Parameters
| Parameter | Type     | Description                          |
| :-------- |:---------|:-------------------------------------|
| `id`      | `number` | **Required**. Id of product to fetch |

Returns

[ProductResponse](#productresponse) object.

### Get all products by type

```http
  GET /api/v1/products
```

#### Query Parameters
| Parameter | Type     | Description                             |
| :-------- | :------- |:----------------------------------------|
| `productType`      | `string` | **Required**. Type of products to fetch |

Returns

[ProductResponse](#productresponse) object.