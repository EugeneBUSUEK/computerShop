# Computer shop

## Contents

* [Installation](#installation)
* [Docker](#docker)
* [API overview](#api_overview)

<a name="installation"><h2>Installation</h2></a> 

* Run terminal window
* Clone project using Git:

```text
git clone https://github.com/EugeneBUSUEK/computerShop.git
```
* move to `computerShop` directory

```text
cd computerShop
```

* To run the application, run the following command in a terminal window (in the computerShop) directory:
```text
./gradlew bootRun
```

<a name="docker"><h2>Docker</h2></a> 

<a name="api_overview"><h2>API overview</h2></a>

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

Returns

[ProductResponse](#productresponse) object.

### Update product

```http
  PUT /api/v1/products
```

Request body

[ProductRequest](#productrequest) object.

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